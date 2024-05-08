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
    private void showList(List<Student> list){
        for(Student student: list){
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
        int agePara = 19;
        List<Student> studentList = studentService.findByAge(agePara);
        System.out.println("Students with age greater than or equal to "+agePara+":");
        showList(studentList);
        double scorePara = 7.0;
        System.out.println("The number of students with Ielts score of "+ scorePara+" is " + studentService.countStudentByScore(scorePara));
        String namePara = "a";
        studentList = studentService.findByName(namePara);
        System.out.println("The students are found by "+namePara +". Their information is: ");
        showList(studentList);
    }
}
