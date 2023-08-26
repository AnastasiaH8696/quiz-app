package il.ac.hit.quizzy;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.quizes.GUIQuiz;
import il.ac.hit.quizzy.quizes.TerminalQuiz;
import il.ac.hit.quizzy.quizes.UIQuiz;

import java.util.HashMap;
import java.util.Map;

public class QuizStore {
    private static Map<QuizType, UIQuiz> quizes = new HashMap<>();

    static
    {
        quizes.put(QuizType.TERMINAL, new TerminalQuiz());
        quizes.put(QuizType.GUI, new GUIQuiz());
    }
    public static UIQuiz getQuiz(QuizType quizType)
    {
        return (UIQuiz) quizes.get(quizType).clone();
    }
}
