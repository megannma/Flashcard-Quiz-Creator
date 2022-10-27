package persistence;

import model.FlashCard;

import static org.junit.Assert.assertEquals;

public class JsonTest {
    protected void checkFlashCard(String question, String answer, boolean isFlagged, FlashCard flashCard) {
        assertEquals(question, flashCard.getQuestion());
        assertEquals(answer, flashCard.getAnswer());
        assertEquals(isFlagged, flashCard.hasFlag());
    }
}
