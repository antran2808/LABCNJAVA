package org.example.lab07_3.repo;

import org.example.lab07_3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
