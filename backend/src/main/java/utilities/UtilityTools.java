import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.*;

/**
 * Utility Tools Collection
 * Word Counter, URL Slug Generator, Quote Generator, 
 * Palindrome Checker, Prime Number Checker, Live Clock
 */

// Word Counter
class WordCounter {
    public static void run(Scanner scanner) {
        System.out.println("\n=== WORD COUNTER ===");
        System.out.println("Enter text (type 'END' on a new line to finish):");
        scanner.nextLine(); // Consume newline
        
        StringBuilder text = new StringBuilder();
        String line;
        
        while (!(line = scanner.nextLine()).equals("END")) {
            text.append(line).append("\n");
        }
        
        String content = text.toString();
        int words = countWords(content);
        int chars = content.length();
        int lines = content.split("\n").length;
        
        System.out.println("\nStatistics:");
        System.out.println("Words: " + words);
        System.out.println("Characters: " + chars);
        System.out.println("Lines: " + lines);
    }
    
    private static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }
}

// URL Slug Generator
class URLSlugGenerator {
    public static void run(Scanner scanner) {
        System.out.println("\n=== URL SLUG GENERATOR ===");
        scanner.nextLine(); // Consume newline
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        String slug = generateSlug(text);
        System.out.println("Generated Slug: " + slug);
    }
    
    private static String generateSlug(String text) {
        return text.toLowerCase()
                   .trim()
                   .replaceAll("[^a-z0-9\\s-]", "")
                   .replaceAll("\\s+", "-")
                   .replaceAll("-+", "-");
    }
}

// Quote Generator
class QuoteGenerator {
    private static final String[] QUOTES = {
        "The only way to do great work is to love what you do. - Steve Jobs",
        "Innovation distinguishes between a leader and a follower. - Steve Jobs",
        "Life is what happens when you're busy making other plans. - John Lennon",
        "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
        "It is during our darkest moments that we must focus to see the light. - Aristotle",
        "Be yourself; everyone else is already taken. - Oscar Wilde",
        "The only impossible journey is the one you never begin. - Tony Robbins",
        "In the middle of difficulty lies opportunity. - Albert Einstein",
        "Success is not final, failure is not fatal. - Winston Churchill",
        "Believe you can and you're halfway there. - Theodore Roosevelt"
    };
    
    public static void run() {
        System.out.println("\n=== QUOTE GENERATOR ===");
        Random random = new Random();
        String quote = QUOTES[random.nextInt(QUOTES.length)];
        System.out.println("\n\"" + quote + "\"");
    }
}

// Palindrome Checker
class PalindromeChecker {
    public static void run(Scanner scanner) {
        System.out.println("\n=== PALINDROME CHECKER ===");
        scanner.nextLine(); // Consume newline
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        boolean isPalindrome = checkPalindrome(text);
        
        if (isPalindrome) {
            System.out.println("\"" + text + "\" is a palindrome! ✓");
        } else {
            System.out.println("\"" + text + "\" is not a palindrome.");
        }
    }
    
    private static boolean checkPalindrome(String text) {
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed) && !cleaned.isEmpty();
    }
}

// Prime Number Checker
class PrimeNumberChecker {
    public static void run(Scanner scanner) {
        System.out.println("\n=== PRIME NUMBER CHECKER ===");
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        
        if (num < 2) {
            System.out.println("Please enter a number greater than 1.");
            return;
        }
        
        boolean isPrime = checkPrime(num);
        
        if (isPrime) {
            System.out.println(num + " is a prime number! ✓");
        } else {
            System.out.println(num + " is not a prime number.");
        }
    }
    
    private static boolean checkPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}

// Live Clock
class LiveClock {
    public static void run() {
        System.out.println("\n=== LIVE CLOCK ===");
        System.out.println("Displaying current time (Press Ctrl+C to stop)...\n");
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        
        try {
            for (int i = 0; i < 10; i++) { // Display 10 times
                LocalDateTime now = LocalDateTime.now();
                System.out.print("\rTime: " + now.format(timeFormatter) + 
                               " | Date: " + now.format(dateFormatter));
                Thread.sleep(1000);
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("\nClock stopped.");
        }
    }
}