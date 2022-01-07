package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public Student findStudent(Long studentId) {
        return this.students.stream().filter(student -> Objects.equals(student.getStudentId(), studentId))
                .findFirst()
                .orElse(null);
    }
}