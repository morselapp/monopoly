package monopoly;

import monopoly.models.Bank;
import monopoly.models.Board;
import monopoly.models.Player;
import monopoly.models.cell.Cell;

public interface ITransactionService {

    void buyCell (Bank bank, Player player, Cell cell);

    void rentCell(Board board,Player tenant, Player owner,Cell cell);
}
