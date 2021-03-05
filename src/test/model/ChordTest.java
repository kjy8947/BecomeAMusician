package model;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChordTest {
    @Test
    public void testDummyChordConstructor() {
        Chord dummyChordForTest = new Chord();
    }

    @Test
    public void testChordConstructor() {
        Chord chordForTest = new Chord("C");
        assertEquals("C", chordForTest.getRootNote());
    }

    @Test
    public void testBuildMajorTriadChord() {
        Note note = new Note();
        Chord chordForTest = new Chord("random string");
        assertEquals("E", chordForTest.buildMajorTriadChord(4).get(1));
        assertFalse(chordForTest.buildMajorTriadChord(13).get(0).equals("H"));
    }

    @Test
    public void testBuildMinorTriadChord() {
        Note note = new Note();
        Chord chordForTest = new Chord("random string");
        assertEquals("d#", chordForTest.buildMinorTriadChord(4).get(1));
        assertFalse(chordForTest.buildMinorTriadChord(13).get(0).equals("h"));
    }

    @Test
    public void testToJson() {
        JSONArray jsonArray = new JSONArray();

        Chord chord1 = new Chord("C");
        Chord chord2 = new Chord("D");
        Chord chord3 = new Chord("E");

        jsonArray.put(chord1.toJson());
        jsonArray.put(chord2.toJson());
        jsonArray.put(chord3.toJson());

        assertEquals("{\"root note\":\"C\"}", jsonArray.get(0).toString());
        assertEquals("{\"root note\":\"D\"}", jsonArray.get(1).toString());
        assertEquals("{\"root note\":\"E\"}", jsonArray.get(2).toString());
    }
}
