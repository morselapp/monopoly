package monopoly.models.cell;

public abstract  class TaxableCell extends Cell implements ITaxable {

    private double charge;

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }
}
