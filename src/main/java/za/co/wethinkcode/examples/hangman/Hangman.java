package za.co.wethinkcode.examples.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// tag::hangman-class[]
public class Hangman {
    private static String selectedWord;

    public static void main(String[] args) throws IOException {
        // end::hangman-class[]
        Player player = new Player();
        Random random = new Random();
        Scanner inputScanner = new Scanner(System.in);
        // tag::numberGuesses[]

        System.out.println("Words file? [leave empty to use short_words.txt]");
        String fileName = inputScanner.nextLine();
        if (fileName.isBlank()) {
            fileName = "short_words.txt";
        }

        List<String> words = Files.readAllLines(Path.of(fileName));

        int randomIndex = random.nextInt(words.size());
        selectedWord = words.get(randomIndex).trim();

        randomIndex = random.nextInt(selectedWord.length() - 1);

        String resultWord = "_".repeat(selectedWord.length());

        String currentAnswer = fillInChar(resultWord,
                selectedWord.charAt(randomIndex));

        System.out.println("Guess the word: " + currentAnswer);

        while (!currentAnswer.equalsIgnoreCase(selectedWord)) {
            String guess = inputScanner.nextLine();
            if (guess.equalsIgnoreCase("exit")
                    || guess.equalsIgnoreCase("quit")) {
                System.out.println("Bye!");
                break;
            }

            if (selectedWord.indexOf(guess.charAt(0)) >= 0
                    && currentAnswer.indexOf(guess.charAt(0)) < 0) {
                currentAnswer = fillInChar(currentAnswer, guess.charAt(0));
                System.out.println(currentAnswer);
            } else {
                // tag::use-numberGuesses[]
                player.lostChance();
                System.out.println("Wrong! Number of guesses left: " + player.getChances());
                if (player.hasNoChances()) {
                    System.out.println("Sorry, you are out of guesses. The word was: " + selectedWord);
                    break;
                }
                // end::use-numberGuesses[]
            }
        }

        if (currentAnswer.equalsIgnoreCase(selectedWord)) {
            System.out.println("That is correct! You escaped the noose .. this time.");
        }
    }

    private static String fillInChar(String answerWord, char guessedChar) {
        // tag::string-builder[]
        // tag::construct-string-builder[]
        StringBuilder result = new StringBuilder();
        // end::construct-string-builder[]
        for (int i = 0; i < selectedWord.length(); i++) {
            if (guessedChar == selectedWord.charAt(i)) {
                result.append(guessedChar);
            } else {
                result.append(answerWord.charAt(i));
            }
        }
        return result.toString();
        // end::string-builder[]
    }
}