package service;

import model.Mark;
import model.School;
import model.Student;
import repository.MarkRepository;
import repository.SchoolRepository;
import repository.StudentRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentService {
    public void loadStudent(StudentRepository studentRepository, MarkRepository markRepository, SchoolRepository schoolRepository) {
        File file = new File("uczniowie.txt");

        try {
            Scanner fileScanner = new Scanner(file);
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(";");

                Long studentId = Long.valueOf(splittedArray[0]);
                String surName = splittedArray[1];
                String name = splittedArray[2];
                String street = splittedArray[3];
                Integer house = Integer.valueOf(splittedArray[4]);
                String classId = splittedArray[5];
                List<Mark> marks = markRepository.getMarks();
                School school = schoolRepository.checkSchool();

                Student student = new Student(studentId, surName, name, street, house, classId, school, marks);
                studentRepository.getStudents().add(student);
                school.getStudents().add(student);
                student.setSchool(school);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Long findStudentFromStreet(List<Student> students, String streetOne, String streetTwo) {

        long count = students.stream()
                .filter(student -> student.getStreet().equals(streetOne) || student.getStreet().equals(streetTwo))
                .count();

        return count;
    }

    public Map<String, List<Long>> countQuantity(List<Student> students) {
        Map<String, List<Long>> multiValuesMap = new HashMap<>();

        Map<String, Long> map1 = students.stream().filter(student -> student.getName().endsWith("a")).collect(Collectors.groupingBy(Student::getClassId, Collectors.counting()));
        Map<String, Long> map2 = students.stream().filter(student -> !student.getName().endsWith("a")).collect(Collectors.groupingBy(Student::getClassId, Collectors.counting()));

        for (Map.Entry<String, Long> entry : map1.entrySet()) {
            for (Map.Entry<String, Long> e : map2.entrySet()) {
                multiValuesMap.put(entry.getKey(), List.of(entry.getValue(), e.getValue()));
            }
        }
        return multiValuesMap;
    }
}