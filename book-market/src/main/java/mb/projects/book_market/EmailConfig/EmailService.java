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

  public void newTradeInitiated(String userOfferingName, String userOfferingEmail, String userReceivingName,
      String userReceivingEmail, String bookOfferedTitle, String bookRequestedTitle) throws MessagingException {

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    TradeTemplate tradeTemplate = new TradeTemplate();

    helper.setTo(userOfferingEmail);
    helper.setSubject("New Trade Offer - Confirmation");
    helper.setText(tradeTemplate.userOfferingNewTradeEmail(userOfferingName, userReceivingName, bookOfferedTitle,
        bookRequestedTitle), true);
    emailSender.send(message);

    helper.setTo(userReceivingEmail);
    helper.setSubject("New Trade Offer Received!");
    helper.setText(tradeTemplate.userReceivingNewTradeEmail(userReceivingName, userOfferingName, bookRequestedTitle,
        bookOfferedTitle), true);
    emailSender.send(message);
  }

  public void tradeUpdateMessage(String userOfferingName, String userOfferingEmail, String userReceivingName,
      String userReceivingEmail, String bookOfferedTitle, String bookRequestedTitle, String status)
      throws MessagingException {

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    TradeTemplate tradeTemplate = new TradeTemplate();

    if (status.equals("Approved")) {

    helper.setTo(userOfferingEmail);
    helper.setSubject("Trade Offer Approved!");
    helper.setText(tradeTemplate.userOfferingAcceptedTradeEmail(userOfferingName, userReceivingName, bookOfferedTitle,
        bookRequestedTitle), true);
    emailSender.send(message);

    helper.setTo(userReceivingEmail);
    helper.setSubject("You Approved a Trade!");
    helper.setText(tradeTemplate.userReceivingAcceptedTradeEmail(userReceivingName, userOfferingName,
        bookRequestedTitle, bookOfferedTitle), true);
    emailSender.send(message);

  }

   if (status.equals("Declined")) {
    helper.setTo(userOfferingEmail);
    helper.setSubject("Trade Offer Declined");
    helper.setText(tradeTemplate.userOfferingDeclinedTradeEmail(userOfferingName, userReceivingName, bookOfferedTitle,
        bookRequestedTitle), true);
    emailSender.send(message);

    helper.setTo(userReceivingEmail);
    helper.setSubject("You Declined a Trade");
    helper.setText(tradeTemplate.userReceivingDeclinedTradeEmail(userReceivingName, userOfferingName,
        bookRequestedTitle, bookOfferedTitle), true);
    emailSender.send(message);
   } 
}

}