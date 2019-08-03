package monopoly;

import monopoly.models.TransactionEntity;

public interface ITransaction1 {

    boolean transact(TransactionEntity buyer, TransactionEntity seller, Double transactionValue);

}
