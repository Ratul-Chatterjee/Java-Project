import java.util.*;

/**
 * To-Do List Tool
 * Add, remove, and check off tasks
 */
public class TodoList {
    private static List<Task> tasks = new ArrayList<>();
    
    static class Task {
        String description;
        boolean completed;
        
        Task(String description) {
            this.description = description;
            this.completed = false;
        }
        
        @Override
        public String toString() {
            return (completed ? "[✓] " : "[ ] ") + description;
        }
    }
    
    public static void run(Scanner scanner) {
        System.out.println("\n=== TO-DO LIST ===");
        
        while (true) {
            System.out.println("\n1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Remove task");
            System.out.println("5. Back to main menu");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    tasks.add(new Task(task));
                    System.out.println("Task added successfully!");
                    break;
                    
                case 2:
                    viewTasks();
                    break;
                    
                case 3:
                    viewTasks();
                    if (!tasks.isEmpty()) {
                        System.out.print("Enter task number to complete: ");
                        int completeIndex = scanner.nextInt() - 1;
                        if (completeIndex >= 0 && completeIndex < tasks.size()) {
                            tasks.get(completeIndex).completed = true;
                            System.out.println("Task marked as completed!");
                        } else {
                            System.out.println("Invalid task number!");
                        }
                    }
                    break;
                    
                case 4:
                    viewTasks();
                    if (!tasks.isEmpty()) {
                        System.out.print("Enter task number to remove: ");
                        int removeIndex = scanner.nextInt() - 1;
                        if (removeIndex >= 0 && removeIndex < tasks.size()) {
                            tasks.remove(removeIndex);
                            System.out.println("Task removed successfully!");
                        } else {
                            System.out.println("Invalid task number!");
                        }
                    }
                    break;
                    
                case 5:
                    return;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks yet. Add one above!");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}