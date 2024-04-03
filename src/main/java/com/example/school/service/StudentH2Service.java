package com.example.school.service;

import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;
import com.example.school.model.Student;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class StudentH2Service implements StudentRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        List<Student> studentList = db.query("select * from student", new StudentRowMapper());
        ArrayList<Student> students = new ArrayList<>(studentList);

        return students;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            Student student = db.queryForObject("select * from student where studentId = ?",
                    new StudentRowMapper(), studentId);
            return student;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public String addStudent(ArrayList<Student> students) {
        /*
         * db.update("INSERT INTO student(studentName, gender, standard) values(?,?,?)",
         * students.getStudentName(),
         * students.getGender(), students.getStandard());
         * Student savedStudents = db.queryForObject(
         * "SELECT * FROM student WHERE studentName = ? and gender = ? and standard = ?"
         * ,
         * new StudentRowMapper(), students.getStudentName(), students.getGender(),
         * students.getStandard());
         */
        int length = students.size();
        for (int i = 0; i < length; i++) {
            db.update("INSERT INTO student(studentName, gender, standard) values(?,?,?)",
                    (students.get(i)).getStudentName(),
                    (students.get(i)).getGender(), (students.get(i)).getStandard());

        }

        return "Successfully added " + String.valueOf(length) + "students";

    }

    @Override
    public Student updateStudent(int studentId, Student students) {
        if (students.getStudentName() != null) {
            db.update("update student set studentName = ? where studentId = ?", students.getStudentName(), studentId);
        }
        if (students.getGender() != null) {
            db.update("update student set gender = ? where studentId = ?", students.getGender(), studentId);
        }
        if (String.valueOf(students.getStandard()).length() > 0) {
            db.update("update student set standard = ? where studentId = ?", students.getStandard(), studentId);
        }

        return getStudentById(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete from student where studentId = ?", studentId);

    }
}
