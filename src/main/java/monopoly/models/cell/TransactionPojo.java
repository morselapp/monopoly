package monopoly.models.cell;

import monopoly.models.Denomination;
import java.util.Map;

public class TransactionPojo {
    private boolean isComplete;
    private Map<Denomination, Integer> currencyInfo;
}
