package monopoly.models;

import monopoly.models.cell.TransactionPojo;
import java.util.*;

public interface TransactionEntity {

     public Optional<TransactionPojo> addMoney(Map<Denomination,Integer> currency);

     public Optional<TransactionPojo>  deductMoney(Map<Denomination,Integer> currency);
}


