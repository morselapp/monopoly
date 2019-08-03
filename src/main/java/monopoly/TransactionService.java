package monopoly;

import monopoly.Utils.CurrencyUtils;
import monopoly.Utils.Utils;
import monopoly.models.*;
import monopoly.models.cell.BuyableCell;
import monopoly.models.cell.Cell;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class TransactionService implements ITransaction {

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
        Map<Denomination,Integer> currencyPlayerNeeds = CurrencyUtils.getCurrencyNotesForGivenAmount(((BuyableCell) cell).getPrice());
        Map<Denomination,Integer> currencyPlayerHas = player.getCurrency();
        Map<Denomination,Integer> currencyPlayerSpends = CurrencyUtils.getCurrencyPlayerSpend(currencyPlayerHas,currencyPlayerNeeds);
        int moneySpendByPlayer = CurrencyUtils.getMoneyFromCurrency(currencyPlayerSpends);
        Map<Denomination,Integer> playerFinalCurrency =  new LinkedHashMap<>();
        Map<Denomination,Integer> bankFinalCurrency =  new LinkedHashMap<>();
        if (moneySpendByPlayer < (int) ((BuyableCell) cell).getPrice()) {
            Map<Denomination,Integer> biggerCurrencyPlayerNeeds = CurrencyUtils.getBiggerCurrencyPlayerNeeds(((BuyableCell) cell).getPrice(),currencyPlayerHas);
            if (biggerCurrencyPlayerNeeds.size()>0) {
                int extraAmount = CurrencyUtils.getMoneyFromCurrency(biggerCurrencyPlayerNeeds)- CurrencyUtils.getMoneyFromCurrency(currencyPlayerNeeds);
                Map<Denomination,Integer> extraCurrency = CurrencyUtils.getCurrencyNotesForGivenAmount((double)extraAmount);
                if (extraCurrency.size() > 0) {
                    bankFinalCurrency = CurrencyUtils.subtractValueOfKeysFromOneMapToAnother(extraCurrency,bank.getCurrency());
                    playerFinalCurrency = CurrencyUtils.addValueOfKeysFromOneMapToAnother(extraCurrency,currencyPlayerHas);
                    bankFinalCurrency = CurrencyUtils.addValueOfKeysFromOneMapToAnother(biggerCurrencyPlayerNeeds,bankFinalCurrency);
                    playerFinalCurrency = CurrencyUtils.subtractValueOfKeysFromOneMapToAnother(biggerCurrencyPlayerNeeds,playerFinalCurrency);
                }

            } else {
                log.info("player has no bigger currency to fulfil the transaction");
            }

        } else {
            bankFinalCurrency = CurrencyUtils.addValueOfKeysFromOneMapToAnother(currencyPlayerSpends,bank.getCurrency());
            playerFinalCurrency = CurrencyUtils.subtractValueOfKeysFromOneMapToAnother(currencyPlayerSpends,currencyPlayerHas);
        }
        bank.setCurrency(bankFinalCurrency);
        player.setCurrency(playerFinalCurrency);
        ((BuyableCell) cell).setBoughtState(true);
        ((BuyableCell) cell).setOwnerId(player.getId());
        log.info("cell "+  cell.getName()+ "has been bought by : " + player.getName());
        log.info ("player "+ player.getName() + "net money currently " +  CurrencyUtils.getMoneyFromCurrency(player.getCurrency()));
        log.info ("bank net money currently " +  CurrencyUtils.getMoneyFromCurrency(bank.getCurrency()));

    }

    public void rentCell(Board board, Player tenant, Player owner, Cell cell) {

    }
}
