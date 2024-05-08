package org.example.lab07_3.service;

import org.example.lab07_3.model.Student;
import org.example.lab07_3.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Iterable<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
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

    @Override
    public List<Student> findByAge(int age) {
        try {
            return studentRepository.findByAge(age);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int countStudentByScore(double score) {
        try {
            return studentRepository.findByScore(score).size();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Student> findByName(String name) {
        try {
            return studentRepository.findByName(name);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Iterable<Student> getAllStudentsSort() {
        try {
            return studentRepository.findAll(Sort.by("age").descending().and(Sort.by("ieltsScore")));
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Student> getStudentList() {
       try {
           Pageable sortedByAgeDescIeltsAsc =
                   PageRequest.of(0, 10, Sort.by("age").descending().and(Sort.by("ieltsScore")));
           Page<Student> studentPage = studentRepository.findAll(sortedByAgeDescIeltsAsc);
           return studentPage.getContent().subList(4,7);
       }
       catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }
}
