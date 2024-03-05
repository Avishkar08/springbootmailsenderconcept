package com.csi.service;

import com.csi.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(Email email) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("avishkarnighot@gmail.com");
            mimeMessageHelper.setTo(email.getToEmail());
            mimeMessageHelper.setCc(email.getCcEmail());
            mimeMessageHelper.setSubject(email.getEmailSubject());
            mimeMessageHelper.setText(email.getEmailBody());

            // Trying to attachment file

            FileSystemResource fileSystemResource = new FileSystemResource(email.getEmailAttachment());
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            javaMailSender.send(mimeMessage);
            log.info(" ##### Mail Send SuccessFully ... ");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
