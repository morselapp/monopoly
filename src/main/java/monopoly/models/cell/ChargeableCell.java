package monopoly.models.cell;


public abstract class ChargeableCell extends Cell implements IChargeable {

    private double charge;

    public double getCharge() {
        return charge;
    }

    public  void setCharge(double charge) {
        this.charge = charge;
    }
}
