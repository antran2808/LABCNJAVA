package org.example.lab07_3.repo;

import org.example.lab07_3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByAgeGreaterThanEqual(Integer age);

    public List<Student> findByIeltsScore(Double ieltsScore);

    List<Student> findByNameContaining(String keyword);
}
