package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * Play the game:
 *  Study chords/scales
 *  Take quizzes on major/minor chords
 *  View and edit your list of chords to memorize
 *  Check the points your character's earned
 *  Save the list of chords
 *  Load the list of chords
 */
public class BecomeAMusician {
    private static final String JSON_STORE = "./data/myGameState.json";
    public static final int MAX_NOTE_NUMBERS = 12;

    private ArrayList<String> activities = new ArrayList<>();
    private ArrayList<String> editTheList = new ArrayList<>();
    private ToMemorize chordsToMemorize = new ToMemorize();
    private Scanner scanner;
    private String characterName;
    private model.Character character;
    private int randomNumber;
    private String randomNote;
    private Random rd = new Random();
    private Note note = new Note();
    private Chord chord;
    private Scale scale;
    private String anotherRandomNote;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Starts the program
    // Begins w/ the brief instructions followed by character creation
    public BecomeAMusician() throws FileNotFoundException {
        activities.add("1: Study chords/scales");
        activities.add("2: Take a quiz");
        activities.add("3: Edit the list of chords to memorize");
        activities.add("4: Check your character's points");
        activities.add("5: Save the state to file");
        activities.add("6: Load a state from file");
        activities.add("7: Quit");
        editTheList.add("1: View the list");
        editTheList.add("2: Add a chord to the list");
        scanner = new Scanner(System.in);
        characterName = "";
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        instructions();
        createCharacter();
    }

    // EFFECTS: provides the user w/ a brief introduction of the program
    public void instructions() {
        System.out.println("Thank you for choosing our program to study music.");
        System.out.println("Please note that our developer is not the smartest person.");
        System.out.println("That is why this program does not understand a flat or double accidentals.");
        System.out.println("Kindly use an enharmonic equivalence of any flat notes. (eg. D sharp instead of E flat)");
        System.out.println("Also, please note that uppercase letter inputs are required for major chords/scales, "
                + "and lowercase letter inputs are required for minor chords/scales.");
    }

    // Citation: the method has been copied and then modified from B4 Lecture Lab
    // EFFECTS: allows the user to create a character with a valid name
    public void createCharacter() {
        while (true) {
            while (!isValidName(characterName)) {
                System.out.print("\nName the character (1-12 alphabetical letters only without spaces): ");
                characterName = scanner.nextLine();
                if (isValidName(characterName)) {
                    character = new model.Character();
                    System.out.println("\nYour character's name is: " + characterName);
                    System.out.println("\nWhat do you want " + characterName + " to do today? (Enter the number)");
                    runGame();
                } else {
                    System.out.println("Invalid name. Please enter a valid name.");
                }
            }
        }
    }

    // EFFECTS: returns true if the name is valid
    //          by "valid" means the name entered by the user is between 1-12 characters, only w/ alphabetical letters
    public boolean isValidName(String name) {
        StringBuilder sb = new StringBuilder();

        for (Character c : name.toCharArray()) {
            if (Character.isLetter(c) && 1 <= name.toCharArray().length && name.toCharArray().length <= 12) {
                sb.append(c);
            } else {
                return false;
            }
        }
        characterName = sb.toString();

        if (characterName.length() == 0) {
            return false;
        }
        return true;
    }

