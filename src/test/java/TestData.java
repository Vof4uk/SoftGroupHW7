import ua.mykytenko.model.Student;

import java.util.Date;

public class TestData {
    public static Student[] STUDENTS;

    static {
        Student student = new Student("Volodymyr", "Mykytenko", "+380956435160", new Date(1988 - 1900, 4, 9), "b@gmail.com");
        student.setId(1);
        STUDENTS = new Student[]{student};
    }
}
