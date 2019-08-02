package monopoly.models.cell;

import monopoly.models.Bank;
import monopoly.models.Player;

public interface IBuyable {

    void buy(Bank bank,Player player, Cell cell);
}
