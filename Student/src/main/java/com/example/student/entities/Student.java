package com.example.student.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@Data
@AllArgsConstructor@NoArgsConstructor@Builder
public class Student {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private int age;
    private String gender;
    @JsonIgnore
    @Lob
    @Column(length = 10000)
    private byte[] image;
    @Transient
    private String base64Image;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;

}
