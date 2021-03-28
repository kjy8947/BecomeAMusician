package gui;

import model.*;
import model.Character;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
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
 *  Study major chords/scales
 *  Take quizzes on major & minor chords
 *  View and edit your list of chords to memorize
 *  Check the points you've earned
 *  Save your points and list of chords
 *  Load your previous points and list of chords
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

    // MODIFIES: this
    // EFFECTS: provides user with instructions for the program by displaying lines of labels
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

    // MODIFIES: this
    // EFFECTS: Horizontally align the JComponent jc to center and add jc to panel
    //          Also add a Dimension below jc w/ RIGID_AREA_HEIGHT to give spacing between other JComponents, if any
    public void centerButtons(JPanel panel, JComponent jc) {
        jc.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(jc);
        panel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
    }

    // start screen of the game w/ 2 buttons: "new game" and "load game"
    // MODIFIES: this
    // EFFECTS: clicking newGameButton will create a character with 0 points and starts w/ an empty chordsToMemorize
    //          clicking loadButton will load the user's previous state from their last play
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

    // main menu of the game that can take you to different pages
    // MODIFIES: this
    // EFFECTS: user can try different activities by clicking each button
    public void mainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.pink);

        buttonSetUpForMainMenu();

        addButtonsForMainMenu(panel);

        add(panel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: call centerButtons(JPanel, JComponent) method for each button on the main menu
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

    // EFFECTS: instantiating buttons for mainMenu()
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

    // MODIFIES: this
    // EFFECTS: setting action commands and adding action listeners for buttons on main menu;
    //          what clicking each button will do will be determined in actionPerformed(e) method near the bottom
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

    // MODIFIES: this
    // EFFECTS: instantiating, setting action commands, and adding action listeners for some buttons used in program
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

    // MODIFIES: this
    // EFFECTS: a panel with 2 buttons, that brings the user to either studyChords() or studyScales()
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

    // MODIFIES: this
    // EFFECTS: user is required to submit either M3 or P5 note of the randomly given chord in order to earn 1 point;
    //          user loses 1 point if an incorrect answer is submitted
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

    // MODIFIES: this
    // EFFECTS: program determines if the answer given by user is correct;
    //          character (user) earns 1 point if the answer is correct and loses 1 point if the answer is incorrect
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

    // MODIFIES: this
    // EFFECTS: user is required to submit either m3 or P5 note of the randomly given chord in order to earn 1 point;
    //          user loses 1 point if an incorrect answer is submitted
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

    // MODIFIES: this
    // EFFECTS: program determines if the answer given by user is correct;
    //          character (user) earns 1 point if the answer is correct and loses 1 point if the answer is incorrect
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

    // MODIFIES: this
    // EFFECTS: user can select chords to add to or remove from their chordsToMemorize;
    //          a confirmation message will pop up when a chord is added/removed;
    //          same chord cannot be added twice
    public void editTheList() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

        String[] majorChords = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        JComboBox majorChordBox = new JComboBox(majorChords);

        JButton selectMajorChordToAddButton = new JButton("Add");
        JButton selectMajorChordToRemoveButton = new JButton("Remove");

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

    // helper method for editTheList()
    // REQUIRES: panel should be the one from the previous page
    //           all the components as parameters should be able to be received from the inside helper methods
    // MODIFIES: this
    // EFFECTS: updates and displays chordsToMemorize
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

    // MODIFIES: this
    // EFFECTS: clicking jb after selecting a major chord from cb will add the chord to chordsToMemorize,
    //          unless the chord is already in chordsToMemorize;
    //          confirmation message will pop up
    public void majorChordToAddButtonClicked(JButton jb, JComboBox cb, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chordToAdd = (String) cb.getSelectedItem();

                chordsToMemorize.addChord(chordToAdd);
                label.setText(chordsToMemorize.getMaterialsToMemorize().toString());
                JOptionPane.showMessageDialog(null, chordToAdd + " major chord has been added to the list");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: clicking jb after selecting a major chord from cb will remove the chord from chordsToMemorize
    //          confirmation message will pop up
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

    // MODIFIES: this
    // EFFECTS: clicking jb after selecting a minor chord from cb will add the chord to chordsToMemorize,
    //          unless the chord is already in chordsToMemorize;
    //          confirmation message will pop up
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

    // MODIFIES: this
    // EFFECTS: clicking jb after selecting a minor chord from cb will remove the chord from chordsToMemorize
    //          confirmation message will pop up
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

    // check points earned
    // MODIFIES: this
    // EFFECTS: user can check the points they've earned so far
    public void checkPoint() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);

        JLabel points = new JLabel();
        points.setText("You have earned " + character.getPoints() + " points so far.");

        panel.add(points);
        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    // CITATION: I used saveWorkRoom() from JsonSerializationDemo as a reference
    // EFFECTS: saves the current chordsToMemorize to file
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

    // CITATION: I used saveWorkRoom() from JsonSerializationDemo as a reference
    // EFFECTS: saves the current points of the character to file
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

    // EFFECTS: this method is called if the user selects "yes" when the program asks them if they want the current
    //          state (chordsToMemorize & points) saved to file at the time of exiting;
    //          saves both chordsToMemorize and points earned
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

    // CITATION: I used loadWorkRoom() from JsonSerializationDemo as a reference
    // MODIFIES: this
    // EFFECTS: loads chordsToMemorize and points from file
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

    // EFFECTS: gives user the option of saving the current state w/ a popup message;
    //          if user selects "yes", saves the current state and then exit;
    //          if user selects "no", simply exits from the program
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

    // MODIFIES: this
    // EFFECTS: the list of the notes for a chord (chosen by user by clicking a button) will be displayed on label
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

            noteButtonClicked(jb, noteValue, label);

            panel.add(jb);
        }

        panel.add(label);
        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: assigns "notes" with the list of notes that is supposed to be generated by buildMajorTriadChord
    //          when called on the chord user has chosen; sets the label with "notes"
    public void noteButtonClicked(JButton jb, int noteValue, JLabel label) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notes = chord.buildMajorTriadChord(noteValue);
                label.setText(notes.toString());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: the list of the notes for a chord (chosen by user by clicking a button) will be displayed on label;
    //          assigns "notes" with the list of notes that is supposed to be generated by buildMajorScale
    //          when called on the chord user has chosen; sets the label with "notes"
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

    // EFFECTS: lets the program play sounds
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
    // MODIFIES: this
    // EFFECTS: assigns method to be called based on which button clicked by user
    //          each button in this method will play buttonClick.wav whenever it's clicked by user
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

    // helper method for actionPerformed(e)
    // MODIFIES: this
    // EFFECTS: assigns method to be called based on which button clicked by user
    //          each button in this method will play buttonClick.wav whenever it's clicked by user
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

    /*
     * Start the program
     */
    public static void main(String[] args) {
        try {
            new BecomeAMusicianGUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}