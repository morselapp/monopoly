package monopoly;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.models.*;
import monopoly.models.cell.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    private ObjectMapper objectMapper;

    public GameService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
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
        return cells.toArray(new Cell[40]);
    }

    public List<Player> initializePlayers() throws IOException{
        return objectMapper.readValue(new File("src/main/configuration/player.json"), new TypeReference<ArrayList<Player>>() {});
    }

    public Bank initializeBank() throws IOException{
        return objectMapper.readValue(new File("src/main/configuration/bank.json"), new TypeReference<Bank>() {});
    }

    public void serPlayerPosition(){

    }
}
