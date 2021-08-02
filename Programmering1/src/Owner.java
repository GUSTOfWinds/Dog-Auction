//Gustav Jonsson gujo1653
import java.util.Arrays;
public class Owner {
    private String ownerName;
    private Dog[] dogsOwned = new Dog[0];

    public Owner(String ownerName){
        this.ownerName = ownerName;
    }

    public String toString(){return ownerName;}
    public String getOwnerName(){return ownerName;}
    public void getDogsOwned(){System.out.println(Arrays.toString(dogsOwned));}
    public boolean isOwnersDog(Dog dog){
        for(Dog arrayDog : dogsOwned){
            if(arrayDog.equals(dog)){
                return true;
            }
        }
        return false;
    }
    public void addDog(Dog dog){
        Dog[] newDogsOwned = new Dog[dogsOwned.length+1];
        for(int i = 0; i < dogsOwned.length; i++){
            newDogsOwned[i] = dogsOwned[i];
        }
        newDogsOwned[dogsOwned.length] = dog;
        dogsOwned = newDogsOwned;
        dog.setOwner(this);
    }
    public void removeDog(Dog dog){
        int currentPosition = 0;
        int dogPosition = 0;
        while(currentPosition < dogsOwned.length){
            if(dogsOwned[currentPosition] == dog){
                dogPosition = currentPosition;
                break;
            }
            currentPosition++;
        }
        dogsOwned[dogPosition] = dogsOwned[dogsOwned.length-1];
        Dog[] newDogsOwned = new Dog[dogsOwned.length-1];
        for(int i = 0; i < newDogsOwned.length; i++){ newDogsOwned[i] = dogsOwned[i]; }
        dogsOwned = newDogsOwned;
    }
}