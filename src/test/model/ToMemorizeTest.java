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

//    public ArrayList getMaterialsToMemorize() {
//        ArrayList listToMemorize = new ArrayList();
//        for (Chord c : chordsToMemorize) {
//            listToMemorize.add(c.getRootNote());
//        }
//        return listToMemorize;
//    }

    @Test
    public void testGetMaterialsToMemorize() {
        assertTrue(listToMemorize.getMaterialsToMemorize().isEmpty());
        listToMemorize.addChord("C");
        //assertArrayEquals(C.toArray(), listToMemorize.getMaterialsToMemorize().toArray());
        assertEquals("C", listToMemorize.getMaterialsToMemorize().get(0));
    }
}
