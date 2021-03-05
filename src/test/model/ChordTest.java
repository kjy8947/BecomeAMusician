package model;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChordTest {

    @Test
    public void testDummyChordConstructor() {
        Chord dummyChordForTest = new Chord();
    }

    @Test
    public void testChordConstructorMajorChord() {
        Chord chordForTest = new Chord("C");
        assertEquals("C", chordForTest.getRootNote());
    }

    @Test
    public void testChordConstructorMinorChord() {
        Chord chordForTest = new Chord("c");
        assertEquals("c", chordForTest.getRootNote());
    }

    @Test
    public void testBuildMajorTriadChordValid() {
        Chord chordForTest = new Chord();
        assertEquals("E", chordForTest.buildMajorTriadChord(4).get(1));
    }

    @Test
    public void testBuildMajorTriadChordNotValid() {
        Chord chordForTest = new Chord();
        assertEquals(chordForTest.buildMajorTriadChord(Scale.MAX_NOTE_NUMBERS).toString(),
                "[Invalid input. Please enter a letter between 'A' and 'G#' "
                        + "(an uppercase letter for a major chord; a lowercase letter for a minor chord).]");
    }

    @Test
    public void testBuildMinorTriadChordValid() {
        Chord chordForTest = new Chord();
        assertEquals("d#", chordForTest.buildMinorTriadChord(4).get(1));
    }

    @Test
    public void testBuildMinorTriadChordNotValid() {
        Chord chordForTest = new Chord();
        assertEquals(chordForTest.buildMinorTriadChord(Scale.MAX_NOTE_NUMBERS).toString(),
                "[Invalid input. Please enter a letter between 'a' and 'g#' "
                        + "(an uppercase letter for a major chord; a lowercase letter for a minor chord).]");
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
