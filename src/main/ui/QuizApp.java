package ui;

import model.FlashCard;
import model.Quiz;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

//Quiz Application
//Disclaimer: I am citing TellerApp because I have referenced it to design the UI, such as the constructor QuizApp()
//            and the methods runQuizApp(), setUp(), doCommand(), and displayCommands().

public class QuizApp {
    private static final String JSON_STORE = "./data/quiz.json";
    private Quiz userQuiz;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the quiz application
    public QuizApp() throws FileNotFoundException {
        runQuizApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runQuizApp() {
        boolean run = true;
        String command;

        setUp();

        System.out.println("Welcome to the Quiz Application, where you can create your own quiz of flash cards!");

        while (run) {
            displayCommands();
            command = input.next();

            if (command.equals("9")) {
                run = false;
            } else {
                doCommand(command);
            }
        }
        System.out.println("Successfully ended.");
    }

    //MODIFIES: this
    //EFFECTS: sets up quiz and input
    private void setUp() {
        userQuiz = new Quiz();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: takes command from user if valid
    private void doCommand(String command) {
        if (command.equals("1")) {
            doAddFlashCard();
        } else if (command.equals("2")) {
            doDeleteFlashCard();
        } else if (command.equals("3")) {
            doViewAllFlashCards();
        } else if (command.equals("4")) {
            doViewAllFlaggedFlashCards();
        } else if (command.equals("5")) {
            runThroughQuiz();
        } else if (command.equals("6")) {
            runThroughFlaggedQuiz();
        } else if (command.equals("7")) {
            saveQuiz();
        } else if (command.equals("8")) {
            loadQuiz();
        } else {
            System.out.println("Invalid command.");
        }
    }

    //EFFECTS: displays list of command options to user
    private void displayCommands() {
        System.out.println("\nInput the number that corresponds to your action:");
        System.out.println("\t1: Add a flash card");
        System.out.println("\t2: Delete a flash card");
        System.out.println("\t3: View all flash cards");
        System.out.println("\t4: View all flagged flash cards");
        System.out.println("\t5: Run through quiz with flash cards");
        System.out.println("\t6: Run through quiz with flagged flash cards");
        System.out.println("\t7: Load quiz to file.");
        System.out.println("\t8: Load quiz from file.");
        System.out.println("\t9: Quit application");
    }

    //MODIFIES: this
    //EFFECTS: adds a flash card to the quiz; if flagged, adds it to the flagged flash cards quiz
    private void doAddFlashCard() {
        System.out.println("Enter the question for your flash card:");
        String question = input.next();
        System.out.println("Enter the answer for your flash card:");
        String answer = input.next();
        System.out.println("Do you want to flag your flash card? Enter \"true\" if yes:");
        String flag = input.next();
        boolean hasFlag = Boolean.parseBoolean(flag);
        //Method used to convert user input to boolean is from
        //https://www.tutorialspoint.com/java-program-to-convert-string-to-boolean

        FlashCard flashCard = new FlashCard(question, answer, hasFlag);

        userQuiz.addFlashCard(flashCard);
        System.out.println("Added!");
    }

    //REQUIRES: user does not input index larger than actual index
    //MODIFIES: this
    //EFFECTS: deletes a flash card from the quiz; if flagged, deletes it from the flagged flash cards quiz
    private void doDeleteFlashCard() {
        System.out.println("Enter the index of the flash card you want to delete:");
        int i = input.nextInt();

        FlashCard flashCard = userQuiz.getFlashCard(i);

        userQuiz.deleteFlashCard(flashCard);
        System.out.println("Deleted!");
    }

    //EFFECTS: displays all flash cards
    private void doViewAllFlashCards() {
        System.out.println("---ALL FLASH CARDS---");
        for (int i = 0; i < userQuiz.getMainQuizSize(); i++) {
            System.out.println("\t" + userQuiz.viewFlashCards().get(i));
        }
        System.out.println("----------");
    }

    //EFFECTS: displays all flagged flash cards
    private void doViewAllFlaggedFlashCards() {
        System.out.println("---ALL FLAGGED FLASH CARDS---");
        for (int i = 0; i < userQuiz.getQuizFlaggedSize(); i++) {
            System.out.println("\t" + userQuiz.viewFlaggedFlashCards().get(i));
        }
        System.out.println("----------");
    }

    //MODIFIES: this
    //EFFECTS: displays each flash card question one by one in a random order,
    //         and displays answer when user prompts it to do so
    private void runThroughQuiz() {
        System.out.println("Quiz starts now! Enter your answer or anything else to display the correct answer,"
                + "and the next question will be displayed after.");
        Collections.shuffle(userQuiz.getMainQuiz());
        //Method used for randomizing the flash cards is from
        //https://www.tutorialspoint.com/shuffle-elements-of-arraylist-with-java-collections
        for (int i = 0; i < userQuiz.getMainQuizSize(); i++) {
            System.out.println("Question: " + userQuiz.getFlashCard(i).getQuestion());
            input.next();
            System.out.println("Correct Answer: " + userQuiz.getFlashCard(i).getAnswer());
        }
        System.out.println("Quiz ended!");
    }

    //MODIFIES: this
    //EFFECTS: displays each flagged flash card question one by one in a random order,
    //         and displays answer when user prompts it to do so
    private void runThroughFlaggedQuiz() {
        System.out.println("Quiz starts now! Enter your answer or anything else to display the correct answer,"
                + "and the next question will be displayed after.");
        Collections.shuffle(userQuiz.getFlaggedQuiz());
        //Method used for randomizing the flash cards is from
        //https://www.tutorialspoint.com/shuffle-elements-of-arraylist-with-java-collections
        for (int i = 0; i < userQuiz.getQuizFlaggedSize(); i++) {
            System.out.println("Question: " + userQuiz.getFlaggedFlashCard(i).getQuestion());
            input.next();
            System.out.println("Correct Answer: " + userQuiz.getFlaggedFlashCard(i).getAnswer());
        }
        System.out.println("Quiz ended!");
    }

    //EFFECTS: saves the quiz to file
    //Referenced method saveWorkRoom() from WorkRoomApp in JsonSerializationDemo at
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void saveQuiz() {
        try {
            jsonWriter.open();
            jsonWriter.write(userQuiz);
            jsonWriter.close();
            System.out.println("Saved quiz to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads quiz from file
    //Referenced method loadWorkRoom() from WorkRoomApp in JsonSerializationDemo at
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void loadQuiz() {
        try {
            userQuiz = jsonReader.read();
            System.out.println("Loaded quiz from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
