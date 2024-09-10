import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

// Main class for the Hi Lo game
public class hilo {
    // Main method - entry point of the program
    public static void main(String[] args) {
        // Scanner for reading user input
        Scanner scanner = new Scanner(System.in);
        // Random object for generating random numbers
        Random random = new Random();
        // Variable to store the target number
        int targetNumber;

        // Print game instructions
        System.out.println("Hi Lo Game");
        System.out.println("1. Auto-Mode (Random Target)");
        System.out.println("2. Manual Mode (Choose Target)");
        System.out.print("Choose mode: ");
        // Read user's choice for game mode
        int mode = scanner.nextInt();

        // If user chooses manual mode
        if (mode == 2) {
            System.out.print("Enter a target number (1-1000): ");
            // Read the target number from user
            targetNumber = scanner.nextInt();
        } else {
            // If auto-mode, generate a random number between 1 and 1000
            targetNumber = random.nextInt(1000) + 1;
        }

        // Print the target number for reference
        System.out.println("Target Number: " + targetNumber);
        System.out.println();

        // Call each guessing algorithm and store the number of guesses
        int guessesRandomDuplicates = randomGuessWithDuplicates(targetNumber);
        int guessesRandomNoDuplicates = randomGuessWithoutDuplicates(targetNumber);
        int guessesSequential = sequentialSearch(targetNumber);
        int guessesBinary = binarySearch(targetNumber, 1, 1000);

        // Print the results from each algorithm
        System.out.println("Random Guess (With Duplicates): " + guessesRandomDuplicates + " guesses.");
        System.out.println("Random Guess (No Duplicates): " + guessesRandomNoDuplicates + " guesses.");
        System.out.println("Sequential Search: " + guessesSequential + " guesses.");
        System.out.println("Binary Search: " + guessesBinary + " guesses.");
    }

    // Random guess algorithm that allows duplicates
    static int randomGuessWithDuplicates(int target) {
        // Create a Random object
        Random random = new Random();
        // Variables to store the current guess and count of guesses
        int guess, count = 0;
        do {
            // Generate a random guess between 1 and 1000
            guess = random.nextInt(1000) + 1;
            // Increment the count of guesses
            count++;
        // Continue until the guess matches the target
        } while (guess != target);
        // Return the total number of guesses made
        return count;
    }

    // Random guess algorithm that avoids duplicates
    static int randomGuessWithoutDuplicates(int target) {
        // Create a Random object
        Random random = new Random();
        // HashSet to store already guessed numbers
        HashSet<Integer> guessedNumbers = new HashSet<>();
        // Variables for the current guess and count of guesses
        int guess, count = 0;
        do {
            do {
                // Generate a random guess
                guess = random.nextInt(1000) + 1;
            // Check if the guess was already made
            } while (guessedNumbers.contains(guess));
            // Add the new guess to the set of guessed numbers
            guessedNumbers.add(guess);
            // Increment the guess count
            count++;
        // Repeat until the guess matches the target
        } while (guess != target);
        // Return the total number of guesses made
        return count;
    }

    // Sequential search algorithm
    static int sequentialSearch(int target) {
        // Variable to count the number of guesses
        int count = 0;
        // Loop from 1 to 1000
        for (int i = 1; i <= 1000; i++) {
            // Increment the guess count
            count++;
            // If the current number is the target
            if (i == target) {
                // Stop the loop
                break;
            }
        }
        // Return the total number of guesses made
        return count;
    }

    // Binary search algorithm
    static int binarySearch(int target, int low, int high) {
        // Variable to count the number of guesses
        int count = 0;
        // Loop until the range is valid
        while (low <= high) {
            // Calculate the middle of the current range
            int mid = low + (high - low) / 2;
            // Increment the guess count
            count++;
            // Check if the middle is the target
            if (mid == target) {
                // If yes, return the count
                return count;
            } else if (mid < target) {
                // If the guess is too low, adjust the lower bound
                low = mid + 1;
            } else {
                // If the guess is too high, adjust the upper bound
                high = mid - 1;
            }
        }
        // Return the total number of guesses made
        return count;
    }
}
