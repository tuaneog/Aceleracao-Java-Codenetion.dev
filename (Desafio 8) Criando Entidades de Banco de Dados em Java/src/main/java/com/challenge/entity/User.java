package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @NotNull
    @Column(length = 100)
    @Size(max = 100)
    private String fullName;

    @NotBlank
    @NotNull
    @Email(message = "O email deve ser válido")
    @Column(length = 100)
    @Size(max = 100)
    private String email;

    @NotBlank
    @NotNull
    @Column(length = 50)
    @Size(max = 50)
    private String nickname;

    @NotBlank
    @NotNull
    @Column
    @Size(max = 255)
    private String password;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date create_at;

    @OneToMany
    List<Candidate> candidate = new ArrayList<>();

    @OneToMany
    List<Submission> submission = new ArrayList<>();

}
