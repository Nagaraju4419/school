package com.example.school.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

import java.util.*;

@RestController
public class StudentController {
    @Autowired
    public StudentH2Service studentService;

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping("/students/bulk")
    public String addStudent(@RequestBody ArrayList<Student> students) {
        return studentService.addStudent(students);

    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student students) {
        return studentService.updateStudent(studentId, students);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
    }

}
