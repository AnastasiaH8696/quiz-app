package il.ac.hit.quizzy;

import il.ac.hit.quizzy.exceptions.QuizException;
import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.interfaces.IQuizFilesDAO;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {
    public static IQuizFilesDAO getInstance() {
        return null;
    }

    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {

    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        return null;
    }

    @Override
    public void saveToFile(IQuiz quiz, String s) {

    }
}
