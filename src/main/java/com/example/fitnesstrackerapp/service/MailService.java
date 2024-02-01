package com.example.fitnesstrackerapp.service;

import java.util.List;

public interface MailService {
    void sendMail(String subject, String to, String message);

    void sendBulkmail(List<String> to);
}
