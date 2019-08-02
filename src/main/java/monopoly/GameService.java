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
    private DiceService diceService;

    @Inject
    public GameService(ObjectMapper objectMapper, Logger log, Scanner scanner, UserInput userInput){
        this.objectMapper = objectMapper;
        this.log = log;
        this.scanner = scanner;
        bidService = new BidService(log, userInput);
        diceService = new DiceService(new DiceSuit(new Dice(), new Dice()));
    }

    public Cell[] initializeBoard() throws IOException {
        List<City> cities = objectMapper.readValue(new File("src/main/configuration/city.json"), new TypeReference<ArrayList<City>>() {});
        List<Transport> transports = objectMapper.readValue(new File("src/main/configuration/transport.json"), new TypeReference<ArrayList<Transport>>() {});
        List<Industry> industries = objectMapper.readValue(new File("src/main/configuration/industry.json"), new TypeReference<ArrayList<Industry>>() {});
        List<Jail> jail = objectMapper.readValue(new File("src/main/configuration/jail.json"), new TypeReference<ArrayList<Jail>>() {});
        List<Free> parking = objectMapper.readValue(new File("src/main/configuration/parking.json"), new TypeReference<ArrayList<Free>>() {});
        List<Start> start = objectMapper.readValue(new File("src/main/configuration/start.json"), new TypeReference<ArrayList<Start>>() {});
        List<Tax> taxes = objectMapper.readValue(new File("src/main/configuration/tax.json"), new TypeReference<ArrayList<Tax>>() {});
        List<Works> works = objectMapper.readValue(new File("src/main/configuration/works.json"), new TypeReference<ArrayList<Works>>() {});
        List<Card> cards = objectMapper.readValue(new File("src/main/configuration/card.json"), new TypeReference<ArrayList<Card>>() {});

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

    public List<Player> initializePlayers() throws IOException{
        log.info("players initialized");
        return objectMapper.readValue(new File("src/main/configuration/player.json"), new TypeReference<ArrayList<Player>>() {});
    }

    public Bank initializeBank() throws IOException{
        log.info("bank initialized");
        return objectMapper.readValue(new File("src/main/configuration/bank.json"), new TypeReference<Bank>() {});
    }

    public void setPlayerPosition(List<Player> players, Board board){
        board.setPlayerCellInfo(new HashMap<>());
        players.forEach(player -> board.getPlayerCellInfo().put(player, board.getBoard()[0]));
        log.info("players placed on board");
    }

    public void play(Player player, Board board, List<Player> players, Bank bank){
        List<DiceTuple> diceTuples = diceService.roll();

        int suitOutcome = 0;

        for (DiceTuple tuple: diceTuples) {
            suitOutcome += tuple.getFaceY() +  tuple.getFaceY();
        }

        if(suitOutcome == -2){

            log.info(player.getName() +" rolled doubles thrice with outcome "+ suitOutcome);
            log.info( player.getName() +" sent to jail");
        }
        else {
            log.info(player.getName() + " rolled " + suitOutcome);
        }
        move(player, board, suitOutcome, players, bank);
    }

    private void move(Player player, Board board, int suitOutcome, List<Player> players, Bank bank){
        Cell playerCell = board.getPlayerCellInfo().get(player);

        int position = 0;
        for(Cell cell: board.getBoard()){
            if(cell.equals(playerCell)){
                log.info(player.getName() +" is at "+ board.getBoard()[position].getName());
                break;
            }
            position++;
        }

        board.getPlayerCellInfo().put(player, board.getBoard()[position+suitOutcome]);
        log.info(player.getName() +" reached "+ board.getPlayerCellInfo().get(player).getName());

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

    private void performAction(Player player, Board board, List<Player> players, Bank bank){
        Cell newCell = board.getPlayerCellInfo().get(player);

        if(newCell instanceof BuyableCell){

           // if(getOwner(bank, newCell).isPresent()){

           // }
           // else {
                log.info(newCell.getName() + " is buyable");
                System.out.println("Do you you want to buy " + newCell.getName() + " worth " + ((BuyableCell) newCell).getPrice() + " ?: ");
                System.out.println("Enter y(YES) to buy or enter n(NO) to deny");
                if (scanner.hasNext()) {
                    if (scanner.next().toLowerCase().equals("y")) {
                        log.info(player.getName() + " decided to buy " + newCell.getName());
                    } else {
                        log.info(newCell.getName() + " is available for bid");
                        Bid bid = bidService.performBid(newCell, player, players);
                    }
                }
           // }
        }
        else if(newCell instanceof ChargeableCell){
            log.info(newCell.getName()+" is chargeable cell. You have to pay fine to arrive here");
        }
        else if(newCell instanceof TaxableCell){
            log.info(newCell.getName()+" is taxable cell. You have to pay tax to arrive here");
        }
        else {
            log.info(newCell.getName()+" is free cell. Enjoy your stay here");
        }
    }
}
