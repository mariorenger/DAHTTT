package com.httt.backend.service;

import com.httt.backend.entity.Email;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(Email email) throws MessagingException;

    void resetPassword(String email, String token) throws MessagingException;
}
