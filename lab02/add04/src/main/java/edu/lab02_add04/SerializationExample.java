package edu.lab02_add04;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import com.thoughtworks.xstream.XStream;

class Faculty implements Serializable {
    private String name;

    public Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Institute implements Serializable {
    private Faculty[] faculties;

    public Institute(Faculty[] faculties) {
        this.faculties = faculties;
    }

    public Faculty[] getFaculties() {
        return faculties;
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        // Створення об'єктів
        Faculty faculty1 = new Faculty("Faculty of Science");
        Faculty faculty2 = new Faculty("Faculty of Arts");
        Faculty[] faculties = {faculty1, faculty2};
        Institute institute = new Institute(faculties);

        // Серіалізація в XML засобами java.beans
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("institute.xml")))) {
            encoder.writeObject(institute);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Десеріалізація з XML засобами java.beans
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("institute.xml")))) {
            Institute deserializedInstitute = (Institute) decoder.readObject();
            System.out.println("Deserialized Institute: ");
            for (Faculty faculty : deserializedInstitute.getFaculties()) {
                System.out.println(faculty.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Серіалізація в XML і JSON засобами XStream
        XStream xstream = new XStream();
        xstream.alias("faculty", Faculty.class);
        xstream.alias("institute", Institute.class);
        String xml = xstream.toXML(institute);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("institute_xstream.xml"))) {
            writer.write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = xstream.toXML(institute);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("institute_xstream.json"))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
