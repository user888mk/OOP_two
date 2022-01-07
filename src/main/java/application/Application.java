package application;

import model.Mark;
import model.Student;
import repository.MarkRepository;
import repository.SchoolRepository;
import repository.StudentRepository;
import repository.SubjectRepository;
import service.MarkService;
import service.SchoolService;
import service.StudentService;
import service.SubjectService;

import java.util.List;
import java.util.Map;

public class Application {
    public void run() {

        MarkRepository markRepository = new MarkRepository();
        StudentRepository studentRepository = new StudentRepository();
        SubjectRepository subjectRepository = new SubjectRepository();
        SchoolRepository schoolRepository = new SchoolRepository();

        List<Student> students = studentRepository.getStudents();
        List<Mark> marks = markRepository.getMarks();

        SchoolService schoolService = new SchoolService();
        schoolService.loadData(schoolRepository);

        StudentService studentService = new StudentService();
        studentService.loadStudent(studentRepository, markRepository, schoolRepository);

        SubjectService subjectService = new SubjectService();
        subjectService.loadSubject(subjectRepository, markRepository, schoolRepository);

        MarkService markService = new MarkService();
        markService.loadMark(markRepository, studentRepository, subjectRepository);

        // Exercise 1. - Podaj, ilu uczniów mieszka poza rejonem szkoły (czyli na jednej z tych dwóch ulic) -Worcella/Sportowa.
        Long studentFromStreet = studentService.findStudentFromStreet(students, "Worcella", "Sportowa");
        System.out.println("Poza rejonem szkoły mieszka: " + studentFromStreet + " uczniów");

        // Exercise 2. - Wypisz wszystkie oceny ucznia Jana Augustyniaka z języka polskiego.
        List<Integer> mark = markService.findStudentMark(marks, "Jan", "Augustyniak");
        System.out.println("Oceny ucznia: " + mark);

        // Exercise 3. - Oblicz, ile dziewcząt i ilu chłopców jest w poszczególnych klasach. Wynik przedstaw
        //w postaci zestawienia: idKlasy, liczba dziewcząt, liczba chłopców. Załóż, że imiona
        //dziewcząt (i tylko dziewcząt) kończą się na literę a.

        Map<String, List<Long>> mapListOfQuantity = studentService.countQuantity(students);
        System.out.println("Ilość w klasie dziewcząt i chłopców: " + mapListOfQuantity);

        // Exercise 4 - Utwórz zestawienie dla klasy 2a zawierające nazwy przedmiotów i średnie ocen klasy
        //z tych przedmiotów (średnie podaj z zaokrągleniem do dwóch miejsc po przecinku)
        //Zestawienie posortuj nierosnąco według średnich ocen.

        List<Map.Entry<String, Double>> entries = markService.subjectAvgInClass(marks);
        System.out.println("Zestawienie dla klasy 2a, przedmioty wraz z średnią arytmetyczną: " + entries);

        // Exercise 5 - Utwórz zestawienie uporządkowane alfabetycznie według nazwisk zawierające wykaz
        //osób z klasy 2 c, które w kwietniu 2009 roku otrzymały oceny niedostateczne (imię,
        //nazwisko, przedmiot).

        List<String> negativeMark = markService.findNegativeMark(marks);
        System.out.println("Osoby z klasy 2c z oceną niedostateczna otrzymaną w kwietniu 2009: " + negativeMark);

        // Exercise 6 - Podaj nazwisko, imię, klasę oraz średnią ocen osoby, która osiągnęła najwyższą średnią
        //ocen w całej szkole (jest tylko jedna taka osoba)

        String person = markService.bestAvgInSchool(marks, students);
        System.out.println("Osoba z najwyższą średnia arytmetyczną w szkole: " + person);
    }
}