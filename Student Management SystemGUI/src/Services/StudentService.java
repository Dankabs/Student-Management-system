package Services;

import dao.StudentDAO;
import models.Student;

import java.util.List;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }

    public Student getStudent(int id) {
        return studentDAO.getStudent(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}
