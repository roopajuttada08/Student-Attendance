# 🎓 Student Attendance System

A console-based **Student Attendance System** built in **Java**. It lets faculty register students, mark daily attendance, and generate attendance reports with automatic eligibility checks.

## ✨ Features

- ➕ **Register students** with a unique ID
- ✅❌ **Mark attendance** as present or absent per class
- 📊 **Individual reports** — attendance % and eligibility
- 📋 **Class-wide report** — tabular summary of all students
- 🎯 **Eligibility rule** — flags students below 75% attendance
- 🛡️ **Input validation** — safe number parsing and duplicate checks

## 🧱 Tech Stack

- **Java 17+** (uses modern `switch` expressions and arrow syntax)
- No external dependencies — pure standard library

## 🚀 Getting Started

Make sure you have JDK 17 or newer installed.

```bash
# compile
javac *.java

# run
java Main
```

Then follow the on-screen menu.

## 📂 Project Structure

```
Student-Attendance/
├── Student.java            # Student model
├── AttendanceManager.java  # Core attendance logic
├── Main.java               # Interactive console UI
└── README.md
```

## 🧪 Sample Session

```
1. Add student      -> ID 101, Roopa
2. Mark attendance  -> 101 Present
3. View student report
   Report for Roopa (101)
     Attended: 1 / 1 classes
     Attendance: 100.00%
     Eligibility: ✅ Eligible
```

---

Built by [Roopa Juttada](https://github.com/roopajuttada08) 🚀
