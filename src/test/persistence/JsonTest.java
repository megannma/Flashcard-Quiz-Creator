package persistence;

import model.FlashCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Referenced JsonTest class in JsonSerializationDemo at
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkFlashCard(String question, String answer, boolean isFlagged, FlashCard flashCard) {
        assertEquals(question, flashCard.getQuestion());
        assertEquals(answer, flashCard.getAnswer());
        assertEquals(isFlagged, flashCard.hasFlag());
    }
}
