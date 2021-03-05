package persistence;

import org.json.JSONObject;

// CITATION: this class has been copied from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
