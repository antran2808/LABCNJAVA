package org.example.lab07_3.service;

import org.example.lab07_3.model.Student;

import java.util.List;

public interface StudentService {
    public Iterable<Student> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void deleteStudent(long id);

    public Student save(Student student);

    public List<Student> findByAge(int age);

    public int countStudentByScore(double score);

    public List<Student> findByName(String name);

    public Iterable<Student> getAllStudentsSort();

    public List<Student> getStudentList();
}
