package il.ac.hit.quizzy.interfaces;

import il.ac.hit.quizzy.enums.QuizType;

import java.util.List;

public interface IQuiz {
    public abstract void start();
    public abstract void setName(String text);
    public abstract String getName();
    public abstract void addQuestion(IQuizQuestion question);
    List<IQuizQuestion> getQuestions();
    public abstract QuizType getQuizType();
}
