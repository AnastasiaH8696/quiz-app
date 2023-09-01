package il.ac.hit.quizzy;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.quizes.GUIQuiz;
import il.ac.hit.quizzy.quizes.TerminalQuiz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizFactoryTest {
    @Test
    public void testCreateTerminalQuiz() {
        QuizFactory quizFactory = new QuizFactory();
        IQuiz quiz = quizFactory.createQuiz(QuizType.TERMINAL);

        assertNotNull(quiz);
        assertTrue(quiz instanceof TerminalQuiz);
    }

    @Test
    public void testCreateGUIQuiz() {
        QuizFactory quizFactory = new QuizFactory();
        IQuiz quiz = quizFactory.createQuiz(QuizType.GUI);

        assertNotNull(quiz);
        assertTrue(quiz instanceof GUIQuiz);
    }

    @Test
    public void testCreateInvalidQuizType() {
        QuizFactory quizFactory = new QuizFactory();

        assertThrows(NullPointerException.class, () -> quizFactory.createQuiz(null));
    }
}
