package model;

import java.util.List;

public class School {

    private final List<Student> students;

    public School(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String toString() {
        return this.students + " ";
    }
}