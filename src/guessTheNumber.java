import java.util.Scanner;

public class guessTheNumber {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int myNumber = (int)(Math.random()*100);
        int numberOfTries = 0;

        System.out.println("Welcome to the 'Guess the number game'!");
        System.out.println("A random number between 1-100 has been assigned.");
        System.out.println("And it is your job to figure it out in 10 tries or the WORLD ENDS!!");
        System.out.println("Just kidding, also u can quit the game by typing -1 as your guess.");
        System.out.println("Good Luck :)");

        while (true) {
            System.out.print("Enter your guess: ");
            numberOfTries++;
            int myGuess = sc.nextInt();
            
            if (myGuess <= 100 && myGuess >= -1) {
                if (myGuess == myNumber) {
                    System.out.println("Congratulations! Your guess is correct!!");
                    System.out.println("It took you " + numberOfTries + " tries to guess");

                    if (numberOfTries <= 10) {
                        System.out.println("Well atleast you would be able to save the world if that happened :P");
                    } 
                    else {
                        System.out.println("Thank god that we didn't bet on you to save the world ;)");
                    }
                    break;
                }
                else if (myGuess == -1) {
                    System.out.println("Thanks for trying.");
                    System.out.println("The number was "+ myNumber);
                    System.out.println("A quiter smh...");
                    break;
                }
                else if (myGuess < myNumber) {
                    System.out.println("Your guess is smaller, think big");
                }
                else if (myGuess > myNumber) {
                    System.out.println("Your guess is larger, think small");
                }
                else {
                    System.out.println("Error! This was never supposed to happen...");
                }
            }
            else {
                System.out.println("Invalid input. Try again");
                System.out.println("Also this will be counted as a guess. Hehehe!");
            }
        }
        sc.close();
    }
}
