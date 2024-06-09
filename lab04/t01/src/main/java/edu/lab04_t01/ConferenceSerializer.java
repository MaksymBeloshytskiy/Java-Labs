package edu.lab04_t01;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.List;

public class ConferenceSerializer implements JsonSerializer<Conference> {
    @Override
    public JsonElement serialize(Conference conference, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", conference.getName());
        jsonObject.addProperty("place", conference.getPlace());
        JsonArray meetingsArray = new JsonArray();
        List<Meeting> meetings = conference.getMeetings();
        for (Meeting meeting : meetings) {
            JsonObject meetingObject = new JsonObject();
            meetingObject.addProperty("date", meeting.getDate());
            meetingObject.addProperty("name", meeting.getName());
            meetingObject.addProperty("numberOfParticipants", meeting.getNumberOfParticipants());
            meetingsArray.add(meetingObject);
        }
        jsonObject.add("meetings", meetingsArray);
        return jsonObject;
    }
}
