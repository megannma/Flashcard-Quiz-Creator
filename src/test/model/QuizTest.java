package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {
    private FlashCard testCard1;
    private FlashCard testCard2;
    private FlashCard testCard3;
    private Quiz testQuiz;
    private Quiz testQuizFlagged;

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
        testQuiz = new Quiz();
        testQuiz.addFlashCard(testCard1);
        testQuiz.addFlashCard(testCard2);
        testQuizFlagged = new Quiz();
    }

    @Test
    void testAddFlashCard() {
        testQuiz.addFlashCard(testCard3);
        assertEquals(3, testQuiz.getQuizSize());
    }

    @Test
    void testDeleteFlashCard() {
        testQuiz.deleteFlashCard(testCard3);
        assertEquals(2, testQuiz.getQuizSize());
    }

    @Test
    void testAddFlaggedFlashCard() {
        testQuizFlagged.addFlaggedFlashCard(testCard1);
        testQuizFlagged.addFlaggedFlashCard(testCard2);
        testQuizFlagged.addFlaggedFlashCard(testCard3);
        assertEquals(1, testQuizFlagged.getFlaggedQuizSize());
    }

    @Test
    void testAddNotFlaggedFlashCard() {
        testQuizFlagged.addFlaggedFlashCard(testCard2);
        testQuizFlagged.addFlaggedFlashCard(testCard3);
        assertEquals(0, testQuizFlagged.getFlaggedQuizSize());
    }
}