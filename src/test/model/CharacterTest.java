package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private Character character;

    @BeforeEach
    public void runBefore() {
        character = new Character();
    }

    @Test
    public void testEarnPoint() {
        assertEquals(0, character.getPoints());
        character.earnPoint();
        assertEquals(1, character.getPoints());
        character.earnPoint();
        assertEquals(2, character.getPoints());
    }

    @Test
    public void testLosePoint() {
        assertEquals(0, character.getPoints());
        character.losePoint();
        assertEquals(0, character.getPoints());
        character.earnPoint();
        assertEquals(1, character.getPoints());
        character.losePoint();
        assertEquals(0, character.getPoints());
    }

    @Test
    public void testGetPoints() {
        assertTrue(character.getPoints() == 0);
    }

    @Test
    public void testSetPoints() {
        assertEquals(0, character.getPoints());
        character.setPoints(50);
        assertEquals(50, character.getPoints());
        character.setPoints(5);
        assertEquals(5, character.getPoints());
    }

    @Test
    public void testToJson() {
        character.setPoints(15);

        assertEquals(character.toJson().toString(),
                "{\"character's points\":15}");
    }
}