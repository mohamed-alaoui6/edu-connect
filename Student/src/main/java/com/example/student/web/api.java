package com.example.student.web;

import com.example.student.entities.Student;
import com.example.student.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Students")
@RequiredArgsConstructor
public class api {

    private final StudentService studentService;

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestParam String fullName,
            @RequestParam("image") MultipartFile image,
            @RequestParam String phoneNumber,
            @RequestParam String password
    ) throws IOException {

        Student student = studentService.updateStudent(id, fullName, image, phoneNumber, password);
        return ResponseEntity.ok(student);
    }




}
