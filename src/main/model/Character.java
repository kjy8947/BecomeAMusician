package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a character with its points
 */
public class Character implements Writable {
    private int points;

    // EFFECTS: creates a character w/ zero points earned
    public Character() {
        points = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds a point to the points already earned
    public void earnPoint() {
        points += 1;
    }

    // MODIFIES: this
    // EFFECTS: subtracts a point from the points earned so far
    public void losePoint() {
        if (0 < points) {
            points -= 1;
        }
    }

    // EFFECTS: returns the points earned so far
    public int getPoints() {
        return points;
    }

    // MODIFIES: this
    // EFFECTS: sets the user's (their character's) points
    public void setPoints(int points) {
        this.points = points;
    }


    @Override
    // CITATION: this method has been copied (and then modified) from JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("character's points", getPoints());
        return json;
    }
}
