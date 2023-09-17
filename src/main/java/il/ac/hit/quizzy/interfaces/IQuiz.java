/**
 * The IQuiz interface defines the contract for representing a quiz in the Quizzy application.
 * It includes methods for starting the quiz, setting its name, adding questions, getting questions,
 * and retrieving the quiz type.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.interfaces;

import il.ac.hit.quizzy.enums.QuizType;

import java.util.List;

public interface IQuiz {
    /**
     * Starts the quiz, allowing the user to participate.
     */
    void start();

    /**
     * Sets the name of the quiz.
     *
     * @param text The name to set.
     */
    void setName(String text);

    /**
     * Gets the name of the quiz.
     *
     * @return The quiz name.
     */
    String getName();

    /**
     * Adds a question to the quiz.
     *
     * @param question The question to add.
     */
    void addQuestion(IQuizQuestion question);

    /**
     * Gets the list of questions in the quiz.
     *
     * @return The list of quiz questions.
     */
    List<IQuizQuestion> getQuestions();

    /**
     * Gets the type of the quiz, such as TERMINAL or GUI.
     *
     * @return The quiz type.
     */
    QuizType getQuizType();
}
