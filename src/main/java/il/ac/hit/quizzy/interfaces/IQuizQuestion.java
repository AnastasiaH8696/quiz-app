/**
 * The IQuizQuestion interface defines the contract for representing a question in a quiz in the Quizzy application.
 * It includes methods for setting the title and question text, adding answers, retrieving answers, question text,
 * title, checking if a specific answer is correct, and validating answer indices.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.interfaces;

import il.ac.hit.quizzy.QuizQuestion;

import java.util.List;

public interface IQuizQuestion {
    /**
     * Sets the title of the question.
     *
     * @param title The title to set.
     */
    void setTitle(String title);

    /**
     * Sets the text of the question.
     *
     * @param question The question text to set.
     */
    void setQuestion(String question);

    /**
     * Adds an answer to the question.
     *
     * @param text    The text of the answer.
     * @param correct true if the answer is correct, false otherwise.
     */
    void addAnswer(String text, boolean correct);

    /**
     * Gets a list of answers associated with the question.
     *
     * @return The list of answers.
     */
    List<QuizQuestion.Answer> getAnswers();

    /**
     * Gets the text of the question.
     *
     * @return The question text.
     */
    String getQuestion();

    /**
     * Gets the title of the question.
     *
     * @return The question title.
     */
    String getTitle();

    /**
     * Checks if the answer at the specified index is correct.
     *
     * @param answerIndex The index of the answer to check.
     * @return true if the answer is correct, false otherwise.
     */
    boolean isAnswerCorrect(int answerIndex);

    /**
     * Checks if the provided answer index is within a valid range.
     *
     * @param answerIndex The answer index to validate.
     * @return true if the answer index is within a valid range, false otherwise.
     */
    boolean isAnswerIndexCorrect(int answerIndex);
}
