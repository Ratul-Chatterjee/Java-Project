import java.util.Scanner;

/**
 * Financial and Health Calculators
 * Interest Calculator and BMI Calculator
 */

// Interest Calculator
class InterestCalculator {
    public static void run(Scanner scanner) {
        System.out.println("\n=== INTEREST CALCULATOR ===");
        System.out.println("1. Simple Interest");
        System.out.println("2. Compound Interest");
        System.out.print("Choice: ");
        
        int choice = scanner.nextInt();
        
        System.out.print("Enter principal amount: $");
        double principal = scanner.nextDouble();
        
        System.out.print("Enter annual interest rate (%): ");
        double rate = scanner.nextDouble();
        
        System.out.print("Enter time period (years): ");
        double time = scanner.nextDouble();
        
        double interest, total;
        
        if (choice == 1) {
            interest = calculateSimpleInterest(principal, rate, time);
            total = principal + interest;
            System.out.println("\n--- Simple Interest ---");
        } else {
            total = calculateCompoundInterest(principal, rate, time);
            interest = total - principal;
            System.out.println("\n--- Compound Interest ---");
        }
        
        System.out.printf("Principal: $%.2f%n", principal);
        System.out.printf("Interest Earned: $%.2f%n", interest);
        System.out.printf("Total Amount: $%.2f%n", total);
    }
    
    private static double calculateSimpleInterest(double p, double r, double t) {
        return (p * r * t) / 100;
    }
    
    private static double calculateCompoundInterest(double p, double r, double t) {
        return p * Math.pow(1 + r / 100, t);
    }
}

// BMI Calculator
class BMICalculator {
    public static void run(Scanner scanner) {
        System.out.println("\n=== BMI CALCULATOR ===");
        System.out.print("Enter weight (kg): ");
        double weight = scanner.nextDouble();
        
        System.out.print("Enter height (cm): ");
        double heightCm = scanner.nextDouble();
        
        double heightM = heightCm / 100;
        double bmi = weight / (heightM * heightM);
        
        String category;
        if (bmi < 18.5) category = "Underweight";
        else if (bmi < 25) category = "Normal weight";
        else if (bmi < 30) category = "Overweight";
        else category = "Obese";
        
        System.out.printf("%nYour BMI: %.1f%n", bmi);
        System.out.println("Category: " + category);
        
        System.out.println("\nBMI Categories:");
        System.out.println("Underweight: < 18.5");
        System.out.println("Normal weight: 18.5 - 24.9");
        System.out.println("Overweight: 25 - 29.9");
        System.out.println("Obese: ≥ 30");
    }
}