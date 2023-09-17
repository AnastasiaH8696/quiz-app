/**
 * The QuizException class represents an exception specific to the Quizzy application.
 * It is used to handle errors and exceptional situations related to quiz operations.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.exceptions;

public class QuizException extends Exception {
    /**
     * Constructs a new QuizException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public QuizException(String message) {
        super(message);
    }

    /**
     * Constructs a new QuizException with the specified error message and a reference to the
     * underlying cause of the exception.
     *
     * @param message The error message associated with the exception.
     * @param cause   The cause of the exception.
     */
    public QuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
