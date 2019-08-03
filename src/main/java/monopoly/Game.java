package monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.models.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Game {

    private GameService gameService;
    private static List<Player> players;
    private static Bank bank;
    private static Board board;

    private static UserInput userInput;
    private static Logger log;
    private static Scanner scanner;
    private static FileHandler fileHandler;


    public Game(GameService gameService){
        this.gameService = gameService;
    }

    void startTheGame(){
        try {

            int ROUNDS = 30;

            gameService.setPlayerPosition(players, board);

            int chance = 0;
            int totalPlayers = players.size();

            Player playerWithChance;

            while (chance /3 < ROUNDS) {

                playerWithChance = players.get(chance % totalPlayers);
                gameService.play(playerWithChance, board, players, bank);
                chance++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        log = Logger.getLogger(GameService.class.getName());
        try {
            fileHandler = new FileHandler();
            fileHandler.setFormatter(new XMLFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.addHandler(fileHandler);
        scanner = new Scanner(System.in);
        userInput = new UserInput();

        ObjectMapper objectMapper = new ObjectMapper();

        board = new Board();
        BoardService boardService = new BoardService(board, log, objectMapper);
        board.setBoard(boardService.initializeBoard());

        PlayerService playerService = new PlayerService(log, objectMapper);
        players = playerService.initializePlayers();
        BankService bankService = new BankService(log, objectMapper);
        bank = bankService.initializeBank();
        new Game(new GameService(objectMapper, log, scanner, userInput, board)).startTheGame();
    }
}
