package gui;

import model.Character;
import model.Chord;
import model.Note;
import model.ToMemorize;
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
import java.util.Random;
import java.util.Scanner;

import static ui.BecomeAMusician.MAX_NOTE_NUMBERS;

public class BecomeAMusicianGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/myGameState.json";
    public static final int WIDTH = 768;
    public static final int HEIGHT = 432;
//    public static final int RIGID_AREA_WIDTH = 10;
    public static final int RIGID_AREA_HEIGHT = 20;

    // TODO: organize the fields
    private model.Character character;
    private Note note = new Note();
    private NoteButtons buttons = new NoteButtons();
    private NoteSound noteSound = new NoteSound();
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private ToMemorize chordsToMemorize = new ToMemorize();
    private BufferedImage keyboardImage = ImageIO.read(new File("./data/keyboard.jpg"));
    private Scanner scanner;

    private JButton studyButton = new JButton("Study chords/scales");
    private JButton quizButton = new JButton("Take a quiz");
    private JButton toMemorizeListButton = new JButton("View/Edit the list of Chords to Memorize");
    private JButton checkPointButton = new JButton("Check your character's points");
    private JButton saveButton = new JButton("Save the state to file");
    private JButton quitButton = new JButton("Quit");
    private JButton mainMenuButton = new JButton("Go back to the main menu");
    private JButton enterButtonForMajorChordQuiz = new JButton("Enter");
    private JButton enterButtonForMinorChordQuiz = new JButton("Enter");
    private int randomNumber;
    private String randomNote;
    private Chord chord = new Chord();
    private String anotherRandomNote;
    private Random rd = new Random();

    public BecomeAMusicianGUI() throws IOException {
        super("Become a Musician");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        startScreen();

//        JPanel initialJPanel = new JPanel();
//        initialJPanel.setLayout(new BoxLayout(initialJPanel, BoxLayout.Y_AXIS));
//
//        JButton newGameButton = new JButton("New Game");
//        newGameButton.setActionCommand("newGameButton");
//        newGameButton.addActionListener(this);
//        initialJPanel.add(Box.createVerticalGlue());
//        initialJPanel.add(newGameButton);
//        initialJPanel.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
//        JButton loadGameButton = new JButton("Load Game");
//        loadGameButton.setActionCommand("loadButton");
//        loadGameButton.addActionListener(this);
//        initialJPanel.add(loadGameButton);
//        initialJPanel.add(Box.createVerticalGlue());
//
//        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        add(initialJPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void startScreen() {
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

        settingActionCommands();

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

    public void settingActionCommands() {
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

        enterButtonForMajorChordQuiz.setActionCommand("enterButtonForMajorChordQuiz");
        enterButtonForMajorChordQuiz.addActionListener(this);
        enterButtonForMinorChordQuiz.setActionCommand("enterButtonForMinorChordQuiz");
        enterButtonForMinorChordQuiz.addActionListener(this);
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

    public void randomMajorChordQuiz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        randomNumber = rd.nextInt(MAX_NOTE_NUMBERS);
        String randomNote = note.getNoteForMajor(randomNumber);
        JLabel question = new JLabel("Select one of the notes that belongs to " + randomNote + " major chord.");

        String[] majorChords = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
        JComboBox cb = new JComboBox(majorChords);

        enterButtonForMajorChordQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String submittedAnswerForQuiz = String.valueOf(cb.getSelectedItem());
                String submittedAnswerForQuiz = (String) cb.getSelectedItem();
//
                if (chord.buildMajorTriadChord(randomNumber).contains(submittedAnswerForQuiz)) {
                    character.earnPoint();
                    JOptionPane.showMessageDialog(null,"Correct! You get one point.");
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect! You failed this one.");
                }

//                randomMinorChordQuiz();
            }
        });

        panel.add(question);
        panel.add(cb);
        panel.add(enterButtonForMajorChordQuiz);

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);

