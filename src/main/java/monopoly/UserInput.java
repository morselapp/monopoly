package monopoly;

import monopoly.models.Bid;

import java.util.Scanner;

public class UserInput {

    Scanner scanner = new Scanner(System.in);

    public int getBidAmount(Bid bid){

        int bidAmount = 0;
        while (scanner.hasNext()) {
            bidAmount = Integer.parseInt(scanner.next());
            if (bidAmount < bid.getBaseQuote()) {
                System.out.println("Invalid bid. Bid value must be at least " + bid.getBaseQuote());

            } else {
                break;
            }
        }
        return bidAmount;
    }
}
