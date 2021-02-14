package model;

import java.util.ArrayList;

public class Scale {
    public static final int MAX_NOTE_NUMBERS = 12;

    private ArrayList<String> scale;
    private ArrayList<Scale> scalesToMemorize;
    private ArrayList<Scale> memorizedScales;

    public Scale(String rootNote) {
        Note note = new Note();
        if (rootNote.equals(rootNote.toUpperCase())) {
            buildMajorScale(note.getNoteForMajor(rootNote));
        } else if (rootNote.equals(rootNote.toLowerCase())) {
            buildMinorScale(note.getNoteForMinor(rootNote));
        }
    }

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

    public void studyMajorScale(String rootNote) {
        Note note = new Note();
        int noteValue;
        noteValue = note.getNoteForMajor(rootNote);
        Scale scale = new Scale(rootNote);
        System.out.println("\nBelow is the list of the notes in " + rootNote + " major scale.");
        System.out.println((scale.buildMajorScale(noteValue)));
    }

    public void studyMinorScale(String rootNote) {
        Note note = new Note();
        int noteValue;
        noteValue = note.getNoteForMinor(rootNote);
        Scale scale = new Scale(rootNote);
        System.out.println("\nBelow is the list of the notes in " + rootNote + " minor scale.");
        System.out.println((scale.buildMinorScale(noteValue)));
    }
}