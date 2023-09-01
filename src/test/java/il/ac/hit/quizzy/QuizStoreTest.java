package il.ac.hit.quizzy;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.quizes.GUIQuiz;
import il.ac.hit.quizzy.quizes.TerminalQuiz;
import il.ac.hit.quizzy.quizes.UIQuiz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizStoreTest {

    @Test
    public void testGetTerminalQuiz() {
        UIQuiz quiz = QuizStore.getQuiz(QuizType.TERMINAL);

        assertNotNull(quiz);
        assertTrue(quiz instanceof TerminalQuiz);
    }

    @Test
    public void testGetGUIQuiz() {
        UIQuiz quiz = QuizStore.getQuiz(QuizType.GUI);

        assertNotNull(quiz);
        assertTrue(quiz instanceof GUIQuiz);
    }

    @Test
    public void testGetUnknownQuizType() {
        assertThrows(NullPointerException.class, () -> QuizStore.getQuiz(null));
    }
}
