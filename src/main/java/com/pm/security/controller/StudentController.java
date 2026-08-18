package com.pm.security.controller;

import com.pm.security.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(new Student(1,"pavi",80),new Student(2,"kavi",90)));

    @GetMapping("/students")
    public List<Student> getStudents(){

       /* List<Student> stud = new ArrayList<>();
        Student s1 = new Student(1,"pavi",90);
        stud.add(s1);*/
        return students;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student)
    {
        students.add(student);
        return student;
    }


}
