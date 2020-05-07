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
    private static Logger logger;
    private static Scanner scanner;
    private static FileHandler fileHandler;


    public Game(GameService gameService) {
        this.gameService = gameService;
    }

    void startTheGame() {
        try {

            int MAX_ROUNDS = 30;

            gameService.setPlayerPosition(players, board);

            int rounds = 0;
            while (rounds < MAX_ROUNDS) {
                players.forEach(player -> gameService.play(player, board, players, bank));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        logger = Logger.getLogger(GameService.class.getName());
        try {
            fileHandler = new FileHandler();
            fileHandler.setFormatter(new XMLFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);
        scanner = new Scanner(System.in);
        userInput = new UserInput();

        ObjectMapper objectMapper = new ObjectMapper();

        board = new Board();
        BoardService boardService = new BoardService(board, logger, objectMapper);
        boardService.initializeBoard();

        PlayerService playerService = new PlayerService(logger, objectMapper);
        players = playerService.initializePlayers();
        BankService bankService = new BankService(logger, objectMapper);
        bank = bankService.initializeBank();
        new Game(new GameService(objectMapper, logger, scanner, userInput, board)).startTheGame();
    }
}
