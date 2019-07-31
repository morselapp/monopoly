package monopoly.models;

public class Dice {
    private Face face;

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public int roll(){
        return 0;
    }

    @Override
    public boolean equals(Object dice){

        if(this.getClass() != dice.getClass()){
            return false;
        }
        return face == ((Dice)dice).face;
    }
}
