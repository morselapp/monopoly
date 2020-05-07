import com.fasterxml.jackson.core.type.TypeReference;
import monopoly.models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.XMLFormatter;

public class PlayerServiceTest extends BaseTest{

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
    public void playerDeserializationTest() throws IOException{
        List<Player> players = objectMapper.readValue(new File("src/main/configuration/player.json"), new TypeReference<ArrayList<Player>>() {});
        Assert.assertEquals("Initial money is not matching", 1500, players.get(0).getInitialMoney());
    }
}
