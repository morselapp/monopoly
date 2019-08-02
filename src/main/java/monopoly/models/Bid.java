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

    public void setFinalQuote(Quote finalQuote) {
        this.finalQuote = finalQuote;
    }

    public Quote getCurrentQuote() {
        return currentQuote;
    }

    public void setBaseQuote(double baseQuote) {
        this.baseQuote = baseQuote;
    }

    public Quote getFinalQuote() {
        return finalQuote;
    }
}
