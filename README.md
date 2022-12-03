# My Personal Project

## Project Proposal
This application will allow users to create a set of flash cards. Users may create a separate set of "flagged" flash
cards for more important flash cards. However, users can *also* delete flash cards. In addition, users will be able to
view all of their flash cards at once. When prompted to, the program will display questions one at a time, and users can
choose to display the answer at any time. The main users of this application will be students because they will be able
to create flash cards for studying. This project is of interest to me because as a student who is always working on
finding helpful study techniques, I have considered but have not yet tried flashcards and other methods that
improve memorization. Hence, this project gives me the opportunity to create a study method that works for me and also
strengthen my programming abilities in the process. I am interested in this project because I believe creating my own
questions will help me even more with remembering how to answer them, and other students may find this project helpful
as well.

## User Stories
* As a user, I want to be able to add a flash card to the quiz an arbitrary number of times.
* As a user, I want to be able to delete a flash card from the quiz.
* As a user, I want to be able to mark flash cards as "flagged" and have them in a separate quiz.
* As a user, I want to be able to view all of my flash cards and view all of my flagged flash cards in a list.
* As a user, I want to be able to display each question one by one and then view the answer when I am ready to do so.
* As a user, I want to be able to save my set of flash cards to file.
* As a user, I want to be able to load my set of flash cards from file.

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by selecting the popup menu item labelled "Add flash card",
  then entering text into the text fields for Question and Answer, checking the box displayed if you want to flag your flash card,
  and then clicking the button labelled "Add".
- You can generate the second required event related to adding Xs to a Y by selecting the popup menu item labelled "Delete flash card",
  entering the index corresponding to the flash card you want to delete, and then clicking the button labelled "Delete".
- You can locate my visual component by clicking the buttons labelled "Save to file," "Load from file," or by adding/deleting a flash card.
- You can save the state of my application by clicking the button labelled "Save to file".
- You can reload the state of my application by clicking the button labelled "Load from file".

## Phase 4: Task 2
Wed Nov 30 22:27:06 PST 2022
New flash card with question: "test flash card question 1", answer: "test flash card answer 1", flagged: "true" added to the quiz.

Wed Nov 30 22:27:26 PST 2022
New flash card with question: "test flash card question 2", answer: "test flash card answer 2", flagged: "true" added to the quiz.

Wed Nov 30 22:27:44 PST 2022
New flash card with question: "test flash card question 3", answer: "test flash card answer 3", flagged: "false" added to the quiz.

Wed Nov 30 22:27:53 PST 2022
Flash card with question: "test flash card question 2", answer: "test flash card answer 2", flagged: "true" deleted from the quiz.

## Phase 4: Task 3

- If I had more time to work on the project, I would refactor the Quiz class to minimize code duplication. It is currently
  organized in such a way that there are several methods that are duplicated, since I added different methods for the set of
  all flash cards and the set of flagged flash cards. Some of these methods have very similar behaviour to each other, such as
  the methods viewFlashCards() and viewFlaggedFlashCards(), so I would use the refactoring feature "extract method" to remove
  duplication.
- If I had more time to work on the project, I would refactor the QuizAppGUI class to increase cohesion. My GUI only has that
  one class so the code inside it is quite messy. For example, the methods I created do not necessarily have organized functionality,
  since my main focus at the time was ensuring each method was not too long. So, I would rewrite the methods in QuizAppGUI to be
  more organized and have a clearer effect. Then I would move some of these methods into another class, for example, a class
  called QuizGUI that handles GUI code related to Quiz.
- If I had more time to work on the project, I would refactor the Quiz class to use the Singleton Pattern. This application
  only has one instance of Quiz, as a new Quiz is created only when the application is opened. Hence, I think implementing
  the Singleton Pattern would be useful for ensuring that there is only one instance of Quiz since there is no reason for
  there to be more instances of Quiz.