package com.nimblix.SchoolPEPProject.ServiceImpl;

import com.nimblix.SchoolPEPProject.Constants.SchoolConstants;
import com.nimblix.SchoolPEPProject.Model.Student;
import com.nimblix.SchoolPEPProject.Repository.StudentRepository;
import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import com.nimblix.SchoolPEPProject.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<?> registerStudent(StudentRegistrationRequest request) {

        // Validate password match
        if (!request.getPassword().equals(request.getReEnterPassword())) {
            return ResponseEntity.badRequest()
                    .body("Password and Re-Enter Password do not match!");
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create and save student
        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setPassword(encodedPassword);
        student.setSchoolId(request.getSchoolId());
        student.setStatus(SchoolConstants.ACTIVE);

        studentRepository.save(student);

        return ResponseEntity.ok("Registration Successful");
    }

    @Override
    public Student getStudentListByStudentId(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public void updateStudentDetails(Integer studentId, StudentRegistrationRequest request) {

        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with ID: " + studentId));

        if (request.getFullName() != null) {
            existingStudent.setFullName(request.getFullName());
        }

        if (request.getEmail() != null) {
            existingStudent.setEmail(request.getEmail());
        }

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {

            if (!request.getPassword().equals(request.getReEnterPassword())) {
                throw new RuntimeException(
                        "Password and Re-Enter Password do not match!");
            }

            String encodedPassword = passwordEncoder.encode(request.getPassword());
            existingStudent.setPassword(encodedPassword);
        }

        if (request.getSchoolId() != null) {
            existingStudent.setSchoolId(request.getSchoolId());
        }

        studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Integer studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with ID: " + studentId));

        studentRepository.delete(student);
    }

    // ✅ THIS WAS MISSING (VERY IMPORTANT)
    @Override
    public List<Student> getAllStudentsBySchoolId(Integer schoolId) {
        return studentRepository.findBySchoolId(schoolId);
    }
}
