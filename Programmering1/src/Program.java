//Gustav Jonsson gujo1653
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Program  {

    private Scanner programScanner = new Scanner(System.in);
    private List<Dog> dogList = new ArrayList<>();
    private List<Owner> ownerList = new ArrayList<>();
    private List<Auction> auctionList = new ArrayList<>();
    private int idToAssign = 1;

    public static void main(String[] args) {
        Program mainProgram = new Program();
        mainProgram.startProgram();
        mainProgram.loop();
        mainProgram.endProgram();
    }

    public boolean isStringInputCorrect(String inputString){
        return !inputString.isEmpty();
    }
    public String normalizeStringInput(String inputString){
        inputString = inputString.trim();
        inputString = inputString.toLowerCase();
        return inputString;
    }
    private void printErrorMessage(String errorReason){
        System.out.println("ERROR: " + errorReason);
    }
    private String stringInput(String scannerNextLine){
        while(true){
            if(isStringInputCorrect(scannerNextLine)){ break; }
            printErrorMessage("Incorrect Formatting.");
            System.out.println("Input Again?>");
            scannerNextLine = programScanner.nextLine();
        }
        normalizeStringInput(scannerNextLine);
        return scannerNextLine;
    }
    private void registerNewDog(){
        System.out.println("Enter Dog Name?>");
        String dogName = stringInput(programScanner.nextLine());
        System.out.println("Enter Dog Breed?>");
        String dogBreed = stringInput(programScanner.nextLine());
        System.out.println("Enter Dog Age?>");
        int dogAge = programScanner.nextInt();
        programScanner.nextLine();
        System.out.println("Enter Dog Weight?>");
        int dogWeight = programScanner.nextInt();
        programScanner.nextLine();
        Dog newDog = new Dog(dogName, dogBreed, dogAge, dogWeight);
        dogList.add(newDog);
    }
    private void listDogs(){
        if(dogList.isEmpty()){ printErrorMessage("List is empty."); }
        else{
            System.out.println("Enter Smallest Tail Length To Display?>");
            double smallestTail = programScanner.nextDouble();
            programScanner.nextLine();
            for(Dog dog: dogList){ if(dog.getTailLength() >= smallestTail ){ System.out.println(dog); }
            }
        }
    }
    public Dog findDog(String dogToFind){
        for(Dog dog: dogList){
            if(dog.getName().equalsIgnoreCase(dogToFind)){ return dog; }
        }
        return null;
    }
    private void increaseDogAge(){
        System.out.println("Enter Dog Name?>");
        String dogName = stringInput(programScanner.nextLine());
        if (findDog(dogName) != null){
            findDog(dogName).increaseAge(1);
            System.out.println(dogName + " is now 1 year older.");
        }
        else{ printErrorMessage("Dog does not exist.");}
    }
    private void removeDog(){
        System.out.println("Enter Dog Name?>");
        String dogName = stringInput(programScanner.nextLine());
        if(findDog(dogName) != null){
            System.out.println(dogName + " was removed.");
            auctionList.removeIf(Auction -> Auction.getAuctionDog() == findDog(dogName));
            if(findDog(dogName).getOwner() != null){
                findDog(dogName).getOwner().removeDog(findDog(dogName));
            }
            dogList.remove(findDog(dogName));
        }
        else{ printErrorMessage("Dog does not exist.");}
    }
    //Algorithm Type: Bubble-Sorting
    //Information About Algorithm was found on stackoverflow, mainly:
    //https://stackoverflow.com/questions/11644858/bubblesort-implementation
    //https:stackoverflow.com/questions/43714994/coding-bubble-sort-using-java
    //https:stackoverflow.com/questions/33240968/java-bubble-sort
    private void sortDogList(){
        boolean sorted = false;
        Dog temporaryDog;
        int compareStrings;
        while(!sorted){
            sorted = true;
            for(int i = 0; i < dogList.size() -1; i++){
                compareStrings = dogList.get(i).getName().compareTo(dogList.get(i+1).getName());
                if(dogList.get(i).getTailLength() > dogList.get(i+1).getTailLength()){
                    temporaryDog = dogList.get(i);
                    dogList.set(i, dogList.get(i+1));
                    dogList.set(i+1, temporaryDog);
                    sorted = false;
                }
                else if(dogList.get(i).getTailLength() == dogList.get(i+1).getTailLength()){
                    if(compareStrings > 0){
                        temporaryDog = dogList.get(i);
                        dogList.set(i, dogList.get(i+1));
                        dogList.set(i+1, temporaryDog);
                        sorted = false;
                    }
                }
            }
        }
    }
    private void registerNewOwner(){
        System.out.println("Enter Owner Name?>");
        String ownerName = stringInput(programScanner.nextLine());
        Owner newOwner = new Owner(ownerName);
        ownerList.add(newOwner);
        System.out.println("Owner " + ownerName + " registered.");
    }
    public Owner findOwner(String ownerToFind){
        for(Owner owner: ownerList){
            if(owner.getOwnerName().equalsIgnoreCase(ownerToFind)){
                return owner;
            }
        }
        return null;
    }
    private void giveDog(){
        System.out.println("Enter Dog Name?>");
        String dogName = stringInput(programScanner.nextLine());
        if(findDog(dogName) != null && findDog(dogName).getOwner() == null){
            System.out.println("Enter Owner Name?>");
            String ownerName = stringInput(programScanner.nextLine());
            if(findOwner(ownerName) != null){
                if(findDog(dogName).getOwner() == null){
                    System.out.println(ownerName + " now owns the dog " + dogName);
                    findDog(dogName).setAuctionStatus(0);
                    auctionList.remove(findAuction(findDog(dogName)));
                    findDog(dogName).setOwner(findOwner(ownerName));
                }
                else{ printErrorMessage("Dog already has an owner."); }
            }
            else{ printErrorMessage("Owner does not exist."); }
        }
        else{ printErrorMessage("Dog and/or Owner does not exist."); }
    }
    private void listOwners(){
        if(ownerList.isEmpty()){ printErrorMessage("Owner list is empty."); }
        else{
            for(Owner owner: ownerList){
                System.out.println(owner.getOwnerName());
                owner.getDogsOwned();
            }
        }
    }
    private void removeOwner(){
        System.out.println("Enter Owner Name?>");
        String ownerName = stringInput(programScanner.nextLine());
        if(findOwner(ownerName) != null){
            System.out.println(ownerName + " was removed.");
            for(Auction auction: auctionList){
                auction.removePreviousBid(findOwner(ownerName));
            }
            dogList.removeIf(Dog -> Dog.getOwner() == findOwner(ownerName));
            ownerList.remove(findOwner(ownerName));
        }
        else{ printErrorMessage("Owner does not exist."); }
    }
    private void startAuction(){
        System.out.println("Enter Dog Name?>");
        String auctionDog = stringInput(programScanner.nextLine());
        if(findDog(auctionDog) != null && findDog(auctionDog).getOwner() == null && !findDog(auctionDog).isUpForAuction()){
            Auction newAuction = new Auction(findDog(auctionDog), idToAssign);
            auctionList.add(newAuction);
            findDog(auctionDog).setAuctionStatus(1);
            idToAssign += 1;
            System.out.println(auctionDog + " has been put up for auction in auction #" + newAuction.getAuctionID());
        }
        else{ printErrorMessage("Dog does not exist and/or Dog already has an owner and/or Dog is already up for auction."); }
    }
    public Auction findAuction (Dog auctionDog){
        for(Auction auction: auctionList){
            if(auction.getAuctionDog() == auctionDog){ return auction; }
        }
        return null;
    }
    private void makeBid(){
        System.out.println("Enter Owner Name?>");
        String bidOwner = stringInput(programScanner.nextLine());
        if (findOwner(bidOwner) != null){
            System.out.println("Enter Dog Name?>");
            String bidDog = stringInput(programScanner.nextLine());
            if(findDog(bidDog) != null && findDog(bidDog).isUpForAuction()){
                while(true){
                    System.out.println("Enter Bid Amount. Minimum amount: " +(findAuction(findDog(bidDog)).getBiggestBid()+1)+"?>");
                    int bidAmount = programScanner.nextInt();
                    programScanner.nextLine();
                    if(bidAmount > findAuction(findDog(bidDog)).getBiggestBid()){
                        findAuction(findDog(bidDog)).removePreviousBid(findOwner(bidOwner));
                        Bid bid = new Bid(findOwner(bidOwner), bidAmount);
                        findAuction(findDog(bidDog)).addBid(bid);
                        System.out.println("Bid set.");
                        break;
                    }
                    else{ printErrorMessage("Incorrect Bid Amount"); }
                }
            }
            else{ printErrorMessage("Dog does not exist or Dog is not up for auction"); }
        }
        else{ printErrorMessage("Owner does not exist."); }
    }
    private void listBids(){
        System.out.println("Enter Dog Name?>");
        String bidDog = stringInput(programScanner.nextLine());
        if(findDog(bidDog) != null && findAuction(findDog(bidDog)) != null){
            findAuction(findDog(bidDog)).listBids();
        }
        else{ printErrorMessage("Dog does not exist or Dog is not up for auction"); }
    }
    private void listAuctions(){
        if(auctionList.isEmpty()){ printErrorMessage("No Auctions Exist"); }
        else{
            for(Auction auction: auctionList){
                System.out.println("Auction #" + auction.getAuctionID() + ": " + auction.getAuctionDog().getName() + ". Top Bids:");
                auction.listTopThreeBids();
            }
        }
    }
    private void closeAuction(){
        System.out.println("Enter Dog Name?>");
        String auctionedDog = stringInput(programScanner.nextLine());
        if(findAuction(findDog(auctionedDog)) == null){
            System.out.println("Error. There is no auction up for this dog.");
        }
        else{
            if(findAuction(findDog(auctionedDog)).isBidListEmpty()){
                System.out.println("The Auction is closed. No bids were made for " + auctionedDog);
                findDog(auctionedDog).setAuctionStatus(0);
                auctionList.remove(findAuction(findDog(auctionedDog)));
            }
            else{
                System.out.println("The Auction is closed. The winning bid was:");
                System.out.println(findAuction(findDog(auctionedDog)).getAuctionWinner());
                System.out.println(findAuction(findDog(auctionedDog)).getBiggestBid());

                findDog(auctionedDog).setAuctionStatus(0);
                findDog(auctionedDog).setOwner(findAuction(findDog(auctionedDog)).getAuctionWinner());
                auctionList.remove(findAuction(findDog(auctionedDog)));
            }
        }
    }
    
    private void startProgram(){
        System.out.println("Program Starting...");
        System.out.println();
    }
    private void printCommandMenu(){
        System.out.println("Here are the available commands for this program:");
        System.out.println("   ");
        System.out.println("Register New Dog");
        System.out.println("List Dogs");
        System.out.println("Increase Age");
        System.out.println("Remove Dog");
        System.out.println("Register New Owner");
        System.out.println("Give Dog");
        System.out.println("List Owners");
        System.out.println("Remove Owner");
        System.out.println("Start Auction");
        System.out.println("Make Bid");
        System.out.println("List Bids");
        System.out.println("List Auctions");
        System.out.println("Close Auction");
        System.out.println("Exit");
    }
    private void loop(){
        String userCommand;
        do{
            printCommandMenu();
            System.out.print("Command?>");
            userCommand = programScanner.nextLine();
            executeCommand(userCommand.toLowerCase());
        }while (!userCommand.equalsIgnoreCase("exit"));
    }

    private void executeCommand(String command){
        switch(command){
            case "exit":
                break;
            case "register new dog":
                registerNewDog();
                break;
            case "list dogs":
                sortDogList();
                listDogs();
                break;
            case "increase age":
                increaseDogAge();
                break;
            case "remove dog":
                removeDog();
                break;
            case "register new owner":
                registerNewOwner();
                break;
            case "give dog":
                giveDog();
                break;
            case "list owners":
                listOwners();
                break;
            case "remove owner":
                removeOwner();
                break;
            case "start auction":
                startAuction();
                break;
            case "make bid":
                makeBid();
                break;
            case "list bids":
                listBids();
                break;
            case "list auctions":
                listAuctions();
                break;
            case "close auction":
                closeAuction();
                break;
            default:
                printErrorCommand();
        }
    }
    private void printErrorCommand(){
        System.out.println("Incorrect Command!");
        System.out.println();
        printCommandMenu();
    }
    private void endProgram(){
        System.out.println("Program Shutting Down");
        programScanner.close();
    }
}