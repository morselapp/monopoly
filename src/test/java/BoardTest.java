import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.GameService;
import monopoly.models.*;
import monopoly.models.cell.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        List<Card> cards = objectMapper.readValue(new File("src/main/configuration/card.json"), new TypeReference<ArrayList<Card>>() {});

    }

    @Test
    public void playerDeserializationTest() throws IOException{
        List<Player> players = objectMapper.readValue(new File("src/main/configuration/player.json"), new TypeReference<ArrayList<Player>>() {});
        Assert.assertEquals("Initial money is not matching", 1500, players.get(0).getInitialMoney());
    }

    @Test
    public void boardInitializationTest() throws IOException{
        new GameService(objectMapper).initializeBoard();
        Assert.assertEquals("Number of cells is not matching", board.getBoard().length, 40);
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[0].getName(), "Bangalore");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[1].getName(), "Mumbai");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[2].getName(), "New Delhi");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[3].getName(), "Chennai");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[4].getName(), "Varanasi");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[5].getName(), "Pragraj");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[6].getName(), "Haridwar");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[7].getName(), "Hampi");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[8].getName(), "Tripura");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[10].getName(), "Ooty");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[15].getName(), "Ladakh");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[16].getName(), "Railways");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[17].getName(), "Aerodrome");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[18].getName(), "Roadways");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[19].getName(), "Waterways");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[20].getName(), "Information Technology");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[32].getName(), "Income tax");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[34].getName(), "Water works");

        Stream.of(board.getBoard()).forEach(cell -> System.out.println(cell.getName()+" "+cell.getId()));

        Assert.assertEquals("Number of cells is not matching", board.getBoard()[35].getId(), 22);
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[35].getName(), "Construction");

        Assert.assertEquals("Number of cells is not matching", board.getBoard()[36].getName(), "Community card");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[37].getName(), "Community card");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[38].getName(), "Chance card");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[39].getName(), "Chance card");
    }


}
