import java.util.*;

/**
 * Password Generator Tool
 * Generate secure passwords with customizable options
 */
public class PasswordGenerator {
    
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== PASSWORD GENERATOR ===");
        System.out.print("Enter password length (4-50): ");
        int length = scanner.nextInt();
        
        if (length < 4 || length > 50) {
            System.out.println("Invalid length! Using default 16.");
            length = 16;
        }
        
        System.out.print("Include uppercase letters? (y/n): ");
        boolean useUpper = scanner.next().equalsIgnoreCase("y");
        
        System.out.print("Include lowercase letters? (y/n): ");
        boolean useLower = scanner.next().equalsIgnoreCase("y");
        
        System.out.print("Include numbers? (y/n): ");
        boolean useNumbers = scanner.next().equalsIgnoreCase("y");
        
        System.out.print("Include symbols? (y/n): ");
        boolean useSymbols = scanner.next().equalsIgnoreCase("y");
        
        if (!useUpper && !useLower && !useNumbers && !useSymbols) {
            System.out.println("At least one character type must be selected!");
            return;
        }
        
        String password = generatePassword(length, useUpper, useLower, useNumbers, useSymbols);
        System.out.println("\nGenerated Password: " + password);
        System.out.println("Password Strength: " + getPasswordStrength(password));
    }
    
    private static String generatePassword(int length, boolean upper, boolean lower, 
                                          boolean numbers, boolean symbols) {
        StringBuilder chars = new StringBuilder();
        if (upper) chars.append(UPPERCASE);
        if (lower) chars.append(LOWERCASE);
        if (numbers) chars.append(NUMBERS);
        if (symbols) chars.append(SYMBOLS);
        
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        
        return password.toString();
    }
    
    private static String getPasswordStrength(String password) {
        int score = 0;
        
        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;:,.<>?].*")) score++;
        
        if (score <= 2) return "Weak";
        if (score <= 4) return "Medium";
        return "Strong";
    }
}