package com.myProject.auth_service.service;


import com.myProject.auth_service.exception.InvalidOtpException;
import com.myProject.auth_service.exception.OtpNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class OtpService {

    private static final Logger log = LoggerFactory.getLogger(OtpService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String generateOtp(){
        return String.valueOf(new Random().nextInt(900000)+100000);
    }


    public void sendOtp(String email){
        String otp = generateOtp();

        redisTemplate.opsForValue()
                .set(email, otp, Duration.ofMinutes(2));

        log.info("OTP stored in Redis for {} with 2 min expiry", email);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Authentication Otp");
        message.setText("Your Otp is :" + otp);
        mailSender.send(message);

        log.info("OTP sent successfully to {}", email);
    }

    public boolean verifyOtp(String otp,String email){

        String storedOtp = redisTemplate.opsForValue().get(email);

        if(storedOtp == null){
            throw new OtpNotFoundException("OTP not found for email: " + email);
        }
        if(!storedOtp.equals(otp)){
            throw new InvalidOtpException("Invalid OTP");
        }
        redisTemplate.delete(email);
        return true;

    }

}
