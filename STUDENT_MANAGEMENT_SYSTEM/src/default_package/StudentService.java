package default_package;

import java.util.List;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    // Add a new student
    public void addStudent(String rollNo, String name, int age) {
        Student student = new Student(rollNo, name, age);
        studentDAO.addStudent(student);
    }

    // Get student by roll number
    public Student getStudentByRollNo(String rollNo) {
        return studentDAO.getStudentByRollNo(rollNo);
    }

    // Update student details
    public boolean updateStudent(String rollNo, String newName, int newAge) {
        return studentDAO.updateStudent(rollNo, newName, newAge);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}
