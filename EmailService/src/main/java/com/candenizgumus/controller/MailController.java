package com.candenizgumus.controller;


import com.candenizgumus.entity.Mail;
import com.candenizgumus.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mail")
@RestController
@RequiredArgsConstructor
public class MailController
{

    private final MailSenderService mailSenderService;



    @PostMapping("/send")
    public String sendMailController(@RequestBody Mail mail) {
        mailSenderService.sendMail(mail);
        return "Mail sent successfully";
    }
}
