package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

/*
 * Represents a triad chord
 */
public class Chord implements Writable {
    public static final int MAX_NOTE_NUMBERS = 12;

    private ArrayList<String> chord;
    private String rootNote;
    private Note note = new Note();

    // EFFECTS:  constructs a Chord object w/o any parameter given
    public Chord() {

    }

    // constructs a Chord object w/ the given rootNote as its root note
    // REQUIRES: a root needs to be one of the 7 letters between A and G
    //           an uppercase letter for a major chord; a lowercase letter for a minor chord
    // EFFECTS:  runs the buildMajorChord method if the input letter is in uppercase
    //           runs the buildMinorChord method if the input letter is in lowercase
    public Chord(String rootNote) {
        this.rootNote = rootNote;
        if (rootNote.equals(rootNote.toUpperCase())) {
            buildMajorTriadChord(note.getNoteForMajor(rootNote));
        } else if (rootNote.equals(rootNote.toLowerCase())) {
            buildMinorTriadChord(note.getNoteForMinor(rootNote));
        }
    }

    // MODIFIES: this
    // EFFECTS:  builds a major chord by adding the major 3rd (+ 4) and the perfect 5th (+ 7) notes
    //           to the root note given
    public ArrayList buildMajorTriadChord(int root) {
        chord = new ArrayList<>();
        if (0 <= root && root < MAX_NOTE_NUMBERS) {
            chord.add(note.getNoteForMajor(root));
            chord.add(note.getNoteForMajor((root + 4) % 12));
            chord.add(note.getNoteForMajor((root + 7) % 12));
            return chord;
        } else {
            chord.add("Invalid input. Please enter a letter between 'A' and 'G#' "
                    + "(an uppercase letter for a major chord; a lowercase letter for a minor chord).");
            return chord;
        }
    }

    // MODIFIES: this
    // EFFECTS:  builds a minor chord by adding the minor 3rd (+ 3) and the perfect 5th (+ 7) notes
    //           to the root note given
    public ArrayList buildMinorTriadChord(int root) {
        chord = new ArrayList<>();
        if (0 <= root && root < MAX_NOTE_NUMBERS) {
            chord.add(note.getNoteForMinor(root));
            chord.add(note.getNoteForMinor((root + 3) % 12));
            chord.add(note.getNoteForMinor((root + 7) % 12));
            return chord;
        } else {
            chord.add("Invalid input. Please enter a letter between 'a' and 'g#' "
                    + "(an uppercase letter for a major chord; a lowercase letter for a minor chord).");
            return chord;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chord chord = (Chord) o;
        return rootNote.equals(chord.rootNote);
    }

    // EFFECTS: returns the root note of the chord
    public String getRootNote() {
        return rootNote;
    }

    @Override
    // CITATION: this method has been copied (and then modified) from JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("root note", rootNote);
        return json;
    }
}
