package ui;

import model.*;

import java.lang.Character;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BecomeAMusician {
    public static final int MAX_NOTE_NUMBERS = 12;

    private ArrayList<String> activities = new ArrayList<>();
    private ArrayList<String> toStudy = new ArrayList<>();
    private ArrayList<Note> notes;
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

    public BecomeAMusician() {
        activities.add("1: Study chords/scales");
        activities.add("2: Take a quiz");
        activities.add("3: Arrange to-memorize & memorized lists");
        activities.add("4: Check your character's points");
        toStudy.add("chords");
        toStudy.add("scales");
        //activities.add("exam");
        notes = new ArrayList<>();
        scanner = new Scanner(System.in);
        characterName = "";

        instructions();
        createCharacter();
    }

    public void instructions() {
        System.out.println("Thank you for choosing our program to study music.");
        System.out.println("Please note that our developer is not the smartest person.");
        System.out.println("That is why this program does not understand a flat or double accidentals.");
        System.out.println("Kindly use an enharmonic equivalence of any flat notes. (eg. D sharp instead of E flat)");
        System.out.println("Also, please note that uppercase letter inputs are required for major chords/scales, "
                + "and lowercase letter inputs are required for minor chords/scales.\n");
    }

    // the method has been copied and then modified from B4 Lecture Lab
    public void createCharacter() {
        while (true) {
            while (!isValidName(characterName)) {
                System.out.print("Name the character (1-12 alphabetical letters only without spaces): ");
                characterName = scanner.nextLine();
                if (isValidName(characterName)) {
                    character = new model.Character();
                    System.out.println("\nYour character's name is: " + characterName);
                    System.out.println("\nWhat do you want " + characterName + " to do today? (Enter the number)");
                    System.out.println(activities);
                    chooseAnActivity();
                } else {
                    System.out.println("Invalid name. Please enter a valid name.");
                }
            }
        }
    }

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

    // TODO: either write a quiz for scales or update the user stories (that only chords available for quiz)
    public void chooseAnActivity() {
        String activity;
        String whatToStudy;

        while (true) {
            activity = scanner.nextLine();

            if (activity.equals("1")) {
                System.out.println("\nWhat do you want to study? Chords? Scales?");
                whatToStudy = scanner.nextLine();
                studyActivity(whatToStudy);
            } else if (activity.equals("2")) {
                System.out.println("\nLet's take a quiz and level up!\n\nFirst, a major chord.");
                randomMajorChordQuizMaker();
                System.out.println("\nNext, a minor chord.");
                randomMinorChordQuizMaker();
            } else if (activity.equals("3")) {
                System.out.println("");
            } else if (activity.equals("4")) {
                System.out.println("Your character has earned " + character.getPoints() + " point(s) so far.");
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            System.out.println("\nWhat next?\n" + activities);
        }
    }

//    public boolean isValidActivity(String activity) {
//        if (!activities.contains(activity)) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    // Use helpers.
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

    public void chordIsChosen() {
        //Chord chord;
        String chordToStudy;
        System.out.println("\nWhich chord do you want to study?");
        chordToStudy = scanner.nextLine();
        chord = new Chord(chordToStudy);
        if (chordToStudy == chordToStudy.toUpperCase()) {
            chord.studyMajorChord(chordToStudy);
        } else if (chordToStudy == chordToStudy.toLowerCase()) {
            chord.studyMinorChord(chordToStudy);
        }
    }

    public void scaleIsChosen() {
        //Scale scale;
        String scaleToStudy;
        System.out.println("\nWhich scale do you want to study?");
        scaleToStudy = scanner.nextLine();
        scale = new Scale(scaleToStudy);
        if (scaleToStudy == scaleToStudy.toUpperCase()) {
            scale.studyMajorScale(scaleToStudy);
        } else if (scaleToStudy == scaleToStudy.toLowerCase()) {
            scale.studyMinorScale(scaleToStudy);
        }
    }

    public void randomMajorChordQuizMaker() {
        //Chord chord;
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

    public void randomMinorChordQuizMaker() {
        //Chord chord;
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

    public static void main(String[] args) {
        new BecomeAMusician();
    }
}
