import java.util.Scanner;
import java.util.Arrays;
import java.util.Scanner;

public class HangPerson {

   private char[] word;
   private boolean[] guessed;
   private int numberWrongGuesses;
   private char[] guessedWordSoFar;

   Scanner wordsFileScanner;
   // Initializes boolean array tracking guessed words, the array used to display the status of the guessed word
   // and the word to be guessed based off of the scanner given
   public HangPerson(Scanner wordsFileScanner) {
      this.wordsFileScanner = wordsFileScanner;
      guessed = new boolean[26];
      resetGame();
      
   }

   // displays game introduction
   public void displayGameIntro() {
      System.out.println("Welcome to the hangperson game ...");
      System.out.println("To play, guess a letter to try to guess the word.");
      System.out.println("Every time you choose an incorrect letter another");
      System.out.println("body part appears on the gallows. If you guess the");
      System.out.println("word before you're hung, you win :-)");
      System.out.println("If you get hung you lose :-(");
      System.out.println();
      System.out.println("Time to guess ...");
   }

   // plays and resets game
   public void play() {
      displayVisualGameStatus();
      displayWrittenGameStatus();
   
      Scanner console = new Scanner(System.in);
      
      while (numberWrongGuesses < 7 && !Arrays.equals(guessedWordSoFar, word)) {
         System.out.print("Choose a letter => ");
         char guessedLetter = console.next().toLowerCase().charAt(0);
         int guessedIndex = guessedLetter - 'a';
         while (guessedIndex < 0 || guessedIndex > guessed.length - 1 || guessed[guessedIndex] == true) {
            System.out.println("Invalid Guess.  Guess a letter from a to z that you have not yet guessed");
            System.out.print("Choose a letter => ");
            guessedLetter = console.next().toLowerCase().charAt(0);
            guessedIndex = guessedLetter - 'a';
         }
         
         boolean letterFound = false;
         for (int i = 0; i < word.length; i++) {
            if (word[i] - 'a' == guessedIndex) {
               guessedWordSoFar[i] = guessedLetter;
               letterFound = true;
            }
         }
         
         if (!letterFound) numberWrongGuesses++;
         
         guessed[guessedIndex] = true;
         displayVisualGameStatus();
         displayWrittenGameStatus();
      }
      
      if (numberWrongGuesses >= 7) {
         System.out.println("You Loose :(");
         resetGame();
         return;
      } else if (Arrays.equals(guessedWordSoFar, word)) {
         System.out.println("You Win :)");
         resetGame();
         return;
      } else {
         System.out.print("Unexpected Behavior Occured");
      }
   }

   // displays the game status in writing
   private void displayWrittenGameStatus() {
      System.out.println();
      System.out.print("Letters guessed already => ");
      printLettersGuessedAlready();
      System.out.println("Number of wrong guesses => " + numberWrongGuesses);
      System.out.println("The word so far => " + String.valueOf(guessedWordSoFar));
      System.out.println();
   }

   // displays game status visually by showing the number of incorrect guesses
   private void displayVisualGameStatus() {
      System.out.println();
      for (int i = 0; i < numberWrongGuesses; i++) {
         System.out.print("X ");
      }
      for (int i = numberWrongGuesses; i < 7; i++) {
         System.out.print("O ");
      }
      System.out.println();
   }
   
   // prints the letters guessed by the player this round already
   private void printLettersGuessedAlready() {
      for (int i = 0; i < guessed.length; i++) {
         if (guessed[i]) {
            System.out.print((char)('a' + i));
         }
      }
      System.out.println();
   }

   // chooses a new word and resets the arrays and number of wrong guesses counter 
   // to their beginning values
   public void resetGame() {
      word = wordsFileScanner.next().toLowerCase().toCharArray();
      guessedWordSoFar = new char[word.length];
      Arrays.fill(guessedWordSoFar, '-');
      Arrays.fill(guessed, false);
      numberWrongGuesses = 0;
   }

}