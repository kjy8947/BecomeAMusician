package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {
    private Note note;

    @BeforeEach
    public void runBefore() {
        note = new Note();
    }

    @Test
    public void testGetNoteForMajorIntToStr() {
        assertEquals(note.getNoteForMajor(0), "G#");
        assertNotEquals(note.getNoteForMajor(0), "Aflat");
        assertEquals(note.getNoteForMajor(4), "C");
        assertNotEquals(note.getNoteForMajor(11), "g");
        assertEquals(note.getNoteForMajor(11), "G");
    }

    @Test
    public void testGetNoteForMajorStrToInt() {
        assertEquals(note.getNoteForMajor("G#"), 0);
        assertEquals(note.getNoteForMajor("A"), 1);
        assertNotEquals(note.getNoteForMajor("A"), 0);
        assertEquals(note.getNoteForMajor("G"), 11);
        assertEquals(note.getNoteForMajor("H"), 999);
    }

    @Test
    public void testGetNoteForMinorIntToStr() {
        assertEquals(note.getNoteForMinor(0), "g#");
        assertNotEquals(note.getNoteForMinor(0), "aflat");
        assertEquals(note.getNoteForMinor(4), "c");
        assertNotEquals(note.getNoteForMinor(11), "G");
        assertEquals(note.getNoteForMinor(11), "g");
    }

    @Test
    public void testGetNoteForMinorStrToInt() {
        assertEquals(note.getNoteForMinor("g#"), 0);
        assertEquals(note.getNoteForMinor("a"), 1);
        assertNotEquals(note.getNoteForMinor("a"), 0);
        assertEquals(note.getNoteForMinor("g"), 11);
    }
}
