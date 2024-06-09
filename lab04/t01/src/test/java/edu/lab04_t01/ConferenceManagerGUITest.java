package edu.lab04_t01;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class ConferenceManagerGUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new ConferenceManagerGUI().start(stage);
    }

    @Test
    public void testAddConference() {
        // Click on the "Add Conference" button
        clickOn("Add Conference");

        // Input conference details in the dialog
        clickOn("Name:").write("Test Conference");
        clickOn("Place:").write("Test Place");

        // Press Enter to add the conference
        press(KeyCode.ENTER);

        // Verify that the conference is added to the table
        verifyThat(".table-view .table-row-cell", hasText("Test Conference"));
        verifyThat(".table-view .table-row-cell", hasText("Test Place"));
    }

    @Test
    public void testAddMeeting() {
        // Click on the "Add Meeting" button
        clickOn("Add Meeting");

        // Input meeting details in the dialog
        clickOn("Conference ID:").write("1");
        clickOn("Topic:").write("Test Topic");
        clickOn("Date:").write("2022-05-25");

        // Press Enter to add the meeting
        press(KeyCode.ENTER);

        // Verify that the meeting is added to the table
        verifyThat(".table-view .table-row-cell", hasText("Test Topic"));
        verifyThat(".table-view .table-row-cell", hasText("2022-05-25"));
    }

    // Add more tests for other functionalities as needed
}
