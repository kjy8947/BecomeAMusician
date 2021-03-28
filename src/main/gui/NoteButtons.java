package gui;

import javax.swing.*;
import java.util.ArrayList;

public class NoteButtons {
    private ArrayList<JButton> buttonList = new ArrayList<>();

    JButton buttonC = new JButton("C");
    JButton buttonCsharp = new JButton("C#");
    JButton buttonD = new JButton("D");
    JButton buttonDsharp = new JButton("D#");
    JButton buttonE = new JButton("E");
    JButton buttonF = new JButton("F");
    JButton buttonFsharp = new JButton("F#");
    JButton buttonG = new JButton("G");
    JButton buttonGsharp = new JButton("G#");
    JButton buttonA = new JButton("A");
    JButton buttonAsharp = new JButton("A#");
    JButton buttonB = new JButton("B");

    public NoteButtons() {
        buttonList.add(buttonC);
        buttonList.add(buttonCsharp);
        buttonList.add(buttonD);
        buttonList.add(buttonDsharp);
        buttonList.add(buttonE);
        buttonList.add(buttonF);
        buttonList.add(buttonFsharp);
        buttonList.add(buttonG);
        buttonList.add(buttonGsharp);
        buttonList.add(buttonA);
        buttonList.add(buttonAsharp);
        buttonList.add(buttonB);
    }

    // EFFECTS: returns the list of buttons in buttonList
    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }
}

//package gui;
//
//import model.Note;
//import sound.NoteSound;
//
//import javax.sound.midi.MidiUnavailableException;
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class NoteButtons implements ActionListener {
//    private Note note;
//    private NoteSound noteSound = new NoteSound();
//    private ArrayList<JButton> buttonList = new ArrayList<>();
//
//    JButton buttonC = new JButton("C");
//    JButton buttonCsharp = new JButton("C#");
//    JButton buttonD = new JButton("D");
//    JButton buttonDsharp = new JButton("D#");
//    JButton buttonE = new JButton("E");
//    JButton buttonF = new JButton("F");
//    JButton buttonFsharp = new JButton("F#");
//    JButton buttonG = new JButton("G");
//    JButton buttonGsharp = new JButton("G#");
//    JButton buttonA = new JButton("A");
//    JButton buttonAsharp = new JButton("A#");
//    JButton buttonB = new JButton("B");
//
//    public NoteButtons() {
//        buttonList.add(buttonC);
//        buttonList.add(buttonCsharp);
//        buttonList.add(buttonD);
//        buttonList.add(buttonDsharp);
//        buttonList.add(buttonE);
//        buttonList.add(buttonF);
//        buttonList.add(buttonFsharp);
//        buttonList.add(buttonG);
//        buttonList.add(buttonGsharp);
//        buttonList.add(buttonA);
//        buttonList.add(buttonAsharp);
//        buttonList.add(buttonB);
//    }
//
//    public void settingActionCommand() {
//        buttonC.setActionCommand("buttonC");
//        buttonCsharp.setActionCommand("buttonCsharp");
//        buttonD.setActionCommand("buttonD");
//        buttonDsharp.setActionCommand("buttonDsharp");
//        buttonE.setActionCommand("buttonE");
//        buttonF.setActionCommand("buttonF");
//        buttonFsharp.setActionCommand("buttonFsharp");
//        buttonG.setActionCommand("buttonG");
//        buttonGsharp.setActionCommand("buttonGsharp");
//        buttonA.setActionCommand("buttonA");
//        buttonAsharp.setActionCommand("buttonAsharp");
//        buttonB.setActionCommand("buttonB");
//    }
//
//    public void addingActionListener() {
//        buttonC.addActionListener(this);
//        buttonCsharp.addActionListener(this);
//        buttonD.addActionListener(this);
//        buttonDsharp.addActionListener(this);
//        buttonE.addActionListener(this);
//        buttonF.addActionListener(this);
//        buttonFsharp.addActionListener(this);
//        buttonG.addActionListener(this);
//        buttonGsharp.addActionListener(this);
//        buttonA.addActionListener(this);
//        buttonAsharp.addActionListener(this);
//        buttonB.addActionListener(this);
//    }
//
//    public ArrayList<JButton> getButtonList() {
//        return buttonList;
//    }
//
//    public void playChord(String noteName) throws MidiUnavailableException {
//        noteSound.playNote(noteName);
//        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 4) % 12));
//        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 7) % 12));
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("buttonC")) {
//            try {
//                playChord("C");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else if (e.getActionCommand().equals("buttonCsharp")) {
//            try {
//                playChord("C#");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else if (e.getActionCommand().equals("buttonD")) {
//            try {
//                playChord("D");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else {
//            actionPerformedFirstHelper(e);
//        }
//    }
//
//    public void actionPerformedFirstHelper(ActionEvent e) {
//        if (e.getActionCommand().equals("buttonDsharp")) {
//            try {
//                playChord("D#");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else if (e.getActionCommand().equals("buttonE")) {
//            try {
//                playChord("E");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else if (e.getActionCommand().equals("buttonF")) {
//            try {
//                playChord("F");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else {
//            actionPerformedSecondHelper(e);
//        }
//    }
//
//    public void actionPerformedSecondHelper(ActionEvent e) {
//        if (e.getActionCommand().equals("buttonFsharp")) {
//            try {
//                playChord("F#");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else if (e.getActionCommand().equals("buttonG")) {
//            try {
//                playChord("G");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else if (e.getActionCommand().equals("buttonGsharp")) {
//            try {
//                playChord("G#");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else {
//            actionPerformedThirdHelper(e);
//        }
//    }
//
//    public void actionPerformedThirdHelper(ActionEvent e) {
//        if (e.getActionCommand().equals("buttonA")) {
//            try {
//                playChord("A");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else if (e.getActionCommand().equals("buttonAsharp")) {
//            try {
//                playChord("A#");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        } else if (e.getActionCommand().equals("buttonB")) {
//            try {
//                playChord("B");
//            } catch (MidiUnavailableException midiUnavailableException) {
//                midiUnavailableException.printStackTrace();
//            }
//        }
//    }
//}