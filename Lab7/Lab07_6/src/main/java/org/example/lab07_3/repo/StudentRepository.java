package org.example.lab07_3.repo;

import org.example.lab07_3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    public List<Student> findByAge(Integer age);
    @Query("SELECT s FROM Student s WHERE s.ieltsScore = :ieltsScore")
    public List<Student> findByScore(Double ieltsScore);
    @Query("SELECT s FROM Student s WHERE s.name like %:keyword%")
    List<Student> findByName(String keyword);
}
