import models.Student;
import Services.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainGUI extends JFrame {
    private StudentService studentService;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, ageField, emailField, courseField, idField;

    public MainGUI() {
        studentService = new StudentService();

        // Set up the main frame
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table to display students
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Email", "Course"}, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for inputs and buttons
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        // ID Field for Update/Delete/View
        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        // Name input
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // Age input
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        // Email input
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        // Course input
        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);

        // Buttons for actions
        JButton addButton = new JButton("Add Student");
        inputPanel.add(addButton);
        JButton updateButton = new JButton("Update Student");
        inputPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete Student");
        inputPanel.add(deleteButton);
        JButton viewButton = new JButton("View Student");
        inputPanel.add(viewButton);
        JButton viewAllButton = new JButton("View All Students");
        inputPanel.add(viewAllButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStudent();
            }
        });

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllStudents();
            }
        });

        // Initial data load
        viewAllStudents();
    }

    // Methods to handle each button action

    private void addStudent() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String email = emailField.getText();
        String course = courseField.getText();

        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setAge(age);
        newStudent.setEmail(email);
        newStudent.setCourse(course);

        studentService.addStudent(newStudent);
        viewAllStudents();  // Refresh the table
        clearFields();
    }

    private void updateStudent() {
        int id = Integer.parseInt(idField.getText());
        Student student = studentService.getStudent(id);

        if (student == null) {
        	JOptionPane.showMessageDialog(this, "Student not found.");
            
        } else {
        	student.setName(nameField.getText());
            student.setAge(Integer.parseInt(ageField.getText()));
            student.setEmail(emailField.getText());
            student.setCourse(courseField.getText());

            studentService.updateStudent(student);
            viewAllStudents();
            clearFields();
        }
    }

    private void deleteStudent() {
        int id = Integer.parseInt(idField.getText());
        studentService.deleteStudent(id);
        viewAllStudents();
        clearFields();
    }
  
    private void viewStudent() {
        int id = Integer.parseInt(idField.getText());
        Student student = studentService.getStudent(id);

        if (student != null) {
            nameField.setText(student.getName());
            ageField.setText(String.valueOf(student.getAge()));
            emailField.setText(student.getEmail());
            courseField.setText(student.getCourse());
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.");
        }
    } 
    

    private void viewAllStudents() {
        List<Student> students = studentService.getAllStudents();
        tableModel.setRowCount(0);  // Clear existing data

        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getEmail(),
                    student.getCourse()
            });
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        emailField.setText("");
        courseField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               coverPage cover = new coverPage();             
               cover.setVisible(true);
           
               
            }
        });
    }
}
