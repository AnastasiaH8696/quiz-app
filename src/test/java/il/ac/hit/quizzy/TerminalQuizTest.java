package il.ac.hit.quizzy;

import il.ac.hit.quizzy.interfaces.IQuizQuestion;
import il.ac.hit.quizzy.quizes.TerminalQuiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TerminalQuizTest {

    private TerminalQuiz terminalQuiz;

    @BeforeEach
    public void setUp() {
        terminalQuiz = new TerminalQuiz();
    }

    @Test
    public void testSetName() {
        terminalQuiz.setName("My Terminal Quiz");
        assertEquals("My Terminal Quiz", terminalQuiz.getName());

        // Test with an empty name
        terminalQuiz.setName("");
        assertEquals("DEFAULT QUIZ", terminalQuiz.getName());
    }

    @Test
    public void testAddQuestionAndGetQuestions() {
        IQuizQuestion question1 = new QuizQuestion();
        IQuizQuestion question2 = new QuizQuestion();

        terminalQuiz.addQuestion(question1);
        terminalQuiz.addQuestion(question2);

        List<IQuizQuestion> questions = terminalQuiz.getQuestions();
        assertEquals(2, questions.size());
        assertTrue(questions.contains(question1));
        assertTrue(questions.contains(question2));
    }

    @Test
    public void testGetScoreAndSetScore() {
        assertEquals(0, terminalQuiz.getScore());

        terminalQuiz.setScore(10);
        assertEquals(10, terminalQuiz.getScore());

        // Test setting a negative score (it should not change the score)
        terminalQuiz.setScore(-5);
        assertEquals(10, terminalQuiz.getScore());
    }

    @Test
    public void testPlay() {
        // This method relies heavily on user input, and testing it with JUnit is challenging.
        // You can consider using mocking frameworks or UI testing tools for such scenarios.
        // This test case is a placeholder.
    }

    @Test
    public void testEndTerminalQuiz() {
        // Redirect System.out to capture the printed output
        InputStream originalIn = System.in;
        try {
            final String expectedOutput = "Quiz ended. Your score: 0/0";

            // Create a fake input stream to provide a response to the Scanner
            String input = "1\n2\n"; // Invalid input to trigger the endTerminalQuiz() method
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            // Redirect System.out to capture the printed output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            terminalQuiz.start();
            String actualOutput = outputStream.toString();

            assertTrue(actualOutput.contains(expectedOutput));
        } finally {
            // Restore System.in and System.out
            System.setIn(originalIn);
            System.setOut(System.out);
        }
    }

}
