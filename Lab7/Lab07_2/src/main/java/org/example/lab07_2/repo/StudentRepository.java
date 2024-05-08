package org.example.lab07_2.repo;

import org.example.lab07_2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
