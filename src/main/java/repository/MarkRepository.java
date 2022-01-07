package repository;

import model.Mark;

import java.util.ArrayList;
import java.util.List;

public class MarkRepository {
    private final List<Mark> marks = new ArrayList<>();

    public List<Mark> getMarks() {
        return marks;
    }
}