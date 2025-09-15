package com.lldproject.udemyacademy.library.sendgrid;

import org.springframework.stereotype.Component;

@Component
public class Sendgrid {

    public void sendEmailAsync(String email, String message) throws Exception {
        System.out.println("Sending email to " + email + " with message " + message);
    }

    public void sendWhatsApp(String phoneNumber, String message) throws Exception {
        System.out.println("Sending WhatsApp to " + phoneNumber + " with message " + message);
    }
}
