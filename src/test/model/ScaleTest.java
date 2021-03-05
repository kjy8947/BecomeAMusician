package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScaleTest {

    @Test
    public void testScaleConstructorMajorScale() {
        Note note = new Note();
        Scale scaleForTest = new Scale("C");
        assertEquals("C", scaleForTest.getRootNote());
        assertEquals("[C, D, E, F, G, A, B, C]",
                scaleForTest.buildMajorScale(note.getNoteForMajor("C")).toString());

        Scale scaleForAnotherTest = new Scale("H");
        assertEquals(scaleForAnotherTest.buildMajorScale(note.getNoteForMajor("H")).toString(),
                "[Invalid input. Please enter a letter between 'A' and 'G#' "
                + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
    }

    @Test
    public void testScaleConstructorMinorScale() {
        Note note = new Note();
        Scale scaleForTest = new Scale("c");
        assertEquals("c", scaleForTest.getRootNote());
        assertEquals("[c, d, d#, f, g, g#, b, c]",
                scaleForTest.buildMinorScale(note.getNoteForMinor("c")). toString());

        Scale scaleForAnotherTest = new Scale("h");
        assertEquals(scaleForAnotherTest.buildMinorScale(note.getNoteForMinor("h")).toString(),
                "[Invalid input. Please enter a letter between 'a' and 'g#' "
                        + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
    }

    @Test
    public void testBuildMajorScaleValid() {
        Scale scaleForTest = new Scale("random string");

        assertEquals("D", scaleForTest.buildMajorScale(4).get(1));

        ArrayList cMajorScaleNotes = scaleForTest.buildMajorScale(4);

        assertTrue(cMajorScaleNotes.get(0).equals("C"));
        assertTrue(cMajorScaleNotes.get(2).equals("E"));
        assertTrue(cMajorScaleNotes.get(4).equals("G"));
        assertTrue(cMajorScaleNotes.contains("C"));
        assertFalse(cMajorScaleNotes.contains("c"));
        assertFalse(cMajorScaleNotes.contains("D#"));
    }

    @Test
    public void testBuildMajorScaleNotValid() {
        Scale scaleForTest = new Scale("random string");
        assertEquals(scaleForTest.buildMajorScale(Scale.MAX_NOTE_NUMBERS).toString(),
                "[Invalid input. Please enter a letter between 'A' and 'G#' "
                + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
    }

    @Test
    public void testBuildMinorScaleValid() {
        Scale scaleForTest = new Scale("random string");

        assertEquals("c", scaleForTest.buildMinorScale(4).get(0));

        ArrayList cMinorScaleNotes = scaleForTest.buildMinorScale(4);

        assertTrue(cMinorScaleNotes.get(0).equals("c"));
        assertTrue(cMinorScaleNotes.get(2).equals("d#"));
        assertTrue(cMinorScaleNotes.get(4).equals("g"));
        assertTrue(cMinorScaleNotes.contains("c"));
        assertFalse(cMinorScaleNotes.contains("C"));
        assertFalse(cMinorScaleNotes.contains("e"));
    }

    @Test
    public void testBuildMinorScaleNotValid() {
        Scale scaleForTest = new Scale("random string");
        assertEquals(scaleForTest.buildMinorScale(Scale.MAX_NOTE_NUMBERS).toString(),
                "[Invalid input. Please enter a letter between 'a' and 'g#' "
                        + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
    }
}
