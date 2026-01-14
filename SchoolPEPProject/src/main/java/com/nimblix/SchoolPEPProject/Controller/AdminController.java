package com.nimblix.SchoolPEPProject.Controller;

import com.nimblix.SchoolPEPProject.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudentsBySchoolId(
            @RequestParam Integer schoolId
    ) {
        return ResponseEntity.ok(
                studentService.getAllStudentsBySchoolId(schoolId)
        );
    }
}
