package monopoly.models;

public class Quote{
    private int quote;
    private Player quoteBy;

    public Quote(){

    }

    public Quote(int quote, Player quoteBy){
        this.quote = quote;
        this.quoteBy = quoteBy;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public void setQuoteBy(Player quoteBy) {
        this.quoteBy = quoteBy;
    }

    public Player getQuoteBy() {
        return quoteBy;
    }
}