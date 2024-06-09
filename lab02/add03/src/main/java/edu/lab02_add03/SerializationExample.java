package edu.lab02_add03;

import com.thoughtworks.xstream.XStream;
import java.io.*;

class Book {
    String title;

    public Book(String title) {
        this.title = title;
    }
}

class Library {
    Book[] books;

    public Library(Book[] books) {
        this.books = books;
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        Book[] books = {new Book("Book 1"), new Book("Book 2"), new Book("Book 3")};
        Library library = new Library(books);

        // Серіалізація в XML
        XStream xstream = new XStream();
        String xml = xstream.toXML(library);

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("library.xml"), "utf-8"))) {
            writer.write(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
