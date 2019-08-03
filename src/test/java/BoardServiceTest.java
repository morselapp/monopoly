import com.fasterxml.jackson.core.type.TypeReference;
import monopoly.BoardService;
import monopoly.models.*;
import monopoly.models.cell.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.XMLFormatter;
import java.util.stream.Stream;

public class BoardServiceTest extends BaseTest{

    private Board board;
    private FileHandler fileHandler;

    {
        try {
            fileHandler = new FileHandler();
            fileHandler.setFormatter(new XMLFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup(){
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
    public void boardInitializationTest() throws IOException{
        board = new Board();
        board.setBoard(new BoardService(board, log, objectMapper).initializeBoard());
        Assert.assertEquals("Number of cells is not matching", board.getBoard().length, 40);
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[0].getName(), "Starting Cell");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[1].getName(), "Bangalore");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[2].getName(), "Mumbai");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[3].getName(), "Information Technology");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[4].getName(), "New Delhi");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[5].getName(), "Railways");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[6].getName(), "Kolkata");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[7].getName(), "Chennai");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[8].getName(), "Agriculture");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[9].getName(), "Community card");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[10].getName(), "Jail");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[11].getName(), "Steel");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[12].getName(), "Varanasi");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[13].getName(), "Water works");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[14].getName(), "Prayagraj");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[15].getName(), "Aerodrome");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[16].getName(), "Haridwar");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[17].getName(), "Hampi");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[18].getName(), "Community card");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[19].getName(), "Film");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[20].getName(), "Parking");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[21].getName(), "Agartala");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[22].getName(), "Construction");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[23].getName(), "Textile");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[24].getName(), "Panaji");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[25].getName(), "Roadways");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[26].getName(), "Chance card");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[27].getName(), "Ooty");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[28].getName(), "Food");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[29].getName(), "Gangtok");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[30].getName(), "Go to Jail");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[31].getName(), "Jalandhar");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[32].getName(), "Income tax");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[33].getName(), "Hyderabad");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[34].getName(), "Automobile");

        Stream.of(board.getBoard()).forEach(cell -> System.out.println(cell.getName()+" "+cell.getId()));

        Assert.assertEquals("Number of cells is not matching", board.getBoard()[35].getId(), 35);
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[35].getName(), "Waterways");

        Assert.assertEquals("Number of cells is not matching", board.getBoard()[36].getName(), "GST");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[37].getName(), "Clean Energy");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[38].getName(), "Ladakh");
        Assert.assertEquals("Number of cells is not matching", board.getBoard()[39].getName(), "Chance card");
    }
}
