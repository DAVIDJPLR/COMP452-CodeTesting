import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOverPanelTest { //using dependency injection

    static class TestJLabel extends JLabel{
        private String text;

        public TestJLabel(){
            text = "";
        }

        public void setText(String txt){
            this.text = txt;
        }

        public String getText(){
            return this.text;
        }
    }

    @Test
    void testSetGameResults_CorrectAnswerDisplayed() {
        TestJLabel answerLabel = new TestJLabel();
        TestJLabel numGuessesLabel = new TestJLabel();
        GameResult result = new GameResult(true, 42, 3);
        GameOverPanel.setTexts(result, answerLabel, numGuessesLabel);
        assertEquals("The answer was 42.", answerLabel.getText());
    }

    @Test
    void testSetGameResults_SingleGuess_Human() {
        TestJLabel answerLabel = new TestJLabel();
        TestJLabel numGuessesLabel = new TestJLabel();
        GameResult result = new GameResult(true, 42, 1);
        GameOverPanel.setTexts(result, answerLabel, numGuessesLabel);
        assertEquals("You guessed it on the first try!", numGuessesLabel.getText());
    }

    @Test
    void testSetGameResults_SingleGuess_Computer() {
        TestJLabel answerLabel = new TestJLabel();
        TestJLabel numGuessesLabel = new TestJLabel();
        GameResult result = new GameResult(false, 42, 1);
        GameOverPanel.setTexts(result, answerLabel, numGuessesLabel);
        assertEquals("I guessed it on the first try!", numGuessesLabel.getText());
    }

    @Test
    void testSetGameResults_MultipleGuesses_Human() {
        TestJLabel answerLabel = new TestJLabel();
        TestJLabel numGuessesLabel = new TestJLabel();
        GameResult result = new GameResult(true, 42, 5);
        GameOverPanel.setTexts(result, answerLabel, numGuessesLabel);
        assertEquals("It took you 5 guesses.", numGuessesLabel.getText());
    }

    @Test
    void testSetGameResults_MultipleGuesses_Computer() {
        TestJLabel answerLabel = new TestJLabel();
        TestJLabel numGuessesLabel = new TestJLabel();
        GameResult result = new GameResult(false , 42, 4);
        GameOverPanel.setTexts(result, answerLabel, numGuessesLabel);
        assertEquals("It took me 4 guesses.", numGuessesLabel.getText());
    }

}
