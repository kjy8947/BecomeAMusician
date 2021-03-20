package gui;

import model.Note;
import sound.NoteSound;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BecomeAMusicianGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int RIGID_AREA_WIDTH = 10;
    public static final int RIGID_AREA_HEIGHT = 20;

    private Note note = new Note();
    private NoteButtons buttons = new NoteButtons();
    private NoteSound noteSound = new NoteSound();

    JButton studyButton = new JButton("Study chords/scales");
    JButton quizButton = new JButton("Take a quiz");
    JButton toMemorizeListButton = new JButton("View/Edit the list of Chords to Memorize");
    JButton checkPointButton = new JButton("Check your character's points");
    JButton saveButton = new JButton("Save the state to file");
    JButton quitButton = new JButton("Quit");

    public BecomeAMusicianGUI() {
        super("Become a Musician");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        JPanel initialJPanel = new JPanel();
        initialJPanel.setLayout(new BoxLayout(initialJPanel, BoxLayout.Y_AXIS));

        JButton newGameButton = new JButton("New Game");
        newGameButton.setActionCommand("newGameButton");
        newGameButton.addActionListener(this);
        initialJPanel.add(Box.createVerticalGlue());
        initialJPanel.add(newGameButton);
        initialJPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setActionCommand("loadGameButton");
        loadGameButton.addActionListener(this);
        initialJPanel.add(loadGameButton);
        initialJPanel.add(Box.createVerticalGlue());

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(initialJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // doesn't do anything. might as well remove it.
//    public void nameTheCharacter() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridBagLayout());
//
//        JLabel label = new JLabel("Name your character: ");
//        JTextField field = new JTextField(12);
//        JButton button = new JButton("Enter");
//        button.setActionCommand("Enter");
//        button.addActionListener(this);
//
//        panel.add(label);
//        panel.add(field);
//        panel.add(button);
//
//        add(panel);
//        setVisible(true);
//    }

    public void mainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        settingActionCommandsForMainMenu();

        panel.add(Box.createVerticalGlue());
        panel.add(studyButton);
        panel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        panel.add(quizButton);
        panel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        panel.add(toMemorizeListButton);
        panel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        panel.add(checkPointButton);
        panel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        panel.add(saveButton);
        panel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        panel.add(quitButton);
        panel.add(Box.createVerticalGlue());

        centerAligningButtonsForMainMenu();

        add(panel);
        setVisible(true);
    }

    public void settingActionCommandsForMainMenu() {
        studyButton.setActionCommand("studyButton");
        studyButton.addActionListener(this);
        quizButton.setActionCommand("quizButton");
        quizButton.addActionListener(this);
        toMemorizeListButton.setActionCommand("toMemorizeListButton");
        toMemorizeListButton.addActionListener(this);
        checkPointButton.setActionCommand("checkPointButton");
        checkPointButton.addActionListener(this);
        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(this);
        quitButton.setActionCommand("quitButton");
        quitButton.addActionListener(this);
    }

    public void centerAligningButtonsForMainMenu() {
        studyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        toMemorizeListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkPointButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void study() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JButton studyChords = new JButton("Study chords");
        studyChords.setActionCommand("studyChords");
        studyChords.addActionListener(this);
        JButton studyScales = new JButton("Study scales");
        studyScales.setActionCommand("studyScales");
        studyScales.addActionListener(this);

        panel.add(studyChords);
        panel.add(studyScales);

        add(panel);
        setVisible(true);
    }

    public void playNotes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        for (JButton jb : buttons.getButtonList()) {
            String noteName = jb.getText();
            // not correct (addActionListener)
            jb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        noteSound.playNote(noteName);
                    } catch (MidiUnavailableException midiUnavailableException) {
                        midiUnavailableException.printStackTrace();
                    }
                }
            });

            panel.add(jb);
            setVisible(true);
        }

        add(panel);
        setVisible(true);
    }

    // TODO: need to print out each note of the chords selected
    public void playChords() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        for (JButton jb : buttons.getButtonList()) {
            String noteName = jb.getText();
            jb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        noteSound.playNote(noteName);
                        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 4) % 12));
                        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 7) % 12));
                    } catch (MidiUnavailableException midiUnavailableException) {
                        midiUnavailableException.printStackTrace();
                    }
                }
            });

            panel.add(jb);
            setVisible(true);
        }

        add(panel);
        setVisible(true);
    }

    public void playScales() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // too messy. no sounds will be provided for studying scales
//        for (JButton jb : buttons.getButtonList()) {
//            String noteName = jb.getText();
//            jb.addMouseListener(new MouseAdapter() {
//                public void mousePressed(MouseEvent e) {
//                    try {
//                        noteSound.playNote(noteName);
//                        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 2) % 12));
//                        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 4) % 12));
//                        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 5) % 12));
//                        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 7) % 12));
//                        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 9) % 12));
//                        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 11) % 12));
//                    } catch (MidiUnavailableException midiUnavailableException) {
//                        midiUnavailableException.printStackTrace();
//                    }
//                }
//            });
//
//            panel.add(jb);
//        }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("newGameButton")) {
            getContentPane().removeAll();
//            nameTheCharacter();
//            NameTheCharacter ntc = new NameTheCharacter();
            mainMenu();
        }
        if (e.getActionCommand().equals("studyButton")) {
            getContentPane().removeAll();
            study();
        }
        if (e.getActionCommand().equals("studyChords")) {
            getContentPane().removeAll();
            playChords();
        }
        if (e.getActionCommand().equals("studyScales")) {
            getContentPane().removeAll();
            playScales();
        }
        // the method using this is not being used anymore
//        if (e.getActionCommand().equals("Enter")) {
//            getContentPane().removeAll();
//            mainMenu();
//        }
    }

    public static void main(String[] args) {
        new BecomeAMusicianGUI();
    }
}
