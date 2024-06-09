package edu.lab04_t02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class AnnotationInfoTest {

    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    public void testAnnotationInfoWithValidInput() {
        String input = "edu.lab04_t02.SampleClass\nsampleMethod\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        AnnotationInfo.main(new String[]{});

        String expectedOutput = "Annotations of the method sampleMethod:\n@java.lang.Deprecated()\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testAnnotationInfoWithInvalidClass() {
        String input = "InvalidClass\nsampleMethod\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        AnnotationInfo.main(new String[]{});

        String expectedOutput = "Class not found: java.lang.ClassNotFoundException: InvalidClass\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testAnnotationInfoWithInvalidMethod() {
        String input = "edu.lab04_t02.SampleClass\ninvalidMethod\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        AnnotationInfo.main(new String[]{});

        String expectedOutput = "Method not found: java.lang.NoSuchMethodException: edu.lab04_t02.SampleClass.invalidMethod()\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}

