package edu.lab02_ind;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * The XmlFileWriter class provides a utility for writing data to an XML file.
 */
public class XmlFileWriter {
    
    /**
     * Writes the given list of conferences to an XML file.
     *
     * @param conferences the list of conferences to write
     * @param fileName the name of the XML file to write to
     */
    public static void writeData(List<ConferenceWithStreams> conferences, String fileName) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("conference", Conference.class);
        xStream.alias("meeting", Meeting.class);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(xStream.toXML(conferences));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
