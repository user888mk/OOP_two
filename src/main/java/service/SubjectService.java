package service;

import model.Mark;
import model.Subject;
import repository.MarkRepository;
import repository.SchoolRepository;
import repository.SubjectRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class SubjectService {

    public void loadSubject(SubjectRepository subjectRepository, MarkRepository markRepository, SchoolRepository schoolRepository) {
        File file = new File("przedmioty.txt");

        try {
            Scanner fileScanner = new Scanner(file);
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(";");

                Long subjectId = Long.valueOf(splittedArray[0]);
                String name = splittedArray[1];
                String teacherSurname = splittedArray[2];
                String teacherName = splittedArray[3];
                List<Mark> marks = markRepository.getMarks();

                Subject subject = new Subject(subjectId, name, teacherSurname, teacherName, marks);
                subjectRepository.getSubjects().add(subject);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}