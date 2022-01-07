package repository;

import model.School;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SchoolRepository {

    private final List<School> schools = new ArrayList<>();

    public List<School> getSchools() {
        return schools;
    }

    public School checkSchool() {
        for (School school : schools) {
            if (school != null || Objects.requireNonNull(school).getStudents() != null) {
                return school;
            }
        }
        return null;
    }
}
