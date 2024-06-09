package lab01.ind.src.main;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.Duration;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class Event {
    protected String topic;
    protected int numberOfParticipants;
    protected ZonedDateTime dateTime;
    protected String comment;
    protected Locale locale;

    public Event(String topic, int numberOfParticipants, ZonedDateTime dateTime, String comment, Locale locale) {
        this.topic = topic;
        this.numberOfParticipants = numberOfParticipants;
        this.dateTime = dateTime;
        this.comment = comment;
        this.locale = locale;
    }

    public String getTopic() {
        return topic;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }  

    public String getLocalizedParticipants(Locale locale) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        return numberFormat.format(numberOfParticipants);
    }
    
    // Format date and time with GMT offset
    public String getFormattedDateTime(ZonedDateTime eventDateTime, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("dict", locale);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(bundle.getString("date_time_format"));
        String formattedDateTime = eventDateTime.format(formatter);
        ZoneOffset zoneOffset = eventDateTime.getOffset();
        String gmtOffset = getGMTOffset(zoneOffset);
        return formattedDateTime + " GMT " + gmtOffset;
    }

    // Helper method to get GMT offset
    public String getGMTOffset(ZoneOffset zoneOffset) {
        int totalSeconds = zoneOffset.getTotalSeconds();
        int hours = totalSeconds / 3600;
        int minutes = Math.abs((totalSeconds % 3600) / 60);
        String sign = (totalSeconds >= 0) ? "+" : "-";
        return String.format("%s%02d:%02d", sign, Math.abs(hours), minutes);
    }
    
    // Format number of participants
    public String getFormattedNumberOfParticipants(Locale locale) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        return numberFormat.format(numberOfParticipants);
    }

    // Calculate time difference between events
    public long calculateTimeDifference(Event otherEvent) {
        Duration duration = Duration.between(this.dateTime, otherEvent.dateTime);
        return Math.abs(duration.toMinutes());
    }

    // Localization Support for Comment
    public String getLocalizedComment(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("dict", locale);
        String comment = bundle.getString("comment");

        // Rest of your code
        if (comment == null || comment.isEmpty()) {
            return comment;
        } else {
            return comment;
        }
    }    
}
