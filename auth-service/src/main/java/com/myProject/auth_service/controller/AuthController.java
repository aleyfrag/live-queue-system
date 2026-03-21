package com.myProject.auth_service.controller;


import com.myProject.auth_service.dto.ApiResponse;
import com.myProject.auth_service.dto.SendOtpRequest;
import com.myProject.auth_service.dto.VerifyOtpRequest;
import com.myProject.auth_service.security.JwtUtil;
import com.myProject.auth_service.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private OtpService otpService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse> sendOtp(@RequestBody SendOtpRequest request) {

        log.info("Received request to send OTP for email: {}", request.getEmail());

        otpService.sendOtp(request.getEmail());

        log.info("OTP sent successfully to email: {}", request.getEmail());

        return ResponseEntity.ok(
                new ApiResponse("OTP sent successfully", true)
        );
    }

    @PostMapping("verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request){

        log.info("Verifying OTP for email: {}", request.getEmail());

        boolean isValid = otpService.verifyOtp(request.getOtp(), request.getEmail());

        System.out.println(isValid);

        if(isValid){
            log.info("OTP verified successfully for email: {}", request.getEmail());
            String token = jwtUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(token);
        }

        log.warn("Invalid OTP attempt for email: {}", request.getEmail());

        return ResponseEntity.badRequest()
                .body(new ApiResponse("Invalid OTP", false));

    }


}
