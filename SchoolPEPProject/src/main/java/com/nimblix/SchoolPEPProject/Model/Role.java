package com.nimblix.SchoolPEPProject.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="roles")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Role {
    @Id
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

}
