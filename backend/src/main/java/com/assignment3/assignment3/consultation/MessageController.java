package com.assignment3.assignment3.consultation;

import com.assignment3.assignment3.consultation.dto.MessageNewPatient;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/send")
    @SendTo("/receive")
    public MessageNewPatient message(MessageNewPatient patientMessage) {
        return patientMessage;
    }
}
