package lab01.ind.src.main;

import java.util.ArrayList;
import java.util.List;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Prompt the user to choose the localization
        Locale locale = chooseLocalization();

        // Create a list of events
        List<Event> eventsList = createEventsList(locale);

        // Translate comments
        translateComments(eventsList, locale);

        // Create ConferenceWithLocalization instance with the chosen localization
        ConferenceWithLocalization conference = new ConferenceWithLocalization("IT International Pole", "France, Montreal", eventsList, locale);

        // Test the conference functionality
        testConference(conference, locale);
    }

    // Prompt the user to choose the localization
    private static Locale chooseLocalization() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose localization:");
        System.out.println("1. English");
        System.out.println("2. Українська\n");
        System.out.print("Enter the number of the chosen localization: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println();

        return (choice == 2) ? Locale.forLanguageTag("uk") : Locale.forLanguageTag("en");
    }

    private static List<Event> createEventsList(Locale locale) {
        List<Event> eventsList = new ArrayList<>();
        eventsList.add(new Event("Artificial Intelligence Workshop", 80, ZonedDateTime.of(2024, 3, 20, 10, 0, 0, 0, ZoneOffset.UTC), "Interesting topic", locale));
        eventsList.add(new Event("Data Science Symposium", 100, ZonedDateTime.of(2024, 3, 21, 13, 30, 0, 0, ZoneOffset.ofHours(1)), "Engaging discussion", locale));
        eventsList.add(new Event("Machine Learning Conference", 120, ZonedDateTime.of(2024, 3, 22, 9, 0, 0, 0, ZoneOffset.ofHours(-5)), "Informative session", locale));
        eventsList.add(new Event("Blockchain Summit", 90, ZonedDateTime.of(2024, 3, 23, 11, 0, 0, 0, ZoneOffset.ofHours(8)), "Finance upport", locale));
        return eventsList;
    }
    
    // Give evet list translated comments
    private static void translateComments(List<Event> eventsList, Locale locale) {
        for (Event event : eventsList) {
            event.setComment(event.getLocalizedComment(locale));
        }
    }

    // Test the conference functionality
    private static void testConference(ConferenceWithLocalization conference, Locale locale) {
        // Print the conference details
        System.out.println(conference.getLocalizedText("conference_details", locale));
        System.out.println(conference.getLocalizedText("conference_name", locale) + conference.getName());
        System.out.println(conference.getLocalizedText("conference_place", locale) + conference.getPlace() + "\n");

        // Sort events by topic and print
        int counter = 1;
        System.out.println("\n" + conference.getLocalizedText("events_sorted_by_topic", locale) + ":");
        conference.sortMeetingsByTopic(locale);
        for (Event event : conference.getEventsList()) {
            System.out.println(counter + ". " + event.getTopic());
            counter++;
        }

        // Find and print the smallest time difference between events
        System.out.println();
        conference.findAndPrintSmallestTimeDifference();

        // Let the user search for words in comments
        System.out.println("\n" + conference.getLocalizedText("search_words_in_comments", locale));
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(conference.getLocalizedText("enter_search_word", locale));
            String searchWord = scanner.nextLine();

            // Flag to track if any match is found
            boolean matchFound = false;

            System.out.print("\n" + conference.getLocalizedText("search_results", locale) + ": ");
            // Iterate over the events
            for (Event event : conference.getEventsList()) {
                // Search for the word in the event's comment
                List<Integer> wordIndices = conference.searchWords(event.getComment(), searchWord);

                // If the word is found, print the event information
                if (!wordIndices.isEmpty()) {
                    matchFound = true;
                    System.out.println("\n" + conference.getLocalizedText("event_details", locale));
                    System.out.println(conference.getLocalizedText("event_name", locale) + event.getTopic());
                    System.out.println(conference.getLocalizedText("event_date", locale) + event.getFormattedDateTime( event.getDateTime(), locale));
                    System.out.println(conference.getLocalizedText("event_comment", locale) + event.getComment());
                }
            }

            // If no match is found, print an error message
            if (!matchFound) {
                System.out.println(conference.getLocalizedText("no_matching_events", locale));
            }
        } finally {
            scanner.close();
        }
    }
}
