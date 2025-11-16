import java.util.*;

/**
 * Game Collection
 * Number Guessing, Tic Tac Toe, Sudoku
 */

// Number Guessing Game
class NumberGuessingGame {
    public static void run(Scanner scanner) {
        System.out.println("\n=== NUMBER GUESSING GAME ===");
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 10;
        
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts to guess it!");
        
        while (attempts < maxAttempts) {
            System.out.print("\nAttempt " + (attempts + 1) + ": Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;
            
            if (guess == number) {
                System.out.println("🎉 Congratulations! You guessed it in " + attempts + " attempts!");
                return;
            } else if (guess < number) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
            
            System.out.println("Attempts remaining: " + (maxAttempts - attempts));
        }
        
        System.out.println("\nGame Over! The number was: " + number);
    }
}

// Tic Tac Toe
class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== TIC TAC TOE ===");
        initBoard();
        
        while (true) {
            printBoard();
            System.out.println("\nPlayer " + currentPlayer + "'s turn");
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            int col = scanner.nextInt();
            
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid position! Try again.");
                continue;
            }
            
            if (board[row][col] != ' ') {
                System.out.println("Position already taken! Try again.");
                continue;
            }
            
            board[row][col] = currentPlayer;
            
            if (checkWin()) {
                printBoard();
                System.out.println("\n🎉 Player " + currentPlayer + " wins!");
                break;
            }
            
            if (isBoardFull()) {
                printBoard();
                System.out.println("\nIt's a draw!");
                break;
            }
            
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }
    
    private static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }
    
    private static void printBoard() {
        System.out.println("\n  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---------");
        }
    }
    
    private static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true;
        }
        
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true;
        
        return false;
    }
    
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}

// Simple Chatbot
class SimpleChatbot {
    public static void run(Scanner scanner) {
        System.out.println("\n=== SIMPLE CHATBOT ===");
        System.out.println("Bot: Hello! I'm a simple chatbot. Type 'bye' to exit.");
        scanner.nextLine(); // Consume newline
        
        while (true) {
            System.out.print("\nYou: ");
            String input = scanner.nextLine().toLowerCase();
            
            if (input.equals("bye") || input.equals("exit")) {
                System.out.println("Bot: Goodbye! Have a great day!");
                break;
            }
            
            String response = getResponse(input);
            System.out.println("Bot: " + response);
        }
    }
    
    private static String getResponse(String input) {
        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! How can I help you today?";
        } else if (input.contains("how are you")) {
            return "I'm doing great, thank you for asking! How about you?";
        } else if (input.contains("name")) {
            return "I'm a simple chatbot created for Java Tools!";
        } else if (input.contains("time")) {
            return "The current time is " + java.time.LocalTime.now();
        } else if (input.contains("date")) {
            return "Today is " + java.time.LocalDate.now();
        } else if (input.contains("help")) {
            return "I can chat with you about basic topics. Try asking about the time, date, or just say hello!";
        } else {
            String[] responses = {
                "That's interesting! Tell me more.",
                "I see. What else would you like to know?",
                "Interesting question! I'm still learning.",
                "I'm not sure about that, but I'm here to chat!",
                "Can you elaborate on that?"
            };
            return responses[new Random().nextInt(responses.length)];
        }
    }
}