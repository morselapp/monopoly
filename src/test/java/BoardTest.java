import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Jail;
import models.Player;
import models.Tax;
import models.Works;
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

    @Before
    public void setup(){
        objectMapper = new ObjectMapper();
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

        List<City> transports = objectMapper.readValue(new File("src/main/configuration/transport.json"), new TypeReference<ArrayList<Transport>>() {});
        List<City> industries = objectMapper.readValue(new File("src/main/configuration/industry.json"), new TypeReference<ArrayList<Industry>>() {});
        List<City> jail = objectMapper.readValue(new File("src/main/configuration/jail.json"), new TypeReference<ArrayList<Jail>>() {});
        List<City> parking = objectMapper.readValue(new File("src/main/configuration/parking.json"), new TypeReference<ArrayList<Free>>() {});
        List<City> start = objectMapper.readValue(new File("src/main/configuration/start.json"), new TypeReference<ArrayList<Start>>() {});
        List<City> taxes = objectMapper.readValue(new File("src/main/configuration/tax.json"), new TypeReference<ArrayList<Tax>>() {});
        List<City> works = objectMapper.readValue(new File("src/main/configuration/works.json"), new TypeReference<ArrayList<Works>>() {});
    }

    @Test
    public void playerDeserializationTest() throws IOException{
        List<Player> players = objectMapper.readValue(new File("src/main/configuration/player.json"), new TypeReference<ArrayList<Player>>() {});
        Assert.assertEquals("Initial money is not matching", 1500, players.get(0).getInitialMoney());
    }
}
