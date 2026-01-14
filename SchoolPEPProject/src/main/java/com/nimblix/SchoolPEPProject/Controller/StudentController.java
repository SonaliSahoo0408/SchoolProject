package com.nimblix.SchoolPEPProject.Controller;

import com.nimblix.SchoolPEPProject.Constants.SchoolConstants;
import com.nimblix.SchoolPEPProject.Model.Student;
import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import com.nimblix.SchoolPEPProject.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    /*
    In this API we are registering the student. It will help to onboard the student, In this we are storing the  student
    fullname,emailId and password.
     */
    @PostMapping("/register")
    public ResponseEntity<?> studentRegistration(@RequestBody StudentRegistrationRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            studentService.registerStudent(request);

            response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_SUCCESS);
            response.put(SchoolConstants.MESSAGE, "Student Registration Successful");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_FAILURE);
            response.put(SchoolConstants.MESSAGE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

 /*
       This API is used to fetch the student details by using the student Id
 */

    @GetMapping("/details")
    public ResponseEntity<?> getStudentDetailsByStudentId(@RequestParam Integer studentId) {

        Map<String, Object> response = new HashMap<>();
        Student student = studentService.getStudentListByStudentId(studentId);

        if (student == null) {
            response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_FAILURE);
            response.put(SchoolConstants.MESSAGE, SchoolConstants.STUDENT_NOT_FOUND);
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_SUCCESS);
        response.put(SchoolConstants.MESSAGE, "Student details fetched successfully");
        response.put("data", student);

        return ResponseEntity.ok(response);
    }



        @PostMapping("/update")
        public ResponseEntity<?> updateStudent(
                @RequestParam Integer studentId,
                @RequestBody StudentRegistrationRequest request) {

            Map<String, Object> response = new HashMap<>();

            try {
                studentService.updateStudentDetails(studentId, request);
                response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_SUCCESS);
                response.put(SchoolConstants.MESSAGE, "Student updated successfully");
                return ResponseEntity.ok(response);

            } catch (Exception e) {
                response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_FAILURE);
                response.put(SchoolConstants.MESSAGE, e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }



        @PostMapping("/delete")
        public ResponseEntity<?> deleteStudent(@RequestParam Integer studentId) {
            Map<String, Object> response = new HashMap<>();

            try {
                studentService.deleteStudent(studentId);
                response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_SUCCESS);
                response.put(SchoolConstants.MESSAGE, "Student deleted successfully");
                return ResponseEntity.ok(response);

            } catch (Exception e) {
                response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_FAILURE);
                response.put(SchoolConstants.MESSAGE, e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }



        @PutMapping("/update")
        public ResponseEntity<?> updateStudentPut(
                @RequestParam Integer studentId,
                @RequestBody StudentRegistrationRequest request) {

            Map<String, Object> response = new HashMap<>();

            try {
                studentService.updateStudentDetails(studentId, request);
                response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_SUCCESS);
                response.put(SchoolConstants.MESSAGE, "Student updated successfully (PUT)");
                return ResponseEntity.ok(response);

            } catch (Exception e) {
                response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_FAILURE);
                response.put(SchoolConstants.MESSAGE, e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }





        @DeleteMapping("/delete")
        public ResponseEntity<?> deleteStudentDelete(@RequestParam Integer studentId) {
            Map<String, Object> response = new HashMap<>();

            try {
                studentService.deleteStudent(studentId);
                response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_SUCCESS);
                response.put(SchoolConstants.MESSAGE, "Student deleted successfully (DELETE)");
                return ResponseEntity.ok(response);

            } catch (Exception e) {
                response.put(SchoolConstants.STATUS, SchoolConstants.STATUS_FAILURE);
                response.put(SchoolConstants.MESSAGE, e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
    }


