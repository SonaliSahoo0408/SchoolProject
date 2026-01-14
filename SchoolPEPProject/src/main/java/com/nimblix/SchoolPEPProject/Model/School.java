package com.nimblix.SchoolPEPProject.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_address")
    private  String schoolAddress;

    @Column(name = "school_phone")
    private String schoolPhone;

    @Column(name = "school_email")
    private String schoolEmail;

}
