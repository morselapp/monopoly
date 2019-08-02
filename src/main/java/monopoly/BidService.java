package monopoly;

import monopoly.models.Bid;
import monopoly.models.Player;
import monopoly.models.Quote;
import monopoly.models.cell.BuyableCell;
import monopoly.models.cell.Cell;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class BidService {

    private Bid bid;
    private Player player;
    private List<Player> players;
    private Logger log;
    private Scanner scanner;

    public BidService(Logger log, Scanner scanner){


        this.log = log;
        this.scanner = scanner;
    }

    public void initiateBid(Cell newCell, Player player){
        bid = new Bid(((BuyableCell)newCell).getPrice());
        log.info(bid.getBaseQuote() +" is the base quote for the bid");

        int currentPlayerIndex = players.indexOf(player);

        while(bid.getFinalQuote() == null) {

            Player nextBidder = players.get(++currentPlayerIndex);
            log.info(nextBidder.getName() + " is the next player to bid");
            System.out.println(nextBidder.getName() + " please enter your bid amount");

            int bidAmount = 0;
            while (scanner.hasNext()) {
                bidAmount = Integer.parseInt(scanner.next());
                if (bidAmount < bid.getBaseQuote()) {
                    System.out.println("Invalid bid. Bid value must be at least " + bid.getBaseQuote());

                } else {
                    break;
                }
            }

            if (bid.getCurrentQuote() == null) {
                bid.setCurrentQuote(new Quote(bidAmount, nextBidder));
            }

            if (bid.getCurrentQuote().getQuote() < bidAmount) {
                bid.setCurrentQuote(new Quote(bidAmount, nextBidder));
            }

            if(players.size() == currentPlayerIndex +1){
                bid.setFinalQuote(bid.getCurrentQuote());
            }
        }
        log.info(bid.getFinalQuote().getQuoteBy().getName() +" won the bid");
        log.info("Initiating the purchase");
    }
}
