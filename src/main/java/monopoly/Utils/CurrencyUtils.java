package monopoly.Utils;

import monopoly.models.Denomination;

import java.util.LinkedHashMap;
import java.util.Map;

public class CurrencyUtils {

    static final int[] currencyNotes = {1, 5, 20, 50, 100, 500};

    public static Map<Denomination,Integer> addValueOfKeysFromOneMapToAnother(Map<Denomination,Integer> mapSource , Map<Denomination,Integer> mapTarget) {
        for (Map.Entry<Denomination,Integer> entrySource : mapTarget.entrySet()) {
            for (Map.Entry<Denomination,Integer> entryTarget : mapTarget.entrySet()) {
                if (entryTarget.getKey().equals(entrySource.getKey())) {
                    int newTargetValue = entryTarget.getValue()+entrySource.getValue();
                    mapTarget.put(entryTarget.getKey(),newTargetValue);
                }
            }
        }
        return mapTarget;
    }

    public static Map<Denomination,Integer> subtractValueOfKeysFromOneMapToAnother(Map<Denomination,Integer> mapSource , Map<Denomination,Integer> mapTarget) {
        for (Map.Entry<Denomination,Integer> entrySource : mapTarget.entrySet()) {
            for (Map.Entry<Denomination,Integer> entryTarget : mapTarget.entrySet()) {
                if (entryTarget.getKey().equals(entrySource.getKey()) && entryTarget.getValue()> entrySource.getValue()) {
                    int newTargetValue = entryTarget.getValue()- entrySource.getValue();
                    mapTarget.put(entryTarget.getKey(),newTargetValue);
                }
            }
        }
        return mapTarget;
    }

    public static Map<Denomination,Integer> getBiggerCurrencyPlayerNeeds (double amount,Map<Denomination,Integer> currencyPlayerHas) {
        int amountRound  = (int) amount;
        Map<Denomination, Integer> biggerCurrencyPlayerSpend = new LinkedHashMap<>();
        for (Map.Entry<Denomination,Integer> entry: currencyPlayerHas.entrySet()) {
            int noteValue = getCurrencyNoteValue(entry.getKey());
            for (int i= 1;i<=entry.getValue();i++) {
                if (noteValue*i == amountRound) {
                    biggerCurrencyPlayerSpend.put(entry.getKey(),i);
                    break;
                }
            }
        }
        return biggerCurrencyPlayerSpend;

    }

    public static Map<Denomination, Integer> getCurrencyPlayerSpend(Map<Denomination, Integer> currencyPlayerHas, Map<Denomination, Integer> currencyPlayerNeeds) {
        Map<Denomination, Integer> currencyPlayerSpend = new LinkedHashMap<>();
        for (Map.Entry<Denomination, Integer> entryCurrencyNeeds : currencyPlayerNeeds.entrySet()) {
            for (Map.Entry<Denomination,Integer> entryCurrencyHas :currencyPlayerHas.entrySet()) {
                if (entryCurrencyHas.getKey().equals(entryCurrencyNeeds.getKey())) {
                    if (entryCurrencyHas.getValue() >= entryCurrencyNeeds.getValue()) {
                        currencyPlayerSpend.put(entryCurrencyHas.getKey(),entryCurrencyNeeds.getValue());
                    } else {
                        currencyPlayerSpend.put(entryCurrencyHas.getKey(),entryCurrencyHas.getValue());
                    }
                }
            }
        }
        return currencyPlayerSpend;
    }

    public static Map<Denomination, Integer> getCurrencyNotesForGivenAmount(double amount) {
        Map<Denomination, Integer> currency = new LinkedHashMap<>();
        int amountRound = (int) amount;
        getCurrencyNotesForGivenAmountHelper(currency, amountRound);
        return currency;
    }

    private static void getCurrencyNotesForGivenAmountHelper(Map<Denomination, Integer> currency, int amountRound) {
        while (amountRound > 0) {
            int maxNote = getBiggestCurrencyNoteBelowAmount(amountRound);
            if (maxNote > 0 && amountRound / maxNote > 0) {
                Denomination currencyNoteValue = getCurrencyNoteForAmount(maxNote);
                if (currencyNoteValue != null) {
                    currency.put(currencyNoteValue, amountRound / maxNote);
                }
                amountRound = amountRound % maxNote;
            }
        }

    }

    private static int getBiggestCurrencyNoteBelowAmount(int amount) {
        int maxNote = 0;
        for (int i = currencyNotes.length - 1; i >= 0; i--) {
            if (amount > currencyNotes[i]) {
                maxNote = currencyNotes[i];
                break;
            }
        }
        return maxNote;

    }

    static Denomination getCurrencyNoteForAmount(int amount) {
        if (amount == 1) {
            return Denomination.ONES;
        } else if (amount == 5) {
            return Denomination.FIVES;
        } else if (amount == 20) {
            return Denomination.TWENTIES;
        } else if (amount == 50) {
            return Denomination.FIFTIES;
        } else if (amount == 100) {
            return Denomination.HUNDREDS;
        } else if (amount == 500) {
            return Denomination.FIVE_HUNDREDS;
        }
        return null;
    }

    static int getCurrencyNoteValue(Denomination denomination) {
        if (denomination.equals(Denomination.ONES)) {
            return 1;
        } else if (denomination.equals(Denomination.FIVES)) {
            return 5;
        } else if (denomination.equals(Denomination.TWENTIES)) {
            return 20;
        } else if (denomination.equals(Denomination.FIFTIES)) {
            return 50;
        } else if (denomination.equals(Denomination.HUNDREDS)) {
            return 100;
        } else if (denomination.equals(Denomination.FIVE_HUNDREDS)) {
            return 500;
        }
        return 0;
    }

    public static int getMoneyFromCurrency (Map<Denomination,Integer> currency) {
        int currentMoney = 0;
        for (Map.Entry<Denomination, Integer> entry : currency.entrySet()) {
            if (entry.getKey().equals(Denomination.ONES)) {
                currentMoney = currentMoney + entry.getValue() * 1;
            } else if (entry.getKey().equals(Denomination.FIVES)) {
                currentMoney = currentMoney + entry.getValue() * 5;
            } else if (entry.getKey().equals(Denomination.TWENTIES)) {
                currentMoney = currentMoney + entry.getValue() * 20;
            } else if (entry.getKey().equals(Denomination.FIFTIES)) {
                currentMoney = currentMoney + entry.getValue() * 50;
            } else if (entry.getKey().equals(Denomination.HUNDREDS)) {
                currentMoney = currentMoney + entry.getValue() * 100;
            } else if (entry.getKey().equals(Denomination.FIVE_HUNDREDS)) {
                currentMoney = currentMoney + entry.getValue() * 500;
            }
        }
        return currentMoney;
    }
}
