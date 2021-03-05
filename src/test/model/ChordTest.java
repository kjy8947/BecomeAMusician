package model;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ChordTest {

    @Test
    public void testDummyChordConstructor() {
        Chord dummyChordForTest = new Chord();
    }

    @Test
    public void testChordConstructorMajorChord() {
        Note note = new Note();
        Chord chordForTest = new Chord("C");
        assertEquals("C", chordForTest.getRootNote());
        assertEquals("[C, E, G]", chordForTest.buildMajorTriadChord(note.getNoteForMajor("C")).toString());

        Chord chordForAnotherTest = new Chord("H");
        assertEquals(chordForAnotherTest.buildMajorTriadChord(note.getNoteForMajor("H")).toString(),
                "[Invalid input. Please enter a letter between 'A' and 'G#' "
                        + "(an uppercase letter for a major chord; a lowercase letter for a minor chord).]");
    }

    @Test
    public void testChordConstructorMinorChord() {
        Note note = new Note();
        Chord chordForTest = new Chord("c");
        assertEquals("c", chordForTest.getRootNote());
        assertEquals("[c, d#, g]",
                chordForTest.buildMinorTriadChord(note.getNoteForMinor("c")).toString());

        Chord chordForAnotherTest = new Chord("h");
        assertEquals(chordForAnotherTest.buildMinorTriadChord(note.getNoteForMinor("h")).toString(),
                "[Invalid input. Please enter a letter between 'a' and 'g#' "
                        + "(an uppercase letter for a major chord; a lowercase letter for a minor chord).]");
    }

    @Test
    public void testBuildMajorTriadChordValid() {
        Chord chordForTest = new Chord();

        assertEquals("C", chordForTest.buildMajorTriadChord(4).get(0));
        assertEquals("E", chordForTest.buildMajorTriadChord(4).get(1));
        assertEquals("G", chordForTest.buildMajorTriadChord(4).get(2));

        ArrayList cMajorChordNotes = chordForTest.buildMajorTriadChord(4);

        assertTrue(cMajorChordNotes.get(0).equals("C"));
        assertTrue(cMajorChordNotes.get(1).equals("E"));
        assertTrue(cMajorChordNotes.get(2).equals("G"));
        assertTrue(cMajorChordNotes.contains("C"));
        assertFalse(cMajorChordNotes.contains("c"));
        assertFalse(cMajorChordNotes.contains("F"));
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

        assertEquals("c", chordForTest.buildMinorTriadChord(4).get(0));
        assertEquals("d#", chordForTest.buildMinorTriadChord(4).get(1));
        assertEquals("g", chordForTest.buildMinorTriadChord(4).get(2));

        ArrayList cMinorChordNotes = chordForTest.buildMinorTriadChord(4);

        assertTrue(cMinorChordNotes.get(0).equals("c"));
        assertTrue(cMinorChordNotes.get(1).equals("d#"));
        assertTrue(cMinorChordNotes.get(2).equals("g"));
        assertTrue(cMinorChordNotes.contains("c"));
        assertFalse(cMinorChordNotes.contains("C"));
        assertFalse(cMinorChordNotes.contains("f"));
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
