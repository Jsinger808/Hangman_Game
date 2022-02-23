package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {

    public static void main (String[] args) throws FileNotFoundException {

    //Picks random word from dictionary.
        Scanner lineScanner = new Scanner(new File("D:\\Downloads D\\wordlist.txt"));

        int lineCounter = 0;
        while (lineScanner.hasNext()) {
            lineScanner.nextLine();
            lineCounter++;
        }

        Random ran = new Random();
        int random = ran.nextInt(lineCounter);
        int currentLine = 0;

        Scanner scanner = new Scanner(new File("D:\\Downloads D\\wordlist.txt"));
        while (currentLine < random) {
            scanner.nextLine();
            currentLine++;
        }
        String chosenWord = scanner.nextLine();
        System.out.println(chosenWord);

    //Creates a hangman game based off chosen word
        int tries = 6;
        boolean badGuess = false;
        boolean letterAdded = false;
        String guess;
        List<String> chosenWordArray = new ArrayList(Arrays.asList(chosenWord.split("")));
        List<String> hangManArray = new ArrayList();
        List<String> lettersUsedArray = new ArrayList();

        Scanner userInput = new Scanner(System.in);
        for (int i = 0; i < chosenWordArray.size(); i++) {
            if (i == 0){
                hangManArray.add(" _ ");
            }
            else {
                hangManArray.add("_ ");
            }
        }


        while (tries > 0) {

            System.out.println("Guess your letter.");

            //Checks if guess is a single letter
            guess = userInput.nextLine();
            while (guess.length() > 1) {
                System.out.println("Please enter a single letter.");
                guess = userInput.nextLine();
            }

            //Checks if letter added was already present
            while (!letterAdded) {
                if (lettersUsedArray.contains(guess)) {
                    System.out.println("You have already entered this letter. Please enter a new one.");
                    guess = userInput.nextLine();
                }
                else {
                    lettersUsedArray.add(guess);
                    letterAdded = true;
                }
            }
            letterAdded = false;

            //Checks if guessed letter was in chosen word
            for (int i = 0; i < chosenWordArray.size(); i++) {
                if (guess.equals(chosenWordArray.get(i))) {
                    hangManArray.set(i, guess);
                    badGuess = true;
                }
            }
            if (!badGuess) {
                tries--;
                System.out.println("Bad guess. You have " + tries + " tries remaining.");
            }
            badGuess = false;

            System.out.println("Letters used: " + lettersUsedArray);
            System.out.println("Current Hangman Board " + hangManArray);

            //Victory or failure conditions
            if (!hangManArray.contains("_ ")) {
                System.out.println("You win!");
                return;
            }
            if (tries == 0){
                System.out.println("Failure. The word was '" + chosenWord + ".' Please try again!");
            }
        }
    }

}
