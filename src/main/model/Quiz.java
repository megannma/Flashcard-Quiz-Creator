package model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final List<FlashCard> quiz;
    private final List<FlashCard> quizFlagged;

    public Quiz(List<FlashCard> newQuiz, List<FlashCard> newQuizFlagged) {
        quiz = newQuiz;
        quizFlagged = newQuizFlagged;
    }

    // MODIFIES: this
    // EFFECTS: adds a flash card to the quiz, and adds it to the flagged quiz if it is flagged
    public void addFlashCard(FlashCard f) {
        if (f.getIsFlagged()) {
            quizFlagged.add(f);
        }
        quiz.add(f);
    }

    // REQUIRES: quiz is not empty
    // MODIFIES: this
    // EFFECTS: deletes a flash card from the quiz, and deletes it from the flagged quiz if it is flagged
    public void deleteFlashCard(FlashCard f) {
        if (f.getIsFlagged()) {
            quizFlagged.remove(f);
        }
        quiz.remove(f);
    }

    // REQUIRES: quiz contains at least one flash card
    // EFFECTS: returns a list of all flash cards, in order
    public List<String> viewFlashCards() {
        List<String> allFlashCards = new ArrayList<>();
        for (int i = 0; i < quiz.size(); i++) {
            String s = i + ": " + quiz.get(i).flashCardToString();
            allFlashCards.add(s);
        }
        return allFlashCards;
    }

    // REQUIRES: user has flagged at least one flash card
    // EFFECTS: returns a list of all flagged flash cards, in order
    public List<String> viewFlaggedFlashCards() {
        List<String> allFlaggedFlashCards = new ArrayList<>();
        for (int i = 0; i < quizFlagged.size(); i++) {
            String s = i + ": " + quizFlagged.get(i).flashCardToString();
            allFlaggedFlashCards.add(s);
        }
        return allFlaggedFlashCards;
    }

    public List<FlashCard> getQuiz() {
        return quiz;
    }

    public List<FlashCard> getQuizFlagged() {
        return quizFlagged;
    }

    public int getQuizSize() {
        return quiz.size();
    }

    public int getQuizFlaggedSize() {
        return quizFlagged.size();
    }

    public FlashCard getFlashCard(int i) {
        return quiz.get(i);
    }

    public FlashCard getFlaggedFlashCard(int i) {
        return quizFlagged.get(i);
    }
}
