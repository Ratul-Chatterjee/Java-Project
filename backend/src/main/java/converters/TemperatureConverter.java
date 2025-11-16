import java.util.Scanner;

/**
 * Temperature Converter Tool
 * Convert between Celsius, Fahrenheit, and Kelvin
 */
public class TemperatureConverter {
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== TEMPERATURE CONVERTER ===");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Celsius to Kelvin");
        System.out.println("3. Fahrenheit to Celsius");
        System.out.println("4. Fahrenheit to Kelvin");
        System.out.println("5. Kelvin to Celsius");
        System.out.println("6. Kelvin to Fahrenheit");
        System.out.print("Choice: ");
        
        int choice = scanner.nextInt();
        System.out.print("Enter temperature: ");
        double temp = scanner.nextDouble();
        
        double result = 0;
        String unit = "";
        
        switch (choice) {
            case 1:
                result = celsiusToFahrenheit(temp);
                unit = "°F";
                break;
            case 2:
                result = celsiusToKelvin(temp);
                unit = "K";
                break;
            case 3:
                result = fahrenheitToCelsius(temp);
                unit = "°C";
                break;
            case 4:
                result = fahrenheitToKelvin(temp);
                unit = "K";
                break;
            case 5:
                result = kelvinToCelsius(temp);
                unit = "°C";
                break;
            case 6:
                result = kelvinToFahrenheit(temp);
                unit = "°F";
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        System.out.printf("Result: %.2f %s%n", result, unit);
    }
    
    private static double celsiusToFahrenheit(double c) {
        return (c * 9.0 / 5.0) + 32;
    }
    
    private static double celsiusToKelvin(double c) {
        return c + 273.15;
    }
    
    private static double fahrenheitToCelsius(double f) {
        return (f - 32) * 5.0 / 9.0;
    }
    
    private static double fahrenheitToKelvin(double f) {
        return celsiusToKelvin(fahrenheitToCelsius(f));
    }
    
    private static double kelvinToCelsius(double k) {
        return k - 273.15;
    }
    
    private static double kelvinToFahrenheit(double k) {
        return celsiusToFahrenheit(kelvinToCelsius(k));
    }
}