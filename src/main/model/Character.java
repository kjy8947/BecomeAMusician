package model;

public class Character {
    private int points;
//    private int level;
//    private String name;

    public Character() {
        points = 0;
    }

//    public String getCharacterName() {
//        return name;
//    }

    public void earnPoint() {
        points += 1;
    }

    public int getPoints() {
        return points;
    }
}
