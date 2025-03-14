import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class HumanGuessesGameTest {  //using dependency injection
    static class TestRandom extends Random {

        private int currentIndex;
        private final int[] numbers;


        public TestRandom(){
            currentIndex = 0;
            numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        }

        @Override
        public int nextInt() {
            int returnIndex = currentIndex;
            if (currentIndex < numbers.length){
                currentIndex += 1;
            } else {
                currentIndex = 0;
            }
            return numbers[returnIndex];
        }
    }

    @Test
    void testRightAnswer(){
        HumanGuessesGame game = new HumanGuessesGame(new TestRandom());
        game.makeGuess(2);
        assertTrue(game.isDone());
    }

    @Test
    void guessLow(){
        HumanGuessesGame game = new HumanGuessesGame(new TestRandom());
        GuessResult result = game.makeGuess(1);
        assertEquals(GuessResult.LOW, result);
    }

    @Test
    void guessOutOfBounds(){
        HumanGuessesGame game = new HumanGuessesGame(new TestRandom());
        assertThrows(Exception.class,
                () -> { game.makeGuess(1001); }
        );
    }
}
