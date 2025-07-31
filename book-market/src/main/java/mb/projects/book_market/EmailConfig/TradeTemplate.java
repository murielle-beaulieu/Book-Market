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

    public String acceptedTradeEmail(String name, String bookName) {

        return String.format("""
                <html>
                <body>
                    <h2>Trade Accepted!</h2>
                    <p><strong>Hello </strong> %s</p>
                    <p><strong>Book:</strong> %s</p>
                    <table border="1">
                        <tr>
                            <th>Trader</th>
                            <th>Book</th>
                            <th>Status</th>
                        </tr>
                        <tr>
                            <td>%s</td>
                            <td>%s</td>
                            <td>Accepted</td>
                        </tr>
                    </table>
                    <p>Thank you for trading!</p>
                </body>
                </html>
                """, name, bookName, name, bookName);

    }

    public String declinedTradeEmail(String name, String bookName) {
        return String.format("""
                <html>
                <body>
                    <h2>Trade Declined</h2>
                    <p><strong>Hello </strong> %s</p>
                    <p><strong>Book:</strong> %s</p>
                    <table border="1">
                        <tr>
                            <th>Trader</th>
                            <th>Book</th>
                            <th>Status</th>
                        </tr>
                        <tr>
                            <td>%s</td>
                            <td>%s</td>
                            <td>Declined</td>
                        </tr>
                    </table>
                    <p>Thank you for being part of the book market community!</p>
                </body>
                </html>
                """, name, bookName, name, bookName);
    }

}