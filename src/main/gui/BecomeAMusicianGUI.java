package gui;

import model.*;
import model.Character;
import persistence.JsonReader;
import persistence.JsonWriter;
import sound.NoteSound;

import javax.imageio.ImageIO;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static ui.BecomeAMusician.MAX_NOTE_NUMBERS;

/*
 * Play the game:
 *  Study chords/scales
 *  Take quizzes on major & minor chords
 *  View and edit your list of chords to memorize
 *  Check the points your character's earned
 *  Save the list of chords
 *  Load the list of chords
 */
public class BecomeAMusicianGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/myGameState.json";
    private static final String JSON_STORE_CHARACTER = "./data/myCharacterState.json";
    public static final int WIDTH = 1024; //768
    public static final int HEIGHT = 432;
    //    public static final int RIGID_AREA_WIDTH = 10;
    public static final int RIGID_AREA_HEIGHT = 20;

    private int randomNumber;
    private String randomNote;
    private Random rd = new Random();
    private ArrayList<String> notes = new ArrayList<>();
    private model.Character character;
    private Note note = new Note();
    private Chord chord = new Chord();
    private Scale scale = new Scale();
    private NoteButtons buttons = new NoteButtons();
    private NoteSound noteSound = new NoteSound();
    private ToMemorize chordsToMemorize = new ToMemorize();
    private BufferedImage keyboardImage = ImageIO.read(new File("./data/keyboard.jpg"));

    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonWriter jsonWriter1 = new JsonWriter(JSON_STORE_CHARACTER);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JsonReader jsonReader1 = new JsonReader(JSON_STORE_CHARACTER);
    private JButton newGameButton = new JButton("New Game");
    private JButton studyButton;
    private JButton studyChordsButton;
    private JButton studyScalesButton;
    private JButton quizButton;
    private JButton toMemorizeListButton;
    private JButton checkPointButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JButton mainMenuButton;
    private JButton enterButtonForMajorChordQuiz;
    private JButton enterButtonForMinorChordQuiz;

    private boolean actionsSet;

    // Starts the program
    // Setup a JFrame with its preferred size
    public BecomeAMusicianGUI() throws IOException {
        super("Become a Musician");
        actionsSet = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        startScreen();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    public void startScreen() {
        JPanel initialJPanel = new JPanel();
        initialJPanel.setLayout(new BoxLayout(initialJPanel, BoxLayout.Y_AXIS));

        buttonSetUpForMainMenu();

        newGameButton.setActionCommand("newGameButton");
        newGameButton.addActionListener(this);
        initialJPanel.add(Box.createVerticalGlue());
        initialJPanel.add(newGameButton);
        initialJPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        loadButton.setActionCommand("loadButton");
        loadButton.addActionListener(this);
        initialJPanel.add(loadButton);
        initialJPanel.add(Box.createVerticalGlue());

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(initialJPanel);
    }

    public void mainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        buttonSetUpForMainMenu();

        panel.add(Box.createVerticalGlue());
        panel.add(loadButton);
        panel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
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

    public void buttonSetUpForMainMenu() {
        studyButton = new JButton("Study chords/scales");
//        studyChordsButton = new JButton("Study chords");
//        studyScalesButton = new JButton("Study scales");
        quizButton = new JButton("Take a quiz");
        toMemorizeListButton = new JButton("View/Edit the list of Chords to Memorize");
        checkPointButton = new JButton("Check your character's points");
        saveButton = new JButton("Save the state to file");
        loadButton = new JButton("Load Game");
        quitButton = new JButton("Quit");


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
        loadButton.setActionCommand("loadButton");
        loadButton.addActionListener(this);
        quitButton.setActionCommand("quitButton");
        quitButton.addActionListener(this);
    }

    public void otherButtonSetUp() {
        mainMenuButton = new JButton("Go back to the main menu");

        mainMenuButton.setActionCommand("mainMenuButton");
        mainMenuButton.addActionListener(this);

        studyChordsButton = new JButton("Study chords");
        studyScalesButton = new JButton("Study scales");

        studyChordsButton.setActionCommand("studyChordsButton");
        studyChordsButton.addActionListener(this);
        studyScalesButton.setActionCommand("studyScalesButton");
        studyScalesButton.addActionListener(this);

        enterButtonForMajorChordQuiz = new JButton("Enter");
        enterButtonForMinorChordQuiz = new JButton("Enter");

        enterButtonForMajorChordQuiz.setActionCommand("enterButtonForMajorChordQuiz");
        enterButtonForMajorChordQuiz.addActionListener(this);
        enterButtonForMinorChordQuiz.setActionCommand("enterButtonForMinorChordQuiz");
        enterButtonForMinorChordQuiz.addActionListener(this);
    }

    public void centerAligningButtonsForMainMenu() {
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
//
//        studyChordsButton.setActionCommand("studyChordsButton");
//        studyChordsButton.addActionListener(this);
//        studyScalesButton.setActionCommand("studyScalesButton");
//        studyScalesButton.addActionListener(this);

        panel.add(studyChordsButton);
        panel.add(studyScalesButton);

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    // TODO: JPanel layout 바꾸기
    public void randomMajorChordQuiz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        randomNumber = rd.nextInt(MAX_NOTE_NUMBERS);
        String randomNote = note.getNoteForMajor(randomNumber);
        JLabel question = new JLabel("Select one of the notes (other than the root note) that belongs to "
                + randomNote + " major chord.");

        String[] majorChords = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        JComboBox cb = new JComboBox(majorChords);

        randomMajorChordQuizButtonClicked(enterButtonForMajorChordQuiz, cb, randomNote);

        panel.add(question);
        panel.add(cb);
        panel.add(enterButtonForMajorChordQuiz);

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void randomMajorChordQuizButtonClicked(JButton jb, JComboBox cb, String randomNote) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                enterButtonForMajorChordQuiz.removeActionListener(this);

                String submittedAnswerForQuiz = (String) cb.getSelectedItem();

                if (chord.buildMajorTriadChord(randomNumber).contains(submittedAnswerForQuiz)
                        && randomNote != submittedAnswerForQuiz) {
                    character.earnPoint();
                    JOptionPane.showMessageDialog(null, "Correct! You get one point.");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect! You failed this one.");
                }
            }
        });
    }

    // TODO: JPanel layout 바꾸기
    public void randomMinorChordQuiz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        randomNumber = rd.nextInt(MAX_NOTE_NUMBERS);
        String randomNote = note.getNoteForMinor(randomNumber);
        JLabel question = new JLabel("Select one of the notes (other than the root note) that belongs to "
                + randomNote + " minor chord.");

        String[] minorChords = {"c", "c#", "d", "d#", "e", "f", "f#", "g", "g#", "a", "a#", "b"};
        JComboBox cb = new JComboBox(minorChords);

        randomMinorChordQuizButtonClicked(enterButtonForMinorChordQuiz, cb, randomNote);

        panel.add(question);
        panel.add(cb);
        panel.add(enterButtonForMinorChordQuiz);

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void randomMinorChordQuizButtonClicked(JButton jb, JComboBox cb, String randomNote) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                enterButtonForMinorChordQuiz.removeActionListener(this);

                String submittedAnswerForQuiz = (String) cb.getSelectedItem();

                if (chord.buildMinorTriadChord(randomNumber).contains(submittedAnswerForQuiz)
                        && randomNote != submittedAnswerForQuiz) {
                    character.earnPoint();
                    JOptionPane.showMessageDialog(null, "Correct! You get one point.");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect! You failed this one.");
                }
            }
        });
    }

    // TODO: JPanel layout 바꾸기
    public void editTheList() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel question = new JLabel("Select the chords you want to add to the Chords to Memorize list");
        JLabel label = new JLabel();

        String[] majorChords = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        JComboBox majorChordBox = new JComboBox(majorChords);

        JButton selectMajorChordToAddButton = new JButton("Add");

        majorChordToAddButtonClicked(selectMajorChordToAddButton, majorChordBox, label);

        String[] minorChords = {"c", "c#", "d", "d#", "e", "f", "f#", "g", "g#", "a", "a#", "b"};
        JComboBox minorChordBox = new JComboBox(minorChords);

        JButton selectMinorChordToAddButton = new JButton("Add");

        minorChordToAddButtonClicked(selectMinorChordToAddButton, minorChordBox, label);

        label.setText(chordsToMemorize.getMaterialsToMemorize().toString()); // viewing the list

        panel.add(question);
        panel.add(label);
        panel.add(majorChordBox);
        panel.add(selectMajorChordToAddButton);
        panel.add(minorChordBox);
        panel.add(selectMinorChordToAddButton);
        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void majorChordToAddButtonClicked(JButton jb, JComboBox cb, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // change the button
//                enterButtonForMinorChordQuiz.removeActionListener(this);

                String chordToAdd = (String) cb.getSelectedItem();

                chordsToMemorize.addChord(chordToAdd);

                label.setText(chordsToMemorize.getMaterialsToMemorize().toString()); // viewing the list

                JOptionPane.showMessageDialog(null, chordToAdd + " major chord has been added to the list");
            }
        });
    }

    public void minorChordToAddButtonClicked(JButton jb, JComboBox cb, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chordToAdd = (String) cb.getSelectedItem();

                chordsToMemorize.addChord(chordToAdd);

                label.setText(chordsToMemorize.getMaterialsToMemorize().toString()); // viewing the list

                JOptionPane.showMessageDialog(null, chordToAdd + " minor chord has been added to the list");
            }
        });
    }

    public void checkPoint() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel points = new JLabel();
        points.setText("Your character has earned " + String.valueOf(character.getPoints()) + " points so far.");

        panel.add(points);
        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void saveState() {
        try {
            jsonWriter.open();
            jsonWriter.write(chordsToMemorize);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "The current state has been saved to file");
            mainMenu();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void saveCharacter() {
        try {
            jsonWriter1.open();
            jsonWriter1.write(character);
            jsonWriter1.close();
//            JOptionPane.showMessageDialog(null, "The current state has been saved to file");
            mainMenu();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CHARACTER);
        }
    }

    public void saveOnQuit() {
        try {
            jsonWriter.open();
            jsonWriter.write(chordsToMemorize);
            jsonWriter.close();
            jsonWriter1.open();
            jsonWriter1.write(character);
            jsonWriter1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadState() {
        try {
            chordsToMemorize = jsonReader.read();
            character = jsonReader1.readPoints();
            getContentPane().removeAll();
            mainMenu();
            JOptionPane.showMessageDialog(null, "Loaded the previous state");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void saveBeforeQuitting() {
        int userSelection = JOptionPane.showConfirmDialog(null,
                "Do you want to save the current state?", "Select an option", JOptionPane.YES_NO_OPTION);

        if (userSelection == 0) {
            saveOnQuit();
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    // TODO: maybe use it for scale buttons (since playing scales sounds too messy, just play a note for each button click)
//    public void playNotes() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridBagLayout());
//
//        for (JButton jb : buttons.getButtonList()) {
//            String noteName = jb.getText();
//            // not correct (addActionListener)
//            jb.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    try {
//                        noteSound.playNote(noteName);
//                    } catch (MidiUnavailableException midiUnavailableException) {
//                        midiUnavailableException.printStackTrace();
//                    }
//                }
//            });
//
//            panel.add(jb);
//            setVisible(true);
//        }
//
//        add(panel);
//        setVisible(true);
//    }

    // TODO: need to divide this into major and minor chords
    // TODO: JPanel layout 바꾸기
    public void studyChords() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
//        panel.setLayout(new FlowLayout());

        JLabel question = new JLabel("Which major chord do you want to study?");
        JLabel label = new JLabel();

        for (JButton jb : buttons.getButtonList()) {
            String noteName = jb.getText();
            int noteValue = note.getNoteForMajor(noteName);

            noteButtonClicked(jb, noteName, noteValue, label);

            panel.add(label);
            panel.add(jb);
        }

        panel.add(question);
        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void noteButtonClicked(JButton jb, String noteName, int noteValue, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                jb.removeActionListener(this); //TODO: uncomment?

                notes = chord.buildMajorTriadChord(noteValue);
                label.setText(notes.toString());
                try {
                    playNote(noteSound, noteName);
                } catch (MidiUnavailableException midiUnavailableException) {
                    midiUnavailableException.printStackTrace();
                }
            }
        });
    }

    public void playNote(NoteSound noteSound, String noteName) throws MidiUnavailableException {
        noteSound.playNote(noteName);
        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 4) % 12));
        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 7) % 12));
    }

    // TODO: JPanel layout 바꾸기
    public void studyScales() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
