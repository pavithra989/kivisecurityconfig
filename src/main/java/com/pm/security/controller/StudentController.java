package com.pm.security.controller;

import com.pm.security.model.Student;
import com.pm.security.model.Users;
import com.pm.security.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private UserRegisterService userRegisterService;

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
    @PostMapping("/register")
    public String registerUser(@RequestBody Users users)
    {
        userRegisterService.registeruser(users);
        return "user registered succesfully";
    }

}
