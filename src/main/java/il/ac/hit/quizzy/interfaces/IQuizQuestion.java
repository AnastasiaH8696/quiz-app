package il.ac.hit.quizzy.interfaces;

public interface IQuizQuestion {
    void setTitle(String title);
    void setQuestion(String question);
    void addAnswer(String text, boolean correct);
}
