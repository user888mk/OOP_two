package model;

import java.util.List;

public class Subject {
    private Long subjectId;
    private String name;
    private String teacherSurname;
    private String teacherName;
    private List<Mark> marks;

    public Subject(Long subjectId, String name, String teacherSurname, String teacherName, List<Mark> marks) {
        this.subjectId = subjectId;
        this.name = name;
        this.teacherSurname = teacherSurname;
        this.teacherName = teacherName;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String toString() {
        return this.subjectId + " " + this.name + " " + this.teacherSurname + " " + this.teacherName + ": ";
    }
}