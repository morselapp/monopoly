package monopoly;

import monopoly.models.Bid;
import monopoly.models.Player;
import monopoly.models.Quote;
import monopoly.models.cell.BuyableCell;
import monopoly.models.cell.Cell;

import java.util.List;
import java.util.logging.Logger;

public class BidService {

    private Bid bid;
    private Logger log;
    private UserInput userInput;

    public BidService(Logger log, UserInput userInput){

        this.log = log;
        this.userInput = userInput;
    }

    public Bid performBid(Cell newCell, Player player, List<Player> players){
        bid = new Bid(((BuyableCell)newCell).getPrice());
        log.info(bid.getBaseQuote() +" is the base quote for the bid");

        int currentPlayerIndex = players.indexOf(player);

        while(bid.getFinalQuote() == null) {

            Player nextBidder = players.get(++currentPlayerIndex);
            log.info(nextBidder.getName() + " is the next player to bid");
            System.out.println(nextBidder.getName() + " please enter your bid amount");

            int bidAmount = userInput.getBidAmount(bid);

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

        Player winner = bid.getFinalQuote().getQuoteBy();

        log.info(winner.getName() +" won the bid");
        log.info("Initiating the purchase");
        return bid;
    }
}
