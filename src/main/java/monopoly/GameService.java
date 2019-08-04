package monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.models.*;
import monopoly.models.cell.*;

import java.util.*;
import java.util.logging.*;

public class GameService {

    private ObjectMapper objectMapper;
    private Logger log;
    private Scanner scanner;
    private BidService bidService;
    private DiceService diceService;
    private BoardService boardService;
    private TransactionService1 transactionService;

    public GameService(ObjectMapper objectMapper, Logger log, Scanner scanner, UserInput userInput, Board board) {
        this.objectMapper = objectMapper;
        this.log = log;
        this.scanner = scanner;
        bidService = new BidService(log, userInput);
        diceService = new DiceService(new DiceSuit(new Dice(), new Dice()));
        boardService = new BoardService(board, log, objectMapper);
        transactionService = new TransactionService1();
    }

    public void setPlayerPosition(List<Player> players, Board board) {
        board.setPlayerCellInfo(new HashMap<>());
        players.forEach(player -> board.getPlayerCellInfo().put(player, board.getCells()[0]));
        log.info("players placed on board");
    }

    public void play(Player player, Board board, List<Player> players, Bank bank) {
        List<DiceTuple> diceTuples = diceService.roll();

        int suitOutcome = 0;

        for (DiceTuple tuple : diceTuples) {
            suitOutcome += tuple.getFaceX() + tuple.getFaceY();
        }

        if (suitOutcome == -2) {

            log.info(player.getName() + " rolled doubles thrice with outcome " + suitOutcome);
            log.info(player.getName() + " sent to jail");
        } else {
            log.info(player.getName() + " rolled " + suitOutcome);
        }
        boardService.move(player, suitOutcome);
        performAction(player, board, players, bank);
    }

/*    private Optional<Player> getOwner(Bank bank, Cell cell){
        if(bank.getAssetsOwner().get(cell) != null){
            return Optional.of(bank.getAssetsOwner().get(cell));
        }
        else{
            return Optional.empty();
        }
    }*/

    private void performAction(Player player, Board board, List<Player> players, Bank bank) {
        Cell newCell = board.getPlayerCellInfo().get(player);

        if (newCell instanceof BuyableCell) {
            if (checkBoughtState(player, newCell)) {
                if (player.getId() == getOwnerId(newCell)) {
                    log.info(" Welcome back on your property");
                } else {
                    log.info(newCell.getName() + " is already brought and now you have to pay rent to the owner");
                    // method to rent a property
                }

            } else if (!checkBoughtState(player, newCell)) {
                log.info(newCell.getName() + " is buyable");
                System.out.println(player.getName() + ", do you want to buy " + newCell.getName() + " worth " + ((BuyableCell) newCell).getPrice() + " ?: ");
                System.out.println("Enter y(YES) to buy or enter n(NO) to deny");
                if (scanner.hasNext()) {
                    double cellPrice = ((BuyableCell) newCell).getPrice();
                    if (scanner.next().toLowerCase().equals("y") && player.currentMoney() >= cellPrice) {
                        log.info(player.getName() + " decided to buy " + newCell.getName());
                        boolean transaction = transactionService.transact(player, bank, cellPrice);
                    } else {
                        log.info(newCell.getName() + " is available for bid");
                        Bid bid = bidService.performBid(newCell, player, players);
                    }
                }
            }
        } else if (newCell instanceof ChargeableCell) {
            log.info(newCell.getName() + " is chargeable cell. You have to pay fine to arrive here");
        } else if (newCell instanceof TaxableCell) {
            log.info(newCell.getName() + " is taxable cell. You have to pay tax to arrive here");
        } else {
            log.info(newCell.getName() + " is free cell. Enjoy your stay here");
        }
    }

    private boolean checkBoughtState(Player player, Cell newCell) {
        if (((BuyableCell) newCell).getBoughtState()) return true;
        return false;
    }

    private int getOwnerId(Cell newCell) {
        return ((BuyableCell) newCell).getOwnerId();

    }
}
