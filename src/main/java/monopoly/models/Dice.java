package monopoly.models;

import java.util.Random;

public class Dice {
    private Face face;
    private int outcome;

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public int roll(){
        Random random = new Random();
        outcome = random.nextInt(Face.values().length)+1;
        return outcome;
    }

    @Override
    public boolean equals(Object dice){

        if(this.getClass() != dice.getClass()){
            return false;
        }
        return outcome == ((Dice)dice).outcome;
    }
}
