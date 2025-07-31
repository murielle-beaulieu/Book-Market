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

  public void newTradeInitiated(String userOfferingName, String userOfferingEmail, String userReceivingName, String userReceivingEmail, String bookOfferedTitle, String bookRequestedTitle, String sendTo ) throws MessagingException {

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    TradeTemplate tradeTemplate = new TradeTemplate();

    helper.setFrom("thebookmarket.app@gmail.com");
    if (sendTo.equals("userOffering")) {
      helper.setTo(userOfferingEmail);
      helper.setSubject("New Trade Offer - Confirmation");
      helper.setText(tradeTemplate.userOfferingNewTradeEmail(userOfferingName, userReceivingName, bookOfferedTitle, bookRequestedTitle), true);
    }

    if (sendTo.equals("userReceiving")) {
      helper.setTo(userReceivingEmail);
      helper.setSubject("New Trade Offer Received!");
      helper.setText(tradeTemplate.userReceivingNewTradeEmail(userReceivingName, userOfferingName, bookRequestedTitle, bookOfferedTitle),true);
    }

    emailSender.send(message);
  }

  public void tradeUpdateMessage(
      String to, String subject, String status) throws MessagingException {

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    TradeTemplate tradeTemplate = new TradeTemplate();

    String template = status.equalsIgnoreCase("Accepted") ? tradeTemplate.acceptedTradeEmail("Mu", "Jane Eyre")
        : tradeTemplate.declinedTradeEmail("Mu", "Paddington");

    helper.setFrom("thebookmarket.app@gmail.com");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(template, true);
    emailSender.send(message);

  }

}