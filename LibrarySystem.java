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
    LocalDate currentDay = LocalDate.of(2021, 8, 1);

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

    void setCurrentDay(int year, int month, int day){
        currentDay = LocalDate.of(year, month, day);
    }

    void loanItem(LoanItem item, User user){
        if(!user.canCheckOut(item, currentDay)){
            return;
        }
        item.loanItem(user);
        user.addLoanItem(item);
        checkedOutItems.add(item);
    }

    void returnItem(LoanItem item, User user){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String today = currentDay.format(formatter);
        System.out.println("Todays Date: " + today);
        item.checkOverDue(currentDay);
        user.removeLoanItem(item);
        item.returnItem();
        checkedOutItems.remove(item);
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

        System.out.println("Tommy checking out " + itemTwo.getTitle());
        library.loanItem(itemTwo, userOne);
        library.setCurrentDay(2022, 12, 20);
        System.out.println("Tommy checking out " + itemThree.getTitle());
        library.loanItem(itemThree, userOne);
        userOne.displayLoans();
        System.out.println("Tommy returning " + itemTwo.getTitle());
        library.returnItem(itemTwo, userOne);
        System.out.println("Tommy checking out " + itemThree.getTitle());
        library.loanItem(itemThree, userOne);
        userOne.displayLoans();



    }
}