package models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.cell.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<Transport> transports = objectMapper.readValue(new File("src/main/configuration/transport.json"), new TypeReference<ArrayList<Transport>>() {});
        List<Industry> industries = objectMapper.readValue(new File("src/main/configuration/industry.json"), new TypeReference<ArrayList<Industry>>() {});
        List<Jail> jail = objectMapper.readValue(new File("src/main/configuration/jail.json"), new TypeReference<ArrayList<Jail>>() {});
        List<Free> parking = objectMapper.readValue(new File("src/main/configuration/parking.json"), new TypeReference<ArrayList<Free>>() {});
        List<Start> start = objectMapper.readValue(new File("src/main/configuration/start.json"), new TypeReference<ArrayList<Start>>() {});
        List<Tax> taxes = objectMapper.readValue(new File("src/main/configuration/tax.json"), new TypeReference<ArrayList<Tax>>() {});
        List<Works> works = objectMapper.readValue(new File("src/main/configuration/works.json"), new TypeReference<ArrayList<Works>>() {});

        board = new Cell[40];

       // fillValues(board, cities);

        cities.forEach( city -> {
            Arrays.stream(board)
                .forEach(cell -> {
                    board[0] = new City();
                    cell.setId(city.getId());
                    cell.setName(city.getName());
                    cell.setType(city.getType());
                    cell.setIcon(city.getIcon());
                    cell.setColor(city.getColor());
                    cell.setDescription(city.getDescription());
                    ((City) cell).setHomestaysCount(city.getHomestaysCount());
                    ((City) cell).setResortsCount(city.getResortsCount());
                    ((City) cell).setPrice(city.getPrice());
                    ((City) cell).setRent(city.getRent());
                    ((City) cell).setMortgage(city.getMortgage());
                    System.out.println(cell.getName());
                });
        });

        transports.forEach( city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        ((City) cell).setPrice(city.getPrice());
                        ((City) cell).setRent(city.getRent());
                        ((City) cell).setMortgage(city.getMortgage());
                        System.out.println(cell.getName());
                    });
        });
    }

    private void fillValues(Cell[] board, List<City> cells){
        cells.forEach( city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        System.out.println(cell.getName());
                    });
        });
    }
}
