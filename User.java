import java.util.*;
import java.time.*;
class User{
    String firstName;
    String lastName;
    String address;
    int phoneNumber;
    int cardNumber;
    boolean adult;
    ArrayList<LoanItem> loanList = new ArrayList<LoanItem>();
    ArrayList<Request> itemRequest = new ArrayList<Request>();
    //constructor
    User(String fn, String ln, String adr, int phone, int card, boolean adult){
        firstName = fn;
        lastName = ln;
        address = adr;
        phoneNumber = phone;
        cardNumber = card;
        this.adult = adult;
    }

    //add item to loan list
    void addLoanItem(LoanItem item){
        if(!isAdult() && getNumberOfLoans() > 4){
            System.out.println("too many items");
            return;
        }
        loanList.add(item);
    }
    //add request
    void addRequest(Request request){
        itemRequest.add(request);
    }

    //remove item from loan list
    void removeLoanItem(Item item){
        loanList.remove(item);
    }
    //get number of items checked out
    int getNumberOfLoans(){
        return loanList.size();
    }
    //type of user
    boolean isAdult(){
        return adult;
    }

    boolean hasOverDueItems(LocalDate currentDay){
        boolean overDue = false;
        for(LoanItem item : loanList){
            if(currentDay.isAfter(item.getDueDate()))
            System.out.println(item.getTitle() + " is overdue!");
            overDue = true;
        }
        return overDue;
    }

    boolean canCheckOut(LoanItem item, LocalDate currentDay){
        if(item.isLoanedOut()){
            System.out.println("Item already checked out!");
            return false;
        }
        else if(!isAdult() && getNumberOfLoans() > 4){
            System.out.println("User has too many items checked out!");
            return false;
        }
        else if(hasOverDueItems(currentDay)){
            System.out.println("Return overdue items to check out more items!");
            return false;
        }
        else{
            return true;
        }
    }
    //display user info
    void display(){
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Library Card Number: " + cardNumber);
        if(adult == true){
            System.out.println("Member Type: Adult");
        }
        else{
            System.out.println("Member Type: Child");
        }
    }

    void displayLoans(){
        System.out.println("-----------------------");
        System.out.println("Books Checked Out by " + firstName + " " + lastName + ":");
        System.out.println("-----------------------");
        for(LoanItem item: loanList){
            System.out.println("Title: " + item.getTitle() +" || Due Date: " + item.dueDateString());
        }
    }


}