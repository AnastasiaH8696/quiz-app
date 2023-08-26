package il.ac.hit.quizzy;

import il.ac.hit.quizzy.interfaces.IQuizAnswer;
import il.ac.hit.quizzy.interfaces.IQuizQuestion;
import il.ac.hit.quizzy.interfaces.IQuizQuestionBuilder;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {

    private String title;
    private String question;
    private List<Answer> answers;

    private QuizQuestion() {
        answers = new ArrayList<>();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public void addAnswer(String text, boolean correct) {
        answers.add(new Answer(text, correct));
    }
    public List<Answer> getAnswers() {
        return this.answers;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isAnswerCorrect(int answerIndex) {
        return this.answers.get(answerIndex).correct;
    }

    @Override
    public boolean isAnswerIndexCorrect(int answerIndex) {
        return (answerIndex >= 1 && answerIndex <= this.answers.size());
    }

    @Override
    public void printAnswers() {
        int answerIndex = 1;
        for (Answer answer : this.answers) {
            System.out.println(answerIndex + ". " + answer.text);
            answerIndex++;
        }
    }

    public static class Builder implements IQuizQuestionBuilder {
        private QuizQuestion quizQuestion;

        public Builder() {
            quizQuestion = new QuizQuestion();
        }

        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            quizQuestion.setTitle(text);
            return this;
        }

        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            quizQuestion.setQuestion(text);
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean correct) {
            quizQuestion.addAnswer(text, correct);
            return this;
        }

        @Override
        public IQuizQuestion create() {
            return quizQuestion;
        }
    }

    public static class Answer implements IQuizAnswer {
        private String text;
        private boolean correct;

        Answer(String text, boolean correct) {
            this.text = text;
            this.correct = correct;
        }

        @Override
        public String getText() {
            return text;
        }

        @Override
        public void setText(String text) {
            this.text = text;
        }

        @Override
        public boolean isCorrect() {
            return correct;
        }

        @Override
        public void setCorrect(boolean correct) {
            this.correct = correct;
        }
    }
}
