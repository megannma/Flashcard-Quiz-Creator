package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashCardTest {
    private FlashCard testCard1;
    private FlashCard testCard2;

    @BeforeEach
    void runBefore() {
        testCard1 = new FlashCard("1+1","2",false);
        testCard2 = new FlashCard("3*5", "15",true);
    }

    @Test
    void testConstructor() {
        assertEquals("1+1", testCard1.getQuestion());
        assertEquals("2", testCard1.getAnswer());
        assertFalse(testCard1.hasFlag());
        assertEquals("3*5", testCard2.getQuestion());
        assertEquals("15", testCard2.getAnswer());
        assertTrue(testCard2.hasFlag());
    }

    @Test
    void testFlashCardToString() {
        assertTrue(testCard1.flashCardToString().contains("Question: \"1+1\"\n\t   " +
                "Answer:   \"2\"\n\t   Flagged:  false"));
    }
}
