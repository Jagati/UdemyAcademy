package com.lldproject.udemyacademy.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lldproject.udemyacademy.library.sendgrid.Sendgrid;
@Component
public class SendGridWhatsappAdapter implements WhatsappAdapter {
    Sendgrid sendgrid;
    @Autowired
    public SendGridWhatsappAdapter(Sendgrid sendgrid){
        this.sendgrid = sendgrid;
    }
    @Override
    public void sendWhatsappMessage(String phoneNumber, String message) throws Exception {
        // TODO Auto-generated method stub
        sendgrid.sendWhatsApp(phoneNumber, message);
    }

}
