package com.nimblix.SchoolPEPProject.Controller;

import com.nimblix.SchoolPEPProject.Constants.SchoolConstants;
import com.nimblix.SchoolPEPProject.Model.User;
import com.nimblix.SchoolPEPProject.Repository.UserRepository;
import com.nimblix.SchoolPEPProject.Request.AuthStudentRequest;
import com.nimblix.SchoolPEPProject.Response.AuthStudentResponse;
import com.nimblix.SchoolPEPProject.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthStudentRequest request) {

        try {
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap(SchoolConstants.MESSAGE, "Email is required."));
            }

            // Fetch from DB
            User user = userRepository
                    .findByEmailIdIgnoreCaseOrMobile(request.getEmail(), request.getEmail())
                    .filter(u -> u.getStatus().equalsIgnoreCase(SchoolConstants.ACTIVE))
                    .orElse(null);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "User not found or inactive."));
            }

            String role = user.getRole().getRoleName().toUpperCase();

            if (role.equals(SchoolConstants.STUDENT)) {

                if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                    return ResponseEntity.badRequest()
                            .body(Collections.singletonMap("message", "Password is required for Student login."));
                }

                // Authenticate
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );
            }

            else {
                // No password check — allow login by email only
                System.out.println(role + " logged in using email only");
            }

            // Generate JWT token
            var userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String token = jwtUtil.generateToken(userDetails);

            // Update login status
            user.setIsLogin(true);
            userRepository.save(user);

            // Build response
            AuthStudentResponse resp = new AuthStudentResponse();
            resp.setUserId(user.getId());
            resp.setFullName(user.getFullName());
            resp.setEmail(user.getEmailId());
            resp.setRole(role);
            resp.setToken(token);

            return ResponseEntity.ok(resp);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Incorrect password."));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Login failed."));
        }
    }
}
