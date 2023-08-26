package il.ac.hit.quizzy.quizes;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.interfaces.IQuizQuestion;

public class GUIQuiz extends UIQuiz implements IQuiz {

    public GUIQuiz() {
        this.quizType = QuizType.GUI;
    }

    @Override
    public void start() {

    }

    @Override
    public void setName(String text) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {

    }
}
