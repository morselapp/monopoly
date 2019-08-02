package monopoly;

import monopoly.models.Bank;
import monopoly.models.Board;
import monopoly.models.Denomination;
import monopoly.models.Player;
import monopoly.models.cell.BuyableCell;
import monopoly.models.cell.Cell;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class TransactionService implements ITransactionService {

    private FileHandler fileHandler;
    private Logger log;

    {
        try {
            fileHandler = new FileHandler();
            fileHandler.setFormatter(new XMLFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  TransactionService() {
        log = Logger.getLogger(GameService.class.getName());
        log.addHandler(fileHandler);
    }

    public void buyCell (Bank bank, Player player, Cell cell) {
        Map<Denomination,Integer> currencyPlayerNeeds = new Utils().getCurrencyNotesForGivenAmount(((BuyableCell) cell).getPrice());
        Map<Denomination,Integer> currencyPlayerHas = player.getCurrency();
        Map<Denomination,Integer> currencyPlayerSpends = new Utils().getCurrencyPlayerSpend(currencyPlayerHas,currencyPlayerNeeds);
        int moneySpendByPlayer = new Utils().getMoneyFromCurrency(currencyPlayerSpends);
        Map<Denomination,Integer> playerFinalCurrency =  new LinkedHashMap<>();
        Map<Denomination,Integer> bankFinalCurrency =  new LinkedHashMap<>();
        if (moneySpendByPlayer < (int) ((BuyableCell) cell).getPrice()) {
            Map<Denomination,Integer> biggerCurrencyPlayerNeeds = new Utils().getBiggerCurrencyPlayerNeeds(((BuyableCell) cell).getPrice(),currencyPlayerHas);
            if (biggerCurrencyPlayerNeeds.size()>0) {
                int extraAmount = new Utils().getMoneyFromCurrency(biggerCurrencyPlayerNeeds)- new Utils().getMoneyFromCurrency(currencyPlayerNeeds);
                Map<Denomination,Integer> extraCurrency = new Utils().getCurrencyNotesForGivenAmount((double)extraAmount);
                if (extraCurrency.size() > 0) {
                    bankFinalCurrency = new Utils().subtractValueOfKeysFromOneMapToAnother(extraCurrency,bank.getCurrency());
                    playerFinalCurrency = new Utils().addValueOfKeysFromOneMapToAnother(extraCurrency,currencyPlayerHas);
                    bankFinalCurrency = new Utils().addValueOfKeysFromOneMapToAnother(biggerCurrencyPlayerNeeds,bankFinalCurrency);
                    playerFinalCurrency = new Utils().subtractValueOfKeysFromOneMapToAnother(biggerCurrencyPlayerNeeds,playerFinalCurrency);
                }

            } else {
                log.info("player has no bigger currency to fulfil the transaction");
            }

        } else {
            bankFinalCurrency = new Utils().addValueOfKeysFromOneMapToAnother(currencyPlayerSpends,bank.getCurrency());
            playerFinalCurrency = new Utils().subtractValueOfKeysFromOneMapToAnother(currencyPlayerSpends,currencyPlayerHas);
        }
        bank.setCurrency(bankFinalCurrency);
        player.setCurrency(playerFinalCurrency);
        ((BuyableCell) cell).setBoughtState(true);
        ((BuyableCell) cell).setOwnerId(player.getId());
        log.info("cell "+  cell.getName()+ "has been bought by : " + player.getName());
        log.info ("player "+ player.getName() + "net money currently " +  new Utils().getMoneyFromCurrency(player.getCurrency()));
        log.info ("bank net money currently " +  new Utils().getMoneyFromCurrency(bank.getCurrency()));

    }

    public void rentCell(Board board, Player tenant, Player owner, Cell cell) {

    }
}
