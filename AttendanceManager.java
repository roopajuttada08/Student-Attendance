import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Core business logic for managing students and their attendance.
 * Tracks total classes held per student and how many were attended.
 */
public class AttendanceManager {

    private final Map<Integer, Student> students = new LinkedHashMap<>();
    // studentId -> [presentCount, totalCount]
    private final Map<Integer, int[]> attendance = new HashMap<>();

    /** Adds a new student. Returns false if the id already exists. */
    public boolean addStudent(int id, String name) {
        if (students.containsKey(id)) {
            return false;
        }
        students.put(id, new Student(id, name));
        attendance.put(id, new int[]{0, 0});
        return true;
    }

    /** Marks a student present for one class. */
    public boolean markPresent(int id) {
        return record(id, true);
    }

    /** Marks a student absent for one class. */
    public boolean markAbsent(int id) {
        return record(id, false);
    }

    private boolean record(int id, boolean present) {
        int[] counts = attendance.get(id);
        if (counts == null) {
            return false; // unknown student
        }
        counts[1]++; // total
        if (present) {
            counts[0]++; // present
        }
        return true;
    }

    /** Attendance percentage for a student (0 if no classes recorded). */
    public double attendancePercentage(int id) {
        int[] counts = attendance.get(id);
        if (counts == null || counts[1] == 0) {
            return 0.0;
        }
        return (counts[0] * 100.0) / counts[1];
    }

    /** True if the student is eligible (>= 75% attendance). */
    public boolean isEligible(int id) {
        return attendancePercentage(id) >= 75.0;
    }

    public Student getStudent(int id) {
        return students.get(id);
    }

    public boolean hasStudent(int id) {
        return students.containsKey(id);
    }

    public Map<Integer, Student> getAllStudents() {
        return students;
    }

    int[] countsFor(int id) {
        return attendance.getOrDefault(id, new int[]{0, 0});
    }
}
