package ua.mykytenko.dao;

import ua.mykytenko.model.Student;

import java.util.List;

public interface StudentsDAO {
    List<Student> getAll();
    Student getById(int id);
    Student update(Student student);
    Student add(Student student);
    boolean deleteById(int id);
}
