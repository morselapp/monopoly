package monopoly.models;

import monopoly.models.cell.*;
import java.util.Map;

public class Board {
    private Cell[] cells;
    private Map<Player, Cell> playerCellInfo;
    public static final int CELL_COUNT = 40;

    public Board(){

    }

    public Board(Cell [] board){
        this.cells = board;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell [] cells){
        this.cells = cells;
    }

    public Map<Player, Cell> getPlayerCellInfo() {
        return playerCellInfo;
    }

    public void setPlayerCellInfo(Map<Player, Cell> playerCellInfo) {
        this.playerCellInfo = playerCellInfo;
    }
}
