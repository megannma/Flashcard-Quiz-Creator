package model;

//Represents a flash card having a question, answer, and flag status
public class FlashCard {
    private String question;
    private String answer;
    private boolean isFlagged;

    // EFFECTS: question is set to user given question
    //          answer is set to user given answer
    //          isFlagged is set to true or false depending on if user chooses to flag the flash card
    public FlashCard(String givenQuestion, String givenAnswer, boolean hasBeenFlagged) {
        question = givenQuestion;
        answer = givenAnswer;
        isFlagged = hasBeenFlagged;
    }

    // EFFECTS: returns a flash card as a string, in a format that is easier for users to read
    public String flashCardToString() {
        return "Question: " + "\"" + question + "\"\n"
                + "\t   Answer:   " + "\"" + answer + "\"\n"
                + "\t   Flagged:  " + isFlagged;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean hasFlag() {
        return isFlagged;
    }
}
