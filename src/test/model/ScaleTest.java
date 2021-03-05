package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScaleTest {

    @Test
    public void testScaleConstructorMajorScale() {
        Scale scaleForTest = new Scale("C");
        assertEquals("C", scaleForTest.getRootNote());
    }

    @Test
    public void testScaleConstructorMinorScale() {
        Scale scaleForTest = new Scale("c");
        assertEquals("c", scaleForTest.getRootNote());
    }

    @Test
    public void testBuildMajorScaleValid() {
        Scale scaleForTest = new Scale("random string");
        assertEquals("D", scaleForTest.buildMajorScale(4).get(1));
    }

    @Test
    public void testBuildMajorScaleNotValid() {
        Scale scaleForTest = new Scale("random string");
        assertEquals(scaleForTest.buildMajorScale(Scale.MAX_NOTE_NUMBERS + 1).toString(),
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
        assertEquals(scaleForTest.buildMinorScale(Scale.MAX_NOTE_NUMBERS + 1).toString(),
                "[Invalid input. Please enter a letter between 'a' and 'g#' "
                        + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).]");
    }
}
