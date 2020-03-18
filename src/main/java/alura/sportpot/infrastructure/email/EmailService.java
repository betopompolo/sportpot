package alura.sportpot.infrastructure.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * EmailService
 */
@Service
public class EmailService {

  @Autowired
  JavaMailSender eMailSender;

  public void sendMessage(String to, String subject, String body) throws Exception {
    MimeMessage message = eMailSender.createMimeMessage();
    MimeMessageHelper messageHelper = new MimeMessageHelper(message);

    messageHelper.setTo(to);
    messageHelper.setSubject(subject);
    messageHelper.setText(body, true);

    eMailSender.send(message);
  }
  
}