//        String submittedAnswerForQuiz = String.valueOf(cb.getSelectedItem());
//
//        if (chord.buildMajorTriadChord(randomNumber).contains(submittedAnswerForQuiz)) {
//            character.earnPoint();
////            return true;
//        }
//        return false;
    }

    public void randomMinorChordQuiz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        randomNumber = rd.nextInt(MAX_NOTE_NUMBERS);
        String randomNote = note.getNoteForMinor(randomNumber);
        JLabel question = new JLabel("Select one of the notes that belongs to " + randomNote + " minor chord.");

        String[] minorChords = { "c", "c#", "d", "d#", "e", "f", "f#", "g", "g#", "a", "a#", "b" };
        JComboBox cb = new JComboBox(minorChords);

        enterButtonForMinorChordQuiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String submittedAnswerForQuiz = String.valueOf(cb.getSelectedItem());
                String submittedAnswerForQuiz = (String) cb.getSelectedItem();
//
                if (chord.buildMinorTriadChord(randomNumber).contains(submittedAnswerForQuiz)) {
                    character.earnPoint();
                    JOptionPane.showMessageDialog(null,"Correct! You get one point.");
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect! You failed this one.");
                }

//                mainMenu();
            }
        });

        panel.add(question);
        panel.add(cb);
        panel.add(enterButtonForMinorChordQuiz);

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);

//        String submitedAnswerforQuiz = cb.getSelectedItem().toString();
//
//        if (chord.buildMinorTriadChord(randomNumber).contains(submitedAnswerforQuiz)) {
//            return true;
//        }
//        return false;
    }

//    // TODO: this method is currently just copied and pasted from UI.
//    //       need to work on it, of course.
//    public void randomMajorChordQuizMaker() {
//        randomNumber = rd.nextInt(MAX_NOTE_NUMBERS);
//        System.out.println("\nList one of the notes that belongs to "
//                + note.getNoteForMajor(randomNumber) + " major chord.");
//        System.out.println("(Please note that you automatically fail with one mistake. "
//                + "Remember this program is case sensitive!)");
//        randomNote = scanner.nextLine();
//        chord = new Chord();
//        if (chord.buildMajorTriadChord(randomNumber).contains(randomNote)) {
//            System.out.println("\nCorrect! What's another note in " + randomNote + " major chord?");
//            anotherRandomNote = scanner.nextLine();
//            if (chord.buildMajorTriadChord(randomNumber).contains(randomNote)
//                    && !anotherRandomNote.equalsIgnoreCase(randomNote)) {
//                System.out.println("\nCorrect! You get one point.");
//                character.earnPoint();
//            } else if (anotherRandomNote.equalsIgnoreCase(randomNote)) {
//                System.out.println("You already entered this note before. That's cheating! No points earned.");
//            }
//        } else {
//            System.out.println("You failed the quiz.");
//        }
//    }

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

        JLabel points = new JLabel();
        points.setText("Your character has earned " + String.valueOf(character.getPoints()) + " points so far.");

        panel.add(points);
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
    public void studyChords() {
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

    public void studyScales() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        for (JButton jb : buttons.getButtonList()) {
//            String noteName = jb.getText();
            panel.add(jb);
        }

        panel.add(mainMenuButton);

        add(panel);
        setVisible(true);
    }

    // TODO: delete if not used
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
        playSound("./data/button-29.wav");
        getContentPane().removeAll();
        if (e.getActionCommand().equals("newGameButton")) {
            character = new Character();
            mainMenu();
        } else if (e.getActionCommand().equals("studyButton")) {
            study();
        } else if (e.getActionCommand().equals("studyChords")) {
            studyChords();
        } else if (e.getActionCommand().equals("studyScales")) {
            studyScales();
        } else if (e.getActionCommand().equals("quizButton")) {
            randomMajorChordQuiz();
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
        } else if (e.getActionCommand().equals("enterButtonForMajorChordQuiz")) {
//            character.earnPoint();
            randomMinorChordQuiz();
        } else if (e.getActionCommand().equals("enterButtonForMinorChordQuiz")) {
            mainMenu();
        }
//         the method using this is not being used anymore
//        if (e.getActionCommand().equals("Enter")) {
//            getContentPane().removeAll();
//            mainMenu();
//        }
    }

    public static void main(String[] args) {
        try {
            new BecomeAMusicianGUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
