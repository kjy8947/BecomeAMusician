package model;

import java.util.HashMap;

public class Note {
    public static final int MAX_NOTE_NUMBERS = 12;

    private HashMap<Integer, String> notesIntToStrForMajor = new HashMap<Integer, String>(MAX_NOTE_NUMBERS);
    private HashMap<String, Integer> notesStrToIntForMajor = new HashMap<String, Integer>(MAX_NOTE_NUMBERS);
    private HashMap<Integer, String> notesIntToStrForMinor = new HashMap<Integer, String>(MAX_NOTE_NUMBERS);
    private HashMap<String, Integer> notesStrToIntForMinor = new HashMap<String, Integer>(MAX_NOTE_NUMBERS);

    public Note() {
        setNotesIntToStrForMajor();
        setNotesIntToStrForMinor();
        setNotesStrToIntForMajor();
        setNotesStrToIntForMinor();
    }

    public void setNotesIntToStrForMajor() {
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

    public void setNotesIntToStrForMinor() {
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

    public void setNotesStrToIntForMajor() {
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

    public void setNotesStrToIntForMinor() {
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

    public String getNoteForMajor(int key) {
        return notesIntToStrForMajor.get(key);
    }

//    public int getNoteForMajor(String key) {
//        return notesStrToIntForMajor.get(key);
//    }

    public int getNoteForMajor(String key) {
        if (notesStrToIntForMajor.keySet().contains(key)) {
            return notesStrToIntForMajor.get(key);
        } else {
            return 999;
        }
    }

    public String getNoteForMinor(int key) {
        return notesIntToStrForMinor.get(key);
    }

    public int getNoteForMinor(String key) {
        if (notesStrToIntForMinor.keySet().contains(key)) {
            return notesStrToIntForMinor.get(key);
        } else {
            return 999;
        }
    }

}
