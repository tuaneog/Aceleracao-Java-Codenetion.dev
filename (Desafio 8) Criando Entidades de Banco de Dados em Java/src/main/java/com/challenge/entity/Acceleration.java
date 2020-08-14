package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Acceleration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @Column
    @Size(max = 100)
    private String name;

    @NotBlank
    @NotNull
    @Column
    @Size(max = 50)
    private String slug;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    @OneToMany
    private List<Candidate> candidate = new ArrayList<>();

    @ManyToOne
    private Challenge challenge;

}
