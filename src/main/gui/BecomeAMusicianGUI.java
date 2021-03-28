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
 *  Check the points you've earned
 *  Save the list of chords
 *  Load the list of chords
 */
public class BecomeAMusicianGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/myGameState.json";
    private static final String JSON_STORE_CHARACTER = "./data/myCharacterState.json";
    public static final int WIDTH = 1280; //1024
    public static final int HEIGHT = 432;
    public static final int RIGID_AREA_HEIGHT = 20;

    private int randomNumber;
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
    private JButton instructionButton;
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

    // Starts the program
    // Setup a JFrame with its preferred size
    public BecomeAMusicianGUI() throws IOException {
        super("Become a Musician");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        startScreen();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    public void instruction() {
        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));
        instructionPanel.setBackground(Color.pink);

        JLabel firstLine = new JLabel("Thank you for choosing our program to study music.");
        JLabel secondLine = new JLabel("Please note that our developer is not the smartest person.");
        JLabel thirdLine = new JLabel("That is why this program does not understand a flat or double accidentals.");
        JLabel fourthLine = new JLabel("Therefore, kindly use an enharmonic equivalence of any flat notes.");
        JLabel fifthLine = new JLabel("Currently, only major keys are supported for studying chords/scales.");
        JLabel sixthLine = new JLabel("Be careful not to give incorrect answers on quizzes. "
                + "You lose one point for each of them!");
        sixthLine.setForeground(Color.RED);

        instructionPanel.add(Box.createVerticalGlue());
        centerButtons(instructionPanel, firstLine);
        centerButtons(instructionPanel, secondLine);
        centerButtons(instructionPanel, thirdLine);
        centerButtons(instructionPanel, fourthLine);
        centerButtons(instructionPanel, fifthLine);
        centerButtons(instructionPanel, sixthLine);
        instructionPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        centerButtons(instructionPanel, mainMenuButton);
        instructionPanel.add(Box.createVerticalGlue());

        add(instructionPanel);
        setVisible(true);
    }

    public void centerButtons(JPanel panel, JComponent jc) {
        jc.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(jc);
        panel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
    }

    public void startScreen() {
        JPanel initialJPanel = new JPanel();
        initialJPanel.setLayout(new BoxLayout(initialJPanel, BoxLayout.Y_AXIS));
        initialJPanel.setBackground(Color.pink);

        buttonSetUpForMainMenu();

        newGameButton.setActionCommand("newGameButton");
        newGameButton.addActionListener(this);
        initialJPanel.add(Box.createVerticalGlue());
        JLabel keyboard = new JLabel(new ImageIcon(keyboardImage));
        centerButtons(initialJPanel, keyboard);
        initialJPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        initialJPanel.add(newGameButton);
        initialJPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        initialJPanel.add(loadButton);
        initialJPanel.add(Box.createVerticalGlue());

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(initialJPanel);
    }

    public void mainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.pink);

        buttonSetUpForMainMenu();

        addButtonsForMainMenu(panel);

        add(panel);
        setVisible(true);
    }

    public void addButtonsForMainMenu(JPanel panel) {
        panel.add(Box.createVerticalGlue());
        centerButtons(panel, loadButton);
        centerButtons(panel, instructionButton);
        centerButtons(panel, studyButton);
        centerButtons(panel, quizButton);
        centerButtons(panel, toMemorizeListButton);
        centerButtons(panel, checkPointButton);
        centerButtons(panel, saveButton);
        panel.add(quitButton);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
    }

    public void createButtonsForMainMenu() {
        instructionButton = new JButton("Instructions");
        studyButton = new JButton("Study chords/scales");
        quizButton = new JButton("Take a quiz");
        toMemorizeListButton = new JButton("View/Edit the list of Chords to Memorize");
        checkPointButton = new JButton("Check your points");
        saveButton = new JButton("Save the state to file");
        loadButton = new JButton("Load Game");
        quitButton = new JButton("Quit");
    }

    public void buttonSetUpForMainMenu() {
        createButtonsForMainMenu();

        instructionButton.setActionCommand("instructionButton");
        instructionButton.addActionListener(this);
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
        mainMenuButton.setBackground(Color.yellow);

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

    public void study() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

        panel.add(studyChordsButton);
        panel.add(studyScalesButton);

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void randomMajorChordQuiz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

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
                String submittedAnswerForQuiz = (String) cb.getSelectedItem();

                if (chord.buildMajorTriadChord(randomNumber).contains(submittedAnswerForQuiz)
                        && randomNote != submittedAnswerForQuiz) {
                    character.earnPoint();
                    JOptionPane.showMessageDialog(null, "Correct! You get one point.");
                } else {
                    character.losePoint();
                    JOptionPane.showMessageDialog(null, "Incorrect! You failed this one. You lost one point.");
                }
            }
        });
    }

    public void randomMinorChordQuiz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

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
                String submittedAnswerForQuiz = (String) cb.getSelectedItem();

                if (chord.buildMinorTriadChord(randomNumber).contains(submittedAnswerForQuiz)
                        && randomNote != submittedAnswerForQuiz) {
                    character.earnPoint();
                    JOptionPane.showMessageDialog(null, "Correct! You get one point.");
                } else {
                    character.losePoint();
                    JOptionPane.showMessageDialog(null, "Incorrect! You failed this one. You lost one point.");
                }
            }
        });
    }

    public void editTheList() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

        String[] majorChords = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        JComboBox majorChordBox = new JComboBox(majorChords);

        JButton selectMajorChordToAddButton = new JButton("Add");
        JButton selectMajorChordToRemoveButton = new JButton("Remove"); //TODO: testing

        String[] minorChords = {"c", "c#", "d", "d#", "e", "f", "f#", "g", "g#", "a", "a#", "b"};
        JComboBox minorChordBox = new JComboBox(minorChords);

        JButton selectMinorChordToAddButton = new JButton("Add");
        JButton selectMinorChordToRemoveButton = new JButton("Remove");

        initializeComponentsForEditTheList(panel, selectMajorChordToAddButton, selectMinorChordToAddButton,
                selectMajorChordToRemoveButton, selectMinorChordToRemoveButton, majorChordBox, minorChordBox);

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void initializeComponentsForEditTheList(JPanel panel, JButton mjab, JButton mnab, JButton mcrb,
                                                   JButton mnrb, JComboBox mjcb, JComboBox mncb) {
        JLabel question = new JLabel("Select the chords you want to add to the Chords to Memorize list. ");
        JLabel label = new JLabel();

        majorChordToAddButtonClicked(mjab, mjcb, label);
        majorChordToRemoveButtonClicked(mcrb, mjcb, label);

        minorChordToAddButtonClicked(mnab, mncb, label);
        minorChordToRemoveButtonClicked(mnrb, mncb, label);

        if (chordsToMemorize.getChords().isEmpty()) {
            label.setText("[empty]");
        } else {
            label.setText(chordsToMemorize.getMaterialsToMemorize().toString()); // viewing the list
        }

        panel.add(question);
        panel.add(label);
        panel.add(mjcb);
        panel.add(mjab);
        panel.add(mcrb);
        panel.add(mncb);
        panel.add(mnab);
        panel.add(mnrb);
    }

    public void majorChordToAddButtonClicked(JButton jb, JComboBox cb, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chordToAdd = (String) cb.getSelectedItem();

                chordsToMemorize.addChord(chordToAdd);
                label.setText(chordsToMemorize.getMaterialsToMemorize().toString()); // viewing the list
                JOptionPane.showMessageDialog(null, chordToAdd + " major chord has been added to the list");
            }
        });
    }

    public void majorChordToRemoveButtonClicked(JButton jb, JComboBox cb, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chordToRemove = (String) cb.getSelectedItem();
                chordsToMemorize.removeChord(chordToRemove);
                label.setText(chordsToMemorize.getMaterialsToMemorize().toString()); // viewing the list
                JOptionPane.showMessageDialog(null, chordToRemove + " major chord has been removed from the list");
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

    public void minorChordToRemoveButtonClicked(JButton jb, JComboBox cb, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chordToRemove = (String) cb.getSelectedItem();
                chordsToMemorize.removeChord(chordToRemove);
                label.setText(chordsToMemorize.getMaterialsToMemorize().toString()); // viewing the list
                JOptionPane.showMessageDialog(null, chordToRemove + " minor chord has been removed from the list");
            }
        });
    }

    public void checkPoint() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

        JLabel points = new JLabel();
        points.setText("You have earned " + String.valueOf(character.getPoints()) + " points so far.");

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

    public void studyChords() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

        JLabel question = new JLabel("Which major chord do you want to study?");
        JLabel label = new JLabel();

        panel.add(question);

        for (JButton jb : buttons.getButtonList()) {
            String noteName = jb.getText();
            int noteValue = note.getNoteForMajor(noteName);

            noteButtonClicked(jb, noteName, noteValue, label);

            panel.add(jb);
        }

        panel.add(label);
        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    public void noteButtonClicked(JButton jb, String noteName, int noteValue, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                notes = chord.buildMajorTriadChord(noteValue);
                label.setText(notes.toString());
            }
        });
    }

    public void playChord(String noteName) throws MidiUnavailableException {
        noteSound.playNote(noteName);
        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 4) % 12));
        noteSound.playNote(note.getNoteForMajor((note.getNoteForMajor(noteName) + 7) % 12));
    }

    // TODO: JPanel layout 바꾸기
    public void studyScales() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

        JLabel question = new JLabel("Which major scale do you want to study?");
        JLabel label = new JLabel();

        panel.add(question);

        for (JButton jb : buttons.getButtonList()) {
            String noteName = jb.getText();
            int noteValue = note.getNoteForMajor(noteName);
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    playSound("./data/buttonClick.wav");
                    notes = scale.buildMajorScale(noteValue);
                    label.setText(notes.toString());
                }
            });

            panel.add(jb);
        }

        panel.add(label);
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
        buttonSetUpForMainMenu();
        otherButtonSetUp();
        getContentPane().removeAll();
        if (e.getActionCommand().equals("newGameButton")) {
            character = new Character();
            mainMenu();
        } else if (e.getActionCommand().equals("studyButton")) {
            study();
        } else if (e.getActionCommand().equals("studyChordsButton")) {
            studyChords();
        } else if (e.getActionCommand().equals("studyScalesButton")) {
            studyScales();
        } else if (e.getActionCommand().equals("quizButton")) {
            randomMajorChordQuiz();
        } else if (e.getActionCommand().equals("toMemorizeListButton")) {
            editTheList();
        } else {
            actionPerformedHelper(e);
        }
    }

    public void actionPerformedHelper(ActionEvent e) {
        if (e.getActionCommand().equals("checkPointButton")) {
            checkPoint();
        } else if (e.getActionCommand().equals("saveButton")) {
            saveState();
            saveCharacter();
        } else if (e.getActionCommand().equals("loadButton")) {
            character = new Character();
            loadState();
        } else if (e.getActionCommand().equals("quitButton")) {
            saveBeforeQuitting();
        } else if (e.getActionCommand().equals("mainMenuButton")) {
            mainMenu();
        } else if (e.getActionCommand().equals("enterButtonForMajorChordQuiz")) {
            randomMinorChordQuiz();
        } else if (e.getActionCommand().equals("enterButtonForMinorChordQuiz")) {
            mainMenu();
        } else if (e.getActionCommand().equals("instructionButton")) {
            instruction();
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
