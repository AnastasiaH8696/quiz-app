/**
 * The QuizFactory class is responsible for creating instances of quizzes based on the specified QuizType.
 * It provides a method to create a quiz of the desired type.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.interfaces.IQuiz;

public class QuizFactory {
    /**
     * Creates a quiz of the specified QuizType.
     *
     * @param type The type of quiz to create (e.g., TERMINAL or GUI).
     * @return An instance of the requested quiz type.
     * @throws IllegalArgumentException If an invalid QuizType is provided.
     */
    public IQuiz createQuiz(QuizType type) {
        return switch (type) {
            case TERMINAL -> QuizStore.getQuiz(QuizType.TERMINAL);
            case GUI -> QuizStore.getQuiz(QuizType.GUI);
            default -> throw new IllegalArgumentException("Invalid QuizType");
        };
    }
}
