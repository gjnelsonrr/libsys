import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
abstract class LoanItem extends Item{
    boolean isOut;
    boolean repeatRenew;
    User checkedOutBy;
    LocalDate checkOutDate;
    LocalDate returnDate;
    double overDueFee;
    //constructor
    LoanItem(int id, String title, double value){
        super(id, title, value);
        isOut = false;
        overDueFee = 0;
    }
    //checkout item
    abstract void loanItem(User user);

    //return item
    void returnItem(){
        checkedOutBy = null;
        isOut = false;
        checkOutDate = null;

    }
    //check if out
    boolean isLoanedOut(){
        return isOut;
    }

    LocalDate getDueDate(){
        return returnDate;
    }

    void overDue(){
        overDueFee = overDueFee + .1;
        if(overDueFee > value){
            overDueFee = value;
        }
        checkedOutBy.overDueFee(overDueFee);
    }

    String dueDateString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String inDate = returnDate.format(formatter);
        return inDate;
    }
    abstract boolean renew();
    abstract void display();

}