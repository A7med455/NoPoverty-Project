package Body;



public class LoanApplication {
	private Client client; // Composition
    private String loanType;
    private float requestAmount;
    private static int applicationCounterID=1;
    private int applicantID;
    private Loan currentLoan;
    public LoanApplication(Client client, String loanType,float requestAmount)
    {
    	this.client=client;
		setLoanType(loanType);
		setRequestAmount(requestAmount);
		this.applicantID = applicationCounterID;
		applicationCounterID++;
		this.currentLoan=null; // no loan when initiated
		client.addLoanApplication(this); // this: the current loanApplication  being created , calls client to add it to his history 
    }
    
    public int getApplicantID() 
    {
        return applicantID;
    }
    public void setLoanType(String loanType) // set loan type 
    {
        if (loanType == null || loanType.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Loan type cannot be null or empty ");
        }
        this.loanType = loanType;
    }

    public String getLoanType()  // return loan type (business || emergency || education) 
    {
        return loanType;
    }
    

    public void setRequestAmount(float requestAmount) // sets  requested amount 
    {
    	if(requestAmount<=0)
    	{
    		 throw new IllegalArgumentException("Request amount must be positive");
    	}
    	 if (requestAmount > 1000000) // limitations because it's a charity balance 
    	 {
    	        throw new IllegalArgumentException("Request amount too high for charity loan");
    	 }
        this.requestAmount = requestAmount;
    }
    
    public float getRequestAmount() // return the amount of money requested for the loan 
    {
        return requestAmount;
    }
    public void setCurrentLoan(Loan loan) // stored loan object only if approved by LoanOfficer
    {
    	if(loan ==null)
    	{
    		throw new IllegalArgumentException("Loan Cannot be Null");
    	}
    	this.currentLoan=loan;
    }
    public Loan getCurrentLoan()
    {
    	return this.currentLoan;
    }
    public boolean hasActiveLoan()
    {
    	return currentLoan !=null;
    }
    public String getLoanState()
    {
    	if(hasActiveLoan()==true)
    	{
    		return "YES";
    	}else
    	{
    		return "NO";
    	}
    }
    public Client getClient()
    {
    	return client;
    }
    public double getIncome()
    {
    	return client.getMonthlyIncome();
    }
    public String getEmploymentStatus()
    {
    	return client.getEmploymentStatus();
    }
    public int getCreditScore()
    {
    	return client.getCreditScore();
    }
    public void displayInfo() {
        System.out.println("------- Loan Application Information ------");
        client.displayBasicInfo();  // Shows client info
        System.out.println("Application ID: " + applicantID);
        System.out.printf("Income: $%.2f%n" ,getIncome());
        System.out.println("Employment: " + getEmploymentStatus());
        System.out.println("Loan Type: " + loanType);
        System.out.printf("Amount Requested: $%.2f%n" ,requestAmount);
        System.out.println("Credit Score: " + getCreditScore());
        System.out.println("Active Loan: " + getLoanState());
    }
}