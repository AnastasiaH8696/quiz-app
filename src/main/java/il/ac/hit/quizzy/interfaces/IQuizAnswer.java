/**
 * The IQuizAnswer interface defines the contract for representing an answer to a quiz question in the Quizzy application.
 * It includes methods for getting and setting the answer text and determining whether the answer is correct.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.interfaces;

public interface IQuizAnswer {
    /**
     * Gets the text of the answer.
     *
     * @return The answer text.
     */
    String getText();

    /**
     * Sets the text of the answer.
     *
     * @param text The text to set.
     */
    void setText(String text);

    /**
     * Checks whether the answer is correct.
     *
     * @return true if the answer is correct, false otherwise.
     */
    boolean isCorrect();

    /**
     * Sets whether the answer is correct or not.
     *
     * @param correct true if the answer is correct, false otherwise.
     */
    void setCorrect(boolean correct);
}
