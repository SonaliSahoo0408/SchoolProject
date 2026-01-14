package com.nimblix.SchoolPEPProject.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthStudentResponse {
    private Integer userId;
    private String fullName;
    private String email;
    private String role;
    private String token;
}
