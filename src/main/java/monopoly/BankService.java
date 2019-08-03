package monopoly;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.models.Bank;
import monopoly.models.Player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class BankService {

    private Player player;
    private Logger log;
    private ObjectMapper objectMapper;

    public BankService(Logger log, ObjectMapper objectMapper){
        this.log = log;
        this.objectMapper = objectMapper;
    }

    public Bank initializeBank() {
        try {
            log.info("bank initialized");
            return objectMapper.readValue(new File("src/main/configuration/bank.json"), new TypeReference<Bank>() {});
        }
            catch (IOException e){
            log.info("Failed to initialize players "+e);
        }
        return null;
    }
}
