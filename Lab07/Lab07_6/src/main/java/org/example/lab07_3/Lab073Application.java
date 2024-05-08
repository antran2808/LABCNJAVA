package org.example.lab07_3;

import org.example.lab07_3.model.Student;
import org.example.lab07_3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lab073Application implements CommandLineRunner {
    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(Lab073Application.class, args);
    }
    private void showStudents() {
        List<Student> studentList = (List<Student>) this.studentService.getAllStudentsSort();
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }
    private void showList(List<Student> list){
        for(Student student: list){
            System.out.println(student.toString());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L, "Hoa", 30, "hoa@tdtu.edu.vn", 7.0));
        studentList.add(new Student(2L, "Linh", 50, "linh@tdtu.edu.vn", 9.0));
        studentList.add(new Student(3L, "Quang", 60, "quang@tdtu.edu.vn", 9.5));
        studentList.add(new Student(4L, "An", 25, "an@tdtu.edu.vn", 8.5));
        studentList.add(new Student(5L, "Bao", 28, "bao@tdtu.edu.vn", 7.8));
        studentList.add(new Student(6L, "Cuc", 32, "cuc@tdtu.edu.vn", 6.9));
        studentList.add(new Student(7L, "Dung", 29, "dung@tdtu.edu.vn", 8.2));
        studentList.add(new Student(8L, "Em", 27, "em@tdtu.edu.vn", 7.4));
        studentList.add(new Student(9L, "Giang", 31, "giang@tdtu.edu.vn", 8.9));
        studentList.add(new Student(10L, "Hien", 26, "hien@tdtu.edu.vn", 9.1));

        for (Student student: studentList){
            this.studentService.save(student);
        }

        showStudents();

        System.out.println("List 3 students 4-5-6:");
        List<Student> students = this.studentService.getStudentList();
        showList(students);
    }
}
