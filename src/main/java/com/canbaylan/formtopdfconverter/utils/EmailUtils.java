package com.canbaylan.formtopdfconverter.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("baylanguvenlik@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        if(list != null && list.size()>0)
            message.setCc(getCcArray(list));
        emailSender.send(message);
    }
    public void sendSimpleMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("baylanguvenlik@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
    public void sendSimpleMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("baylanguvenlik@gmail.com");
        message.setTo("canbaylan34@gmail.com");
        message.setSubject(LocalDate.now().toString());
        message.setText("text");


        emailSender.send(message);
    }
    private String[] getCcArray(List<String> ccList){
        String[] cc = new String[ccList.size()];
        for(int i=0;i<ccList.size();i++){
            cc[i] = ccList.get(i);
        }
        return cc;
    }

}