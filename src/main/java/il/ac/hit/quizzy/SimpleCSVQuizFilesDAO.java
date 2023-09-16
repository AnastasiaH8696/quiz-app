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
    private static final String CSV_SEPARATOR = ",";
    private QuizType quizType;

    // Private constructor to enforce using getInstance() method for obtaining an instance.
    private SimpleCSVQuizFilesDAO() {
        this.quizType = QuizType.TERMINAL; //Default
    }

    public static IQuizFilesDAO getInstance() {
        return new SimpleCSVQuizFilesDAO();
    }

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

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        List<IQuizQuestion> questions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(CSV_SEPARATOR);
                if (parts.length < 5) {
                    throw new QuizException("Invalid CSV format. Please make sure you have a title, a question and at least two answers");
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
        quiz.getQuestions().clear(); //Because we are using store design pattern, we should remove previous cache items
        for (IQuizQuestion question : questions) {
            quiz.addQuestion(question);
        }

        return quiz;
    }
}

