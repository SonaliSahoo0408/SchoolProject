package com.nimblix.SchoolPEPProject.Service;

import com.nimblix.SchoolPEPProject.Model.Student;
import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<?> registerStudent(StudentRegistrationRequest studentRegistrationRequest);

    Student getStudentListByStudentId(Integer studentId);

    void deleteStudent(Integer studentId);

    void updateStudentDetails(Integer studentId, StudentRegistrationRequest request);

    List<Student> getAllStudentsBySchoolId(Integer schoolId);

}
