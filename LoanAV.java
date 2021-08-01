import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
class LoanAV extends LoanItem{
    LoanAV(int id, String title, double value){
        super(id, title, value);
    }

    void loanItem(User user){
        checkedOutBy = user;
        isOut = true;
        checkOutDate = LocalDate.now();
        returnDate = checkOutDate.plus(2, ChronoUnit.WEEKS);
    }

    boolean renew(){
        if(repeatRenew){
            return false;
        }
        returnDate = returnDate.plus(2, ChronoUnit.WEEKS);
        repeatRenew = true;
        return true;
    }
    void display(){

        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Value: $" + value);
        System.out.println("Type: Loan Audio Video");
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