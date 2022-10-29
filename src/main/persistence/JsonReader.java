package persistence;

import model.FlashCard;
import model.Quiz;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a reader that reads quiz from JSON data stored in file
//Referenced JsonReader class in JsonSerializationDemo at
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads quiz from file and returns it; throws IOException if error occurs reading data from file
    public Quiz read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuiz(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses quiz from JSON object and returns it
    private Quiz parseQuiz(JSONObject jsonObject) {
        Quiz q = new Quiz();
        addQuiz(q, jsonObject);
        return q;
    }

    //MODIFIES: q
    //EFFECTS: parses list of flash cards from JSON object and adds them to quiz
    private void addQuiz(Quiz q, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("main quiz");
        for (Object json : jsonArray) {
            JSONObject nextFlashCard = (JSONObject) json;
            addFlashCard(q, nextFlashCard);
        }
    }

    //MODIFIES: q
    //EFFECTS: parses flash card from JSON object and adds it to quiz
    private void addFlashCard(Quiz q, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        boolean isFlagged = jsonObject.getBoolean("flagged");
        FlashCard flashCard = new FlashCard(question, answer, isFlagged);
        q.addFlashCard(flashCard);
    }
}
