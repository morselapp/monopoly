import monopoly.BidService;
import monopoly.models.Bid;
import monopoly.models.Player;
import monopoly.models.cell.BuyableCell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BidServiceTest extends BaseTest{

    private BidService bidService;
    private BuyableCell cell;
    private Player player1, player2, player3;
    private List<Player> players;

    @Before
    public void setup(){
        bidService = new BidService(log, userInput);
        cell = mock(BuyableCell.class);

        player1 = mock(Player.class);
        player2 = mock(Player.class);
        player3 = mock(Player.class);

        when(player1.getName()).thenReturn("Ajeet");
        when(player2.getName()).thenReturn("Ashutosh");
        when(player3.getName()).thenReturn("Kunal");

        players = Arrays.asList(player1, player2, player3);

        when(cell.getPrice()).thenReturn(100.0);
        when(userInput.getBidAmount(any())).thenReturn(200);
    }

    @Test
    public void performBidTest_player2Wins(){

        Bid bid = bidService.performBid(cell, player1, players);
        Assert.assertEquals("Unexpected bid winner","Ashutosh", bid.getFinalQuote().getQuoteBy().getName());
    }

    @Test
    public void performBidTest_player3Wins(){

        Bid bid = bidService.performBid(cell, player2, players);
        Assert.assertEquals("Unexpected bid winner","Kunal", bid.getFinalQuote().getQuoteBy().getName());
    }
}
