package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {
    private FlashCard testCard1;
    private FlashCard testCard2;
    private FlashCard testCard3;
    private FlashCard testCard4;
    private Quiz testQuizAll;
    private List<FlashCard> testQuiz;
    private List<FlashCard> testQuizFlagged;

    @BeforeEach
    void runBefore() {
        testCard1 = new FlashCard("1+1","2",false);
        testCard2 = new FlashCard("3*5","15",true);
        testCard3 = new FlashCard("4^2","16",false);
        testCard4 = new FlashCard("2-1", "1", true);
        testQuiz = new ArrayList<>();
        testQuizFlagged = new ArrayList<>();
        testQuizAll = new Quiz(testQuiz, testQuizFlagged);
        testQuizAll.addFlashCard(testCard1);
        testQuizAll.addFlashCard(testCard2);
    }

    @Test
    void testConstructor() {
        assertEquals(testCard1, testQuiz.get(0));
        assertEquals(testCard2, testQuizFlagged.get(0));
        assertEquals(2, testQuiz.size());
        assertEquals(1, testQuizFlagged.size());
        assertEquals(2, testQuizAll.getQuiz().size());
        assertEquals(1, testQuizAll.getQuizFlagged().size());
    }

    @Test
    void testAddFlashCard() {
        testQuizAll.addFlashCard(testCard3);
        testQuizAll.addFlashCard(testCard4);

        assertEquals(4, testQuizAll.getQuizSize());
        assertEquals(2, testQuizAll.getQuizFlaggedSize());

        assertEquals(testCard1, testQuizAll.getFlashCard(0));
        assertEquals(testCard2, testQuizAll.getFlashCard(1));
        assertEquals(testCard3, testQuizAll.getFlashCard(2));
        assertEquals(testCard4, testQuizAll.getFlashCard(3));
        assertEquals(testCard2, testQuizAll.getFlaggedFlashCard(0));
        assertEquals(testCard4, testQuizAll.getFlaggedFlashCard(1));
    }

    @Test
    void testDeleteFlashCard() {
        testQuizAll.addFlashCard(testCard3);
        testQuizAll.addFlashCard(testCard4);
        testQuizAll.deleteFlashCard(testCard1);
        testQuizAll.deleteFlashCard(testCard2);

        assertEquals(2, testQuizAll.getQuizSize());
        assertEquals(1, testQuizAll.getQuizFlaggedSize());

        assertEquals(testCard3, testQuiz.get(0));
        assertEquals(testCard4, testQuiz.get(1));
        assertEquals(testCard4, testQuizFlagged.get(0));
    }

    @Test
    void testViewFlashCards() {
        assertEquals("0: Question: \"1+1\"\n\t   Answer:   \"2\"\n\t   Flagged:  false",
                testQuizAll.viewFlashCards().get(0));
        assertEquals("1: Question: \"3*5\"\n\t   Answer:   \"15\"\n\t   Flagged:  true",
                testQuizAll.viewFlashCards().get(1));
    }

    @Test
    void testViewFlaggedFlashCards() {
        assertEquals("0: Question: \"3*5\"\n\t   Answer:   \"15\"\n\t   Flagged:  true",
                testQuizAll.viewFlaggedFlashCards().get(0));
    }

}