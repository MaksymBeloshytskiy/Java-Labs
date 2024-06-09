package edu.lab04_ind;

import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * The ConferenceManager class is responsible for managing conferences and meetings.
 * It provides functionality to add, delete, display, search, and sort conferences and meetings.
 */
public class ConferenceManager {
    private static final String URL = "jdbc:mysql://localhost:3306/ConferenceDB";
    private static final String USER = "root";
    private static final String PASSWORD = "228228R@x";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            createTables(conn);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nPlease choose an action:");
                System.out.println("1. Add a new conference");
                System.out.println("2. Add a meeting to a conference");
                System.out.println("3. Delete a conference");
                System.out.println("4. Delete a meeting");
                System.out.println("5. Display all conferences and meetings");
                System.out.println("6. Search for meetings");
                System.out.println("7. Sort meetings");
                System.out.println("8. Import conferences and meetings from JSON");
                System.out.println("9. Export conferences and meetings to JSON");
                System.out.println("10. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addConference(conn, scanner);
                        break;
                    case 2:
                        addMeetingToConference(conn, scanner);
                        break;
                    case 3:
                        deleteConference(conn, scanner);
                        break;
                    case 4:
                        deleteMeeting(conn, scanner);
                        break;
                    case 5:
                        displayConferencesAndMeetings(conn);
                        break;
                    case 6:
                        searchMeetings(conn, scanner);
                        break;
                    case 7:
                        sortMeetings(conn, scanner);
                        break;
                    case 8:
                        importFromJSON(conn, scanner);
                        break;
                    case 9:
                        exportToJSON(conn, scanner);
                        break;
                    case 10:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        String conferencesTableSQL = "CREATE TABLE IF NOT EXISTS Conferences (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "place VARCHAR(255) NOT NULL)";
        String meetingsTableSQL = "CREATE TABLE IF NOT EXISTS Meetings (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "conference_id INT," +
                "date DATE NOT NULL," +
                "name VARCHAR(255) NOT NULL," +
                "numberOfParticipants INT NOT NULL," +
                "FOREIGN KEY (conference_id) REFERENCES Conferences(id) ON DELETE CASCADE)";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(conferencesTableSQL);
            stmt.executeUpdate(meetingsTableSQL);
        }
    }

    private static void addConference(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter conference name:");
        String conferenceName = scanner.nextLine();
        System.out.println("Enter conference place:");
        String conferencePlace = scanner.nextLine();

        String insertConferenceSQL = "INSERT INTO Conferences (name, place) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertConferenceSQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, conferenceName);
            stmt.setString(2, conferencePlace);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int conferenceId = 0;
            if (rs.next()) {
                conferenceId = rs.getInt(1);
            }

            System.out.println("Conference added successfully with ID: " + conferenceId);
        }
    }

    private static void addMeetingToConference(Connection conn, Scanner scanner) throws SQLException {
        displayConferences(conn);

        System.out.println("Enter the ID of the conference to which you want to add a meeting:");
        int conferenceId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter meeting date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        System.out.println("Enter meeting name:");
        String meetingName = scanner.nextLine();
        System.out.println("Enter number of participants:");
        int numberOfParticipants = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String insertMeetingSQL = "INSERT INTO Meetings (conference_id, date, name, numberOfParticipants) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertMeetingSQL)) {
            stmt.setInt(1, conferenceId);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setString(3, meetingName);
            stmt.setInt(4, numberOfParticipants);
            stmt.executeUpdate();

            System.out.println("Meeting added successfully to conference with ID: " + conferenceId);
        }
    }

    private static void deleteConference(Connection conn, Scanner scanner) throws SQLException {
        displayConferences(conn);
        System.out.println("Enter the ID of the conference to delete:");
        int conferenceId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        String deleteMeetingsSQL = "DELETE FROM Meetings WHERE conference_id = ?";
        String deleteConferenceSQL = "DELETE FROM Conferences WHERE id = ?";
        
        try (PreparedStatement deleteMeetingsStmt = conn.prepareStatement(deleteMeetingsSQL);
             PreparedStatement deleteConferenceStmt = conn.prepareStatement(deleteConferenceSQL)) {
            deleteMeetingsStmt.setInt(1, conferenceId);
            deleteMeetingsStmt.executeUpdate();
            
            deleteConferenceStmt.setInt(1, conferenceId);
            int deletedRows = deleteConferenceStmt.executeUpdate();
    
            if (deletedRows > 0) {
                System.out.println("Conference with ID " + conferenceId + " deleted successfully.");
            } else {
                System.out.println("No conference found with ID " + conferenceId);
            }
        }
    }
    
    private static void deleteMeeting(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter the ID of the meeting to delete:");
        int meetingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        String deleteMeetingSQL = "DELETE FROM Meetings WHERE id = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(deleteMeetingSQL)) {
            stmt.setInt(1, meetingId);
            int deletedRows = stmt.executeUpdate();
    
            if (deletedRows > 0) {
                System.out.println("Meeting with ID " + meetingId + " deleted successfully.");
            } else {
                System.out.println("No meeting found with ID " + meetingId);
            }
        }
    }
    

    private static void displayConferences(Connection conn) throws SQLException {
        String query = "SELECT * FROM Conferences";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nConferences:");
            while (rs.next()) {
                int conferenceId = rs.getInt("id");
                String conferenceName = rs.getString("name");
                String conferencePlace = rs.getString("place");
                System.out.println("ID: " + conferenceId + ", Name: " + conferenceName + ", Place: " + conferencePlace);
            }
        }
    }

    private static void displayConferencesAndMeetings(Connection conn) throws SQLException {
        String query = "SELECT * FROM Conferences";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nConferences and Meetings:");
            while (rs.next()) {
                int conferenceId = rs.getInt("id");
                String conferenceName = rs.getString("name");
                String conferencePlace = rs.getString("place");
                System.out.println("Conference ID: " + conferenceId + ", Name: " + conferenceName + ", Place: " + conferencePlace);

                String meetingQuery = "SELECT * FROM Meetings WHERE conference_id = ?";
                try (PreparedStatement meetingStmt = conn.prepareStatement(meetingQuery)) {
                    meetingStmt.setInt(1, conferenceId);
                    ResultSet meetingRs = meetingStmt.executeQuery();
                    while (meetingRs.next()) {
                        int meetingId = meetingRs.getInt("id");
                        String date = meetingRs.getDate("date").toString();
                        String meetingName = meetingRs.getString("name");
                        int numberOfParticipants = meetingRs.getInt("numberOfParticipants");
                        System.out.println("\tMeeting ID: " + meetingId + ", Date: " + date + ", Name: " + meetingName + ", Participants: " + numberOfParticipants);
                    }
                }
            }
        }
    }

    private static void searchMeetings(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter search criteria:");
        String criteria = scanner.nextLine();
        String query = "SELECT * FROM Meetings WHERE name LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + criteria + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nSearch Results:");
            while (rs.next()) {
                int meetingId = rs.getInt("id");
                int conferenceId = rs.getInt("conference_id");
                String date = rs.getDate("date").toString();
                String meetingName = rs.getString("name");
                int numberOfParticipants = rs.getInt("numberOfParticipants");
                System.out.println("Meeting ID: " + meetingId + ", Conference ID: " + conferenceId + ", Date: " + date + ", Name: " + meetingName + ", Participants: " + numberOfParticipants);
            }
        }
    }

    private static void sortMeetings(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter sort criteria (name, date, numberOfParticipants):");
        String criteria = scanner.nextLine();
        String query = "SELECT * FROM Meetings ORDER BY " + criteria;
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nSorted Meetings:");
            while (rs.next()) {
                int meetingId = rs.getInt("id");
                int conferenceId = rs.getInt("conference_id");
                String date = rs.getDate("date").toString();
                String meetingName = rs.getString("name");
                int numberOfParticipants = rs.getInt("numberOfParticipants");
                System.out.println("Meeting ID: " + meetingId + ", Conference ID: " + conferenceId + ", Date: " + date + ", Name: " + meetingName + ", Participants: " + numberOfParticipants);
            }
        }
    }
    
    private static void importFromJSON(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter the file path to import:");
        String filePath = scanner.nextLine();
    
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Conference[] conferences = gson.fromJson(reader, Conference[].class);
    
            String insertConferenceSQL = "INSERT INTO Conferences (name, place) VALUES (?, ?)";
            String insertMeetingSQL = "INSERT INTO Meetings (conference_id, date, name, numberOfParticipants) VALUES (?, ?, ?, ?)";
    
            for (Conference conference : conferences) {
                try (PreparedStatement stmt = conn.prepareStatement(insertConferenceSQL, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, conference.getName());
                    stmt.setString(2, conference.getPlace());
                    stmt.executeUpdate();
    
                    ResultSet rs = stmt.getGeneratedKeys();
                    int conferenceId = 0;
                    if (rs.next()) {
                        conferenceId = rs.getInt(1);
                    }
    
                    for (Meeting meeting : conference.getMeetings()) {
                        try (PreparedStatement meetingStmt = conn.prepareStatement(insertMeetingSQL)) {
                            meetingStmt.setInt(1, conferenceId);
                            meetingStmt.setDate(2, Date.valueOf(meeting.getDate()));
                            meetingStmt.setString(3, meeting.getName());
                            meetingStmt.setInt(4, meeting.getNumberOfParticipants());
                            meetingStmt.executeUpdate();
                        }
                    }
                }
            }
            System.out.println("Import successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void exportToJSON(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("Enter the file path to export:");
        String filePath = scanner.nextLine();
    
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(Conference.class, new ConferenceSerializer())
                    .registerTypeAdapter(Meeting.class, new MeetingSerializer())
                    .create();
            gson.toJson(getConferences(conn), writer);
            System.out.println("Export successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }        
    
    private static Conference[] getConferences(Connection conn) throws SQLException {
        List<Conference> conferences = new ArrayList<>();
    
        String conferenceQuery = "SELECT * FROM Conferences";
        try (PreparedStatement conferenceStmt = conn.prepareStatement(conferenceQuery);
             ResultSet conferenceRs = conferenceStmt.executeQuery()) {
    
            while (conferenceRs.next()) {
                int conferenceId = conferenceRs.getInt("id");
                String conferenceName = conferenceRs.getString("name");
                String conferencePlace = conferenceRs.getString("place");
    
                // Fetch meetings for this conference
                List<Meeting> meetings = getMeetings(conn, conferenceId);
    
                // Create Conference object and add meetings
                Conference conference = new Conference(conferenceName, conferencePlace, meetings);
                conferences.add(conference);
            }
        }
    
        return conferences.toArray(new Conference[0]);
    }
    
    private static List<Meeting> getMeetings(Connection conn, int conferenceId) throws SQLException {
        String query = "SELECT * FROM Meetings WHERE conference_id = ?";
        List<Meeting> meetings = new ArrayList<>();
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, conferenceId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String date = rs.getDate("date").toString();
                    String meetingName = rs.getString("name");
                    int numberOfParticipants = rs.getInt("numberOfParticipants");
                    Meeting meeting = new Meeting(date, meetingName, numberOfParticipants);
                    meetings.add(meeting);
                }
            }
        }
        return meetings;
    }
}    
