package edu.lab04_ind;

import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * This class is responsible for serializing Meeting objects into JSON format.
 */
public class MeetingSerializer implements JsonSerializer<Meeting> {
    @Override
    public JsonElement serialize(Meeting meeting, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", meeting.getDate());
        jsonObject.addProperty("name", meeting.getName());
        jsonObject.addProperty("numberOfParticipants", meeting.getNumberOfParticipants());
        return jsonObject;
    }
}
