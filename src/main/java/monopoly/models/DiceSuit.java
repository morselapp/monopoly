package monopoly.models;

public class DiceSuit {

    private Dice diceX;
    private Dice diceY;

    public Dice getDiceX() {
        return diceX;
    }

    public void setDiceX(Dice diceX) {
        this.diceX = diceX;
    }

    public Dice getDiceY() {
        return diceY;
    }

    public void setDiceY(Dice diceY) {
        this.diceY = diceY;
    }

    public int roll(){
        return diceX.roll() + diceY.roll();
    }

    public boolean isDouble(){
        return diceX.equals(diceY);
    }
}
