package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashCardTest {
    private FlashCard testCard1;
    private FlashCard testCard2;
    private FlashCard testCard3;

    @BeforeEach
    void runBefore() {
        testCard1 = new FlashCard("A software design method that models the characteristics of abstract or" +
                " real objects using classes and objects.",
                "object-oriented design",
                true);
        testCard2 = new FlashCard("The numbering system that uses 16 as its base.",
                "hexadecimal",
                false);
        testCard3 = new FlashCard("A collection of data items, all of the same type, in which each item's " +
                "position is uniquely designated by an integer.",
                "array",
                false);
    }

    @Test
    void testConstructor() {
        assertEquals("The numbering system that uses 16 as its base.", testCard2.getQuestion());
        assertEquals("hexadecimal", testCard2.getAnswer());
        assertFalse(testCard2.getIsFlagged());
    }

    @Test
    void testRemoveFlagFromFlashCard() {
        testCard1.flagFlashCard();
        assertFalse(testCard1.getIsFlagged());
    }

    @Test
    void testFlagFlashCard() {
        testCard3.flagFlashCard();
        assertTrue(testCard3.getIsFlagged());
    }
}
