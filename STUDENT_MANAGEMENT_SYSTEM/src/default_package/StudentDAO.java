package default_package;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private List<Student> students = new ArrayList<>();

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Find a student by roll number
    public Student getStudentByRollNo(String rollNo) {
        for (Student student : students) {
            if (student.getRollNo().equals(rollNo)) {
                return student;
            }
        }
        return null; // Return null if student not found
    }

    // Update student details
    public boolean updateStudent(String rollNo, String newName, int newAge) {
        Student student = getStudentByRollNo(rollNo);
        if (student != null) {
            student.setName(newName);
            student.setAge(newAge);
            return true;
        }
        return false;
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return students;
    }
}

