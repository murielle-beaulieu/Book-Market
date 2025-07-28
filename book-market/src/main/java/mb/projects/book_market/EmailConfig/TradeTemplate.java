package mb.projects.book_market.EmailConfig;

import lombok.Data;

@Data
public class TradeTemplate {

    public String acceptedEmail(String name, String bookName) {

        return String.format("""
                <html>
                <body>
                    <h2>Trade Accepted!</h2>
                    <p><strong>Hello </strong> %s</p>
                    <p><strong>Book:</strong> %s</p>
                    <table border="1" style="border-collapse: collapse;">
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
                    <table border="1" style="border-collapse: collapse;">
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
                    <p>Thank you for being part of the book market community</p>
                </body>
                </html>
                """, name, bookName, name, bookName);
    }
}