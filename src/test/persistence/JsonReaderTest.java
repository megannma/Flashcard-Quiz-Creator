package persistence;

import model.FlashCard;
import model.Quiz;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Quiz q = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyQuiz.json");
        try {
            Quiz q = reader.read();
            assertEquals(0, q.getMainQuizSize());
            assertEquals(0, q.getQuizFlaggedSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralQuiz.json");
        try {
            Quiz q = reader.read();
            List<FlashCard>  mainQuiz = q.getMainQuiz();
            List<FlashCard> flaggedQuiz = q.getFlaggedQuiz();
            assertEquals(2, mainQuiz.size());
            assertEquals(1, flaggedQuiz.size());
            checkFlashCard("Test Q1", "Test A1", false, mainQuiz.get(0));
            checkFlashCard("Test Q2", "Test A2", true, mainQuiz.get(1));
            checkFlashCard("Test Q2", "Test A2", true, flaggedQuiz.get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
