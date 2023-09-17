/**
 * The SimpleCSVQuizFilesDAO class implements the IQuizFilesDAO interface and provides methods
 * for saving and loading quizzes from CSV files. It allows for the storage and retrieval of
 * quizzes in a simple CSV format.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.exceptions.QuizException;
import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.interfaces.IQuizFilesDAO;
import il.ac.hit.quizzy.interfaces.IQuizQuestion;
import il.ac.hit.quizzy.interfaces.IQuizAnswer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {

    /**
     * The CSV_SEPARATOR constant defines the separator used in the CSV file.
     */
    private static final String CSV_SEPARATOR = ",";

    /**
     * The quizType variable stores the type of quiz being operated on.
     */
    private QuizType quizType;

    /**
     * Private constructor to enforce using getInstance() method for obtaining an instance.
     * Initializes the quizType to QuizType.TERMINAL as the default value.
     */
    private SimpleCSVQuizFilesDAO() {
        this.quizType = QuizType.TERMINAL; // Default
    }

    /**
     * Gets an instance of the SimpleCSVQuizFilesDAO class.
     *
     * @return An instance of SimpleCSVQuizFilesDAO.
     */
    public static IQuizFilesDAO getInstance() {
        return new SimpleCSVQuizFilesDAO();
    }

    /**
     * Saves a quiz to a CSV file.
     *
     * @param quiz     The quiz to be saved.
     * @param fileName The name of the CSV file to save the quiz to.
     * @throws QuizException If an error occurs while saving the quiz.
     */
    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {
        this.quizType = quiz.getQuizType();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (IQuizQuestion question : quiz.getQuestions()) {
                writer.write(question.getTitle());
                writer.write(CSV_SEPARATOR);
                writer.write(question.getQuestion());
                writer.write(CSV_SEPARATOR);
                for (IQuizAnswer answer : question.getAnswers()) {
                    writer.write(answer.getText());
                    writer.write(CSV_SEPARATOR);
                    writer.write(Boolean.toString(answer.isCorrect()));
                    writer.write(CSV_SEPARATOR);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            throw new QuizException("Error saving quiz to file: " + e.getMessage(), e);
        }
    }

    /**
     * Loads a quiz from a CSV file.
     *
     * @param fileName The name of the CSV file to load the quiz from.
     * @return The loaded quiz.
     * @throws QuizException If an error occurs while loading the quiz.
     */
    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        List<IQuizQuestion> questions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(CSV_SEPARATOR);
                if (parts.length < 5) {
                    throw new QuizException("Invalid CSV format. Please make sure you have a title, a question, and at least two answers");
                }

                QuizQuestion.Builder questionBuilder = new QuizQuestion.Builder();
                questionBuilder.setTitle(parts[0]);
                questionBuilder.setQuestion(parts[1]);

                for (int i = 2; i < parts.length; i += 2) {
                    String answerText = parts[i];
                    boolean isCorrect = Boolean.parseBoolean(parts[i + 1]);
                    questionBuilder.addAnswer(answerText, isCorrect);
                }

                questions.add(questionBuilder.create());
            }
        } catch (IOException e) {
            throw new QuizException("Error loading quiz from file: " + e.getMessage(), e);
        }

        // Create a new quiz and add the loaded questions.
        QuizFactory factory = new QuizFactory();
        IQuiz quiz = factory.createQuiz(this.quizType);
        quiz.getQuestions().clear(); // Because we are using store design pattern, we should remove previous cache items
        for (IQuizQuestion question : questions) {
            quiz.addQuestion(question);
        }

        return quiz;
    }
}
