package monopoly;

import monopoly.models.*;
import monopoly.models.cell.*;

import java.util.*;

public class Utils {

    final int[] currencyNotes = {1, 5, 20, 50, 100, 500};

    public static void renderCells(Cell[] board, List<City> cities, List<Transport> transports, List<Industry> industries,
                                   List<Jail> jails, List<Free> parkings, List<Start> starts,
                                   List<Tax> taxes, List<Works> works, List<Card> cards, List<Cell> cells) {

        cities.forEach(city -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new City();
                        cell.setId(city.getId());
                        cell.setName(city.getName());
                        cell.setType(city.getType());
                        cell.setIcon(city.getIcon());
                        cell.setColor(city.getColor());
                        cell.setDescription(city.getDescription());
                        ((City) cell).setHomestaysCount(city.getHomestaysCount());
                        ((City) cell).setResortsCount(city.getResortsCount());
                        ((City) cell).setPrice(city.getPrice());
                        ((City) cell).setRent(city.getRent());
                        ((City) cell).setMortgage(city.getMortgage());
                        cells.add(cell);
                    });
        });

        transports.forEach(transport -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Transport();
                        cell.setId(transport.getId());
                        cell.setName(transport.getName());
                        cell.setType(transport.getType());
                        cell.setIcon(transport.getIcon());
                        cell.setColor(transport.getColor());
                        cell.setDescription(transport.getDescription());
                        ((Transport) cell).setPrice(transport.getPrice());
                        ((Transport) cell).setRent(transport.getRent());
                        ((Transport) cell).setMortgage(transport.getMortgage());
                        cells.add(cell);
                    });
        });

        industries.forEach(industry -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Industry();
                        cell.setId(industry.getId());
                        cell.setName(industry.getName());
                        cell.setType(industry.getType());
                        cell.setIcon(industry.getIcon());
                        cell.setColor(industry.getColor());
                        cell.setDescription(industry.getDescription());
                        ((Industry) cell).setPrice(industry.getPrice());
                        ((Industry) cell).setRent(industry.getRent());
                        ((Industry) cell).setMortgage(industry.getMortgage());
                        cells.add(cell);
                    });
        });

        jails.forEach(jail -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Jail();
                        cell.setId(jail.getId());
                        cell.setName(jail.getName());
                        cell.setType(jail.getType());
                        cell.setIcon(jail.getIcon());
                        cell.setColor(jail.getColor());
                        cell.setDescription(jail.getDescription());
                        cells.add(cell);
                    });
        });

        parkings.forEach(parking -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Free();
                        cell.setId(parking.getId());
                        cell.setName(parking.getName());
                        cell.setType(parking.getType());
                        cell.setIcon(parking.getIcon());
                        cell.setColor(parking.getColor());
                        cell.setDescription(parking.getDescription());
                        cells.add(cell);
                    });
        });

        starts.forEach(start -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Start();
                        cell.setId(start.getId());
                        cell.setName(start.getName());
                        cell.setType(start.getType());
                        cell.setIcon(start.getIcon());
                        cell.setColor(start.getColor());
                        cell.setDescription(start.getDescription());
                        cells.add(cell);
                    });
        });

        taxes.forEach(tax -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Tax();
                        cell.setId(tax.getId());
                        cell.setName(tax.getName());
                        cell.setType(tax.getType());
                        cell.setIcon(tax.getIcon());
                        cell.setColor(tax.getColor());
                        cell.setDescription(tax.getDescription());
                        cells.add(cell);
                    });
        });

        works.forEach(work -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Works();
                        cell.setId(work.getId());
                        cell.setName(work.getName());
                        cell.setType(work.getType());
                        cell.setIcon(work.getIcon());
                        cell.setColor(work.getColor());
                        cell.setDescription(work.getDescription());
                        cells.add(cell);
                    });
        });

        cards.forEach(card -> {
            Arrays.stream(board)
                    .forEach(cell -> {
                        cell = new Card();
                        cell.setId(card.getId());
                        cell.setName(card.getName());
                        cell.setType(card.getType());
                        cell.setIcon(card.getIcon());
                        cell.setColor(card.getColor());
                        cell.setDescription(card.getDescription());
                        cells.add(cell);
                    });
        });
    }

    public Map<Denomination,Integer> addValueOfKeysFromOneMapToAnother(Map<Denomination,Integer> mapSource , Map<Denomination,Integer> mapTarget) {
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

    public Map<Denomination,Integer> subtractValueOfKeysFromOneMapToAnother(Map<Denomination,Integer> mapSource , Map<Denomination,Integer> mapTarget) {
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



    public Map<Denomination,Integer> getBiggerCurrencyPlayerNeeds (double amount,Map<Denomination,Integer> currencyPlayerHas) {
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

    public Map<Denomination, Integer> getCurrencyPlayerSpend(Map<Denomination, Integer> currencyPlayerHas, Map<Denomination, Integer> currencyPlayerNeeds) {
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

    public Map<Denomination, Integer> getCurrencyNotesForGivenAmount(double amount) {
        Map<Denomination, Integer> currency = new LinkedHashMap<>();
        int amountRound = (int) amount;
        getCurrencyNotesForGivenAmountHelper(currency, amountRound);
        return currency;
    }

    private void getCurrencyNotesForGivenAmountHelper(Map<Denomination, Integer> currency, int amountRound) {
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

    private int getBiggestCurrencyNoteBelowAmount(int amount) {
        int maxNote = 0;
        for (int i = currencyNotes.length - 1; i >= 0; i--) {
            if (amount > currencyNotes[i]) {
                maxNote = currencyNotes[i];
                break;
            }
        }
        return maxNote;

    }

    Denomination getCurrencyNoteForAmount(int amount) {
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

    int getCurrencyNoteValue(Denomination denomination) {
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

    public  int getMoneyFromCurrency (Map<Denomination,Integer> currency) {
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
