package monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.models.*;
import monopoly.models.cell.*;
import java.io.IOException;
import java.util.List;

public class Game {

    private GameService gameService;
    private Cell[] board;
    private List<Player> players;
    private Bank bank;


    public Game(GameService gameService){
        this.gameService = gameService;
    }

    void startTheGame(){
        try {
            board = gameService.initializeBoard();
            players = gameService.initializePlayers();
            bank = gameService.initializeBank();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game(new GameService(new ObjectMapper())).startTheGame();
    }
}
