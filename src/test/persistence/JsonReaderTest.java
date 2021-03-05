package persistence;

import model.Chord;
import model.ToMemorize;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: all the methods in this test class have been copied (and then modified) from JsonSerializationDemo
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
            assertEquals(2, tm.getMaterialsToMemorize().size());
            List<Chord> chords = tm.getMaterialsToMemorize();
            assertEquals("C", chords.get(0));
            assertEquals("c", chords.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}