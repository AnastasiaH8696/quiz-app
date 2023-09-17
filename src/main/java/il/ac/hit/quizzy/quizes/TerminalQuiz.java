/**
 * The TerminalQuiz class extends the UIQuiz class and represents a quiz that can be played in a terminal (command-line) environment.
 * It includes functionality for starting, playing, and ending the quiz, as well as managing the user's score.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.quizes;

import il.ac.hit.quizzy.QuizQuestion;
import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.interfaces.IQuizQuestion;
import il.ac.hit.quizzy.utils.QuizConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalQuiz extends UIQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score;

    /**
     * Constructs a new TerminalQuiz instance and sets its quizType to QuizType.TERMINAL.
     */
    public TerminalQuiz() {
        this.quizType = QuizType.TERMINAL;
    }

    /**
     * Starts the terminal quiz, plays it, and ends it by displaying the user's score.
     */
    @Override
    public void start() {
        System.out.println("Starting the terminal quiz");
        play();
        endTerminalQuiz();
    }

    /**
     * Ends the terminal quiz and displays the user's score.
     */
    private void endTerminalQuiz() {
        System.out.println("Quiz ended. Your score: " + getScore() + "/" +
                QuizConstants.POINTS_TO_ADD * questions.size()
        );
    }

    /**
     * Sets the name of the quiz. If the provided name is empty, it sets the default name.
     *
     * @param name The name to set.
     */
    @Override
    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("Warning: the quiz name was empty. Setting default name to: 'DEFAULT QUIZ'");
            this.name = QuizConstants.DEFAULT_QUIZ_NAME;
        } else {
            this.name = name;
        }
    }

    /**
     * Gets the name of the quiz.
     *
     * @return The quiz name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Adds a question to the quiz.
     *
     * @param question The question to add.
     */
    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }

    /**
     * Plays the terminal quiz by presenting questions and handling user input and scoring.
     */
    private void play() {
        System.out.println("Quiz: " + getName());
        for (IQuizQuestion question : questions) {
            boolean isValidChoose = false;
            int userAnswer = -1;
            int answersRange = question.getAnswers().size();
            System.out.println("Question: " + question.getQuestion());
            System.out.println("Answers:");
            printAnswers(question);

            Scanner scanner = new Scanner(System.in);

            while (!isValidChoose) {
                System.out.print("Enter your answer (1-" + answersRange + "): ");
                try {
                    userAnswer = scanner.nextInt();
                    scanner.nextLine();

                    if (question.isAnswerIndexCorrect(userAnswer)) {
                        isValidChoose = true;
                    } else {
                        System.out.println("Your answer is out of range. Please choose an answer from 1 to " + answersRange);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid type of answer. Please enter a valid number.");
                    scanner.nextLine();
                }
            }

            if (question.isAnswerCorrect(userAnswer - 1)) {
                System.out.println("Correct! +" + QuizConstants.POINTS_TO_ADD + " points!\n");
                setScore(getScore() + QuizConstants.POINTS_TO_ADD);
            } else {
                System.out.println("Incorrect.\n");
            }
        }
    }

    /**
     * Gets the user's score in the quiz.
     *
     * @return The user's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the user's score. Updates the score only if the provided score is positive.
     *
     * @param score The score to set.
     */
    public void setScore(int score) {
        // Updates the score only if the score is positive
        if (score > 0) {
            this.score = score;
        }
    }

    /**
     * Gets the list of questions in the quiz.
     *
     * @return The list of quiz questions.
     */
    public List<IQuizQuestion> getQuestions() {
        return questions;
    }

    /**
     * Gets the type of the quiz.
     *
     * @return The quiz type (e.g., TERMINAL or GUI).
     */
    @Override
    public QuizType getQuizType() {
        return this.quizType;
    }

    /**
     * Sets the list of questions in the quiz.
     *
     * @param questions The list of quiz questions to set.
     */
    public void setQuestions(List<IQuizQuestion> questions) {
        this.questions = questions;
    }

    /**
     * Overrides the toString() method to provide a string representation of the TerminalQuiz instance.
     *
     * @return A string representation of the TerminalQuiz.
     */
    @Override
    public String toString() {
        return "TerminalQuiz{" +
                "name='" + name + '\'' +
                ", questions=" + questions +
                ", score=" + score +
                '}';
    }

    /**
     * Helper method to print the answers for a quiz question.
     *
     * @param question The quiz question to print answers for.
     */
    private void printAnswers(IQuizQuestion question) {
        int answerIndex = 1;
        for (QuizQuestion.Answer answer : question.getAnswers()) {
            System.out.println(answerIndex + ". " + answer.getText());
            answerIndex++;
        }
    }
}
