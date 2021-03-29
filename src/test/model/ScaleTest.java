package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScaleTest {

    @Test
    public void testScaleConstructorNoArgument() {
        Scale dummyScale = new Scale();
    }

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
    public void testScaleConstructorNotMakingSense() {
        Note note = new Note();
        Scale stupidScale = new Scale("cC");
        assertEquals(stupidScale.buildMajorScale(note.getNoteForMajor("cC")).toString(),
                "[Invalid input. Please enter a letter between 'A' and 'G#' "
                        + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
        assertEquals(stupidScale.buildMinorScale(note.getNoteForMinor("cC")).toString(),
                "[Invalid input. Please enter a letter between 'a' and 'g#' "
                        + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
    }

    @Test
    public void testBuildMajorScaleValid() {
        Scale scaleForTest = new Scale("random string");

        assertEquals("D", scaleForTest.buildMajorScale(4).get(1));
    }

    @Test
    public void testBuildMajorScaleNotValid() {
        Scale scaleForTest = new Scale("random string");
        assertEquals(scaleForTest.buildMajorScale(Scale.MAX_NOTE_NUMBERS).toString(),
                "[Invalid input. Please enter a letter between 'A' and 'G#' "
                + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
        assertEquals(scaleForTest.buildMajorScale(-1).toString(),
                "[Invalid input. Please enter a letter between 'A' and 'G#' "
                        + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
    }

    @Test
    public void testBuildMinorScaleValid() {
        Scale scaleForTest = new Scale("random string");

        assertEquals("c", scaleForTest.buildMinorScale(4).get(0));
    }

    @Test
    public void testBuildMinorScaleNotValid() {
        Scale scaleForTest = new Scale("random string");
        assertEquals(scaleForTest.buildMinorScale(Scale.MAX_NOTE_NUMBERS).toString(),
                "[Invalid input. Please enter a letter between 'a' and 'g#' "
                        + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
        assertEquals(scaleForTest.buildMinorScale(-1).toString(),
                "[Invalid input. Please enter a letter between 'a' and 'g#' "
                        + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
    }
}
