package repository;

import model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {

    List<Subject> subjects = new ArrayList<>();

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Subject findSubject(Long subjectID) {
        for (Subject subject : subjects) {
            if (subject.getSubjectId().equals(subjectID)) {
                return subject;
            }
        }
        return null;
    }
}