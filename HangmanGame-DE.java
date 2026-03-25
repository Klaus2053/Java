// ============================================
// HangmanGame.java
// © Klaus Barth Rochol
// ============================================

import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = {"liebe", "freude", "frieden", "geduld", "guete", "treue"};

    private static String word;
    private static StringBuilder geratenesBuchwort;
    private static int versucheUebrig;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        spielen();
        scanner.close();
    }

    public static void spielen() {
        spielInitialisieren();

        while (versucheUebrig > 0 && geratenesBuchwort.indexOf("_") != -1) {
            System.out.println("\nVersuche übrig: " + versucheUebrig);
            System.out.println("Wort: " + geratenesBuchwort.toString());

            char tipp = tippVomSpielerHolen();
            tippPruefen(tipp);
        }

        if (geratenesBuchwort.indexOf("_") == -1) {
            System.out.println("\nGlückwunsch! Du hast das Wort erraten: " + word);
        } else {
            System.out.println("\nSpiel vorbei! Du hast keine Versuche mehr. Das Wort war: " + word);
        }
    }

    private static void spielInitialisieren() {
        word = zufaelligesWortHolen();
        geratenesBuchwort = new StringBuilder(word.length());

        for (int i = 0; i < word.length(); i++) {
            geratenesBuchwort.append("_");
        }

        versucheUebrig = 6;
    }

    private static String zufaelligesWortHolen() {
        int index = (int) (Math.random() * WORDS.length);
        return WORDS[index];
    }

    private static char tippVomSpielerHolen() {
        System.out.print("Gib deinen Tipp ein: ");
        String eingabe = scanner.nextLine().toLowerCase();

        while (eingabe.length() != 1 || !Character.isLetter(eingabe.charAt(0))) {
            System.out.println("Ungültiger Tipp. Bitte gib einen einzelnen Buchstaben ein.");
            System.out.print("Gib deinen Tipp ein: ");
            eingabe = scanner.nextLine().toLowerCase();
        }

        return eingabe.charAt(0);
    }

    private static void tippPruefen(char tipp) {
        boolean richtigGetippt = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == tipp) {
                geratenesBuchwort.setCharAt(i, tipp);
                richtigGetippt = true;
            }
        }

        if (richtigGetippt) {
            System.out.println("Richtiger Tipp!");
        } else {
            System.out.println("Falscher Tipp!");
            versucheUebrig--;
        }
    }
}
