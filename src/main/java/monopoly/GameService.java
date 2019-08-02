package monopoly;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import monopoly.models.*;
import monopoly.models.cell.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

public class GameService {

    private ObjectMapper objectMapper;
    private Logger log;
    private Scanner scanner;
    private BidService bidService;

    {
        try {
            fileHandler = new FileHandler();
            fileHandler.setFormatter(new XMLFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameService(ObjectMapper objectMapper, Logger log, Scanner scanner) {
        this.objectMapper = objectMapper;
        this.log = log;
        this.scanner = scanner;
        bidService = new BidService(log, scanner);
    }

    public Cell[] initializeBoard() throws IOException {
        List<City> cities = objectMapper.readValue(new File("src/main/configuration/city.json"), new TypeReference<ArrayList<City>>() {
        });
        List<Transport> transports = objectMapper.readValue(new File("src/main/configuration/transport.json"), new TypeReference<ArrayList<Transport>>() {
        });
        List<Industry> industries = objectMapper.readValue(new File("src/main/configuration/industry.json"), new TypeReference<ArrayList<Industry>>() {
        });
        List<Jail> jail = objectMapper.readValue(new File("src/main/configuration/jail.json"), new TypeReference<ArrayList<Jail>>() {
        });
        List<Free> parking = objectMapper.readValue(new File("src/main/configuration/parking.json"), new TypeReference<ArrayList<Free>>() {
        });
        List<Start> start = objectMapper.readValue(new File("src/main/configuration/start.json"), new TypeReference<ArrayList<Start>>() {
        });
        List<Tax> taxes = objectMapper.readValue(new File("src/main/configuration/tax.json"), new TypeReference<ArrayList<Tax>>() {
        });
        List<Works> works = objectMapper.readValue(new File("src/main/configuration/works.json"), new TypeReference<ArrayList<Works>>() {
        });
        List<Card> cards = objectMapper.readValue(new File("src/main/configuration/card.json"), new TypeReference<ArrayList<Card>>() {
        });

        Cell[] board = new Cell[1];
        List<Cell> cells = new ArrayList<>();
        Utils.renderCells(board, cities, transports, industries, jail, parking, start, taxes, works, cards, cells);

        /*
            Sorting it here to make sure the cells are orders as per the ids.
         */
        Collections.sort(cells);
        log.info("board initialized");
        return cells.toArray(new Cell[40]);
    }

    public List<Player> initializePlayers() throws IOException {
        log.info("players initialized");
        return objectMapper.readValue(new File("src/main/configuration/player.json"), new TypeReference<ArrayList<Player>>() {
        });
    }

    public Bank initializeBank() throws IOException {
        log.info("bank initialized");
        return objectMapper.readValue(new File("src/main/configuration/bank.json"), new TypeReference<Bank>() {
        });
    }

    public void setPlayerPosition(List<Player> players, Board board) {
        board.setPlayerCellInfo(new HashMap<>());
        players.forEach(player -> board.getPlayerCellInfo().put(player, board.getBoard()[0]));
        log.info("players placed on board");
    }

    public void play(Player player, Board board, DiceSuit diceSuit, List<Player> players, Bank bank) {
        int suitOutcome = diceSuit.roll();
        boolean isDouble = diceSuit.isDouble();
        if (isDouble) {
            log.info(player.getName() + " rolled a double with outcome " + suitOutcome);
        } else {
            log.info(player.getName() + " rolled " + suitOutcome);
        }
        move(player, board, suitOutcome, isDouble, players, bank);
    }

    private void move(Player player, Board board, int suitOutcome, boolean isDouble, List<Player> players, Bank bank) {
        Cell playerCell = board.getPlayerCellInfo().get(player);

        int position = 0;
        for (Cell cell : board.getBoard()) {
            if (cell.equals(playerCell)) {
                log.info(player.getName() + " is at " + board.getBoard()[position].getName());
                break;
            }
            position++;
        }

        board.getPlayerCellInfo().put(player, board.getBoard()[(position + suitOutcome) % 40]);
        log.info(player.getName() + " reached " + board.getPlayerCellInfo().get(player).getName());

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
                System.out.println("Do you you want to buy " + newCell.getName() + " worth " + ((BuyableCell) newCell).getPrice() + " ?: ");
                System.out.println("Enter y(YES) to buy or enter n(NO) to deny");
                if (scanner.hasNext()) {
                    if (scanner.next().toLowerCase().equals("y") && player.currentMoney() >= ((BuyableCell) newCell).getPrice()) {
                        log.info(player.getName() + " decided to buy " + newCell.getName());
                        ((BuyableCell) newCell).buy(bank, player, newCell);


                    } else {
                        log.info(newCell.getName() + " is available for bid");
                        Bid bid = new Bid(((BuyableCell) newCell).getPrice());
                        bidService.initiateBid(newCell, player);
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

    private void initiateBid(Bid bid, Player player, List<Player> players) {
        log.info(bid.getBaseQuote() + " is the base quote for the bid");

        int currentPlayerIndex = players.indexOf(player);
        Player nextBidder = players.get(currentPlayerIndex + 1);

        log.info(nextBidder.getName() + " is the next player to bid");

        System.out.println(nextBidder.getName() + " please enter your bid amount");

        while (scanner.hasNext()) {
            int bidAmount = Integer.parseInt(scanner.next());
            if (bidAmount < bid.getBaseQuote()) {
                System.out.println("Invalid bid. Bid value must be at least " + bid.getBaseQuote());
            } else {
                bid.setCurrentQuote(new Quote(bidAmount, nextBidder));
                break;
            }
        }

    }

}
}
