package com.example.student.web;


import com.example.student.entities.Student;
import com.example.student.services.StudentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductGraphQLController {

    private final StudentService studentService;


    public ProductGraphQLController(StudentService studentService) {
        this.studentService = studentService;
    }



    @MutationMapping
    public Student saveStudent(
            @Argument String fullName,
            @Argument int age,
            @Argument String gender,
            @Argument String email,
            @Argument String password,
            @Argument String phone
            ) throws IOException {

        Student student = Student.builder()
                .fullName(fullName)
                .age(age)
                .gender(gender)
                .email(email)
                .password(password)
                .phoneNumber(phone)
                .build();

        return studentService.saveStudent(student);
    }

    @MutationMapping
    public Boolean deleteStudentById(@Argument Long id) {
        studentService.deleteStudentById(id);
        return true;
    }


    @QueryMapping
    public Student getStudentById(@Argument Long id) {
        return studentService.getStudentById(id);
    }

    @QueryMapping
    public Map<String, String> getByEmail(@Argument String email) {
        Student student = studentService.findByEmail(email);
        if (student != null) {
            Map<String, String> infos_user = new HashMap<>();
            infos_user.put("email", student.getEmail());
            infos_user.put("password", student.getPassword());
            infos_user.put("scope", student.getRole());
            return infos_user;
        }
        return null;
    }



}
