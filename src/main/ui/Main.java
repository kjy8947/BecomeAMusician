package ui;

import java.io.FileNotFoundException;

/*
 * Start the program
 * CITATION: the structure of this Main class has been copied from JsonSerializationDemo
 */
public class Main {
    public static void main(String[] args) {
        try {
            new BecomeAMusician();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}