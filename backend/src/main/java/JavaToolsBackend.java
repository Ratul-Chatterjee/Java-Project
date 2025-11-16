import java.util.*;

/**
 * Java Tools Backend
 * Main backend logic for all 15 tools
 * Developed by: Ratul Chatterjee
 */
public class JavaToolsBackend {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Java Tools Backend ===");
        System.out.println("Developed by: Ratul Chatterjee\n");
        
        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-16, 0 to exit): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (choice == 0) {
                System.out.println("Thank you for using Java Tools!");
                break;
            }
            
            handleChoice(choice, scanner);
            System.out.println("\n" + "=".repeat(50) + "\n");
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("Select a tool:");
        System.out.println("1.  To-Do List");
        System.out.println("2.  Temperature Converter");
        System.out.println("3.  Password Generator");
        System.out.println("4.  Calculator");
        System.out.println("5.  Word Counter");
        System.out.println("6.  URL Slug Generator");
        System.out.println("7.  Quote Generator");
        System.out.println("8.  Palindrome Checker");
        System.out.println("9.  Prime Number Checker");
        System.out.println("10. Interest Calculator");
        System.out.println("11. BMI Calculator");
        System.out.println("12. Live Clock");
        System.out.println("13. Number Guessing Game");
        System.out.println("14. Chatbot");
        System.out.println("15. Tic Tac Toe");
        System.out.println("16. Sudoku Game");
        System.out.println("0.  Exit");
    }
    
    private static void handleChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                TodoList.run(scanner);
                break;
            case 2:
                TemperatureConverter.run(scanner);
                break;
            case 3:
                PasswordGenerator.run(scanner);
                break;
            case 4:
                Calculator.run(scanner);
                break;
            case 5:
                WordCounter.run(scanner);
                break;
            case 6:
                URLSlugGenerator.run(scanner);
                break;
            case 7:
                QuoteGenerator.run();
                break;
            case 8:
                PalindromeChecker.run(scanner);
                break;
            case 9:
                PrimeNumberChecker.run(scanner);
                break;
            case 10:
                InterestCalculator.run(scanner);
                break;
            case 11:
                BMICalculator.run(scanner);
                break;
            case 12:
                LiveClock.run();
                break;
            case 13:
                NumberGuessingGame.run(scanner);
                break;
            case 14:
                SimpleChatbot.run(scanner);
                break;
            case 15:
                TicTacToe.run(scanner);
                break;
            case 16:
                SudokuGame.run(scanner);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }
}