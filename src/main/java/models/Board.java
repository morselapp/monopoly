package models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.cell.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private Cell[] board;

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

    public void initialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<City> cities = objectMapper.readValue(new File("src/main/configuration/city.json"), new TypeReference<ArrayList<City>>() {});
        List<City> transports = objectMapper.readValue(new File("src/main/configuration/transport.json"), new TypeReference<ArrayList<Transport>>() {});
        List<City> industries = objectMapper.readValue(new File("src/main/configuration/industry.json"), new TypeReference<ArrayList<Industry>>() {});
        List<City> jail = objectMapper.readValue(new File("src/main/configuration/jail.json"), new TypeReference<ArrayList<Jail>>() {});
        List<City> parking = objectMapper.readValue(new File("src/main/configuration/parking.json"), new TypeReference<ArrayList<Free>>() {});
        List<City> start = objectMapper.readValue(new File("src/main/configuration/city.json"), new TypeReference<ArrayList<Start>>() {});
        List<City> taxes = objectMapper.readValue(new File("src/main/configuration/city.json"), new TypeReference<ArrayList<Tax>>() {});
        List<City> works = objectMapper.readValue(new File("src/main/configuration/city.json"), new TypeReference<ArrayList<Works>>() {});
    }
}
