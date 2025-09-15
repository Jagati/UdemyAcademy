package com.lldproject.udemyacademy.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lldproject.udemyacademy.library.sendgrid.Sendgrid;
@Component
public class SendGridEmailAdapter implements EmailAdapter {
    Sendgrid sendgrid;
    @Autowired
    public SendGridEmailAdapter(Sendgrid sendgrid){
        this.sendgrid = sendgrid;
    }
    @Override
    public void sendEmail(String email, String message) throws Exception {
        // TODO Auto-generated method stub
        sendgrid.sendEmailAsync(email, message);
    }

}
