package lab01.ind.src.main;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ConferenceWithLocalization class represents a conference with localization support.
 * It extends the ConferenceWithStreams class and adds additional functionality for localization.
 */
public class ConferenceWithLocalization extends ConferenceWithStreams {
    protected List<Event> eventsList;
    protected Locale locale;

    /**
     * Constructs a ConferenceWithLocalization object with the specified name, place, events list, and locale.
     *
     * @param name       the name of the conference
     * @param place      the place of the conference
     * @param eventsList the list of events in the conference
     * @param locale     the locale for localization
     */
    public ConferenceWithLocalization(String name, String place, List<Event> eventsList, Locale locale) {
        super(name, place, new ArrayList<>());
        this.eventsList = eventsList;
        this.locale = locale;
    }

    /**
     * Gets the list of events in the conference.
     *
     * @return the list of events
     */
    public List<Event> getEventsList() {
        return eventsList;
    }

    /**
     * Sets the list of events in the conference.
     *
     * @param eventsList the list of events
     */
    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

    /**
     * Gets the localized text for the given key and locale.
     *
     * @param key    the key for the localized text
     * @param locale the locale for localization
     * @return the localized text
     */
    public String getLocalizedText(String key, Locale locale) {
        ResourceBundle messages = ResourceBundle.getBundle("dict", locale);
        return messages.getString(key);
    }

    /**
     * Searches for words in the given text that match the specified regular expression.
     *
     * @param text  the text to search in
     * @param regex the regular expression to match
     * @return a list of indices where the words are found
     */
    public List<Integer> searchWords(String text, String regex) {
        List<Integer> wordIndices = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE); // Compile pattern with CASE_INSENSITIVE flag
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int startIndex = matcher.start();
            wordIndices.add(startIndex);
        }
        return wordIndices;
    }

    /**
     * Sorts the meetings in the events list by topic using the specified locale.
     *
     * @param locale the locale for sorting
     */
    public void sortMeetingsByTopic(Locale locale) {
        Collator collator = Collator.getInstance(locale);
        eventsList.sort((m1, m2) -> collator.compare(m1.getTopic(), m2.getTopic()));
    }

    /**
     * Finds and prints the smallest time difference between events in the events list.
     * Prints the topics of the events and the time difference in minutes.
     */
    public void findAndPrintSmallestTimeDifference() {
        long smallestDifference = Long.MAX_VALUE;
        Event event1 = null, event2 = null;

        for (int i = 0; i < eventsList.size(); i++) {
            for (int j = i + 1; j < eventsList.size(); j++) {
                long difference = eventsList.get(i).calculateTimeDifference(eventsList.get(j));
                if (difference < smallestDifference) {
                    smallestDifference = difference;
                    event1 = eventsList.get(i);
                    event2 = eventsList.get(j);
                }
            }
        }

        if (event1 != null && event2 != null) {
            System.out.println(getLocalizedText("smallest_time_difference_header", locale) + ":");
            System.out.println(event1.getTopic());
            System.out.println(event2.getTopic());
            System.out.println(getLocalizedText("time_difference", locale) + smallestDifference + " " + getLocalizedText("minutes", locale));
        } else {
            System.out.println(getLocalizedText("no_events_found", locale));
        }
    }
}
