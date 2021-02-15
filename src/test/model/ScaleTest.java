package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// I don't know how to make these work...
public class ScaleTest {
    private Scale scale;

    @Test
    public void testBuildMajorScale() {
        String rootNote;
        scale.buildMajorScale(0);
        rootNote = scale.getRootNote();
        assertTrue(rootNote.equals("G#"));
    }

    @Test
    public void testBuildMinorScale() {
        String rootNote;
        scale.buildMinorScale(0);
        rootNote = scale.getRootNote();
        assertTrue(rootNote.equals("g#"));
    }
}
