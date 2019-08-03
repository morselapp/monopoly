package monopoly.models;

import monopoly.BankService;
import monopoly.ITransaction1;
import monopoly.PlayerService;
import monopoly.Utils.CurrencyUtils;
import monopoly.models.cell.BuyableCell;
import monopoly.models.cell.TransactionPojo;

import java.util.Map;
import java.util.Optional;

public class TransactionService1 implements ITransaction1 {

    @Override
    public boolean transact(TransactionEntity sender, TransactionEntity receiver, Double transactionValue) {
        boolean isSuccessful = false;
        Map<Denomination, Integer> currencyRequired = CurrencyUtils.getCurrencyNotesForGivenAmount(transactionValue);
        Optional<TransactionPojo> deductMoneyInfo = sender.deductMoney(currencyRequired);
        if(deductMoneyInfo.isPresent() && deductMoneyInfo.get().getIsComplete()){
            Optional<TransactionPojo> addMoneyInfo = receiver.addMoney(deductMoneyInfo.get().getCurrencyInfo());
            if(addMoneyInfo.isPresent() && addMoneyInfo.get().getIsComplete()){
                Optional<TransactionPojo> exchangeMoneyInfo = sender.addMoney(addMoneyInfo.get().getCurrencyInfo());
                if(exchangeMoneyInfo.isPresent() && exchangeMoneyInfo.get().getIsComplete()){
                    isSuccessful = true;
                }
            }
        }
        return isSuccessful;
    }
}
