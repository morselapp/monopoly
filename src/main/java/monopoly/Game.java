package monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import monopoly.models.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Game {

    private GameService gameService;
    private Board board;
    private List<Player> players;
    private Bank bank;
    private static Logger log;
    private static Scanner scanner;
    private static FileHandler fileHandler;


    @Inject
    public Game(GameService gameService){
        this.gameService = gameService;
    }

    void startTheGame(){
        try {
            board = new Board();
            board.setBoard(gameService.initializeBoard());
            board.getBoard();
            players = gameService.initializePlayers();
            bank = gameService.initializeBank();
            gameService.setPlayerPosition(players, board);
            gameService.play(players.get(0), board, players, bank);

        }catch (IOException e){
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
        UserInput userInput = new UserInput();
        new Game(new GameService(new ObjectMapper(), log, scanner, userInput)).startTheGame();
    }
}
