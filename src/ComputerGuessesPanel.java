    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionListener;
    import java.util.function.Consumer;

    /**
     * UI screen for when the computer is guessing a number
     *
     * Displays the computer's guesses and processes human's answers
     * Tracks the computer's guesses
     *
     * TODO: refactor this class / Done
     */
    public class ComputerGuessesPanel extends JPanel {

        private int numGuesses;
        private int lastGuess;

        // upperBound and lowerBound track the computer's knowledge about the correct number
        // They are updated after each guess is made
        private int upperBound; // correct number is <= upperBound
        private int lowerBound; // correct number is >= lowerBound

        public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
            initGame();

            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            JLabel guessMessage = addLabel("I guess ___.", Component.LEFT_ALIGNMENT);
            guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.add(Box.createRigidArea(new Dimension(0, 40)));

            JLabel prompt = addLabel("Your number is...", Component.CENTER_ALIGNMENT);
            this.add(Box.createRigidArea(new Dimension(0,10)));

            JButton lowerBtn = addButton("Lower", Component.CENTER_ALIGNMENT, (e -> {
                upperBound = Math.min(upperBound, lastGuess);

                makeGuess(guessMessage);
                numGuesses += 1;
            }));

            this.add(Box.createRigidArea(new Dimension(0,10)));

            JButton correctBtn = addButton("Equal", Component.CENTER_ALIGNMENT, (e -> {
                guessMessage.setText("I guess ___.");

                // Send the result of the finished game to the callback
                GameResult result = new GameResult(false, lastGuess, numGuesses);
                gameFinishedCallback.accept(result);

                CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
                cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
            }));

            this.add(Box.createRigidArea(new Dimension(0,10)));

            JButton higherBtn = addButton("Higher", Component.CENTER_ALIGNMENT, (e -> {
                lowerBound = Math.max(lowerBound, lastGuess + 1);

                makeGuess(guessMessage);
                numGuesses += 1;
            }));

            this.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentShown(java.awt.event.ComponentEvent e) {
                    initGame();

                    makeGuess(guessMessage);
                }
            });
        }

        private void makeGuess(JLabel label){
            this.lastGuess = (this.lowerBound + this.upperBound + 1) / 2;
            label.setText("I guess " + lastGuess + ".");
        }

        private JLabel addLabel(String text, float alignment){
            JLabel label = new JLabel(text);
            label.setAlignmentX(alignment);
            this.add(label);
            return label;
        }

        private JButton addButton(String text, float alignment, ActionListener listener){
            JButton button = new JButton(text);
            button.setAlignmentX(alignment);
            button.addActionListener(listener);
            this.add(button);
            return button;
        }

        private void initGame(){
            this.numGuesses = 0;
            this.upperBound = 1000;
            this.lowerBound = 1;
        }
    }


