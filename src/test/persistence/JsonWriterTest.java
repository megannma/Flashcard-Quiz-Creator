package persistence;

import model.FlashCard;
import model.Quiz;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Referenced JsonWriterTest class in JsonSerializationDemo at
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Quiz q = new Quiz();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyQuiz() {
        try {
            Quiz q = new Quiz();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyQuiz.json");
            writer.open();
            writer.write(q);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyQuiz.json");
            q = reader.read();
            assertEquals(0, q.getMainQuizSize());
            assertEquals(0, q.getQuizFlaggedSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralQuiz() {
        try {
            Quiz q = new Quiz();
            q.addFlashCard(new FlashCard("Test Q1", "Test A1", false));
            q.addFlashCard(new FlashCard("Test Q2", "Test A2", true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralQuiz.json");
            writer.open();
            writer.write(q);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralQuiz.json");
            q = reader.read();
            List<FlashCard> mainQuiz = q.getMainQuiz();
            List<FlashCard> flaggedQuiz = q.getFlaggedQuiz();
            assertEquals(2, mainQuiz.size());
            assertEquals(1, q.getQuizFlaggedSize());
            checkFlashCard("Test Q1", "Test A1", false, mainQuiz.get(0));
            checkFlashCard("Test Q2", "Test A2", true, mainQuiz.get(1));
            checkFlashCard("Test Q2", "Test A2", true, flaggedQuiz.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
