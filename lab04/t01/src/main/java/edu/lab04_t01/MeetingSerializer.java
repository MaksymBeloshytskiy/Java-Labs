package edu.lab04_t01;

import com.google.gson.*;
import java.lang.reflect.Type;

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
