//Gustav Jonsson gujo1653
public class Dog {
    private String dogName;
    private String dogBreed;
    private int dogAge;
    private int dogWeight;
    private double tailLength;
    private final double defaultTailLength = 3.7;
    private Owner ownerOfDog;
    private boolean isUpForAuction;

    public Dog(String dogName, String dogBreed, int dogAge, int dogWeight){
        this.dogName = dogName;
        this.dogBreed = dogBreed;
        this.dogAge = dogAge;
        this.dogWeight = dogWeight;
    }

    public String toString(){
        return dogName + "(" + dogBreed + ", " + dogAge + " year(s), " + dogWeight + " kilo, " + getTailLength() + " cm tail, Owner: " + ownerOfDog;
    }
    public String getName(){ return dogName; }
    public String getBreed(){ return dogBreed; }
    public int getAge(){ return dogAge; }
    public int getWeight(){ return dogWeight; }
    public double getTailLength(){
        if(dogBreed.equalsIgnoreCase("Tax") || dogBreed.equalsIgnoreCase("Dachshund")){
            tailLength = defaultTailLength;
        }
        else{
            double doubleDogAge = dogAge;
            double doubleDogWeight = dogWeight;
            tailLength = doubleDogAge * (doubleDogWeight / 10);
        }
        return tailLength;
    }
    public Owner getOwner(){return ownerOfDog;}
    public boolean isUpForAuction(){return isUpForAuction;}
    public void increaseAge(int age){
        dogAge += Math.max(age, 0);
    }
    public void setOwner(Owner owner){
        if (ownerOfDog == null) {
            ownerOfDog = owner;
            if(!owner.isOwnersDog(this)){
                owner.addDog(this);
            }
        }
    }
    public void setAuctionStatus(int i){
        if(i == 1){ isUpForAuction = true; }
        else{ isUpForAuction = false; }
    }
}