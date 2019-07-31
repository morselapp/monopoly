package monopoly.models;

public class Bid {

    private double baseQuote;

    private Quote currentQuote;
    private Quote finalQuote;

    public Bid(){

    }

    public Bid(double baseQuote){
        this.baseQuote = baseQuote;
    }

    public double getBaseQuote() {
        return baseQuote;
    }

    public void setCurrentQuote(Quote currentQuote){
        this.currentQuote = currentQuote;
    }
}