//        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel();

        for (JButton jb : buttons.getButtonList()) {
            String noteName = jb.getText();
            int noteValue = note.getNoteForMajor(noteName);
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playSound("./data/buttonClick.wav");
                    notes = scale.buildMajorScale(noteValue);
                    label.setText(notes.toString());
                }
            });

            panel.add(label);
            panel.add(jb);
        }

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playSound("./data/buttonClick.wav");
        buttonSetUpForMainMenu(); // new
        otherButtonSetUp(); // new
        getContentPane().removeAll();
        if (e.getActionCommand().equals("newGameButton")) {
            character = new Character();
            newGameButton.removeActionListener(this);
            mainMenu();
        } else if (e.getActionCommand().equals("studyButton")) {
            studyButton.removeActionListener(this);
            study();
        } else if (e.getActionCommand().equals("studyChordsButton")) {
            studyChordsButton.removeActionListener(this);
            studyChords();
        } else if (e.getActionCommand().equals("studyScalesButton")) {
            studyScalesButton.removeActionListener(this);
            studyScales();
        } else if (e.getActionCommand().equals("quizButton")) {
            quizButton.removeActionListener(this);
            randomMajorChordQuiz();
        } else {
            actionPerformedFirstHelper(e);
        }
    }

    public void actionPerformedFirstHelper(ActionEvent e) {
        if (e.getActionCommand().equals("toMemorizeListButton")) {
            toMemorizeListButton.removeActionListener(this);
            editTheList();
        } else if (e.getActionCommand().equals("checkPointButton")) {
            checkPointButton.removeActionListener(this);
            checkPoint();
        } else if (e.getActionCommand().equals("saveButton")) {
            saveButton.removeActionListener(this);
            saveState();
            saveCharacter();
        } else if (e.getActionCommand().equals("loadButton")) {
            character = new Character();
            loadButton.removeActionListener(this);
            loadState();
        } else if (e.getActionCommand().equals("quitButton")) {
            quitButton.removeActionListener(this);
            saveBeforeQuitting();
        } else if (e.getActionCommand().equals("mainMenuButton")) {
            mainMenuButton.removeActionListener(this);
            mainMenu();
        } else {
            actionPerformedSecondHelper(e);
        }
    }

    public void actionPerformedSecondHelper(ActionEvent e) {
        if (e.getActionCommand().equals("enterButtonForMajorChordQuiz")) {
            enterButtonForMajorChordQuiz.removeActionListener(this);
            randomMinorChordQuiz();
        } else if (e.getActionCommand().equals("enterButtonForMinorChordQuiz")) {
            enterButtonForMinorChordQuiz.removeActionListener(this);
            mainMenu();
        }
    }

    public static void main(String[] args) {
        try {
            new BecomeAMusicianGUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
