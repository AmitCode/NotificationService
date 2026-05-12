package com.notifications.service.impl;

import com.notifications.dto.request.EmailRequest;
import com.notifications.dto.response.EmailResponse;
import com.notifications.exception.exceptionClasses.MailSendingException;
import com.notifications.exception.exceptionClasses.SenderMailIdException;
import com.notifications.service.EmailNotificationService;
import com.notifications.template.RegistrationEmailTemplate;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {
    private final Environment environment;
    private final JavaMailSender mailer;
    EmailResponse response=new EmailResponse();
    public EmailNotificationServiceImpl(Environment environment, JavaMailSender mailer){
        this.environment=environment;
        this.mailer=mailer;
    }
    @Override
    public EmailResponse sendEmail(EmailRequest request){
        try{
            String senderMailId=environment.getProperty("sender-email");
            MimeMessage message=mailer.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message);
            messageHelper.setTo(request.getEmailId());
            //assert senderMailId != null;This will throw assertion error in case of null, but in a
            // production ready system:
            // 1.Validate input properly
            // 2.Throw meaningful exceptions
            // 3.Return proper HTTP error responses
            if (senderMailId == null)
                throw new MailSendingException("Sender mailId can't be empty!...");
            else if(request.getEmailId() == null)
                throw new MailSendingException("Receiver mailId can't be empty!...");

            messageHelper.setFrom(senderMailId);
            messageHelper.setSubject(request.getEmailSubject());
            messageHelper.setText(RegistrationEmailTemplate.generateRegistrationEmailTemplate(
                    request.getUserName(), request.getEmailId(), request.getVerificationUrl()
            ));
            mailer.send(message);
            response.setStatusCode(HttpStatus.OK.toString());
            response.setEmailStatus("Success");
            response.setEmailMessage("Registration Email send successfully!...");
        }catch (Exception exception){
            throw new SenderMailIdException("[EmailNotificationService]-{Ex}:- "+exception.getMessage());
        }


        return response;
    }
}
