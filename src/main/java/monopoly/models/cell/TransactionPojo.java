package monopoly.models.cell;

import monopoly.models.Denomination;
import java.util.Map;

public class TransactionPojo {
    private boolean isComplete;
    private Map<Denomination, Integer> currencyInfo;

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Map<Denomination, Integer> getCurrencyInfo() {
        return currencyInfo;
    }

    public void setCurrencyInfo(Map<Denomination, Integer> currencyInfo) {
        this.currencyInfo = currencyInfo;
    }
}
