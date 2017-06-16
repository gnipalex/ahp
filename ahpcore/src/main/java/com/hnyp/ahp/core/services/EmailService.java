package com.hnyp.ahp.core.services;

import com.hnyp.ahp.core.services.data.EmailMessage;

public interface EmailService {

    void sendEmail(EmailMessage message);
    
}
