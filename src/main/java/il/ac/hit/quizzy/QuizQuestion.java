/**
 * The QuizQuestion class implements the IQuizQuestion interface and represents a single quiz question.
 * It allows setting the title, question text, and multiple answers with their correctness status.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
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

    /**
     * Constructs a new QuizQuestion instance with an empty list of answers.
     */
    protected QuizQuestion() {
        answers = new ArrayList<>();
    }

    /**
     * Sets the title of the quiz question.
     *
     * @param title The title to set.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the text of the quiz question.
     *
     * @param question The question text to set.
     */
    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Adds an answer with its correctness status to the quiz question.
     *
     * @param text    The text of the answer.
     * @param correct The correctness status of the answer.
     */
    @Override
    public void addAnswer(String text, boolean correct) {
        answers.add(new Answer(text, correct));
    }

    /**
     * Gets the list of answers associated with this quiz question.
     *
     * @return The list of answers.
     */
    @Override
    public List<Answer> getAnswers() {
        return this.answers;
    }

    /**
     * Gets the text of the quiz question.
     *
     * @return The question text.
     */
    @Override
    public String getQuestion() {
        return this.question;
    }

    /**
     * Gets the title of the quiz question.
     *
     * @return The question title.
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Checks if an answer at the specified index is correct.
     *
     * @param answerIndex The index of the answer to check.
     * @return True if the answer at the specified index is correct; otherwise, false.
     */
    @Override
    public boolean isAnswerCorrect(int answerIndex) {
        return this.answers.get(answerIndex).correct;
    }

    /**
     * Checks if the provided answer index is valid for this quiz question.
     *
     * @param answerIndex The answer index to check.
     * @return True if the answer index is within a valid range; otherwise, false.
     */
    @Override
    public boolean isAnswerIndexCorrect(int answerIndex) {
        return (answerIndex >= 1 && answerIndex <= this.answers.size());
    }

    /**
     * The Builder class implements the IQuizQuestionBuilder interface and is used to construct
     * instances of QuizQuestion with a fluent builder pattern.
     */
    public static class Builder implements IQuizQuestionBuilder {
        private QuizQuestion quizQuestion;

        /**
         * Constructs a new Builder instance and initializes a QuizQuestion.
         */
        public Builder() {
            quizQuestion = new QuizQuestion();
        }

        /**
         * Sets the title of the quiz question being built.
         *
         * @param text The title text to set.
         * @return The Builder instance.
         */
        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            quizQuestion.setTitle(text);
            return this;
        }

        /**
         * Sets the question text of the quiz question being built.
         *
         * @param text The question text to set.
         * @return The Builder instance.
         */
        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            quizQuestion.setQuestion(text);
            return this;
        }

        /**
         * Adds an answer with its correctness status to the quiz question being built.
         *
         * @param text    The text of the answer.
         * @param correct The correctness status of the answer.
         * @return The Builder instance.
         */
        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean correct) {
            quizQuestion.addAnswer(text, correct);
            return this;
        }

        /**
         * Creates and returns the constructed QuizQuestion.
         *
         * @return The constructed QuizQuestion instance.
         */
        @Override
        public IQuizQuestion create() {
            return quizQuestion;
        }
    }

    /**
     * The Answer class implements the IQuizAnswer interface and represents an answer option
     * within a quiz question, including its text and correctness status.
     */
    public static class Answer implements IQuizAnswer {
        private String text;
        private boolean correct;

        /**
         * Constructs a new Answer instance with the provided text and correctness status.
         *
         * @param text    The text of the answer.
         * @param correct The correctness status of the answer.
         */
        Answer(String text, boolean correct) {
            this.text = text;
            this.correct = correct;
        }

        /**
         * Gets the text of the answer.
         *
         * @return The answer text.
         */
        @Override
        public String getText() {
            return text;
        }

        /**
         * Sets the text of the answer.
         *
         * @param text The text to set.
         */
        @Override
        public void setText(String text) {
            this.text = text;
        }

        /**
         * Checks if the answer is correct.
         *
         * @return True if the answer is correct; otherwise, false.
         */
        @Override
        public boolean isCorrect() {
            return correct;
        }

        /**
         * Sets the correctness status of the answer.
         *
         * @param correct The correctness status to set.
         */
        @Override
        public void setCorrect(boolean correct) {
            this.correct = correct;
        }
    }
}
