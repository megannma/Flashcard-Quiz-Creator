package ui;

import java.io.FileNotFoundException;

//Referenced Main class in JsonSerializationDemo at
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class Main {
    public static void main(String[] args) {
        try {
            new QuizAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
