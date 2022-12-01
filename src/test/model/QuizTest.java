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
    private Quiz testQuiz;
    private EventLog eventLog;
    private List<Event> events;

    @BeforeEach
    void runBefore() {
        eventLog = EventLog.getInstance();
        events = new ArrayList<Event>();

        testCard1 = new FlashCard("1+1","2",false);
        testCard2 = new FlashCard("3*5","15",true);
        testCard3 = new FlashCard("4^2","16",false);
        testCard4 = new FlashCard("2-1", "1", true);
        testQuiz = new Quiz();
        testQuiz.addFlashCard(testCard1);
        testQuiz.addFlashCard(testCard2);
        testQuiz.addFlashCard(testCard3);
        testQuiz.addFlashCard(testCard4);
        testQuiz.deleteFlashCard(testCard3);
        testQuiz.deleteFlashCard(testCard4);
    }

    @Test
    void testConstructor() {
        assertEquals(testCard1, testQuiz.getFlashCard(0));
        assertEquals(testCard2, testQuiz.getFlaggedFlashCard(0));
        assertEquals(2, testQuiz.getMainQuizSize());
        assertEquals(1,testQuiz.getQuizFlaggedSize());
        assertEquals(2, testQuiz.getMainQuiz().size());
        assertEquals(1, testQuiz.getFlaggedQuiz().size());
    }

    @Test
    void testAddFlashCard() {
        testQuiz.addFlashCard(testCard3);
        testQuiz.addFlashCard(testCard4);

        assertEquals(4, testQuiz.getMainQuizSize());
        assertEquals(2, testQuiz.getQuizFlaggedSize());

        assertEquals(testCard1, testQuiz.getFlashCard(0));
        assertEquals(testCard2, testQuiz.getFlashCard(1));
        assertEquals(testCard3, testQuiz.getFlashCard(2));
        assertEquals(testCard4, testQuiz.getFlashCard(3));
        assertEquals(testCard2, testQuiz.getFlaggedFlashCard(0));
        assertEquals(testCard4, testQuiz.getFlaggedFlashCard(1));
    }

    @Test
    void testDeleteFlashCard() {
        testQuiz.addFlashCard(testCard3);
        testQuiz.addFlashCard(testCard4);
        testQuiz.deleteFlashCard(testCard1);
        testQuiz.deleteFlashCard(testCard2);

        assertEquals(2, testQuiz.getMainQuizSize());
        assertEquals(1, testQuiz.getQuizFlaggedSize());

        assertEquals(testCard3, testQuiz.getFlashCard(0));
        assertEquals(testCard4, testQuiz.getFlashCard(1));
        assertEquals(testCard4, testQuiz.getFlaggedFlashCard(0));
    }

    @Test
    void testViewFlashCards() {
        assertEquals("0: Question: \"1+1\"\n\t   Answer:   \"2\"\n\t   Flagged:  false",
                testQuiz.viewFlashCards().get(0));
        assertEquals("1: Question: \"3*5\"\n\t   Answer:   \"15\"\n\t   Flagged:  true",
                testQuiz.viewFlashCards().get(1));
    }

    @Test
    void testViewFlaggedFlashCards() {
        assertEquals("0: Question: \"3*5\"\n\t   Answer:   \"15\"\n\t   Flagged:  true",
                testQuiz.viewFlaggedFlashCards().get(0));
    }

    @Test
    void testEventsLogged() {
        for (Event e : eventLog) {
            events.add(e);
        }
        assertEquals("New flash card with question: \"1+1\", answer: \"2\", flagged: \"false\" added to the quiz.",
                events.get(0).getDescription());
        assertEquals("New flash card with question: \"3*5\", answer: \"15\", flagged: \"true\" added to the quiz.",
                events.get(1).getDescription());
        assertEquals("New flash card with question: \"4^2\", answer: \"16\", flagged: \"false\" added to the quiz.",
                events.get(2).getDescription());
        assertEquals("New flash card with question: \"2-1\", answer: \"1\", flagged: \"true\" added to the quiz.",
                events.get(3).getDescription());
        assertEquals("Flash card with question: \"4^2\", answer: \"16\", flagged: \"false\" deleted from the quiz.",
                events.get(4).getDescription());
        assertEquals("Flash card with question: \"2-1\", answer: \"1\", flagged: \"true\" deleted from the quiz.",
                events.get(5).getDescription());
    }
}