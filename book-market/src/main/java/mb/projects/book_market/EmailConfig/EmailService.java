package mb.projects.book_market.EmailConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender emailSender;

  public void tradeUpdateMessage(
      String to, String subject, String status) throws MessagingException {

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    TradeTemplate tradeTemplate = new TradeTemplate();

    String template = status.equalsIgnoreCase("Accepted") ? tradeTemplate.acceptedEmail("Mu", "Jane Eyre")
        : tradeTemplate.declinedTradeEmail("Mu", "Paddington");

    helper.setFrom("thebookmarket.app@gmail.com");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(template, true);
    emailSender.send(message);

  }

}