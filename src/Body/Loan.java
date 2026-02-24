package Body;



public interface Loan {
    double calculateMonthlyPayment();
    double calculateTotalRepayment();
    String getLoanDetails(); // Single method to display all loan information including duration (years, months)
    int getLoanTermMonths(); //returns months
    double getPrincipalAmount();
}