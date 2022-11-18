package ui;

import model.FlashCard;
import model.Quiz;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//Quiz Application GUI
//Referenced IntersectionGUI in C3-LectureLabStarter
//Referenced https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html for swing components
public class QuizAppGUI extends JFrame {
    private Quiz quiz;
    private static final String JSON_STORE = "./data/quiz.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ImageIcon saveSuccess;
    private ImageIcon saveFail;
    private ImageIcon loadSuccess;
    private ImageIcon loadFail;
    private ImageIcon added;
    private ImageIcon deleted;
    private String[] commands = {"Select", "Add flash card", "Delete flash card",
            "View all flash cards", "View all flagged flash cards"};

    // EFFECTS: runs the quiz application
    public QuizAppGUI() throws FileNotFoundException {
        super("Quiz App UI");
        setLayout(new GridLayout(3, 1));
        setPreferredSize(new Dimension(800, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createQuizApp();
        createCommandMenu();
        addMainScreenButtons();
        pack();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets up quiz, jsonWriter, and jsonReader
    private void createQuizApp() {
        quiz = new Quiz();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: saves the quiz to file
    //Referenced method saveWorkRoom() from WorkRoomApp in JsonSerializationDemo at
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void saveQuiz() {
        try {
            jsonWriter.open();
            jsonWriter.write(quiz);
            jsonWriter.close();
            JFrame frame = new JFrame("Saved quiz to " + JSON_STORE);
            frame.setPreferredSize(new Dimension(800,400));
            loadImages();
            frame.add(new JLabel(saveSuccess));
            frame.pack();
            frame.setVisible(true);
        } catch (FileNotFoundException e) {
            JFrame confirmNotSaved = new JFrame("Unable to write to file: " + JSON_STORE);
            loadImages();
            confirmNotSaved.add(new JLabel(saveFail));
            confirmNotSaved.pack();
            confirmNotSaved.setVisible(true);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads quiz from file
    //Referenced method loadWorkRoom() from WorkRoomApp in JsonSerializationDemo at
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void loadQuiz() {
        try {
            quiz = jsonReader.read();
            JFrame frame = new JFrame("Loaded quiz from  " + JSON_STORE);
            frame.setPreferredSize(new Dimension(800,400));
            loadImages();
            frame.add(new JLabel(loadSuccess));
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            JFrame confirmNotLoaded = new JFrame("Unable to read from file: " + JSON_STORE);
            loadImages();
            confirmNotLoaded.add(new JLabel(loadFail));
            confirmNotLoaded.pack();
            confirmNotLoaded.setVisible(true);
        }
    }

    //EFFECTS: initializes images used
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        saveSuccess = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "saveSuccess.jpeg");
        saveFail = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "saveFail.jpeg");
        loadSuccess = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "loadSuccess.jpeg");
        loadFail = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "loadFail.jpeg");
        added = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "added.jpeg");
        deleted = new ImageIcon(System.getProperty("user.dir") + sep
                + "data" + sep + "deleted.jpeg");
    }

