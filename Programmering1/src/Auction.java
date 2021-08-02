//Gustav Jonsson gujo1653
import java.util.ArrayList;
import java.util.List;
public class Auction {
    private int auctionID;
    private Dog auctionDog;
    private List<Bid> bidList = new ArrayList<Bid>();

    public Auction(Dog auctionDog, int auctionID){
        this.auctionDog = auctionDog;
        this.auctionID = auctionID;
    }

    public int getAuctionID(){return auctionID;}
    public Dog getAuctionDog(){return auctionDog;}
    public boolean isBidListEmpty(){
        return bidList.isEmpty();
    }
    public int getBiggestBid(){
        int bidValue = 0;
        for(Bid bid: bidList){
            if(bid.getBidAmount() > bidValue){
                bidValue = bid.getBidAmount();
            }
        }
        return bidValue;
    }
    public Owner getAuctionWinner(){
        int bidValue = 0;
        Owner bidWinner = null;
        for(Bid bid: bidList){
            if(bid.getBidAmount() > bidValue){
                bidValue = bid.getBidAmount();
                bidWinner = bid.getBidOwner();
            }
        }
        return bidWinner;
    }
    public void listBids(){
        if(bidList.isEmpty()){ System.out.println("No bids yet registered for auction of " + auctionDog.getName()); }
        else{
            System.out.println("Here are the bids for the Auction of " + auctionDog.getName());
            for(int i = bidList.size()-1; i >= 0; i--){
                System.out.println(bidList.get(i).getBidOwner() + " " + bidList.get(i).getBidAmount() + " Kr.");
            }
        }
    }
    public void listTopThreeBids(){
        if(bidList.isEmpty()){ System.out.println("No bids registered for auction of " + auctionDog.getName()); }
        else{
            for(int i = bidList.size()-1; i >= bidList.size()-3; i--){
                System.out.println(bidList.get(i).getBidOwner() + " " + bidList.get(i).getBidAmount() + " Kr.");
                if(i == 0){ break; }
            }
        }
    }
    public void addBid(Bid bid){
        bidList.add(bid);
    }
    public void removePreviousBid(Owner owner){
        for(int i = bidList.size()-1; i >= 0; i--){
            if(bidList.get(i).getBidOwner().equals(owner)){
                bidList.remove(i);
            }
        }
    }
}