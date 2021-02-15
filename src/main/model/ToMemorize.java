package model;

import java.util.ArrayList;

/*
 * Represents a list of chords to memorize
 */
public class ToMemorize {
    private ArrayList<Chord> chordsToMemorize;

    // constructs an empty list of chords to memorize
    public ToMemorize() {
        this.chordsToMemorize = new ArrayList<>();
    }

//    // REQUIRES: String rootNote is an element of Note
//    // MODIFIES: this
//    // EFFECTS: add a chord (with a rootNote as its root note) to the list
//    public void addChord(String rootNote) {
//        Chord chord = new Chord(rootNote);
//        this.chordsToMemorize.add(chord);
//    }

    // REQUIRES: String rootNote is an element of Note
    // MODIFIES: this
    // EFFECTS: add a chord (with a rootNote as its root note) to the list and then returns the list
    public ArrayList addChord(String rootNote) {
        Chord chord = new Chord(rootNote);
        this.chordsToMemorize.add(chord);
        return chordsToMemorize;
    }

    // EFFECTS: returns the list of the chords in the list to memorize (chord names are represented w/ their root notes)
    public ArrayList getMaterialsToMemorize() {
        ArrayList listToMemorize = new ArrayList();
        for (Chord c : chordsToMemorize) {
            listToMemorize.add(c.getRootNote());
        }
        return listToMemorize;
    }
}
