package monopoly;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.models.Denomination;
import monopoly.models.Player;
import monopoly.models.cell.TransactionPojo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Logger;

public class PlayerService {

    private Player player;
    private Logger log;
    private ObjectMapper objectMapper;

    public PlayerService(Logger log, ObjectMapper objectMapper){
        this.log = log;
        this.objectMapper = objectMapper;
    }


    public List<Player> initializePlayers() {
        try {
            log.info("players initialized");
            return objectMapper.readValue(new File("src/main/configuration/player.json"), new TypeReference<ArrayList<Player>>() {
            });
        }catch (IOException e){
            log.info("Failed to initialize players "+e);
        }

        return null;
    }


}
