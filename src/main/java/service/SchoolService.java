package service;

import model.School;
import model.Student;
import repository.SchoolRepository;

import java.util.ArrayList;
import java.util.List;

public class SchoolService {

    public void loadData(SchoolRepository schoolRepository) {

        List<Student> studentList = new ArrayList<>();
        School school = new School(studentList);
        schoolRepository.getSchools().add(school);
    }
}