package mb.projects.book_market.EmailConfig;

import lombok.Data;

@Data
public class TradeTemplate {

    public String userOfferingNewTradeEmail(String userOffering, String userReceiving, String bookOffered,
            String bookRequested) {

        return String.format("""
                <html>
                <body>
                    <h2>New Trade Offer</h2>
                    <p><strong> Hi %s,</p>
                    <p>We've sent your trade offer to %s<p>
                    <p>To trade your book, %s for %s </p>
                    <br>
                    <p>We'll let you know once they have a decision :)</p>
                    <p>Thank you for being part of the Book Market community!</p>
                </body>
                </html>
                """, userOffering, userReceiving, bookOffered, bookRequested);

    }

    public String userReceivingNewTradeEmail(String userReceiving, String userOffering, String bookRequested,
            String bookOffered) {

        return String.format("""
                <html>
                <body>
                    <h2>New Trade Offer</h2>
                    <p><strong> Hi %s,</p>
                    <p>You have received a new trade offer from %s<p>
                    <p>To trade your book, %s for %s </p>
                    <br>
                    <p>You can see more info about the trade on your profile</p>
                    <p>Please accept or declined the offer</p>
                    <p>Thank you for being part of the Book Market community!</p>
                </body>
                </html>
                """, userReceiving, userOffering, bookRequested, bookOffered);

    }

    public String userOfferingAcceptedTradeEmail(String userOffering, String userReceiving, String bookOffered,
            String bookRequested) {

        return String.format("""
                <html>
                <body>
                    <h2>Trade Accepted!</h2>
                    <p><strong> Hi %s,</p>
                    <p>%s has accepted your trade offer<p>
                    <p>To trade your book, %s for %s </p>
                    <br>
                    <p>You can see more info about the trade on your profile</p>
                    <p>Thank you for being part of the Book Market community!</p>
                </body>
                </html>
                """, userOffering, userReceiving, bookOffered, bookRequested);

    }

    public String userReceivingAcceptedTradeEmail(String userReceiving, String userOffering, String bookRequested,
            String bookOffered) {

        return String.format("""
                <html>
                <body>
                    <h2>Trade Accepted!</h2>
                    <p><strong> Hi %s,</p>
                    <p>You have accepted a trade offer from %s<p>
                    <p>To trade your book, %s for %s </p>
                    <br>
                    <p>You can see more info about the trade on your profile</p>
                    <p>Thank you for being part of the Book Market community!</p>
                </body>
                </html>
                """, userReceiving, userOffering, bookRequested, bookOffered);

    }

    public String userOfferingDeclinedTradeEmail(String userOffering, String userReceiving, String bookOffered,
            String bookRequested) {

        return String.format("""
                <html>
                <body>
                    <h2>Trade Declined</h2>
                    <p><strong> Hi %s,</p>
                    <p>%s has declined your trade offer<p>
                    <p>To trade your book, %s for %s </p>
                    <br>
                    <p>You can see more info about the trade on your profile</p>
                    <p>Thank you for being part of the Book Market community!</p>
                </body>
                </html>
                """, userOffering, userReceiving, bookOffered, bookRequested);
    }

    public String userReceivingDeclinedTradeEmail(String userOffering, String userReceiving, String bookOffered,
            String bookRequested) {

        return String.format("""
                <html>
                <body>
                    <h2>Trade Declined</h2>
                    <p><strong> Hi %s,</p>
                    <p>You have declined %s's trade offer<p>
                    <p>To trade your book, %s for %s </p>
                    <br>
                    <p>You can see more info about the trade on your profile</p>
                    <p>Thank you for being part of the Book Market community!</p>
                </body>
                </html>
                """, userReceiving, userOffering, bookRequested, bookOffered);

    }

}