package model;

import org.json.JSONArray;
import org.json.JSONObject;
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
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        Chord chord1 = new Chord("C");
        Chord chord2 = new Chord("D");
        Chord chord3 = new Chord("E");

        jsonArray.put(chord1.toJson());
        jsonArray.put(chord2.toJson());
        jsonArray.put(chord3.toJson());

        json.put("chords to memorize", jsonArray);

//        assertEquals("{\"root note\":\"C\"}", jsonArray.get(0).toString());
//        assertEquals("{\"root note\":\"D\"}", jsonArray.get(1).toString());
//        assertEquals("{\"root note\":\"E\"}", jsonArray.get(2).toString());
        assertTrue(jsonArray.get(0).toString().equals("{\"root note\":\"C\"}"));
        assertTrue(jsonArray.get(1).toString().equals("{\"root note\":\"D\"}"));
        assertTrue(jsonArray.get(2).toString().equals("{\"root note\":\"E\"}"));

//        assertEquals("{\"chords to memorize\":[{\"root note\":\"C\"},{\"root note\":\"D\"},{\"root note\":\"E\"}]}", json.toString());
        assertTrue(json.toString().equals("{\"chords to memorize\":[{\"root note\":\"C\"},{\"root note\":\"D\"},{\"root note\":\"E\"}]}"));
    }

    @Test
    public void testChordsToJson() {
        JSONArray jsonArray = new JSONArray();

        Chord chord1 = new Chord("C");
        Chord chord2 = new Chord("D");
        Chord chord3 = new Chord("E");

        jsonArray.put(chord1.toJson());
        jsonArray.put(chord2.toJson());
        jsonArray.put(chord3.toJson());

        assertEquals("{\"root note\":\"C\"}", jsonArray.get(0).toString());
        assertEquals("{\"root note\":\"D\"}", jsonArray.get(1).toString());
        assertEquals("{\"root note\":\"E\"}", jsonArray.get(2).toString());
    }
}
