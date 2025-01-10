package com.funnyawm.studentmanager.controller;

import com.funnyawm.studentmanager.model.Student;
import com.funnyawm.studentmanager.model.StudentIdWrapper;
import com.funnyawm.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = {"/getStudent", "/updateStudent", "/deleteStudent", "/addStudent"})
    public ResponseEntity<String> returnMethodNotAllowed() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Get method not allowed");
    }

    @PostMapping("/getStudent")
    public Student getStudentById(@RequestBody StudentIdWrapper studentIdWrapper) {
        return studentService.getStudentById(studentIdWrapper.getId());
    }

    @PostMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        if (!student.checkEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty Field detected!");
        }
        if (!student.checkInjection()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SQL injection was not allowed!");
        }
        studentService.updateStudent(student);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/deleteStudent")
    public void deleteStudent(@RequestBody StudentIdWrapper studentIdWrapper) {
        studentService.deleteStudentById(studentIdWrapper.getId());
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        if (!student.checkEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty Field detected!");
        }
        if (!student.checkInjection()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SQL injection was not allowed!");
        }
        studentService.insertStudent(student);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/allStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
