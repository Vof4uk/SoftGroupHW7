package ua.mykytenko.service;

import ua.mykytenko.model.Student;

import java.util.List;

public interface StudentsService {
    void deleteById(int id);

    List<Student> getAll();

    Student getById(int id);

    void add(Student student);

    void update(Student student);
}
