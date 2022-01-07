package model;

import java.util.List;

public class Student {
    private Long studentId;
    private String surName;
    private String name;
    private String street;
    private Integer house;
    private String classId;
    private School school;
    private List<Mark> marks;

    public Student(Long studentId, String surName, String name, String street, Integer house, String classId,
                   School school, List<Mark> marks) {
        this.studentId = studentId;
        this.surName = surName;
        this.name = name;
        this.street = street;
        this.house = house;
        this.classId = classId;
        this.school = school;
        this.marks = marks;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getSurName() {
        return surName;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getClassId() {
        return classId;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String toString() {
        return this.studentId + " " + this.surName + " " + this.name + " " + this.street + " " + this.house;
    }
}