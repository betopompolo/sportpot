package alura.sportpot.infrastructure.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * EmailService
 */
@Service
public class EmailService {

  @Autowired
  JavaMailSender eMailSender;

  public void sendMessage(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);

    eMailSender.send(message);
  }
  
}