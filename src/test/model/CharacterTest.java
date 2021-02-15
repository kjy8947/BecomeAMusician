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
}