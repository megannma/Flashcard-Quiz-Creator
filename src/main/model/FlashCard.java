package model;

public class FlashCard {
    private String question;
    private String answer;
    private boolean isFlagged;

    // REQUIRES: question and answer must not be null
    // MODIFIES: nothing
    // EFFECTS: question is set to user given question
    //          answer is set to user given answer
    //          isFlagged is set to true or false depending on if user chooses to flag the flash card
    public FlashCard(String givenQuestion, String givenAnswer, boolean hasBeenFlagged) {
        question = givenQuestion;
        answer = givenAnswer;
        isFlagged = hasBeenFlagged;
    }

    // REQUIRES: nothing
    // MODIFIES: this
    // EFFECTS: adds or removes a flag from a flash card
    public void flagFlashCard() {
        isFlagged = !isFlagged;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean getIsFlagged() {
        return isFlagged;
    }
}
