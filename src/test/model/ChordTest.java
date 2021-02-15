package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// I don't know how to make these work...
public class ChordTest {
    private Chord chord;

    @Test
    public void testBuildMajorTriadChord() {
        String rootNote;
        chord.buildMajorTriadChord(0);
        rootNote = chord.getRootNote();
        assertTrue(rootNote.equals("G#"));
    }

    @Test
    public void testBuildMinorTriadChord() {
        String rootNote;
        chord.buildMinorTriadChord(0);
        rootNote = chord.getRootNote();
        assertTrue(rootNote.equals("g#"));
    }
}
