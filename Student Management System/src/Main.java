import models.Student;
import Services.StudentService;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Management System:");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Student");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");
      
            int choice = scanner.nextInt();
            switch (choice) {
            case 1:
            	

                System.out.println("Enter age:");
                int age = scanner.nextInt();  

                scanner.nextLine();  
                
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                


                System.out.println("Enter email:");
                String email = scanner.nextLine();

                System.out.println("Enter course:");
                String course = scanner.nextLine();

                Student newStudent = new Student();
                newStudent.setName(name);
                newStudent.setAge(age);
                newStudent.setEmail(email);
                newStudent.setCourse(course);

                studentService.addStudent(newStudent);
                break;
          

                case 2:
                    System.out.println("Enter student id to update:");
                    int id = scanner.nextInt();
                    Student updateStudent = studentService.getStudent(id);
                    if (updateStudent != null) {
                        System.out.println("Enter new name, age, email, course:");
                        updateStudent.setName(scanner.next());
                        updateStudent.setAge(scanner.nextInt());
                        scanner.nextLine();
                        updateStudent.setEmail(scanner.next());
                        updateStudent.setCourse(scanner.next());
                        studentService.updateStudent(updateStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.println("Enter student id to delete:");
                    int deleteId = scanner.nextInt();
                    studentService.deleteStudent(deleteId);
                    break;

                case 4:
                    System.out.println("Enter student id:");
                    int viewId = scanner.nextInt();
                    Student viewStudent = studentService.getStudent(viewId);
                    if (viewStudent != null) {
                        System.out.println("Name: " + viewStudent.getName());
                        System.out.println("Age: " + viewStudent.getAge());
                        System.out.println("Email: " + viewStudent.getEmail());
                        System.out.println("Course: " + viewStudent.getCourse());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    List<Student> students = studentService.getAllStudents();
                    for (Student s : students) {
                        System.out.println(s.getId() + ". " + s.getName() + " - " + s.getCourse());
                    }
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
