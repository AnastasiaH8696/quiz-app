/**
 * The QuizStore class is responsible for storing and providing access to different types of quizzes.
 * It maintains a collection of quizzes based on their QuizType and allows clients to obtain cloned instances
 * of quizzes based on the desired quiz type.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.quizes.GUIQuiz;
import il.ac.hit.quizzy.quizes.TerminalQuiz;
import il.ac.hit.quizzy.quizes.UIQuiz;

import java.util.HashMap;
import java.util.Map;

public class QuizStore {
    /**
     * The quizes map stores instances of UIQuiz implementations based on their QuizType.
     */
    private static Map<QuizType, UIQuiz> quizes = new HashMap<>();

    static {
        // Initialize the quizes map with default instances of quizzes.
        quizes.put(QuizType.TERMINAL, new TerminalQuiz());
        quizes.put(QuizType.GUI, new GUIQuiz());
    }

    /**
     * Gets a cloned instance of the specified quiz type.
     *
     * @param quizType The type of quiz to retrieve.
     * @return A cloned instance of the requested quiz type.
     */
    public static UIQuiz getQuiz(QuizType quizType) {
        // Retrieve the quiz instance from the map and clone it before returning.
        return (UIQuiz) quizes.get(quizType).clone();
    }
}
