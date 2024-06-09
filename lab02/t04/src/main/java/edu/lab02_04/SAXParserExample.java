package edu.lab02_04;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXParserExample {
    public static void main(String[] args) {
        try {
            File inputFile = new File("students.xml");
            if (!inputFile.exists()) {
                System.out.println("XML-файл не існує.");
                return;
            }

            // Створення фабрики парсера та парсера
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Парсинг XML-документа та виведення даних за допомогою SAX
            DefaultHandler handler = new DefaultHandler() {
                boolean bId = false;
                boolean bFirstName = false;
                boolean bLastName = false;
                boolean bGroup = false;

                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("id")) {
                        bId = true;
                    }
                    if (qName.equalsIgnoreCase("firstName")) {
                        bFirstName = true;
                    }
                    if (qName.equalsIgnoreCase("lastName")) {
                        bLastName = true;
                    }
                    if (qName.equalsIgnoreCase("group")) {
                        bGroup = true;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (bId) {
                        System.out.println("ID: " + new String(ch, start, length));
                        bId = false;
                    }
                    if (bFirstName) {
                        System.out.println("Ім'я: " + new String(ch, start, length));
                        bFirstName = false;
                    }
                    if (bLastName) {
                        System.out.println("Прізвище: " + new String(ch, start, length));
                        bLastName = false;
                    }
                    if (bGroup) {
                        System.out.println("Група: " + new String(ch, start, length));
                        bGroup = false;
                    }
                }
            };

            saxParser.parse(inputFile, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
