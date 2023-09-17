/**
 * The IQuizQuestionBuilder interface defines the contract for constructing quiz questions in the Quizzy application.
 * It includes methods for setting the title, question text, and answers of a quiz question, as well as creating the question.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.interfaces;

public interface IQuizQuestionBuilder {
    /**
     * Sets the title of the quiz question.
     *
     * @param text The title to set.
     * @return The builder instance to support method chaining.
     */
    IQuizQuestionBuilder setTitle(String text);

    /**
     * Sets the text of the quiz question.
     *
     * @param text The question text to set.
     * @return The builder instance to support method chaining.
     */
    IQuizQuestionBuilder setQuestion(String text);

    /**
     * Adds an answer to the quiz question.
     *
     * @param text    The text of the answer.
     * @param correct true if the answer is correct, false otherwise.
     * @return The builder instance to support method chaining.
     */
    IQuizQuestionBuilder addAnswer(String text, boolean correct);

    /**
     * Creates and returns a constructed quiz question.
     *
     * @return The created quiz question.
     */
    IQuizQuestion create();
}
