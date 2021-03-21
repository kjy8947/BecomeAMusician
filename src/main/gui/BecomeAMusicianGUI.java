package gui;

import model.Note;
import model.ToMemorize;
import persistence.JsonReader;
import persistence.JsonWriter;
import sound.NoteSound;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// TODO: create a "back" or "main menu" button
public class BecomeAMusicianGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/myGameState.json";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int RIGID_AREA_WIDTH = 10;
    public static final int RIGID_AREA_HEIGHT = 20;

    private Note note = new Note();
    private NoteButtons buttons = new NoteButtons();
    private NoteSound noteSound = new NoteSound();
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private ToMemorize chordsToMemorize = new ToMemorize();
    private String characterName;

    private JButton studyButton = new JButton("Study chords/scales");
    private JButton quizButton = new JButton("Take a quiz");
    private JButton toMemorizeListButton = new JButton("View/Edit the list of Chords to Memorize");
    private JButton checkPointButton = new JButton("Check your character's points");
    private JButton saveButton = new JButton("Save the state to file");
    private JButton quitButton = new JButton("Quit");
    private JButton mainMenuButton = new JButton("Go back to the main menu");

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
        loadGameButton.setActionCommand("loadButton");
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
        mainMenuButton.setActionCommand("mainMenuButton");
        mainMenuButton.addActionListener(this);
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

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void quiz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // TODO: randomly ask questions and give points when correct answers are returned
        // Use combobox

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void editTheList() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

//        JButton viewList = new JButton("View the list");
//        viewList.setActionCommand("viewList");
//        viewList.addActionListener(this);
//        JButton editList = new JButton("Add a chord to the list");
//        editList.setActionCommand("editList");
//        editList.addActionListener(this);

//        panel.add(viewList);
//        panel.add(editList);

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    // TODO: just print the character's point
    public void checkPoint() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    // TODO: save. no need for a new panel. maybe a message pop up (get help at OH!)
    // TODO: need to fix the pop-up message keep popping up
    public void saveState() {
        try {
            jsonWriter.open();
            jsonWriter.write(chordsToMemorize);
            jsonWriter.close();
//            System.out.println("Saved " + characterName + "'s progress to " + JSON_STORE);
            JOptionPane.showMessageDialog(null,"The current state has been saved to file");
            mainMenu();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // TODO: Visit OH (office hours) for help!!!
    public void loadState() {
        try {
            chordsToMemorize = jsonReader.read();
//            System.out.println("Loaded " + characterName + "'s progress from " + JSON_STORE);
            getContentPane().removeAll();
            mainMenu();
            JOptionPane.showMessageDialog(null,"Loaded the previous state");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void saveBeforeQuitting() {

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

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void playScales() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // sound too messy. no sounds will be provided for studying scales
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

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("newGameButton")) {
//            getContentPane().removeAll();
////            nameTheCharacter();
//            mainMenu();
//        } else if (e.getActionCommand().equals("studyButton")) {
//            getContentPane().removeAll();
//            study();
//        } else if (e.getActionCommand().equals("studyChords")) {
//            getContentPane().removeAll();
//            playChords();
//        } else if (e.getActionCommand().equals("studyScales")) {
//            getContentPane().removeAll();
//            playScales();
//        } else if (e.getActionCommand().equals("quizButton")) {
//            getContentPane().removeAll();
//            quiz();
//        } else if (e.getActionCommand().equals("toMemorizeListButton")) {
//            getContentPane().removeAll();
//            editTheList();
//        } else if (e.getActionCommand().equals("checkPointButton")) {
//            getContentPane().removeAll();
//            checkPoint();
//        } else if (e.getActionCommand().equals("saveButton")) {
//            getContentPane().removeAll();
//            saveState();
//        } else if (e.getActionCommand().equals("loadButton")) {
//            getContentPane().removeAll();
//            loadState();
//        } else if (e.getActionCommand().equals("quitButton")) {
//            // we need to ask the user whether they want to save the state
//            System.exit(0);
//        }
//        // the method using this is not being used anymore
////        if (e.getActionCommand().equals("Enter")) {
////            getContentPane().removeAll();
////            mainMenu();
////        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        getContentPane().removeAll();
        if (e.getActionCommand().equals("newGameButton")) {
//            nameTheCharacter();
            mainMenu();
        } else if (e.getActionCommand().equals("studyButton")) {
            study();
        } else if (e.getActionCommand().equals("studyChords")) {
            playChords();
        } else if (e.getActionCommand().equals("studyScales")) {
            playScales();
        } else if (e.getActionCommand().equals("quizButton")) {
            quiz();
        } else if (e.getActionCommand().equals("toMemorizeListButton")) {
            editTheList();
        } else if (e.getActionCommand().equals("checkPointButton")) {
            checkPoint();
        } else if (e.getActionCommand().equals("saveButton")) {
            saveState();
        } else if (e.getActionCommand().equals("loadButton")) {
            loadState();
        } else if (e.getActionCommand().equals("quitButton")) {
            // we need to ask the user whether they want to save the state
//            saveBeforeQuitting();
            System.exit(0);
        } else if (e.getActionCommand().equals("mainMenuButton")) {
            mainMenu();
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
