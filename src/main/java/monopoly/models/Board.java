package monopoly.models;

import monopoly.models.cell.*;
import java.util.Map;

public class Board {
    private Cell[] board;
    private Map<Player, Cell> playerCellInfo;

    public Board(){

    }

    public Board(Cell [] board){
        this.board = board;
    }

    public Cell[] getBoard() {
        return board;
    }

    public void setBoard(Cell [] board){
        this.board = board;
    }

    public Map<Player, Cell> getPlayerCellInfo() {
        return playerCellInfo;
    }

    public void setPlayerCellInfo(Map<Player, Cell> playerCellInfo) {
        this.playerCellInfo = playerCellInfo;
    }
}
