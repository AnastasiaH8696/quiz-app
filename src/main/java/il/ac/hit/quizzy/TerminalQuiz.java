package il.ac.hit.quizzy;

import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.interfaces.IQuizAnswer;
import il.ac.hit.quizzy.interfaces.IQuizQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score;

    private final int POINTS_TO_ADD = 5; // TODO add it to some Utils class...

    @Override
    public void start() {
        System.out.println("starting the terminal quiz");
        System.out.println("Quiz: " + getName());
        play();
    }

    @Override
    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("warning: the quiz name was empty, set default name to : 'DEFAULT QUIZ'");
            this.name = "DEFAULT QUIZ";
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
        for (IQuizQuestion question : questions) {
            System.out.println("Question: " + question.getQuestion());
            System.out.println("Answers:");

            int answerIndex = 1;
            for (IQuizAnswer answer : question.getAnswers()) {
                System.out.println(answerIndex + ". " + answer.getText());
                answerIndex++;
            }

            Scanner scanner = new Scanner(System.in);

            boolean isValidChoose = false;
            int userAnswer = -1;
            while (!isValidChoose) {
                int answersRange = question.getAnswers().size();
                System.out.print("Enter your answer (1-" + answersRange + "): ");
                userAnswer = scanner.nextInt();

                if (userAnswer >= 1 && userAnswer <=answersRange) {
                    isValidChoose = true;
                } else {
                    System.out.println("invalid Input please choose answer from 1 to " + answersRange);
                }
            }

            if (question.isAnswerCorrect(userAnswer - 1)) {
                System.out.println("Correct! +" + POINTS_TO_ADD + "points!");
                setScore(getScore() + POINTS_TO_ADD);
            } else {
                System.out.println("Incorrect.\n");
            }

        }
        System.out.println("Quiz ended. Your score: " + score);
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
}
