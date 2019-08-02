package monopoly.models.cell;

import monopoly.models.Bank;
import monopoly.models.Player;
import monopoly.models.Type;

public class City extends BuyableCell {

    private int homestaysCount;
    private int resortsCount;

    public City(){
        super(Type.CITY);
    }

    public int getHomestaysCount() {
        return homestaysCount;
    }

    public void setHomestaysCount(int homestaysCount) {
        this.homestaysCount = homestaysCount;
    }

    public int getResortsCount() {
        return resortsCount;
    }

    public void setResortsCount(int resortsCount) {
        this.resortsCount = resortsCount;
    }

    //public void buy (Bank bank, Player player, Cell cell);
}
