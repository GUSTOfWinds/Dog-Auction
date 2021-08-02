//Gustav Jonsson gujo1653
public class Bid {
    private Owner bidOwner;
    private int bidAmount;

    public Bid(Owner bidOwner, int bidAmount){
        this.bidOwner = bidOwner;
        this.bidAmount = bidAmount;
    }

    public Owner getBidOwner(){return bidOwner;}
    public int getBidAmount(){return bidAmount;}
}