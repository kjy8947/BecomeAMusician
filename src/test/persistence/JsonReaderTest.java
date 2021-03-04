package persistence;

import model.Chord;
import model.Note;
import model.ToMemorize;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToMemorize tm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyWorkRoom.json");
        try {
            ToMemorize tm = reader.read();
            assertEquals(0, tm.getMaterialsToMemorize().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralChordList() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralChordList.json");
        try {
            ToMemorize tm = reader.read();
            //List<Chord> chords = tm.getMaterialsToMemorize();
            assertEquals(2, tm.getMaterialsToMemorize().size());
            List<Chord> chords = tm.getMaterialsToMemorize();
//            checkChord("C", chords.get(0));
//            checkChord("c", chords.get(1));
            assertEquals("C", chords.get(0));
            assertEquals("c", chords.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

//    // MODIFIES: tm
//    // EFFECTS: parses chords from JSON object and adds them to workroom
//    private void addChords(ToMemorize tm, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("chords to memorize");
//        for (Object json : jsonArray) {
//            JSONObject nextChord = (JSONObject) json;
//            addChord(tm, nextChord);
//        }
//    }

//    public void testAddChords() {
//        JSONObject jsonObject;
//        JSONArray jsonArray = jsonObject.getJSONArray("chords to memorize");
//    }
//
//    // MODIFIES: tm
//    // EFFECTS: parses chord from JSON object and adds it to workroom
//    private void addChord(ToMemorize tm, JSONObject jsonObject) {
//        String rootNote = jsonObject.getString("root note");
//        Chord chord = new Chord();
//        Note note = new Note();
//
//        tm.addChord(rootNote);
//    }
}