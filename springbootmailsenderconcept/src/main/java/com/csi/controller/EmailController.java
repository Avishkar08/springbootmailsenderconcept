package com.csi.controller;

import com.csi.model.Email;
import com.csi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emailsender")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("")
    public ResponseEntity<String> sendEmail(@RequestBody Email email){
        emailService.sendEmail(email);
        return ResponseEntity.ok("Email Send SuccessFully ... ");
    }
}
