package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScaleTest {
    @Test
    public void testScaleConstructor() {
        Scale scaleForTest = new Scale("C");
        assertEquals("C", scaleForTest.getRootNote());
    }

    @Test
    public void testBuildMajorTriadChord() {
        Note note = new Note();
        Scale scaleForTest = new Scale("random string");
        assertEquals("D", scaleForTest.buildMajorScale(4).get(1));
        assertFalse(scaleForTest.buildMajorScale(13).get(0).equals("H"));
    }

    @Test
    public void testBuildMinorTriadChord() {
        Note note = new Note();
        Scale scaleForTest = new Scale("random string");
        assertEquals("c", scaleForTest.buildMinorScale(4).get(0));
        assertFalse(scaleForTest.buildMinorScale(13).get(0).equals("h"));
    }
}
