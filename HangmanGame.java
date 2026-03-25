// ============================================
// HangmanGame.java
// © Klaus Barth Rochol
// ============================================

import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = {"love", "joy", "peace", "patience", "kindness", "faithfulness"};

    private static String word;
    private static StringBuilder guessedWord;
    private static int attemptsLeft;
    private static Scanner scanner = new Scanner(System.in);
    // Scanner als Klassenfeld — wird nicht mehr bei jedem Aufruf neu erstellt

    public static void main(String[] args) {
        play();
        scanner.close();
    }

    public static void play() {
        initializeGame();

        while (attemptsLeft > 0 && guessedWord.indexOf("_") != -1) {
            System.out.println("\nAttempts left: " + attemptsLeft);
            System.out.println("Word: " + guessedWord.toString());

            char guess = getGuessFromPlayer();
            checkGuess(guess);
        }

        if (guessedWord.indexOf("_") == -1) {
            System.out.println("\nCongratulations! You guessed the word: " + word);
        } else {
            System.out.println("\nGame over! You ran out of attempts. The word was: " + word);
        }
    }

    private static void initializeGame() {
        word = getRandomWord();
        guessedWord = new StringBuilder(word.length());

        for (int i = 0; i < word.length(); i++) {
            guessedWord.append("_");
        }

        attemptsLeft = 6;
    }

    private static String getRandomWord() {
        int index = (int) (Math.random() * WORDS.length);
        return WORDS[index];
    }

    private static char getGuessFromPlayer() {
        System.out.print("Enter your guess: ");
        String input = scanner.nextLine().toLowerCase();

        while (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("Invalid guess. Please enter a single letter.");
            System.out.print("Enter your guess: ");
            input = scanner.nextLine().toLowerCase();
        }

        return input.charAt(0);
    }

    private static void checkGuess(char guess) {
        boolean guessedCorrectly = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedWord.setCharAt(i, guess);
                guessedCorrectly = true;
            }
        }

        if (guessedCorrectly) {
            System.out.println("Correct guess!");
        } else {
            System.out.println("Incorrect guess!");
            attemptsLeft--;
        }
    }

}
