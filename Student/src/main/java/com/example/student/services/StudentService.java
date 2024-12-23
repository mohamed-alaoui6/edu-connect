package com.example.student.services;


import com.example.student.entities.Student;
import com.example.student.repositories.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public Student saveStudent(Student student ) throws IOException {


        student.setRole("Student");
        return studentRepo.save(student);
    }

    public Student getStudentById(Long id){

        if (studentRepo.existsById(id)){
            return studentRepo.findById(id).get();
        }
        return null;
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public void deleteStudentById(Long id){
        studentRepo.deleteById(id);
    }

    public Student findByEmail(String email){
        return studentRepo.findByEmail(email);
    }

    public Student updateStudent(Long id, String fullName, MultipartFile image,
                                 String phoneNumber, String password) throws IOException {


        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFullName(fullName);
        student.setPhoneNumber(phoneNumber);
        student.setPassword(password);


        if (image != null && !image.isEmpty()) {
            student.setImage(image.getBytes());
        }

        student = studentRepo.save(student);

        if (student.getImage() != null) {
            student.setBase64Image(Base64.getEncoder().encodeToString(student.getImage()));
        }
        return student;
    }

}
