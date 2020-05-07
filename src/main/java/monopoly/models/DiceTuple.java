package monopoly.models;

public class DiceTuple {

    private int faceX;
    private int faceY;

    public DiceTuple(int faceX, int faceY){
        this.faceX = faceX;
        this.faceY = faceY;
    }

    public int getFaceX() {
        return faceX;
    }

    public int getFaceY() {
        return faceY;
    }
}
