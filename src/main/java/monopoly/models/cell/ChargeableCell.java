package monopoly.models.cell;


import monopoly.models.Type;

public abstract class ChargeableCell extends Cell implements IChargeable {

    public  ChargeableCell (Type type) { super(type);}

    private double charge;

    public double getCharge() {
        return charge;
    }

    public  void setCharge(double charge) {
        this.charge = charge;
    }
}
