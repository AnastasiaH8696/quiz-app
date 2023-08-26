package il.ac.hit.quizzy.interfaces;

import il.ac.hit.quizzy.QuizQuestion;

import java.util.List;

public interface IQuizQuestion {
    void setTitle(String title);
    void setQuestion(String question);
    void addAnswer(String text, boolean correct);
    List<QuizQuestion.Answer> getAnswers();
    String getQuestion();
    String getTitle();
    boolean isAnswerCorrect(int answerIndex);
    boolean isAnswerIndexCorrect(int answerIndex);
}
