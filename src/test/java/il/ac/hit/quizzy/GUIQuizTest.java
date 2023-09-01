package il.ac.hit.quizzy;

import il.ac.hit.quizzy.interfaces.IQuizQuestion;
import il.ac.hit.quizzy.quizes.GUIQuiz;
import il.ac.hit.quizzy.utils.QuizConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GUIQuizTest {

    private GUIQuiz guiQuiz;

    @BeforeEach
    public void setUp() {
        guiQuiz = new GUIQuiz();
    }

    @Test
    public void testSetName() {
        guiQuiz.setName("My Quiz");
        assertEquals("My Quiz", guiQuiz.getName());

        // Test with an empty name
        guiQuiz.setName("");
        assertEquals(QuizConstants.DEFAULT_QUIZ_NAME, guiQuiz.getName());
    }

    @Test
    public void testAddQuestionAndGetQuestions() {
        IQuizQuestion question1 = new QuizQuestion();
        IQuizQuestion question2 = new QuizQuestion();

        guiQuiz.addQuestion(question1);
        guiQuiz.addQuestion(question2);

        List<IQuizQuestion> questions = guiQuiz.getQuestions();
        assertEquals(2, questions.size());
        assertTrue(questions.contains(question1));
        assertTrue(questions.contains(question2));
    }

    @Test
    public void testGetScoreAndSetScore() {
        assertEquals(0, guiQuiz.getScore());

        guiQuiz.setScore(10);
        assertEquals(10, guiQuiz.getScore());

        // Test setting a negative score (it should not change the score)
        guiQuiz.setScore(-5);
        assertEquals(10, guiQuiz.getScore());
    }
}
