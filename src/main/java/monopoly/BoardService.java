package monopoly;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.Utils.Utils;
import monopoly.models.*;
import monopoly.models.cell.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class BoardService {

    private Board board;
    private Logger log;
    private ObjectMapper objectMapper;

    public BoardService(Board board, Logger log, ObjectMapper objectMapper){
        this.board = board;
        this.log = log;
        this.objectMapper = objectMapper;
    }

    public Cell[] initializeBoard() {
        try {
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
        catch (IOException e){
            log.severe("Unable to intitialize cells "+e);
        }
        return null;
    }

    public void move(Player player, int suitOutcome) {
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
    }
}
