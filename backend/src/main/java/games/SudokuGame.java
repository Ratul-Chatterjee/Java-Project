import java.util.*;

/**
 * Sudoku Game
 * Classic 9x9 Sudoku puzzle generator and solver
 */
public class SudokuGame {
    private static int[][] board = new int[9][9];
    private static int[][] solution = new int[9][9];
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== SUDOKU GAME ===");
        generateSudoku();
        
        while (true) {
            printBoard();
            System.out.println("\n1. Enter number");
            System.out.println("2. Check solution");
            System.out.println("3. Show solution");
            System.out.println("4. New game");
            System.out.println("5. Back to main menu");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter row (0-8): ");
                    int row = scanner.nextInt();
                    System.out.print("Enter column (0-8): ");
                    int col = scanner.nextInt();
                    System.out.print("Enter number (1-9): ");
                    int num = scanner.nextInt();
                    
                    if (row >= 0 && row < 9 && col >= 0 && col < 9 && num >= 1 && num <= 9) {
                        if (board[row][col] == 0) {
                            board[row][col] = num;
                        } else {
                            System.out.println("Cannot modify fixed cell!");
                        }
                    } else {
                        System.out.println("Invalid input!");
                    }
                    break;
                    
                case 2:
                    if (checkSolution()) {
                        System.out.println("\n🎉 Congratulations! You solved it!");
                    } else {
                        System.out.println("\nNot quite right. Keep trying!");
                    }
                    break;
                    
                case 3:
                    System.out.println("\nSolution:");
                    printSolution();
                    break;
                    
                case 4:
                    generateSudoku();
                    System.out.println("New game started!");
                    break;
                    
                case 5:
                    return;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void generateSudoku() {
        // Initialize boards
        for (int i = 0; i < 9; i++) {
            Arrays.fill(board[i], 0);
            Arrays.fill(solution[i], 0);
        }
        
        // Fill diagonal 3x3 boxes
        fillDiagonalBoxes(solution);
        
        // Solve the rest
        solveSudoku(solution, 0, 0);
        
        // Copy to board and remove numbers
        for (int i = 0; i < 9; i++) {
            board[i] = solution[i].clone();
        }
        
        // Remove 40-45 numbers
        Random random = new Random();
        int cellsToRemove = 40 + random.nextInt(6);
        
        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            
            if (board[row][col] != 0) {
                board[row][col] = 0;
                cellsToRemove--;
            }
        }
    }
    
    private static void fillDiagonalBoxes(int[][] grid) {
        for (int box = 0; box < 9; box += 3) {
            fillBox(grid, box, box);
        }
    }
    
    private static void fillBox(int[][] grid, int row, int col) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);
        
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[row + i][col + j] = nums.get(idx++);
            }
        }
    }
    
    private static boolean solveSudoku(int[][] grid, int row, int col) {
        if (row == 9) return true;
        if (col == 9) return solveSudoku(grid, row + 1, 0);
        if (grid[row][col] != 0) return solveSudoku(grid, row, col + 1);
        
        for (int num = 1; num <= 9; num++) {
            if (isValid(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1)) return true;
                grid[row][col] = 0;
            }
        }
        
        return false;
    }
    
    private static boolean isValid(int[][] grid, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) return false;
        }
        
        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] == num) return false;
            }
        }
        
        return true;
    }
    
    private static void printBoard() {
        System.out.println("\n    0 1 2   3 4 5   6 7 8");
        System.out.println("  -------------------------");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("  -------------------------");
            }
            System.out.print(i + " | ");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (board[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("  -------------------------");
    }
    
    private static void printSolution() {
        System.out.println("\n    0 1 2   3 4 5   6 7 8");
        System.out.println("  -------------------------");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("  -------------------------");
            }
            System.out.print(i + " | ");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(solution[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("  -------------------------");
    }
    
    private static boolean checkSolution() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != solution[i][j]) return false;
            }
        }
        return true;
    }
}