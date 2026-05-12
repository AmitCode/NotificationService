package com.notifications.controller;

import com.notifications.dto.request.EmailRequest;
import com.notifications.dto.response.EmailResponse;
import com.notifications.service.EmailNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailNotificationService notificationService;
    public EmailController(EmailNotificationService service){
        this.notificationService=service;
    }

    @PostMapping("/sendMail")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest request){
        return new ResponseEntity<>(notificationService.sendEmail(request), HttpStatus.OK);
    }
}
