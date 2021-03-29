package persistence;

import model.Chord;
import model.ToMemorize;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: all the methods in this test class have been copied (and then modified) from JsonSerializationDemo
class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ToMemorize wr = new ToMemorize();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ToMemorize tm = new ToMemorize();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(tm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            tm = reader.read();
            assertEquals(0, tm.getMaterialsToMemorize().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralChordList() {
        try {
            ToMemorize tm = new ToMemorize();
            tm.addChord("C");
            tm.addChord("c");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralChordList.json");
            writer.open();
            writer.write(tm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralChordList.json");
            tm = reader.read();
            List<Chord> chords = tm.getMaterialsToMemorize();
            assertEquals(2, chords.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCharacterPoints() {
        try {
            model.Character character = new model.Character();
            character.setPoints(15);
            JsonWriter writer = new JsonWriter("./data/testWriterCharacterPoints.json");
            writer.open();
            writer.write(character);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCharacterPoints.json");
            character = reader.readPoints();
            int points = character.getPoints();
            assertEquals(15, points);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
