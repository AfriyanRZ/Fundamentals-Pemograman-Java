// Aplikasi Manajemen Tugas
import java.util.ArrayList;
import java.util.Scanner;

// Task class (Encapsulation)
class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }
}

// TaskManager class (Core Logic)
class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("Task added: " + description);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Task List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getDescription() +
                    (task.isCompleted() ? " (Completed)" : " (Pending)"));
        }
    }

    public void markTaskAsCompleted(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }
        Task task = tasks.get(index);
        if (task.isCompleted()) {
            System.out.println("Task is already completed!");
        } else {
            task.markAsCompleted();
            System.out.println("Task marked as completed: " + task.getDescription());
        }
    }

    public void removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }
        Task task = tasks.remove(index);
        System.out.println("Task removed: " + task.getDescription());
    }
}

// Main class
public class ToDoListApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- To-Do List Management System ---");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(description);
                    break;
                case 2:
                    taskManager.listTasks();
                    break;
                case 3:
                    System.out.print("Enter task number to mark as completed: ");
                    int taskIndex = scanner.nextInt() - 1;
                    taskManager.markTaskAsCompleted(taskIndex);
                    break;
                case 4:
                    System.out.print("Enter task number to remove: ");
                    int removeIndex = scanner.nextInt() - 1;
                    taskManager.removeTask(removeIndex);
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
