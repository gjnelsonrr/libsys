import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
class LoanBook extends LoanItem{
    boolean bestseller;

    LoanBook(int id, String title, double value, boolean bestseller){
        super(id, title, value);
        this.bestseller = bestseller;
    }

    void loanItem(User user){
        checkedOutBy = user;
        isOut = true;
        checkOutDate = LocalDate.now();
        if(bestseller){
            returnDate = checkOutDate.plus(2, ChronoUnit.WEEKS);
        }
        else{
            returnDate = checkOutDate.plus(3, ChronoUnit.WEEKS);
        }
    }

    boolean renew(){
        if(repeatRenew){
            System.out.println("Cannot renew same item twice!");
            return false;
        }
        if(bestseller){
            returnDate = returnDate.plus(2, ChronoUnit.WEEKS);
        }
        else{
            returnDate = returnDate.plus(3, ChronoUnit.WEEKS);
        }
        repeatRenew = true;
        return true;
    }

    void display(){
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Value: $" + value);
        if(bestseller){
            System.out.println("Type: Bestseller Loan Book");
        }
        else{
            System.out.println("Type: Loan Book");
        }
        if(isOut){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String outDate = checkOutDate.format(formatter);
            String inDate = returnDate.format(formatter);
            System.out.println("Status: Checked Out");
            System.out.println("Checked Out On: " + outDate);
            System.out.println("Due Back On: " + inDate);
        }
        else{
            System.out.println("Status: In Library");
        }
    }
}