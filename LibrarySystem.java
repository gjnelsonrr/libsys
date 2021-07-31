import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
class LibrarySystem{
    ArrayList<User> userList = new ArrayList<User>();
    ArrayList<Item> itemList = new ArrayList<Item>();
    ArrayList<LoanItem> checkedOutItems = new ArrayList<LoanItem>();
    ArrayList<Request> requestList = new ArrayList<Request>();
    ArrayList<Renew> renewList = new ArrayList<Renew>();
    LocalDate currentDay = LocalDate.now();

    void addUser(User newUser){
        userList.add(newUser);
    }

    void addItem(Item newItem){
        itemList.add(newItem);
    }

    void addRequest(LoanItem item, User user){
        Request request = new Request(item, user);
        requestList.add(request);
    }

    void addRenew(LoanItem item, User user){
        Renew renew = new Renew(item, user);
        for(Request request: requestList){
            if(request.getItem() == renew.getItem()){
                return;
            }
        }
        if(item.renew()){
            renewList.add(renew);
        }

    }


    void loanItem(LoanItem item, User user){
        if(!item.loanItem(user)){
            return;
        }
        user.addLoanItem(item);
        checkedOutItems.add(item);
    }

    void returnItem(LoanItem item, User user){
        user.removeLoanItem(item);
        item.returnItem();
        checkedOutItems.remove(item);
    }

    void checkOverDueItems(){
        for(LoanItem item: checkedOutItems){
            if(item.getDueDate().isBefore(currentDay)){
                item.overDue();
            }
        }
    }
    void displayUsers(){
        for (User user: userList){
            System.out.println("-----------------------");
            user.display();
            System.out.println("-----------------------");
        }
    }

    void displayItems(){
        for (Item item: itemList){
            System.out.println("-----------------------");
            item.display();
            System.out.println("-----------------------");
        }
    }

    public static void main(String args[]){
        LibrarySystem library = new LibrarySystem();
        User userOne = new User("Tommy", "Frans", "123 New Lane", 22, 1, false);
        library.addUser(userOne);
        User userTwo = new User("Jenny", "Hamilton", "777 Lucky Ave", 33, 2, true);
        library.addUser(userTwo);
        LoanItem itemOne = new LoanBook(2244, "Book of Snail", 10, true);
        library.addItem(itemOne);
        LoanItem itemTwo = new LoanAV(2266, "End of All Things", 12);
        library.addItem(itemTwo);
        LoanItem itemThree = new LoanAV(2277, "Avatar", 20);
        library.addItem(itemThree);
        Item itemFour = new RefItem(2277, "Science Weekly", 2);
        library.addItem(itemFour);
        Item itemFive = new RefBook(2277, "Dictionary", 20);
        library.addItem(itemFive);
        Item itemSix = new RefMag(2277, "Math Journal", 3);
        library.addItem(itemSix);
        LoanItem itemSeven = new LoanBook(1277, "Bad Book", 10, false);
        library.addItem(itemSeven);

        System.out.println("Displaying all items in library");
        library.displayItems();
        System.out.println();
        System.out.println("Tommy checking out AV item");
        library.loanItem(itemTwo, userOne);
        System.out.println("Tommy checking out AV item again");
        library.loanItem(itemTwo, userOne);
        System.out.println("Tommy checking out book item");
        library.loanItem(itemOne, userOne);
        System.out.println("Tommy checking out book item again");
        library.loanItem(itemOne, userOne);
        userOne.displayLoans();
        System.out.println();
        System.out.println("Tommy renewing both items");
        library.addRenew(itemOne, userOne);
        library.addRenew(itemTwo, userOne);
        userOne.displayLoans();
        System.out.println();
        System.out.println("Tommy renewing both items AGAIN");
        library.addRenew(itemOne, userOne);
        library.addRenew(itemTwo, userOne);
        userOne.displayLoans();
        System.out.println("Returning items");
        library.returnItem(itemTwo, userOne);
        library.returnItem(itemOne, userOne);
        userOne.displayLoans();




    }
}