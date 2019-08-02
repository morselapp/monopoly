package monopoly.models.cell;

import monopoly.TransactionService;
import monopoly.models.Bank;
import monopoly.models.Player;
import monopoly.models.Type;
import java.util.logging.*;



public abstract class BuyableCell extends Cell implements IBuyable {

    private double price;
    private double rent;
    private double mortgage;
    private boolean boughtState;
    private int ownerId;

    public BuyableCell(Type type){
        super(type);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getMortgage() {
        return mortgage;
    }

    public void setMortgage(double mortgage) {
        this.mortgage = mortgage;
    }

    public boolean getBoughtState() {
        return boughtState;
    }

    public void setBoughtState(boolean boughtState) {
        this.boughtState = boughtState;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void buy(Bank bank, Player player, Cell cell) {
        new TransactionService().buyCell(bank,player,cell);

    }
}
