package model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<FlashCard> quiz = new ArrayList<>();
    private List<FlashCard> quizFlagged = new ArrayList<>();

    public Quiz() {

    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: adds a flash card to the quiz
    public void addFlashCard(FlashCard f) {
        quiz.add(f);
    }

    // REQUIRES: Quiz is not empty
    // MODIFIES: this
    // EFFECTS: deletes a flash card from the quiz
    public void deleteFlashCard(FlashCard f) {
        quiz.remove(f);
    }

    // REQUIRES: nothing
    // MODIFIES: quiz of flagged flash cards
    // EFFECTS: adds flash card to set of flagged flash cards
    public void addFlaggedFlashCard(FlashCard f) {
        if (f.getIsFlagged()) {
            quizFlagged.add(f);
        }
    }

    public int getQuizSize() {
        return quiz.size();
    }

    public int getFlaggedQuizSize() {
        return quizFlagged.size();
    }
}
