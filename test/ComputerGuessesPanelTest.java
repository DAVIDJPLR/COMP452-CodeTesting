import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerGuessesPanelTest {
//    ComputerGuessesPanel will make its guess incorrectly because instead of guessing the middle of lowerbound and upper bound it guesses one more than that
//    so if lowerbound and upperbound have a difference of 2 the solution will never be reached. Updating lowerbound should also just be updated to last guess
//    not last guess +1

    @Test
    void newGame(){
        ComputerGuessesPanel computerPanel = new ComputerGuessesPanel();

        assertEquals(0, computerPanel.getNumGuesses());
        assertEquals(1000, computerPanel.getUpperBound());
        assertEquals(1, computerPanel.getLowerBound());
    }

    @Test
    void incNumGuesses(){
        ComputerGuessesPanel computerPanel = new ComputerGuessesPanel();
        computerPanel.incNumGuesses();
        assertEquals(1, computerPanel.getNumGuesses());
    }

    @Test
    void guessText(){
        ComputerGuessesPanel computerPanel = new ComputerGuessesPanel();
        String guessString = computerPanel.makeGuess();

        assertEquals("I guess " + computerPanel.getLastGuess() +".", guessString);
    }

    @Test
    void testUpdateUpperBound(){
        ComputerGuessesPanel computerPanel = new ComputerGuessesPanel();
        computerPanel.makeGuess();
        computerPanel.updateUpperBound();
        assertEquals(500, computerPanel.getUpperBound());
    }

    @Test
    void testUpdateLowerBound(){
        ComputerGuessesPanel computerPanel = new ComputerGuessesPanel();
        computerPanel.makeGuess();
        computerPanel.updateLowerBound();
        assertEquals(500, computerPanel.getLowerBound());
    }
}
