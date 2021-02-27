package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
