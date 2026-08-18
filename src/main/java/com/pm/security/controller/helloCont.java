package com.pm.security.controller;


import com.pm.security.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


//@RestController
//@RequestMapping("/")
public class helloCont {


   @GetMapping("/")
   public String greet(HttpServletRequest request) {
        return "hello pavithra"+ request.getSession().getId();
    }
   //public List<Student> stud = new ArrayList<>(List.of(new Student(1,"pavi",80),new Student(2,"kavi",90)));

    //stud.add(new Student(2,"kavi",50));

   @GetMapping("/students")
    public List<Student> getStudents(){


       List<Student> stud = new ArrayList<>();
       Student s1 = new Student(1,"pavi",90);
       stud.add(s1);
       return stud;
   }

}
