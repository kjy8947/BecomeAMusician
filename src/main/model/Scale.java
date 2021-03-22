package model;

import java.util.ArrayList;

/*
 * Represents a scale
 */
public class Scale {
    public static final int MAX_NOTE_NUMBERS = 12;

    private ArrayList<String> scale;
    private String rootNote;

    // EFFECTS:  constructs a Scale object w/o any parameter given
    public Scale() {

    }

    // constructs a Scale object w/ the given rootNote as its root note
    // REQUIRES: a root needs to be one of the 7 letters between A and G
    //           an uppercase letter for a major scale; a lowercase letter for a minor scale
    // MODIFIES: this
    // EFFECTS:  runs the buildMajorScale method if the input letter is in uppercase
    //           runs the buildMinorScale method if the input letter is in lowercase
    public Scale(String rootNote) {
        this.rootNote = rootNote;
        Note note = new Note();
        if (rootNote.equals(rootNote.toUpperCase())) {
            buildMajorScale(note.getNoteForMajor(rootNote));
        } else if (rootNote.equals(rootNote.toLowerCase())) {
            buildMinorScale(note.getNoteForMinor(rootNote));
        }
    }

    // MODIFIES: this
    // EFFECTS:  builds a major scale by adding the next 7 notes after the root note given
    public ArrayList buildMajorScale(int root) {
        Note note = new Note();
        scale = new ArrayList<>();
        if (0 <= root && root < MAX_NOTE_NUMBERS) {
            scale.add(note.getNoteForMajor(root));
            scale.add(note.getNoteForMajor((root + 2) % 12));
            scale.add(note.getNoteForMajor((root + 4) % 12));
            scale.add(note.getNoteForMajor((root + 5) % 12));
            scale.add(note.getNoteForMajor((root + 7) % 12));
            scale.add(note.getNoteForMajor((root + 9) % 12));
            scale.add(note.getNoteForMajor((root + 11) % 12));
            scale.add(note.getNoteForMajor((root + 12) % 12));
            return scale;
        } else {
            scale.add("Invalid input. Please enter a letter between 'A' and 'G#' "
                    + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).");
            return scale;
        }
    }

    // MODIFIES: this
    // EFFECTS:  builds a minor scale by adding the next 7 notes after the root note given
    public ArrayList buildMinorScale(int root) {
        Note note = new Note();
        scale = new ArrayList<>();
        if (0 <= root && root < MAX_NOTE_NUMBERS) {
            scale.add(note.getNoteForMinor(root));
            scale.add(note.getNoteForMinor((root + 2) % 12));
            scale.add(note.getNoteForMinor((root + 3) % 12));
            scale.add(note.getNoteForMinor((root + 5) % 12));
            scale.add(note.getNoteForMinor((root + 7) % 12));
            scale.add(note.getNoteForMinor((root + 8) % 12));
            scale.add(note.getNoteForMinor((root + 11) % 12));
            scale.add(note.getNoteForMinor((root + 12) % 12));
            return scale;
        } else {
            scale.add("Invalid input. Please enter a letter between 'a' and 'g#' "
                    + "(an uppercase letter for a major scale; a lowercase letter for a minor scale).");
            return scale;
        }
    }

    // EFFECTS: returns the root note of the scale
    public String getRootNote() {
        return rootNote;
    }
}