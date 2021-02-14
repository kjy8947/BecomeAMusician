//package model;
//
//import model.Note;
//import model.Character;
//
//import java.util.Random;
//import java.util.Scanner; //move to UI?
//
//public class Quiz {
//    public static final int NOTE_NUMBERS = 12;
//
//    private int randomNumber;
//    private String randomNote;
//    private String anotherRandomNote;
//    private Random rd = new Random();
//    private Scanner scanner;
//    private Note note = new Note();
//    private Chord chord;
//    private Character character;
//    private int point;
//
//    public Quiz() {
//        scanner = new Scanner(System.in);
//        point = 0;
//        character = new Character();
//    }
//
//    public void randomMajorChordQuizMaker() {
//        randomNumber = rd.nextInt(NOTE_NUMBERS);
//        System.out.println("List one of the notes that belongs to "
//                + note.getNoteForMajor(randomNumber) + " major chord.");
//        System.out.println("(Please note that you automatically fail with one mistake.)");
//        randomNote = scanner.nextLine();
//        chord = new Chord();
//        if (chord.buildMajorTriadChord(randomNumber).contains(randomNote)) {
//            System.out.println("Correct! What's another note in " + randomNote + " major chord?");
//            anotherRandomNote = scanner.nextLine();
//            // check if two randomNote is the same.
//            if (chord.buildMajorTriadChord(randomNumber).contains(randomNote)
//                    && !anotherRandomNote.equalsIgnoreCase(randomNote)) {
//                System.out.println("Correct! You get one point.");
//                character.earnPoint();
//            }
//        } else {
//            System.out.println("You failed the quiz.");
//        }
//    }
//
//    public void randomMinorChordQuizMaker() {
//        randomNumber = rd.nextInt(NOTE_NUMBERS);
//        System.out.println("List one of the notes that belongs to "
//                + note.getNoteForMinor(randomNumber) + " minor chord.");
//        System.out.println("(Please note that you automatically fail with one mistake.)");
//        randomNote = scanner.nextLine();
//        chord = new Chord();
//        if (chord.buildMinorTriadChord(randomNumber).contains(randomNote)) {
//            System.out.println("Correct! What's another note in " + randomNote + " minor chord?");
//            anotherRandomNote = scanner.nextLine();
//            // check if two randomNote is the same.
//            if (chord.buildMinorTriadChord(randomNumber).contains(randomNote)
//                    && !anotherRandomNote.equalsIgnoreCase(randomNote)) {
//                System.out.println("Correct! You get one point.");
//                character.earnPoint();
//            }
//        } else {
//            System.out.println("You failed the quiz.");
//        }
//    }
//}
