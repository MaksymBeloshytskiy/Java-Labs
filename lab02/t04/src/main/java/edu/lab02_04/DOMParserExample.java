package edu.lab02_04;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParserExample {
    public static void main(String[] args) {
        try {
            File inputFile = new File("students.xml");
            if (!inputFile.exists()) {
                System.out.println("XML-файл не існує.");
                return;
            }

            // Створення фабрики документів та документа
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            // Отримання кореневого елемента
            Element root = document.getDocumentElement();

            // Отримання списку елементів <student>
            NodeList studentList = root.getElementsByTagName("student");

            // Читання та модифікація даних
            for (int i = 0; i < studentList.getLength(); i++) {
                Node studentNode = studentList.item(i);
                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) studentNode;

                    // Зміна даних
                    Element groupElement = (Element) studentElement.getElementsByTagName("group").item(0);
                    groupElement.setTextContent("CS103");
                }
            }

            // Запис модифікованого документа в новий файл
            File outputFile = new File("modified_students.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);

            System.out.println("Дані були модифіковані та записані в новий XML-файл.");
        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
