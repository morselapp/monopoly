package models;

public class Board {
    private Cell [] board;

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
}
