package ua.mykytenko.service;

import ua.mykytenko.dao.StudentsDAO;
import ua.mykytenko.dao.StudentsDAOImpl;
import ua.mykytenko.model.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentsServiceImpl implements StudentsService {
    private StudentsDAO studentsDAO = new StudentsDAOImpl();

    @Override
    public void deleteById(int id) {
        studentsDAO.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = studentsDAO.getAll();
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId() - o2.getId();
            }
        });
        return students;
    }

    @Override
    public Student getById(int id) {
        return studentsDAO.getById(id);
    }

    @Override
    public void add(Student student) {
        if(studentIsConsistent(student)) {
            studentsDAO.add(student);
        }else {
            throw new RuntimeException("student info is not full or valid");
        }
    }

    @Override
    public void update(Student student) {
        if(studentIsConsistent(student)) {
            studentsDAO.update(student);
        }else {
            throw new RuntimeException("student info is not full or valid");
        }
    }

    private boolean studentIsConsistent(Student student) {
        if (student == null) {
            return false;
        } else if (student.getEmail() == null || student.getEmail().isEmpty() || student.getEmail().trim().isEmpty()) {
            return false;
        } else if (student.getFirstName() == null|| student.getFirstName().isEmpty() || student.getFirstName().trim().isEmpty()) {
            return false;
        } else if (student.getLastName() == null|| student.getLastName().isEmpty() || student.getLastName().trim().isEmpty()) {
            return false;
        } else if (student.getPhoneNumber() == null || student.getPhoneNumber().isEmpty()
                || !student.getPhoneNumber().trim().matches("\\+?[0-9]+")) {
            return false;
        } else {
            return true;
        }
        //TODO some other check like format of number, age of respondent etc.
    }
}
