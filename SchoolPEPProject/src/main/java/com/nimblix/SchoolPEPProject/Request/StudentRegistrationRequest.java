package com.nimblix.SchoolPEPProject.Request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StudentRegistrationRequest {
    private String fullName;
    private String email;
    private String password;
    private String reEnterPassword;
    private Integer schoolId;

}
