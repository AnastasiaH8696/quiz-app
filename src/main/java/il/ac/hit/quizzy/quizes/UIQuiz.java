/**
 * The UIQuiz abstract class implements the IQuiz interface and serves as a base class for specific types of quizzes.
 * It provides common functionality and properties for user interface (UI) quizzes and is cloneable.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.quizes;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.interfaces.IQuiz;

public abstract class UIQuiz implements IQuiz, Cloneable {
    /**
     * The quizType field stores the type of the quiz, such as TERMINAL or GUI.
     */
    protected QuizType quizType;

    /**
     * Performs a shallow clone of the UIQuiz object.
     *
     * @return A cloned instance of the UIQuiz.
     */
    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
