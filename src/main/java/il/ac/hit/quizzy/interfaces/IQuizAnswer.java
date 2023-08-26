package il.ac.hit.quizzy.interfaces;

public interface IQuizAnswer {
    String getText();
    void setText(String text);
    boolean isCorrect();
    void setCorrect(boolean correct);
}
