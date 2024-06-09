package edu.lab03_t02;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DictionaryAppTest {

    private DictionaryApp dictionaryApp;

    @Before
    public void setUp() {
        dictionaryApp = new DictionaryApp();
        dictionaryApp.populateDictionary(); // Populate dictionary with initial values
    }

    @Test
    public void testSearchExistingWord() {
        dictionaryApp.searchField.setText("hello");
        dictionaryApp.searchWord();
        assertEquals("Search result:\nhello - привіт", dictionaryApp.resultArea.getText());
    }

    @Test
    public void testSearchNonExistingWord() {
        dictionaryApp.searchField.setText("xyz");
        dictionaryApp.searchWord();
        assertEquals("Search result:\nxyz - Word not found.", dictionaryApp.resultArea.getText());
    }

    @Test
    public void testAddWord() {
        dictionaryApp.englishField.setText("test");
        dictionaryApp.ukrainianField.setText("тест");
        dictionaryApp.addWord();
        assertEquals("Word added:\ntest - тест", dictionaryApp.resultArea.getText());
        // Check if the word was added to the dictionary
        assertEquals("тест", dictionaryApp.dictionary.get("test"));
    }

    @Test
    public void testAddEmptyWord() {
        dictionaryApp.englishField.setText("");
        dictionaryApp.ukrainianField.setText("тест");
        dictionaryApp.addWord();
        assertEquals("Error: Both fields must be filled.", dictionaryApp.resultArea.getText());
        // Check if the dictionary remains unchanged
        assertEquals(4, dictionaryApp.dictionary.size());
    }

    @Test
    public void testAddEmptyTranslation() {
        dictionaryApp.englishField.setText("test");
        dictionaryApp.ukrainianField.setText("");
        dictionaryApp.addWord();
        assertEquals("Error: Both fields must be filled.", dictionaryApp.resultArea.getText());
        // Check if the dictionary remains unchanged
        assertEquals(4, dictionaryApp.dictionary.size());
    }
}
