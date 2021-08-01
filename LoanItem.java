import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.text.DecimalFormat;
abstract class LoanItem extends Item{
    boolean isOut;
    boolean repeatRenew;
    User checkedOutBy;
    LocalDate checkOutDate;
    LocalDate returnDate;
    double fee;

    //constructor
    LoanItem(int id, String title, double value){
        super(id, title, value);
        isOut = false;
        fee = 0;
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

    void checkOverDue(LocalDate currentDay){
        if (currentDay.isAfter(returnDate)){
            calcFee(currentDay);
        }

    }

    void calcFee(LocalDate currentDay){
        DecimalFormat money = new DecimalFormat("0.00");
        long days = ChronoUnit.DAYS.between(returnDate, currentDay);
        System.out.println(title + " is overdue by " + days + " days!");
        fee = days * .1;
        if(fee > value){
            fee = value;
        }
        System.out.println("A fee of $" + money.format(fee) + " is owed!");
    }



    String dueDateString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String inDate = returnDate.format(formatter);
        return inDate;
    }
    abstract boolean renew();
    abstract void display();

}