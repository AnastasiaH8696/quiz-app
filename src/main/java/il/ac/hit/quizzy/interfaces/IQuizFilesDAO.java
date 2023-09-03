package il.ac.hit.quizzy.interfaces;

import il.ac.hit.quizzy.exceptions.QuizException;

public interface IQuizFilesDAO {
    public abstract void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException;
    public abstract IQuiz loadQuizFromFile(String fileName) throws QuizException;
}
