package il.ac.hit.quizzy.quizes;

import il.ac.hit.quizzy.QuizQuestion;
import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.interfaces.IQuizQuestion;
import il.ac.hit.quizzy.utils.QuizConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalQuiz extends UIQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score;

    public TerminalQuiz() {
        this.quizType = QuizType.TERMINAL;
    }

    @Override
    public void start() {
        System.out.println("starting the terminal quiz");
        play();
        endTerminalQuiz();
    }

    private void endTerminalQuiz() {
        System.out.println("Quiz ended. Your score: " + getScore() + "/" +
                QuizConstants.POINTS_TO_ADD * questions.size()
        );
    }

    @Override
    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("warning: the quiz name was empty, set default name to : 'DEFAULT QUIZ'");
            this.name = QuizConstants.DEFAULT_QUIZ_NAME;
        } else {
            this.name = name;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }

    private void play() {
        System.out.println("Quiz: " + getName());
        for (IQuizQuestion question : questions) {
            boolean isValidChoose = false;
            int userAnswer = -1;
            int answersRange = question.getAnswers().size();
            System.out.println("Question: " + question.getQuestion());
            System.out.println("Answers:");
            printAnswers(question);

            Scanner scanner = new Scanner(System.in);

            while (!isValidChoose) {
                System.out.print("Enter your answer (1-" + answersRange + "): ");
                try {
                    userAnswer = scanner.nextInt();
                    scanner.nextLine();

                    if (question.isAnswerIndexCorrect(userAnswer)) {
                        isValidChoose = true;
                    } else {
                        System.out.println("Your answer is out of range please choose answer from 1 to " + answersRange);
                    }
                } catch (Exception e) {
                    System.out.println("invalid type answer. Please enter a valid number.");
                    scanner.nextLine();
                }
            }

            if (question.isAnswerCorrect(userAnswer - 1)) {
                System.out.println("Correct! +" + QuizConstants.POINTS_TO_ADD + " points!\n");
                setScore(getScore() + QuizConstants.POINTS_TO_ADD);
            } else {
                System.out.println("Incorrect.\n");
            }

        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        // updates the score only if the score is positive
        if (score > 0) {
            this.score = score;
        }
    }

    public List<IQuizQuestion> getQuestions() {
        return questions;
    }

    @Override
    public QuizType getQuizType() {
        return this.quizType;
    }

    public void setQuestions(List<IQuizQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "TerminalQuiz{" +
                "name='" + name + '\'' +
                ", questions=" + questions +
                ", score=" + score +
                '}';
    }

    private void printAnswers(IQuizQuestion question) {
        int answerIndex = 1;
        for (QuizQuestion.Answer answer : question.getAnswers()) {
            System.out.println(answerIndex + ". " + answer.getText());
            answerIndex++;
        }
    }
}
