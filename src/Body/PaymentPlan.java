package Body;



public class PaymentPlan
{
    private int repaymentId;
    private static int idCounter=1;
    private double principalAmount;
    private int termMonths;
    private double monthlyPayment;
    private double totalPaid;
    private int paymentsMade;
    
    public PaymentPlan(Loan loan )
    {
    this.principalAmount=loan.getPrincipalAmount();
    this.termMonths=loan.getLoanTermMonths();
    this.monthlyPayment =loan.calculateMonthlyPayment();
    this.totalPaid=0.0;
   this.paymentsMade=0;
    this.repaymentId=idCounter;
    idCounter++; 
    }

	public void makePayment(double amount) // process a payment from the client 
	{
		if(amount<=0)
		{
			throw new IllegalArgumentException("Payment must be greater  than 0 ");
		}
		if(isFullyPaid()) // check if already paid before accepting payment 
		{
			throw new IllegalArgumentException("Loan already fully paid ");
		}
		
		totalPaid+=amount;
		paymentsMade++;
		
		LoanOfficer.addRepayment(amount);
		 System.out.println("Payment #" + paymentsMade + " of $" + amount + " received");
	     System.out.printf("   Total paid: $%.2f / $%.2f%n", totalPaid, principalAmount);
	     if(isFullyPaid())
	     {
	    	 System.out.println("Loan Fully Paid Off ! ");
	     }
	}
	
	public boolean isFullyPaid() //  check if loan is completely paid off or not
	{
		if(totalPaid>=principalAmount)
		{
			return true;
		}
		return false;
	}
	public double getRemainingBalance() // calculate how much the client still owes
	{
		return principalAmount-totalPaid;
	}
	public double getMonthlyPayment() // get the fixed monthly payment
	{
		return monthlyPayment;
	}
	public double getTotalPaid() // get total amount paid so far
	{
		return totalPaid;
	}
	public int getPaymentsMade() // get number of payments made so far
	{
		return paymentsMade;
	}
	public int getRemainingPayments() // return months left in term
	{
		if(isFullyPaid())
		{
			return 0;
		}
		return termMonths-paymentsMade;
	}
	
	public void displayPaymentPlan()
	{
		 	System.out.println(" === PAYMENT PLAN #" + repaymentId + "  ===");
	        System.out.printf("Principal Amount: $%.2f%n", principalAmount);
	        System.out.println("Term: " + termMonths + " months");
	        System.out.printf("Monthly Payment: $%.2f%n", monthlyPayment);
	        System.out.printf("Total Paid: $%.2f%n", totalPaid);
	        System.out.printf("Remaining Balance: $%.2f%n", getRemainingBalance());
	        System.out.println("Payments Made: " + paymentsMade);
	        System.out.println("Remaining Payments: " + getRemainingPayments());
	        System.out.println("Status: " + (isFullyPaid() ? "PAID OFF 🎉" : "ACTIVE"));
	        System.out.println("==============================");
	    
	}
}