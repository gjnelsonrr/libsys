import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
class LoanBook extends LoanItem{
    boolean bestseller;

    LoanBook(int id, String title, double value, boolean bestseller){
        super(id, title, value);
        this.bestseller = bestseller;
    }

    boolean loanItem(User user){
        if(isOut){
            System.out.println("Book already checked out!");
            return false;
        }
        else if(!user.isAdult() && user.getNumberOfLoans() > 4){
            System.out.println("User has too many books checked out!");
            return false;
        }
        
        checkedOutBy = user;
        isOut = true;
        checkOutDate = LocalDate.now();
        if(bestseller){
            returnDate = checkOutDate.plus(2, ChronoUnit.WEEKS);
        }
        else{
            returnDate = checkOutDate.plus(3, ChronoUnit.WEEKS);
        }
        return true;
    }

    boolean renew(){
        if(repeatRenew){
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