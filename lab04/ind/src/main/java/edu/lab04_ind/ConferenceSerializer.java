package edu.lab04_ind;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This class is responsible for serializing a Conference object into JSON format.
 */
public class ConferenceSerializer implements JsonSerializer<Conference> {

    /**
     * Serializes a Conference object into JSON format.
     *
     * @param conference The Conference object to be serialized.
     * @param type The type of the object to be serialized.
     * @param context The serialization context.
     * @return The serialized JSON element.
     */
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
