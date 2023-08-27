package il.ac.hit.quizzy.quizes;

import il.ac.hit.quizzy.enums.QuizType;
import il.ac.hit.quizzy.interfaces.IQuiz;
import il.ac.hit.quizzy.interfaces.IQuizAnswer;
import il.ac.hit.quizzy.interfaces.IQuizQuestion;
import il.ac.hit.quizzy.utils.QuizConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUIQuiz extends UIQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;
    private int currentQuestionIndex = 0;

    private JFrame frame;
    private JLabel questionLabel;
    private JPanel answerPanel;
    private JButton checkButton;
    private JLabel scoreLabel;


    public GUIQuiz() {
        this.quizType = QuizType.GUI;
        frame = new JFrame("quiz tittle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionLabel = new JLabel("question....");
        checkButton = new JButton("check answer");
        answerPanel = new JPanel();
        scoreLabel = new JLabel("score: " + getScore());
    }


    @Override
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initComponents();
                play();
            }
        });
    }

    @Override
    public void setName(String name) {
        if (name.isEmpty()) {
            this.name = QuizConstants.DEFAULT_QUIZ_NAME;
            frame.setTitle(this.name);
        } else {
            this.name = name;
            frame.setTitle(name);
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

    public List<IQuizQuestion> getQuestions() {
        return questions;
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

    private void play() {

        // render the first question
        showNextQuestion();

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });
    }

    private void initComponents() {
        frame.setLayout(new BorderLayout());
        frame.add(questionLabel, BorderLayout.NORTH);
        frame.add(checkButton, BorderLayout.SOUTH);
        frame.add(answerPanel, BorderLayout.CENTER);
        frame.add(scoreLabel, BorderLayout.EAST);
        frame.setSize(800,600);
        answerPanel.setLayout(new GridLayout(questions.get(currentQuestionIndex).getAnswers().size(), 1));

        Border padding = BorderFactory.createEmptyBorder(10, 10, 450, 50);
        scoreLabel.setBorder(padding);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.setVisible(true);
    }

    private void renderAnswers(IQuizQuestion currentQuestion) {
        for (IQuizAnswer answer : currentQuestion.getAnswers()) {
            JRadioButton radioButton = new JRadioButton(answer.getText());
            answerPanel.add(radioButton);
        }
    }


    private void checkAnswer() {
        if (currentQuestionIndex - 1 < questions.size()) {
            IQuizQuestion question = questions.get(currentQuestionIndex - 1);
            Component[] components = answerPanel.getComponents();

            for (int i = 0; i < components.length; i++) {
                if (((JRadioButton) components[i]).isSelected()) {
                    if (question.isAnswerCorrect(i)) {
                        setScore(getScore() + QuizConstants.POINTS_TO_ADD);
                    }
                    break;
                }
            }
        }
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            scoreLabel.setText("score: " + getScore());
            IQuizQuestion question = questions.get(currentQuestionIndex);
            answerPanel.setLayout(new GridLayout(question.getAnswers().size(), 1));
            questionLabel.setText(question.getQuestion());
            answerPanel.removeAll();
            answerPanel.setLayout(new GridLayout(question.getAnswers().size(), 1));

            for (IQuizAnswer answer : question.getAnswers()) {
                JRadioButton radioButton = new JRadioButton(answer.getText());
                answerPanel.add(radioButton);
            }

            frame.revalidate(); // Refresh the UI
            currentQuestionIndex++;
        } else {
            showResult();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(frame, "Quiz ended. Your score: " +
                getScore() + "/" +  QuizConstants.POINTS_TO_ADD * questions.size()
        );
        frame.dispose();
    }

}
