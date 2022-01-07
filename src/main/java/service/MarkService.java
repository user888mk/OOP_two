package service;

import model.Mark;
import model.Student;
import model.Subject;
import repository.MarkRepository;
import repository.StudentRepository;
import repository.SubjectRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.*;

public class MarkService {

    public void loadMark(MarkRepository markRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        File file = new File("oceny.txt");

        try {
            Scanner fileScanner = new Scanner(file);
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(";");

                Long studentId = Long.valueOf(splittedArray[0]);
                Integer integerMark = Integer.valueOf(splittedArray[1]);
                String stringDate = splittedArray[2];
                LocalDate date = LocalDate.parse(stringDate);
                Long subjectId = Long.valueOf(splittedArray[3]);
                Student student = studentRepository.findStudent(studentId);
                Subject subject = subjectRepository.findSubject(subjectId);

                Mark mark = new Mark(studentId, integerMark, date, subjectId, student, subject);
                markRepository.getMarks().add(mark);

                if (student == null || mark == null) {
                    throw new IllegalArgumentException("Student i ocena nie mogą być nullami ");
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> findStudentMark(List<Mark> marks, String name, String surName) {
        List<Integer> marksList = new ArrayList<>();

        for (Mark mark : marks) {
            if (mark.getStudent().getName().equals(name) && mark.getStudent().getSurName().equals(surName)
                    && mark.getSubjectId().equals(1L)) {
                marksList.add(mark.getMark());
            }
        }
        return marksList;
    }

    public List<Mark> markInApril(List<Mark> marks) {

        LocalDate startDate = LocalDate.of(2009, 04, 01);
        LocalDate endDate = LocalDate.of(2009, 05, 01);

        List<Mark> dateRange = new ArrayList<>();
        for (Mark mark : marks) {
            if (mark.getDate().isAfter(startDate) && mark.getDate().isBefore(endDate)) {
                dateRange.add(mark);
            }
        }
        return dateRange;
    }

    public List<String> findNegativeMark(List<Mark> marks) {

        List<Mark> marks1 = markInApril(marks);
        List<Mark> marks2 = new ArrayList<>();

        for (Mark mark : marks1) {
            if (mark.getStudent().getClassId().equals("2c") && mark.getMark().equals(1)) {
                marks2.add(mark); // dopuszczalne jest zapis- .add(mark.getStudent().getName() + " " + mark.getStudent().getSurName() +...?
            }
        }
        List<String> collect = marks2.stream()
                .sorted(Comparator.comparing(mark -> mark.getStudent().getSurName()))
                .map(mark -> mark.getStudent().getName() + " " + mark.getStudent().getSurName() + " " + mark.getSubject().getName())
                .collect(toList());

        return collect;
    }

    public List<Map.Entry<String, Double>> subjectAvgInClass(List<Mark> marks) {

        Map<String, Double> collect = marks.stream().filter(mark -> mark.getStudent().getClassId().equals("2a"))
                .collect(groupingBy(mark -> mark.getSubject().getName(), averagingDouble(Mark::getMark)));

        collect.replaceAll((k, v) -> Math.round(v * Math.pow(10, 2)) / Math.pow(10, 2));

        List<Map.Entry<String, Double>> list = new ArrayList<>(collect.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        return list;
    }

    public String bestAvgInSchool(List<Mark> marks, List<Student> students) {

        Map<Long, Double> collect = marks.stream().collect(groupingBy(Mark::getStudentId, averagingDouble(Mark::getMark)));

        Map.Entry<Long, Double> max = Collections.max(collect.entrySet(), Map.Entry.comparingByValue());

        for (Student s : students) {
            if (s.getStudentId().equals(max.getKey())) {
                return s.getSurName() + " " + s.getName() + " " + s.getClassId() + " " + max.getValue();
            }
        }
        return null;
    }
}