package org.example.lab07_3.service;

import org.example.lab07_3.model.Student;
import org.example.lab07_3.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(long id) throws Exception {
        try {
            return studentRepository.findById(id).get();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student save(Student student) {
        try {
            return studentRepository.save(student);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
