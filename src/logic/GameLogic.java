package logic;

import java.util.Random;

public class GameLogic {

    private String[] choices = {
            "Rock",
            "Paper",
            "Scissors"
    };

    // Computer random choice
    public String getComputerChoice() {

        Random random = new Random();

        int index = random.nextInt(choices.length);

        return choices[index];
    }

    // Winner decide karna
    public String checkWinner(String userChoice, String computerChoice) {

        // Draw
        if (userChoice.equals(computerChoice)) {

            return "Draw";

        }

        // User Wins
        if (
                (userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (userChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {

            return "Win";

        }

        // User Loses
        return "Lose";
    }

}