    //MODIFIES: this
    //EFFECTS: adds a flash the quiz; if flagged, adds it to the flagged flash cards quiz
    private void doAddFlashCard() {
        JFrame addFrame = new JFrame("Add flash card");
        addFrame.setPreferredSize(new Dimension(800, 400));
        addFrame.setLayout(new GridLayout(4, 2));
        JTextField questionField = new JTextField(1);
        JTextField answerField = new JTextField(1);
        JCheckBox flag = new JCheckBox();
        JButton addButton = new JButton("Add");
        addButton.setActionCommand("add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quiz.addFlashCard(new FlashCard(questionField.getText(), answerField.getText(), flag.isSelected()));
                confirmAdd();
                addFrame.dispose();
            }
        });
        addAddComponents(addFrame, questionField, answerField, flag, addButton);
    }

    //EFFECTS: displays a popup window to confirm user has added a flash card
    private void confirmAdd() {
        JFrame frame = new JFrame("Added!");
        frame.setPreferredSize(new Dimension(800,400));
        loadImages();
        frame.add(new JLabel(added));
        frame.pack();
        frame.setVisible(true);
    }

    //REQUIRES: index is valid; in other words, 0 <= index < quiz.getMainQuizSize()
    //MODIFIES: this
    //EFFECTS: deletes a flash card from the quiz; if flagged, deletes it from the flagged flash cards quiz
    private void doDeleteFlashCard() {
        JFrame frame = new JFrame("Delete Flash Card");
        frame.setLayout(new GridLayout(4, 1));
        frame.setPreferredSize(new Dimension(800, 400));
        addTable(frame);
        frame.add(new JLabel("Enter the index of the flash card you want to delete (please enter a valid index):"));
        JTextField textField = new JTextField(1);
        frame.add(textField);
        frame.pack();
        JButton deleteButton = new JButton("Delete");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quiz.deleteFlashCard(quiz.getFlashCard(Integer.parseInt(textField.getText())));
                confirmDelete();
                frame.dispose();
            }
        });
        frame.add(deleteButton);
        frame.setVisible(true);
    }

    //EFFECTS: displays a popup window to confirm user has deleted a flash card
    private void confirmDelete() {
        JFrame frame = new JFrame("Deleted!");
        frame.setPreferredSize(new Dimension(800,400));
        loadImages();
        frame.add(new JLabel(deleted));
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: displays the table of flash cards on the screen
    private void doViewAllFlashCards() {
        JFrame frame = new JFrame("All Flash Cards");
        frame.setPreferredSize(new Dimension(800, 400));
        addTable(frame);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: creates a table with each flagged flash card and its information, then displays it to the screen
    private void doViewAllFlaggedFlashCards() {
        JFrame frame = new JFrame("All Flagged Flash Cards");
        frame.setPreferredSize(new Dimension(800, 400));
        String[] titles = {"Question", "Answer", "Flagged?"};
        Object[][] data = new Object[quiz.getQuizFlaggedSize()][3];
        for (int i = 0; i < quiz.getQuizFlaggedSize(); i++) {
            data[i][0] = quiz.getFlaggedFlashCard(i).getQuestion();
            data[i][1] = quiz.getFlaggedFlashCard(i).getAnswer();
            data[i][2] = quiz.getFlaggedFlashCard(i).hasFlag();
        }
        JTable table = new JTable(data, titles);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: creates the popup menu for actions related to flash cards, then adds it to the main window
    private void createCommandMenu() {
        final JComboBox commandCombo = new JComboBox(commands);
        commandCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = (String) commandCombo.getSelectedItem();
                if (command.equals("Add flash card")) {
                    doAddFlashCard();
                } else if (command.equals("Delete flash card")) {
                    doDeleteFlashCard();
                } else if (command.equals("View all flash cards")) {
                    doViewAllFlashCards();
                } else if (command.equals("View all flagged flash cards")) {
                    doViewAllFlaggedFlashCards();
                }
            }
        });
        add(commandCombo);
    }

    //MODIFIES: this
    //EFFECTS: displays the components of the "Add Flash Card" screen onto the screen
    private void addAddComponents(JFrame f, JTextField q, JTextField a, JCheckBox c, JButton b) {
        f.add(new JLabel("Question: "));
        f.add(q);
        f.add(new JLabel("Answer: "));
        f.add(a);
        f.add(new JLabel("Flag your flash card?"));
        f.add(c);
        f.add(b);
        f.pack();
        f.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates the save and load buttons, then adds them to the main window
    private void addMainScreenButtons() {
        JButton saveButton = new JButton("Save to file");
        saveButton.setActionCommand("save to file");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveQuiz();
            }
        });
        JButton loadButton = new JButton("Load from file");
        loadButton.setActionCommand("load from file");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadQuiz();
            }
        });
        add(saveButton);
        add(loadButton);
    }

    //MODIFIES: this
    //EFFECTS: creates a table with each flash card and its information, then displays it on the screen
    private void addTable(JFrame f) {
        String[] titles = {"Index", "Question", "Answer", "Flagged?"};
        Object[][] data = new Object[quiz.getMainQuizSize()][4];
        for (int i = 0; i < quiz.getMainQuizSize(); i++) {
            data[i][0] = i;
            data[i][1] = quiz.getFlashCard(i).getQuestion();
            data[i][2] = quiz.getFlashCard(i).getAnswer();
            data[i][3] = quiz.getFlashCard(i).hasFlag();
        }
        JTable table = new JTable(data, titles);
        JScrollPane scrollPane = new JScrollPane(table);
        f.add(scrollPane);
    }
}

