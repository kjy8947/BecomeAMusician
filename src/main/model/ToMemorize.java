package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a list of chords to memorize
 */
public class ToMemorize implements Writable {
    private ArrayList<Chord> chordsToMemorize;

    // constructs an empty list of chords to memorize
    public ToMemorize() {
        this.chordsToMemorize = new ArrayList<>();
    }

    // REQUIRES: String rootNote is an element of Note
    // MODIFIES: this
    // EFFECTS: add a chord (with a rootNote as its root note) to the list and then returns the list
    public ArrayList addChord(String rootNote) {
        Chord chord = new Chord(rootNote);
        if (!chordsToMemorize.contains(chord)) {
            this.chordsToMemorize.add(chord);
        }
        return chordsToMemorize;
    }

     //not working, maybe with the way I designed how a chord object gets created
    public void removeChord(String rootNote) {
        Chord chord = new Chord(rootNote);
        if (chordsToMemorize.contains(chord)) {
            chordsToMemorize.remove(chord);
        }
    }

    public ArrayList getChords() {
        return chordsToMemorize;
    }

    // EFFECTS: returns the list of the chords in the list to memorize (chord names are represented w/ their root notes)
    public List<Chord> getMaterialsToMemorize() {
        ArrayList listToMemorize = new ArrayList();
        for (Chord c : chordsToMemorize) {
            listToMemorize.add(c.getRootNote());
        }
        return listToMemorize;
    }

    @Override
    // CITATION: this method has been copied (and then modified) from JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("chords to memorize", chordsToJson());
        return json;
    }

    // CITATION: this method has been copied (and then modified) from JsonSerializationDemo
    // EFFECTS: returns chords in this list to memorize as a JSON array
    public JSONArray chordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Chord c : chordsToMemorize) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
