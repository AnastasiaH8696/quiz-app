/**
 * The IQuizFilesDAO interface defines the contract for managing the persistence of quizzes in the Quizzy application.
 * It includes methods for saving a quiz to a file and loading a quiz from a file, both of which may throw a QuizException.
 *
 * @author
 *    Anastasia Hamandritov (ID: 321924433)
 *    Sergey Juchenko (ID: 319365102)
 *    Shirel Bitan (ID: 209322395)
 */
package il.ac.hit.quizzy.interfaces;

import il.ac.hit.quizzy.exceptions.QuizException;

public interface IQuizFilesDAO {
    /**
     * Saves the provided quiz to a file with the specified fileName.
     *
     * @param quiz     The quiz to be saved.
     * @param fileName The name of the file where the quiz will be saved.
     * @throws QuizException If an error occurs during the saving process.
     */
    void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException;

    /**
     * Loads a quiz from a file with the specified fileName.
     *
     * @param fileName The name of the file from which the quiz will be loaded.
     * @return The loaded quiz.
     * @throws QuizException If an error occurs during the loading process.
     */
    IQuiz loadQuizFromFile(String fileName) throws QuizException;
}
