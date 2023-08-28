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
    private JRadioButton selectedRadioButton = null;
    private JFrame frame;
    private JLabel questionLabel;
    private JPanel answerPanel;
    private JButton checkButton;
    private JLabel scoreLabel;


    public GUIQuiz() {
        this.quizType = QuizType.GUI;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionLabel = new JLabel("question....");
        checkButton = new JButton("check answer");
        scoreLabel = new JLabel("score: " + getScore());
        answerPanel = new JPanel();
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
        // renders the first question
        showNextQuestion();
    }

    private void initComponents() {

        frame.setLayout(new BorderLayout());
        frame.add(questionLabel, BorderLayout.NORTH);
        frame.add(checkButton, BorderLayout.SOUTH);
        frame.add(answerPanel, BorderLayout.CENTER);
        frame.add(scoreLabel, BorderLayout.EAST);
        frame.setSize(800,600);

        answerPanel.setLayout(new GridLayout(questions.get(currentQuestionIndex).getAnswers().size(), 1));

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });

        Border padding = BorderFactory.createEmptyBorder(10, 10, 450, 50);
        scoreLabel.setBorder(padding);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.setVisible(true);
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            scoreLabel.setText("score: " + getScore());
            IQuizQuestion question = questions.get(currentQuestionIndex);
            questionLabel.setText(question.getQuestion());
            answerPanel.removeAll();
            answerPanel.setLayout(new GridLayout(question.getAnswers().size(), 1));

            renderAnswers(question);

            frame.revalidate(); // Refresh the UI
            currentQuestionIndex++;
        } else {
            showResult();
        }
    }

    private void renderAnswers(IQuizQuestion currentQuestion) {
        ButtonGroup buttonGroup = new ButtonGroup();


        for (IQuizAnswer answer : currentQuestion.getAnswers()) {
            JRadioButton radioButton = new JRadioButton(answer.getText());
            buttonGroup.add(radioButton); // Add the radio button to the ButtonGroup
            answerPanel.add(radioButton);

            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedRadioButton = radioButton;
                }
            });
        }
    }

    private void checkAnswer() {
        if (selectedRadioButton != null) {
            IQuizQuestion question = questions.get(currentQuestionIndex - 1);
            Component[] components = answerPanel.getComponents();

            for (int i = 0; i < components.length; i++) {
                if (components[i] == selectedRadioButton) {
                    if (question.isAnswerCorrect(i)) {
                        setScore(getScore() + QuizConstants.POINTS_TO_ADD);
                        scoreLabel.setText("score: " + getScore());
                    }
                    break;
                }
            }

            // Clear the selection after processing the answer
            selectedRadioButton = null;
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(frame, "Quiz ended. Your score: " +
                getScore() + "/" +  QuizConstants.POINTS_TO_ADD * questions.size()
        );
        frame.dispose();
    }

}
