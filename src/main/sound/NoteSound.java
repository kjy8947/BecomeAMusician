package sound;

import model.Note;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.util.HashMap;

public class NoteSound {
    private Note note = new Note();

//    private HashMap<String, JButton> noteList = new HashMap<>(Note.MAX_NOTE_NUMBERS);

    public NoteSound() {
//        noteList.put("C", new JButton("C"));
//        noteList.put("C#", new JButton("D"));
//        noteList.put("D", new JButton("D#"));
//        noteList.put("D#", new JButton("E"));
//        noteList.put("E", new JButton("E#"));
//        noteList.put("F", new JButton("F"));
//        noteList.put("F#", new JButton("F#"));
//        noteList.put("G", new JButton("G"));
//        noteList.put("G#", new JButton("G#"));
//        noteList.put("A", new JButton("A"));
//        noteList.put("A#", new JButton("A#"));
//        noteList.put("B", new JButton("B"));
    }

    public void playNote(String noteName) throws MidiUnavailableException {
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        MidiChannel midiChannel = synthesizer.getChannels()[0];
        if (noteName.equals("G#") || noteName.equals("A") || noteName.equals("A#") || noteName.equals("B")) {
            midiChannel.noteOn(note.getNoteForMajor(noteName) + 68, 100);
        } else {
            midiChannel.noteOn(note.getNoteForMajor(noteName) + 56, 100);
        }
    }
}
