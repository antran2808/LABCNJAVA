package org.example.lab07_3;

import org.example.lab07_3.model.Student;
import org.example.lab07_3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Lab073Application implements CommandLineRunner {
    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(Lab073Application.class, args);
    }
    private void showStudents() {
        List<Student> studentList = (List<Student>) this.studentService.getAllStudents();
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }
    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student(1L, "Hoa", 30, "hoa@tdtu.edu.vn", 7.0);
        Student student2 = new Student(2L, "Linh", 50, "linh@tdtu.edu.vn", 9.0);
        Student student3 = new Student(3L, "Quang", 60, "quang@tdtu.edu.vn", 9.5);
        this.studentService.save(student1);
        this.studentService.save(student2);
        this.studentService.save(student3);
        showStudents();
        System.out.println("Updating students: "+student1.getName());
        student1.setIeltsScore(8.0);
        this.studentService.save(student1);
        showStudents();
        Long idDelete = 1L;
        System.out.println("Deleting students: "+ studentService.getStudent(idDelete).getName());
        this.studentService.deleteStudent(idDelete);
        showStudents();
    }
}
