package model;

/*
 * Represents a character
 */
public class Character {
    private int points;

    // EFFECTS: creates a character w/ zero points earned
    public Character() {
        points = 0;
    }

    // MODIFIES: points
    // EFFECTS: adds a point to the points already earned
    public void earnPoint() {
        points += 1;
    }

    // EFFECTS: returns the points earned so far
    public int getPoints() {
        return points;
    }
}
