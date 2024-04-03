package com.example.school.repository;

import java.util.*;

import com.example.school.model.*;

public interface StudentRepository {
    ArrayList<Student> getStudents();

    Student getStudentById(int studentId);

    String addStudent(ArrayList<Student> students);

    Student updateStudent(int studentId, Student students);

    void deleteStudent(int studentId);

}
