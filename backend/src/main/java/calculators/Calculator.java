import java.util.*;

/**
 * Calculator Tool
 * Basic calculator with history tracking
 */
public class Calculator {
    
    private static List<String> history = new ArrayList<>();
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== CALCULATOR ===");
        
        while (true) {
            System.out.println("\n1. Perform calculation");
            System.out.println("2. View history");
            System.out.println("3. Clear history");
            System.out.println("4. Back to main menu");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    performCalculation(scanner);
                    break;
                case 2:
                    viewHistory();
                    break;
                case 3:
                    history.clear();
                    System.out.println("History cleared!");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void performCalculation(Scanner scanner) {
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Enter operator (+, -, *, /): ");
        String operator = scanner.next();
        
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        
        double result = 0;
        boolean validOp = true;
        
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("Error: Division by zero!");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                System.out.println("Invalid operator!");
                validOp = false;
        }
        
        if (validOp) {
            String calculation = String.format("%.2f %s %.2f = %.2f", num1, operator, num2, result);
            System.out.println("Result: " + result);
            history.add(calculation);
        }
    }
    
    private static void viewHistory() {
        if (history.isEmpty()) {
            System.out.println("\nNo calculation history yet.");
        } else {
            System.out.println("\nCalculation History:");
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i));
            }
        }
    }
}