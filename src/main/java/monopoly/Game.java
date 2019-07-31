package monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.models.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Game {

    private GameService gameService;
    private Board board;
    private List<Player> players;
    private Bank bank;


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


            gameService.play(players.get(0), board, new DiceSuit(new Dice(), new Dice()), players, bank);





        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game(new GameService(new ObjectMapper())).startTheGame();
    }
}
