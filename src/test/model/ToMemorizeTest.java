package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToMemorizeTest {
    ToMemorize listToMemorize;

    @BeforeEach
    public void runBefore() {
        listToMemorize = new ToMemorize();
    }

    // duplicates are allowed
    @Test
    public void testAddChord() {
        ArrayList chords = listToMemorize.addChord("C");
        assertEquals(1, chords.size());
        listToMemorize.addChord("C");
        assertEquals(2, chords.size());
        listToMemorize.addChord("c");
        assertEquals(3, chords.size());
    }

    @Test
    public void testGetMaterialsToMemorize() {
        assertTrue(listToMemorize.getMaterialsToMemorize().isEmpty());
        listToMemorize.addChord("C");
        //assertArrayEquals(C.toArray(), listToMemorize.getMaterialsToMemorize().toArray());
        assertEquals("C", listToMemorize.getMaterialsToMemorize().get(0));
    }

    @Test
    public void testToJson() {
        ToMemorize tm = new ToMemorize();

        tm.addChord("C");
        tm.addChord("D");
        tm.addChord("E");

        assertEquals(tm.toJson().toString(),
                "{\"chords to memorize\":[{\"root note\":\"C\"},{\"root note\":\"D\"},{\"root note\":\"E\"}]}");
    }

    @Test
    public void testChordsToJson() {
        ToMemorize tm = new ToMemorize();

        tm.addChord("C");
        tm.addChord("D");
        tm.addChord("E");

        assertEquals(tm.chordsToJson().toString(),
                "[{\"root note\":\"C\"},{\"root note\":\"D\"},{\"root note\":\"E\"}]");
    }
}
