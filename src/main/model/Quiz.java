package model;

import java.util.ArrayList;
import java.util.List;

//Represents a quiz containing a main quiz of all flash cards, and a flagged quiz of all flagged flash cards
public class Quiz {
    private List<FlashCard> mainQuiz;
    private List<FlashCard> flaggedQuiz;

    //EFFECTS: mainQuiz is set to an empty list of flash cards
    //         flaggedQuiz is set to an empty list of flash cards
    public Quiz(List<FlashCard> newQuiz, List<FlashCard> newFlaggedQuiz) {
        mainQuiz = newQuiz;
        flaggedQuiz = newFlaggedQuiz;
    }

    //MODIFIES: this
    //EFFECTS: adds a flash card to the quiz, and adds it to the flagged quiz if it is flagged
    public void addFlashCard(FlashCard f) {
        if (f.hasFlag()) {
            flaggedQuiz.add(f);
        }
        mainQuiz.add(f);
    }

    //REQUIRES: quiz is not empty
    //MODIFIES: this
    //EFFECTS: deletes a flash card from the quiz, and deletes it from the flagged quiz if it is flagged
    public void deleteFlashCard(FlashCard f) {
        if (f.hasFlag()) {
            flaggedQuiz.remove(f);
        }
        mainQuiz.remove(f);
    }

    //REQUIRES: quiz contains at least one flash card
    //EFFECTS: returns a list of all flash cards as strings, in order
    public List<String> viewFlashCards() {
        List<String> allFlashCards = new ArrayList<>();
        for (int i = 0; i < mainQuiz.size(); i++) {
            String s = i + ": " + mainQuiz.get(i).flashCardToString();
            allFlashCards.add(s);
        }
        return allFlashCards;
    }

    //REQUIRES: user has flagged at least one flash card
    //EFFECTS: returns a list of all flagged flash cards as strings, in order
    public List<String> viewFlaggedFlashCards() {
        List<String> allFlaggedFlashCards = new ArrayList<>();
        for (int i = 0; i < flaggedQuiz.size(); i++) {
            String s = i + ": " + flaggedQuiz.get(i).flashCardToString();
            allFlaggedFlashCards.add(s);
        }
        return allFlaggedFlashCards;
    }

    public List<FlashCard> getMainQuiz() {
        return mainQuiz;
    }

    public List<FlashCard> getFlaggedQuiz() {
        return flaggedQuiz;
    }

    //EFFECTS: returns the flash card with the given index
    public FlashCard getFlashCard(int i) {
        return mainQuiz.get(i);
    }

    //EFFECTS: returns the flagged flash card with the given index
    public FlashCard getFlaggedFlashCard(int i) {
        return flaggedQuiz.get(i);
    }

    //EFFECTS: returns the size of the main quiz
    public int getMainQuizSize() {
        return mainQuiz.size();
    }

    //EFFECTS: returns the size of the flagged quiz
    public int getQuizFlaggedSize() {
        return flaggedQuiz.size();
    }
}
