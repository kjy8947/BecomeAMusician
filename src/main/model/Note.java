package model;

import java.util.HashMap;

/*
 * Represents a musical note
 * (uppercase letter notes to be used for major chords/scales;
 * lowercase letter notes to be used for minor chords/scales)
 */
public class Note {
    public static final int MAX_NOTE_NUMBERS = 12;

    private HashMap<Integer, String> notesIntToStrForMajor = new HashMap<Integer, String>(MAX_NOTE_NUMBERS);
    private HashMap<String, Integer> notesStrToIntForMajor = new HashMap<String, Integer>(MAX_NOTE_NUMBERS);
    private HashMap<Integer, String> notesIntToStrForMinor = new HashMap<Integer, String>(MAX_NOTE_NUMBERS);
    private HashMap<String, Integer> notesStrToIntForMinor = new HashMap<String, Integer>(MAX_NOTE_NUMBERS);

    // constructs hashmaps of 12 musical notes w/ a number assigned to each note
    public Note() {
        setNotesIntToStrForMajor();
        setNotesIntToStrForMinor();
        setNotesStrToIntForMajor();
        setNotesStrToIntForMinor();
    }

    // MODIFIES: this
    // EFFECTS: assigning 12 musical notes as the values for each key (numbers 0-11) in the hashmap
    //          these notes are to be used for major chords/scales as the letters are in uppercase
    private void setNotesIntToStrForMajor() {
        notesIntToStrForMajor.put(1, "A");
        notesIntToStrForMajor.put(2, "A#");
        notesIntToStrForMajor.put(3, "B");
        notesIntToStrForMajor.put(4, "C");
        notesIntToStrForMajor.put(5, "C#");
        notesIntToStrForMajor.put(6, "D");
        notesIntToStrForMajor.put(7, "D#");
        notesIntToStrForMajor.put(8, "E");
        notesIntToStrForMajor.put(9, "F");
        notesIntToStrForMajor.put(10, "F#");
        notesIntToStrForMajor.put(11, "G");
        notesIntToStrForMajor.put(0, "G#");
    }

    // MODIFIES: this
    // EFFECTS: assigning 12 musical notes as the values for each key (numbers 0-11) in the hashmap
    //          these notes are to be used for minor chords/scales as the letters are in lowercase
    private void setNotesIntToStrForMinor() {
        notesIntToStrForMinor.put(1, "a");
        notesIntToStrForMinor.put(2, "a#");
        notesIntToStrForMinor.put(3, "b");
        notesIntToStrForMinor.put(4, "c");
        notesIntToStrForMinor.put(5, "c#");
        notesIntToStrForMinor.put(6, "d");
        notesIntToStrForMinor.put(7, "d#");
        notesIntToStrForMinor.put(8, "e");
        notesIntToStrForMinor.put(9, "f");
        notesIntToStrForMinor.put(10, "f#");
        notesIntToStrForMinor.put(11, "g");
        notesIntToStrForMinor.put(0, "g#");
    }

    // MODIFIES: this
    // EFFECTS: assigning 12 numbers as the values for each key (musical notes A-G#) in the hashmap
    //          these notes are to be used for major chords/scales as the letters are in uppercase
    private void setNotesStrToIntForMajor() {
        notesStrToIntForMajor.put("A", 1);
        notesStrToIntForMajor.put("A#", 2);
        notesStrToIntForMajor.put("B", 3);
        notesStrToIntForMajor.put("C", 4);
        notesStrToIntForMajor.put("C#", 5);
        notesStrToIntForMajor.put("D", 6);
        notesStrToIntForMajor.put("D#", 7);
        notesStrToIntForMajor.put("E", 8);
        notesStrToIntForMajor.put("F", 9);
        notesStrToIntForMajor.put("F#", 10);
        notesStrToIntForMajor.put("G", 11);
        notesStrToIntForMajor.put("G#", 0);
    }

    // MODIFIES: this
    // EFFECTS: assigning 12 numbers as the values for each key (musical notes A-G#) in the hashmap
    //          these notes are to be used for minor chords/scales as the letters are in lowercase
    private void setNotesStrToIntForMinor() {
        notesStrToIntForMinor.put("a", 1);
        notesStrToIntForMinor.put("a#", 2);
        notesStrToIntForMinor.put("b", 3);
        notesStrToIntForMinor.put("c", 4);
        notesStrToIntForMinor.put("c#", 5);
        notesStrToIntForMinor.put("d", 6);
        notesStrToIntForMinor.put("d#", 7);
        notesStrToIntForMinor.put("e", 8);
        notesStrToIntForMinor.put("f", 9);
        notesStrToIntForMinor.put("f#", 10);
        notesStrToIntForMinor.put("g", 11);
        notesStrToIntForMinor.put("g#", 0);
    }

    // REQUIRES: key must be an integer [0, 11]
    // EFFECTS:  returns the note name that is paired w/ the key in the hashmap, notesIntToStrForMajor
    public String getNoteForMajor(int key) {
        return notesIntToStrForMajor.get(key);
    }

    // REQUIRES: key must be a string that belongs to the hashmap, notesStrToIntForMajor
    // EFFECTS:  returns the number that is paired w/ the key (note name) in the hashmap, notesStrToIntForMajor
    public int getNoteForMajor(String key) {
        if (notesStrToIntForMajor.keySet().contains(key)) {
            return notesStrToIntForMajor.get(key);
        } else {
            return 999;
        }
    }

    // REQUIRES: key must be an integer [0, 11]
    // EFFECTS: returns the note name that is paired w/ the key in the hashmap, notesIntToStrForMinor
    public String getNoteForMinor(int key) {
        return notesIntToStrForMinor.get(key);
    }

    // REQUIRES: key must be a string that belongs to the hashmap, notesStrToIntForMinor
    // EFFECTS: returns the number that is paired w/ the key (note name) in the hashmap, notesStrToIntForMinor
    public int getNoteForMinor(String key) {
        if (notesStrToIntForMinor.keySet().contains(key)) {
            return notesStrToIntForMinor.get(key);
        } else {
            return 999;
        }
    }
}
