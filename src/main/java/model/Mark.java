package model;

import java.time.LocalDate;

public class Mark {
    private Long studentId;
    private Integer mark;
    private LocalDate date;
    private Long subjectId;
    private Student student;
    private Subject subject;

    public Mark(Long studentId, Integer mark, LocalDate date, Long subjectId,
                Student student, Subject subject) {
        if (student == null || subject == null) {
            throw new IllegalArgumentException("Student lub przedmiot nie mogą być nullami");
        }
        this.studentId = studentId;
        this.mark = mark;
        this.date = date;
        this.subjectId = subjectId;
        this.student = student;
        this.subject = subject;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Integer getMark() {
        return mark;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public String toString() {
        return this.studentId + " " + this.mark + " " + this.date + " " + this.subjectId;
    }
}