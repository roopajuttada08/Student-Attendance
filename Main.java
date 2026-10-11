import java.util.Map;
import java.util.Scanner;

/**
 * Student Attendance System - console application.
 *
 * Features:
 *  - Register students
 *  - Mark attendance (present / absent)
 *  - View individual and class-wide attendance reports
 *  - Check eligibility (>= 75% attendance)
 *
 * Run:  javac *.java && java Main
 */
public class Main {

    private static final AttendanceManager manager = new AttendanceManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("   STUDENT ATTENDANCE SYSTEM");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "Select an option: ");
            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> markAttendance(scanner);
                case 3 -> viewStudentReport(scanner);
                case 4 -> viewClassReport();
                case 5 -> running = false;
                default -> System.out.println("⚠ Invalid option, try again.");
            }
        }
        System.out.println("👋 Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--------- MENU ---------");
        System.out.println("1. Add student");
        System.out.println("2. Mark attendance");
        System.out.println("3. View student report");
        System.out.println("4. View class report");
        System.out.println("5. Exit");
    }

    private static void addStudent(Scanner scanner) {
        int id = readInt(scanner, "Enter student ID: ");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("⚠ Name cannot be empty.");
            return;
        }
        boolean added = manager.addStudent(id, name);
        System.out.println(added
                ? "✅ Student added."
                : "⚠ A student with ID " + id + " already exists.");
    }

    private static void markAttendance(Scanner scanner) {
        int id = readInt(scanner, "Enter student ID: ");
        if (!manager.hasStudent(id)) {
            System.out.println("⚠ No student found with ID " + id);
            return;
        }
        int status = readInt(scanner, "Mark 1 = Present, 2 = Absent: ");
        boolean ok = switch (status) {
            case 1 -> manager.markPresent(id);
            case 2 -> manager.markAbsent(id);
            default -> false;
        };
        System.out.println(ok
                ? "✅ Attendance recorded."
                : "⚠ Invalid status.");
    }

    private static void viewStudentReport(Scanner scanner) {
        int id = readInt(scanner, "Enter student ID: ");
        Student s = manager.getStudent(id);
        if (s == null) {
            System.out.println("⚠ No student found with ID " + id);
            return;
        }
        int[] counts = manager.countsFor(id);
        double pct = manager.attendancePercentage(id);
        System.out.printf("%nReport for %s (%d)%n", s.getName(), s.getId());
        System.out.printf("  Attended: %d / %d classes%n", counts[0], counts[1]);
        System.out.printf("  Attendance: %.2f%%%n", pct);
        System.out.println("  Eligibility: " + (manager.isEligible(id) ? "✅ Eligible" : "❌ Shortage"));
    }

    private static void viewClassReport() {
        Map<Integer, Student> all = manager.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        System.out.printf("%n%-6s %-20s %-12s %-10s%n", "ID", "Name", "Attendance", "Status");
        System.out.println("-------------------------------------------------------");
        for (Student s : all.values()) {
            int[] counts = manager.countsFor(s.getId());
            double pct = manager.attendancePercentage(s.getId());
            String status = manager.isEligible(s.getId()) ? "Eligible" : "Shortage";
            System.out.printf("%-6d %-20s %-12s %-10s%n",
                    s.getId(), s.getName(), String.format("%.2f%%", pct), status);
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Please enter a valid number.");
            }
        }
    }
}
