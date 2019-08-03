package monopoly.models;

import monopoly.ITransaction1;

public class TransactionService1 implements ITransaction1 {

    @Override
    public boolean transact(TransactionEntity buyer, TransactionEntity seller, Double transactionValue) {

        return false;
    }
}
