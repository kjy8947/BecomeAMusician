package persistence;

import model.ToMemorize;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Citation: all the methods in this class have been copied (and then modified) from JsonSerializationDemo
// Represents a reader that reads a list of chords (to memorize) from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the list of chords to memorize from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToMemorize read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseState(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the previous game state (i.e. list of the chords to memorize) from JSON object and returns it
    private ToMemorize parseState(JSONObject jsonObject) {
        ToMemorize tm = new ToMemorize();

        JSONArray array = jsonObject.getJSONArray("chords to memorize");
        for (int i = 0; i < array.length(); i++) {
            JSONObject o = array.getJSONObject(i);
            tm.addChord((String) o.get("root note"));
        }
        return tm;
    }
}
