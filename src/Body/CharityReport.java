package Body;



public class CharityReport 
{
	private Loan loan;
	private PaymentPlan paymentPlan;
	private Client client; // added
	private double totalPaid;
	private double remainingBalance;
	
	public CharityReport(Loan loan, PaymentPlan paymentPlan, Client client)
	{
	    this.loan=loan;
	    this.paymentPlan=paymentPlan;
	    this.client=client;
	    this.totalPaid=paymentPlan.getTotalPaid();
	    this.remainingBalance= paymentPlan.getRemainingBalance();
	
	}
	public void updateReport()
	{
		this.totalPaid=paymentPlan.getTotalPaid();
		this.remainingBalance=paymentPlan.getRemainingBalance();
	}
	public String generateReport()
	{
		updateReport(); // get refresh data 
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append("======== CHARITY LOAN REPORT ========\n");
	    sb.append("Client: ").append(client.getName()).append("\n");
	    sb.append("Client ID: ").append(client.getClientID()).append("\n");
	    sb.append("Poverty Level: ").append(client.getPovertyLevel()).append("\n");
	    
	    if(loan instanceof EducationLoan) // loan type & specific info
	    {
	    	EducationLoan eduLoan = (EducationLoan) loan;
	        sb.append("Loan Type: EDUCATION LOAN\n");
	        sb.append("Institution: ").append(eduLoan.getInstitutionName()).append("\n");
	        sb.append("Course: ").append(eduLoan.getCourseName()).append("\n");
	    }
	    else if(loan instanceof BusinessLoan)
	    {
	    	 BusinessLoan busLoan = (BusinessLoan) loan;
	         sb.append("Loan Type: BUSINESS LOAN\n");
	         sb.append("Business: ").append(busLoan.getBusinessName()).append("\n");
	         sb.append("Business Type: ").append(busLoan.getBusinessType()).append("\n");
	    }
	    
	    sb.append(String.format("Loan Amount: $%.2f%n", loan.getPrincipalAmount()));
	    sb.append(String.format("Loan Term: %d months%n", loan.getLoanTermMonths()));
	    sb.append(String.format("Monthly Payment: $%.2f%n", loan.calculateMonthlyPayment()));
	    //payment info
	    sb.append(String.format("Total Paid: $%.2f%n", totalPaid));
	    sb.append(String.format("Remaining Balance: $%.2f%n", remainingBalance));
	    sb.append(String.format("Payments Made: %d%n", paymentPlan.getPaymentsMade()));
	    sb.append(String.format("Payments Remaining: %d%n", paymentPlan.getRemainingPayments()));
	    
	    sb.append("=== CHARITY IMPACT ===\n");
	    sb.append("This loan helps a ").append(client.getPovertyLevel()).append(" poverty level client\n");
	    sb.append("Repayments recycle back to help others in need\n");
	    
	    sb.append("====================================\n");
	    return sb.toString();
	}
	public void displayReport()
	{
		  System.out.println(generateReport());
	}
	public double getTotalPaid()
	{
		return totalPaid;
	}
	public double getRemainingBalance()
	{
		return remainingBalance;
	}




}