import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/* 
(!) Refine later
- Make code more efficient and reduce lines of code while retaining its functionality if possible, in other words utilize everything that you know to make shorter code that is still logical and does what the program is intended to do.
- Properly indent between each group of steps to give better clarity as to the process of what's on

(!) Foolproof later
- Use String methods to take only the capital letter and first letter of the user
*/  

public class Main {
  public static void main(String[] args) {
    try {
      File myObj = new File("words.txt");
      Scanner myReader = new Scanner(myObj);
      String[] words = new String[279496];
      
      while(myReader.hasNextLine()) {  
        for(int i = 0; i < 279496; i++){
          words[i] = myReader.nextLine();
        }  // Words are in Array
      }
      // Ask how many players there are
      Scanner input = new Scanner(System.in);
      System.out.print("Enter the number of players: ");
      int numOfPlayers = input.nextInt();
      
      // Create array to tell what the Current Player is
      int[] currentPlayer = new int[numOfPlayers];
      for(int i = 0; i < numOfPlayers; i++) {
        currentPlayer[i] = i + 1;  // Label each player 1, 2, 3, ...
      }  // if numOfPlayers was 3, then currentPlayer[] = {1, 2, 3};

      // Create an array to tell what the Current Letter is
      String Letters = "";

      
      // Start the game loop
      int cP = 0;  // Begin with player 1
      String playerInput;
      boolean lettersMatch = false;
      boolean challengeLost = false;
      do {
        System.out.println("Player " + currentPlayer[cP] + ", it's your turn. The letters are " + Letters + ". Enter a letter or enter * to challenge.");

        playerInput = input.next();

        if(playerInput.equals("*")) {  // User challenges
          for(int i = 0; i < 279496; i++) { 
            if(words[i].length() < Letters.length()) {  
            continue;  // If the length of the word is less than the length of the current letters, skip the rest of this iteration
            }
            String wordsSubstring = words[i].substring(0, Letters.length());  // Create a wordSubstring to store the substring of the word that is of the same length of the current letters
            if(Letters.equals(wordsSubstring)) { 
              System.out.print(words[i] + " begins with those letters. Player " + currentPlayer[cP] + " loses!");
              challengeLost = true;
              break;  // If the current letters are the same as the substring of an actual word, then the current player loses.
            }
          }
          if (cP > 0 && challengeLost == false) System.out.print("No word begins with those letters. Player " + currentPlayer[cP - 1] + " loses!"); else if (cP == 0 && challengeLost == false) System.out.print("No word begins with those letters. Player " + numOfPlayers + " loses!"); break;  // If the current letters do not match the substring of an actual word, or any word in particular, the previous player loses.
        }
        
        Letters += playerInput;  // User enters a letter

        
        for(int i = 0; i < 279496; i++) {  // Loop through each word and see if it is the same as the current Letters
          if(Letters.equalsIgnoreCase(words[i])) {
            System.out.print(words[i] + " is a word. Player " + currentPlayer[cP] + " Loses!");
            lettersMatch = true;
            break;
          }
        }
        
        if(cP == (numOfPlayers - 1)) {  // Go back to first player
          cP = 0;
        } else cP++;
      } while(lettersMatch == false && challengeLost == false);






      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occured.");
      e.printStackTrace();
    }
    
  }
}