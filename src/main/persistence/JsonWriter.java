package persistence;

import model.ToMemorize;

import org.json.JSONObject;
import java.io.*;

// CITATION: all the methods in this class have been copied (and then modified) from JsonSerializationDemo
// Represents a writer that writes JSON representation of game state to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of game state (i.e. the list of chords to memorize) to file
    public void write(ToMemorize tm) {
        JSONObject json = tm.toJson();
        saveToFile(json.toString(TAB));
        //saveToFile(characterName);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
