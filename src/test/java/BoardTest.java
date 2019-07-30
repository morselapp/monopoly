import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import models.cell.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    private ObjectMapper objectMapper;
    private Board board;

    @Before
    public void setup(){
        objectMapper = new ObjectMapper();
        board = new Board();
    }

    @Test
    public void initialize() throws IOException {
        List<City> cities = objectMapper.readValue(new File("src/main/configuration/city.json"), new TypeReference<ArrayList<City>>() {});
        Assert.assertEquals("City name is not matching", "Bangalore", cities.get(0).getName());
        Assert.assertEquals("City description is not matching", "Bangalore is considered as the IT capital of India", cities.get(0).getDescription());
        Assert.assertEquals("City price is not matching", 450.0, cities.get(0).getPrice(), 0.0);
    }

    @Test
    public void cellDeserializationTest() throws IOException{

        List<Transport> transports = objectMapper.readValue(new File("src/main/configuration/transport.json"), new TypeReference<ArrayList<Transport>>() {});
        List<Industry> industries = objectMapper.readValue(new File("src/main/configuration/industry.json"), new TypeReference<ArrayList<Industry>>() {});
        List<Jail> jail = objectMapper.readValue(new File("src/main/configuration/jail.json"), new TypeReference<ArrayList<Jail>>() {});
        List<Free> parking = objectMapper.readValue(new File("src/main/configuration/parking.json"), new TypeReference<ArrayList<Free>>() {});
        List<Start> start = objectMapper.readValue(new File("src/main/configuration/start.json"), new TypeReference<ArrayList<Start>>() {});
        List<Tax> taxes = objectMapper.readValue(new File("src/main/configuration/tax.json"), new TypeReference<ArrayList<Tax>>() {});
        List<Works> works = objectMapper.readValue(new File("src/main/configuration/works.json"), new TypeReference<ArrayList<Works>>() {});
    }

    @Test
    public void playerDeserializationTest() throws IOException{
        List<Player> players = objectMapper.readValue(new File("src/main/configuration/player.json"), new TypeReference<ArrayList<Player>>() {});
        Assert.assertEquals("Initial money is not matching", 1500, players.get(0).getInitialMoney());
    }

    @Test
    public void boardInitializationTest() throws IOException{
        board.initialize();
        Assert.assertEquals("Number of cells is not matching", board.getBoard().length, 40);
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[0], "Bangalore");

    }
}
