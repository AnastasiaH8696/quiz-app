package il.ac.hit.quizzy;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.exceptions.QuizException;
import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.interfaces.IQuizAnswer;
import il.ac.hit.quizzy.interfaces.IQuizFilesDAO;
import il.ac.hit.quizzy.interfaces.IQuizQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleCSVQuizFilesDAOTest {
    private IQuizFilesDAO quizFilesDAO;
    private IQuiz quiz;

    @BeforeEach
    public void setUp() {
        quizFilesDAO = SimpleCSVQuizFilesDAO.getInstance();
        QuizFactory factory = new QuizFactory();
        quiz = factory.createQuiz(QuizType.TERMINAL);
        quiz.getQuestions().clear();
    }

    @Test
    public void saveAndLoadQuizToFileTest() throws QuizException {
        // Create a sample quiz
        IQuizQuestion question1 = new QuizQuestion.Builder()
                .setTitle("Title1")
                .setQuestion("Question1")
                .addAnswer("Answer1", true)
                .addAnswer("Answer2", false)
                .addAnswer("Answer3", false)
                .addAnswer("Answer4", false)
                .addAnswer("Answer5", false)
                .create();
        quiz.addQuestion(question1);

        String filePath = "quiz.csv";

        // Save the quiz to a file
        assertDoesNotThrow(() -> quizFilesDAO.saveQuizToFile(quiz, filePath));

        // Load the quiz from the file
        IQuiz loadedQuiz = quizFilesDAO.loadQuizFromFile(filePath);

        // Verify that the loaded quiz is the same as the original quiz
        assertEquals(quiz.getQuizType(), loadedQuiz.getQuizType());
        List<IQuizQuestion> originalQuestions = quiz.getQuestions();
        List<IQuizQuestion> loadedQuestions = loadedQuiz.getQuestions();

        assertEquals(originalQuestions.size(), loadedQuestions.size());

        for (int i = 0; i < originalQuestions.size(); i++) {
            IQuizQuestion originalQuestion = originalQuestions.get(i);
            IQuizQuestion loadedQuestion = loadedQuestions.get(i);

            assertEquals(originalQuestion.getTitle(), loadedQuestion.getTitle());
            assertEquals(originalQuestion.getQuestion(), loadedQuestion.getQuestion());

            List<QuizQuestion.Answer> originalAnswers = originalQuestion.getAnswers();
            List<QuizQuestion.Answer> loadedAnswers = loadedQuestion.getAnswers();

            assertEquals(originalAnswers.size(), loadedAnswers.size());

            for (int j = 0; j < originalAnswers.size(); j++) {
                IQuizAnswer originalAnswer = originalAnswers.get(j);
                IQuizAnswer loadedAnswer = loadedAnswers.get(j);

                assertEquals(originalAnswer.getText(), loadedAnswer.getText());
                assertEquals(originalAnswer.isCorrect(), loadedAnswer.isCorrect());
            }
        }
    }

    @Test
    public void testMinimumQuestions() throws QuizException {
        // Create a sample quiz
        QuizFactory factory = new QuizFactory();
        IQuiz quiz = factory.createQuiz(QuizType.TERMINAL);
        IQuizQuestion question1 = new QuizQuestion.Builder()
                .setTitle("Title1")
                .setQuestion("Question1")
                .addAnswer("Answer1", true)
                .create();
        quiz.addQuestion(question1);

        String filePath = "quiz.csv";

        quizFilesDAO.saveQuizToFile(quiz, filePath);

        assertThrows(QuizException.class, () -> quizFilesDAO.loadQuizFromFile(filePath));
    }

}
