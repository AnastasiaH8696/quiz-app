package il.ac.hit.quizzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizQuestionTest {

    private QuizQuestion quizQuestion;

    @BeforeEach
    public void setUp() {
        quizQuestion = (QuizQuestion) new QuizQuestion.Builder()
                .setTitle("Title")
                .setQuestion("What is the capital of France?")
                .addAnswer("Berlin", false)
                .addAnswer("London", false)
                .addAnswer("Paris", true)
                .create();
    }

    @Test
    public void testGetTitle() {
        assertEquals("Title", quizQuestion.getTitle());
    }

    @Test
    public void testGetQuestion() {
        assertEquals("What is the capital of France?", quizQuestion.getQuestion());
    }

    @Test
    public void testGetAnswers() {
        List<QuizQuestion.Answer> answers = quizQuestion.getAnswers();
        assertNotNull(answers);
        assertEquals(3, answers.size());
    }

    @Test
    public void testIsAnswerCorrect() {
        assertTrue(quizQuestion.isAnswerCorrect(2)); // Correct answer at index 2
        assertFalse(quizQuestion.isAnswerCorrect(0)); // Incorrect answer at index 0
    }

    @Test
    public void testIsAnswerIndexCorrect() {
        assertTrue(quizQuestion.isAnswerIndexCorrect(1)); // Valid index 1
        assertFalse(quizQuestion.isAnswerIndexCorrect(4)); // Invalid index 4
    }

    @Test
    public void testAnswerText() {
        List<QuizQuestion.Answer> answers = quizQuestion.getAnswers();
        assertEquals("Berlin", answers.get(0).getText());

        // Change answer text
        answers.get(0).setText("Hamburg");
        assertEquals("Hamburg", answers.get(0).getText());
    }

    @Test
    public void testAnswerCorrect() {
        List<QuizQuestion.Answer> answers = quizQuestion.getAnswers();
        assertTrue(answers.get(2).isCorrect()); // Correct answer at index 2

        // Change answer correctness
        answers.get(2).setCorrect(false);
        assertFalse(answers.get(2).isCorrect()); // Updated to incorrect
    }
}
