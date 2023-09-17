/**
 * The QuizConstants class contains constants that are used in the Quizzy application.
 * It provides values such as points to add and the default quiz name.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.utils;

public final class QuizConstants {
    /**
     * Private constructor to prevent the instantiation of the class.
     * All methods and fields are static, so there's no need to create instances of this class.
     */
    private QuizConstants() {
        // No need to instantiate the class, we can hide its constructor
    }

    /**
     * The POINTS_TO_ADD constant defines the number of points to add for correct quiz answers.
     */
    public static final int POINTS_TO_ADD = 5;

    /**
     * The DEFAULT_QUIZ_NAME constant provides the default name for quizzes when no name is specified.
     */
    public static final String DEFAULT_QUIZ_NAME = "DEFAULT QUIZ";
}
