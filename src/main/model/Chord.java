package model;

import java.util.ArrayList;

public class Chord {
    public static final int MAX_NOTE_NUMBERS = 12;

//    private int root;
//    private String rootNote;

    private ArrayList<String> chord;
    private ArrayList<Chord> chordsToMemorize;
    private ArrayList<Chord> memorizedChords;

    public Chord() {

    }

    // REQUIRES: a root needs to be one of the 7 letters between A and G
    // MODIFIES:
    // EFFECTS:
    public Chord(String rootNote) {
        Note note = new Note();
        if (rootNote.equals(rootNote.toUpperCase())) {
            buildMajorTriadChord(note.getNoteForMajor(rootNote));
        } else if (rootNote.equals(rootNote.toLowerCase())) {
            buildMinorTriadChord(note.getNoteForMinor(rootNote));
        }
    }

    public ArrayList buildMajorTriadChord(int root) {
        Note note = new Note();
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

    public ArrayList buildMinorTriadChord(int root) {
        Note note = new Note();
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

    public void studyMajorChord(String rootNote) {
        Note note = new Note();
        int noteValue;
        noteValue = note.getNoteForMajor(rootNote);
        Chord chord = new Chord(rootNote);
        System.out.println("\nBelow is the list of the notes in " + rootNote + " major chord.");
        System.out.println(chord.buildMajorTriadChord(noteValue));
    }

    public void studyMinorChord(String rootNote) {
        Note note = new Note();
        int noteValue;
        noteValue = note.getNoteForMinor(rootNote);
        Chord chord = new Chord(rootNote);
        System.out.println("\nBelow is the list of the notes in " + rootNote + " minor chord.");
        System.out.println(chord.buildMinorTriadChord(noteValue));
    }
}