    // Citation: got help for method design from JsonSerializationDemo project
    //           runWork() method in WorkRoomApp in ui
    // MODIFIES: this
    // EFFECTS: either quits the program (option 7) or keeps playing (when 1-6 is chosen)
    //          user can save their list of chords (to memorize) before quitting, if they choose to
    public void runGame() {
        boolean keepGoing = true;
        String command;
        scanner = new Scanner(System.in);
        System.out.println(activities);

        while (keepGoing) {

            command = scanner.next();

            if (command.equals("7")) {
                System.out.println("Would you like to save the current progress? (y/n)");
                String saveOrNot = scanner.next();
                if (saveOrNot.equalsIgnoreCase("y")) {
                    saveTheState();
                    System.exit(0);
                } else {
                    keepGoing = false; //originally the only line inside the 'if' bracket
                    System.exit(0);
                }
            } else {
                chooseAnActivity(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: provides the user w/ 4 options to choose from for activities
    //          Users are required to enter an integer value [1, 4]
    //          otherwise, it goes back to the main menu w/o processing anything
    //          Entering 1 will run the studyActivity method
    //          Entering 2 will run the randomMajorChordQuizMaker and randomMinorChordQuizMaker methods
    //          Entering 3 will run the editTheListOfChordsToMemorize method
    //          Entering 4 will print the number of points earned by the character the user is playing
    //          Entering 5 will save the current state
    //          Entering 6 will load a saved state
    public void chooseAnActivity(String activity) {
        String whatToStudy;
        scanner = new Scanner(System.in);

        if (activity.equals("1")) {
            System.out.println("\nWhat do you want to study? Chords? Scales?");
            whatToStudy = scanner.nextLine();
            studyActivity(whatToStudy);
        } else if (activity.equals("2")) {
            takeQuiz();
        } else if (activity.equals("3")) {
            System.out.println("\nLet's edit " + characterName + "'s list of chords to memorize." + "(Enter num.)");
            editTheListOfChordsToMemorize();
        } else if (activity.equals("4")) {
            System.out.println("Your character has earned " + character.getPoints() + " point(s) so far.");
        } else if (activity.equals("5")) {
            saveTheState();
        } else if (activity.equals("6")) {
            loadTheState();
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        System.out.println("\nWhat next? (Enter the number)\n" + activities);
    }

    // takes quiz
    // EFFECTS: quiz the user on the 2 notes of a randomly generated major chord and then a minor chord
    //          user earns one point per quiz s/he passes
    public void takeQuiz() {
        System.out.println("\nLet's take a quiz and level up!\n\nFirst, a major chord.");
        randomMajorChordQuizMaker();
        System.out.println("\nNext, a minor chord.");
        randomMinorChordQuizMaker();
    }

    // EFFECTS: lets the user to choose on a study activity
    //          from the 2 options available: chord(s) or scale(s)
    //          if any other input is entered, goes back to the main menu after printing a message
    public void studyActivity(String whatToStudy) {
        if (whatToStudy.equalsIgnoreCase("chord")
                || whatToStudy.equalsIgnoreCase("chords")) {

            chordIsChosen();

        } else if (whatToStudy.equalsIgnoreCase("scale")
                || whatToStudy.equalsIgnoreCase("scales")) {

            scaleIsChosen();

        } else {
            System.out.println("Invalid input. Going back to the main menu.");
        }
    }

    // EFFECTS: runs either studyMajorChord method or studyMinorChord based on the case of the user input
    //          if the input is in an uppercase, it'll run the studyMajorChord method
    //          if the input is in an lowercase, it'll run the studyMinorChord method
    public void chordIsChosen() {
        String chordToStudy;
        System.out.println("\nWhich chord do you want to study?");
        chordToStudy = scanner.nextLine();
        chord = new Chord(chordToStudy);
        if (chordToStudy.equals(chordToStudy.toUpperCase())) {
            studyMajorChord(chordToStudy);
        } else if (chordToStudy.equals(chordToStudy.toLowerCase())) {
            studyMinorChord(chordToStudy);
        }
    }

    // EFFECTS: runs either studyMajorScale method or studyMinorScale based on the case of the user input
    //          if the input is in an uppercase, it'll run the studyMajorScale method
    //          if the input is in an lowercase, it'll run the studyMinorScale method
    public void scaleIsChosen() {
        String scaleToStudy;
        System.out.println("\nWhich scale do you want to study?");
        scaleToStudy = scanner.nextLine();
        scale = new Scale(scaleToStudy);
        if (scaleToStudy.equals(scaleToStudy.toUpperCase())) {
            studyMajorScale(scaleToStudy);
        } else if (scaleToStudy.equals(scaleToStudy.toLowerCase())) {
            studyMinorScale(scaleToStudy);
        }
    }

    // REQUIRES: String rootNote is an element of Note
    // EFFECTS: prints the list of notes in a (major) chord whose root note is given
    public void studyMajorChord(String rootNote) {
        Note note = new Note();
        int noteValue;
        noteValue = note.getNoteForMajor(rootNote);
        Chord chord = new Chord(rootNote);
        System.out.println("\nBelow is the list of the notes in " + rootNote + " major chord.");
        System.out.println(chord.buildMajorTriadChord(noteValue));
    }

    // REQUIRES: String rootNote is an element of Note
    // EFFECTS: prints the list of notes in a (minor) chord whose root note is given
    public void studyMinorChord(String rootNote) {
        Note note = new Note();
        int noteValue;
        noteValue = note.getNoteForMinor(rootNote);
        Chord chord = new Chord(rootNote);
        System.out.println("\nBelow is the list of the notes in " + rootNote + " minor chord.");
        System.out.println(chord.buildMinorTriadChord(noteValue));
    }

    // REQUIRES: String rootNote is an element of Note
    // EFFECTS: prints the list of notes in a (major) scale whose root note is given
    public void studyMajorScale(String rootNote) {
        Note note = new Note();
        int noteValue;
        noteValue = note.getNoteForMajor(rootNote);
        Scale scale = new Scale(rootNote);
        System.out.println("\nBelow is the list of the notes in " + rootNote + " major scale.");
        System.out.println((scale.buildMajorScale(noteValue)));
    }

    // REQUIRES: String rootNote is an element of Note
    // EFFECTS: prints the list of notes in a (major) scale whose root note is given
    public void studyMinorScale(String rootNote) {
        Note note = new Note();
        int noteValue;
        noteValue = note.getNoteForMinor(rootNote);
        Scale scale = new Scale(rootNote);
        System.out.println("\nBelow is the list of the notes in " + rootNote + " minor scale.");
        System.out.println((scale.buildMinorScale(noteValue)));
    }

    // EFFECTS: tests the user w/ knowledge of a randomly generated major chord
    //          Users will earn a point if they enter two of the three notes in the chord asked
    //          If the 2nd note is the same one as the 1st note, the program perceives it as cheating, thus failing
    //          If the user fails on the quiz, no points will be earned
    //          Since it's for a major chord, the correct answers scanned must be in uppercase, letters from A-G#
    public void randomMajorChordQuizMaker() {
        randomNumber = rd.nextInt(MAX_NOTE_NUMBERS);
        System.out.println("\nList one of the notes that belongs to "
                + note.getNoteForMajor(randomNumber) + " major chord.");
        System.out.println("(Please note that you automatically fail with one mistake. "
                + "Remember this program is case sensitive!)");
        randomNote = scanner.nextLine();
        chord = new Chord();
        if (chord.buildMajorTriadChord(randomNumber).contains(randomNote)) {
            System.out.println("\nCorrect! What's another note in " + randomNote + " major chord?");
            anotherRandomNote = scanner.nextLine();
            if (chord.buildMajorTriadChord(randomNumber).contains(randomNote)
                    && !anotherRandomNote.equalsIgnoreCase(randomNote)) {
                System.out.println("\nCorrect! You get one point.");
                character.earnPoint();
            } else if (anotherRandomNote.equalsIgnoreCase(randomNote)) {
                System.out.println("You already entered this note before. That's cheating! No points earned.");
            }
        } else {
            System.out.println("You failed the quiz.");
        }
    }

    // EFFECTS: tests the user w/ knowledge of a randomly generated minor chord
    //          Users will earn a point if they enter two of the three notes in the chord asked
    //          If the 2nd note is the same one as the 1st note, the program perceives it as cheating, thus failing
    //          If the user fails on the quiz, no points will be earned
    //          Since it's for a minor chord, the correct answers scanned must be in lowercase, letters from a-g#
    public void randomMinorChordQuizMaker() {
        randomNumber = rd.nextInt(MAX_NOTE_NUMBERS);
        System.out.println("\nList one of the notes that belongs to "
                + note.getNoteForMinor(randomNumber) + " minor chord.");
        System.out.println("(Please note that you automatically fail with one mistake. "
                + "Remember this program is case sensitive!)");
        randomNote = scanner.nextLine();
        chord = new Chord();
        if (chord.buildMinorTriadChord(randomNumber).contains(randomNote)) {
            System.out.println("\nCorrect! What's another note in " + randomNote + " minor chord?");
            anotherRandomNote = scanner.nextLine();
            if (chord.buildMinorTriadChord(randomNumber).contains(randomNote)
                    && !anotherRandomNote.equalsIgnoreCase(randomNote)) {
                System.out.println("\nCorrect! You get one point.");
                character.earnPoint();
            } else if (anotherRandomNote.equalsIgnoreCase(randomNote)) {
                System.out.println("You already entered this note before. That's cheating! No points earned.");
            }
        } else {
            System.out.println("You failed the quiz.");
        }
    }

    // EFFECTS: lets the user to view the list of chords they put in the 'list to memorize' by selecting 1
    //          User can also add chords to the list by selecting 2
    //          Only valid chords can be added to the list
    //          If an invalid chord is entered, the program will print out a message w/o adding anything to the list
    public void editTheListOfChordsToMemorize() {
        System.out.println(editTheList);
        String activity = scanner.nextLine();
        if (activity.equals("1")) {
            System.out.println("\nHere is the list of chords to memorize:");
            System.out.println(chordsToMemorize.getMaterialsToMemorize());
        } else if (activity.equals("2")) {
            System.out.println("\nPlease enter the chord you wish to add to the list to memorize.");
            String noteEntered = scanner.nextLine();
            if ((0 <= note.getNoteForMajor(noteEntered) && note.getNoteForMajor(noteEntered) < MAX_NOTE_NUMBERS)
                    || ((0 <= note.getNoteForMinor(noteEntered)
                    && note.getNoteForMinor(noteEntered) < MAX_NOTE_NUMBERS))) {
                chordsToMemorize.addChord(noteEntered);
                System.out.println("The chord has been added to the list!");
            } else {
                System.out.println("Invalid chord. Going back to the main menu.");
            }
        } else {
            System.out.println("Invalid number. Going back to the main menu.");
        }
    }

    // Citation: this method has been copied (and then modified) from JsonSerializationDemo
    // EFFECTS: saves the current state to file
    public void saveTheState() {
        try {
            jsonWriter.open();
            jsonWriter.write(chordsToMemorize);
            jsonWriter.close();
            System.out.println("Saved " + characterName + "'s progress to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Citation: this method has been copied (and then modified) from JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads a state from file
    public void loadTheState() {
        try {
            chordsToMemorize = jsonReader.read();
            System.out.println("Loaded " + characterName + "'s progress from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
