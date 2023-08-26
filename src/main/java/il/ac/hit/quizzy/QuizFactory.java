package il.ac.hit.quizzy;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.interfaces.IQuiz;

public class QuizFactory {
    public IQuiz createQuiz(QuizType type) {
        return switch (type) {
            case TERMINAL -> QuizStore.getQuiz(QuizType.TERMINAL);
            case GUI -> QuizStore.getQuiz(QuizType.GUI);
            default -> throw new IllegalArgumentException("Invalid QuizType");
        };
    }
}
