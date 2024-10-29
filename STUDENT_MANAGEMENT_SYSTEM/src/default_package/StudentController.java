package default_package;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentController extends JFrame {
    private JTextField rollNoField;
    private JButton searchButton, allDetailsButton, selectOperationButton;
    private StudentService studentService = new StudentService();

    public StudentController() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.CYAN);
        JLabel headerLabel = new JLabel("Student Management System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("SEARCH STUDENT DETAILS"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel rollNoLabel = new JLabel("Student RollNo:");
        rollNoField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(rollNoLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(rollNoField, gbc);

        searchButton = new JButton("Search Student");
        allDetailsButton = new JButton("All Students Details");
        selectOperationButton = new JButton("Select Operation");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(searchButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(allDetailsButton, gbc);

        gbc.gridy = 3;
        mainPanel.add(selectOperationButton, gbc);

        // Event listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        allDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllStudents();
            }
        });

        selectOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openOperationDialog();
            }
        });

        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    // Method to open a dialog for additional operations (Add/Edit)
    private void openOperationDialog() {
        JDialog operationDialog = new JDialog(this, "Select Operation", true);
        operationDialog.setSize(400, 200);
        operationDialog.setLayout(new FlowLayout());
        operationDialog.setLocationRelativeTo(this);

        JButton addButton = new JButton("Add Student");
        JButton editButton = new JButton("Edit Student");
        JButton closeButton = new JButton("Close");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationDialog.dispose(); // Close the dialog
            }
        });

        operationDialog.add(addButton);
        operationDialog.add(editButton);
        operationDialog.add(closeButton);
        operationDialog.setVisible(true);
    }

    private void searchStudent() {
        String rollNo = rollNoField.getText();
        Student student = studentService.getStudentByRollNo(rollNo);
        if (student != null) {
            JOptionPane.showMessageDialog(this, "Found Student: " + student);
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    private void showAllStudents() {
        List<Student> students = studentService.getAllStudents();
        StringBuilder message = new StringBuilder("All Students:\n");
        for (Student student : students) {
            message.append(student).append("\n");
        }
        JOptionPane.showMessageDialog(this, message.toString());
    }

    private void addStudent() {
        String rollNo = JOptionPane.showInputDialog("Enter Roll No:");
        String name = JOptionPane.showInputDialog("Enter Name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
        studentService.addStudent(rollNo, name, age);
        JOptionPane.showMessageDialog(this, "Student added successfully!");
    }

    private void editStudent() {
        String rollNo = JOptionPane.showInputDialog("Enter Roll No of the student to edit:");
        Student student = studentService.getStudentByRollNo(rollNo);
        if (student != null) {
            String newName = JOptionPane.showInputDialog("Enter new name:", student.getName());
            int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new age:", student.getAge()));
            if (studentService.updateStudent(rollNo, newName, newAge)) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error updating student.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentController().setVisible(true));
    }
}
