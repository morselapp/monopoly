package monopoly;

import monopoly.models.TransactionEntity;

public interface ITransaction1 {

    boolean transact(TransactionEntity sender, TransactionEntity receiver, Double transactionValue);

}
