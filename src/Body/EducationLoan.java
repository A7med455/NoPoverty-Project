package Body;



public class EducationLoan implements Loan {
	
    private String institutionName;
    private String courseName;
    private int courseDuration; // in years
    private double principalAmount;
    private int loanTermMonths; // in months
    
    public EducationLoan( double principalAmount , int loanTermYears, String institutionName,String courseName, int courseDuration)
    {
    	setPrincipalAmount(principalAmount);
    	setLoanTerm(loanTermYears);
        setInstitutionName(institutionName);
        setCourseName(courseName);
        setCourseDuration(courseDuration);
       
    }

    public void setPrincipalAmount(double principalAmount)
    {
    	if(principalAmount<=0)
    	{
    		throw new IllegalArgumentException("Principal amount must be positive ");
    	}
    	this.principalAmount=principalAmount;
    }
    public void setLoanTerm(int loanTermYears)
    {
    	if(loanTermYears<=0)
    	{
    		throw new IllegalArgumentException("Loan term must be positive ");
    	}
    	 if (loanTermYears < 1 || loanTermYears > 20) {
             throw new IllegalArgumentException("Loan term for education loans should be between 1 and 20 years ");
         }
    	this.loanTermMonths=loanTermYears*12; // convert from years to months
    }
    
    public void setInstitutionName(String institutionName) 
    {
        if (institutionName == null || institutionName.trim().isEmpty()) {
            throw new IllegalArgumentException("Institution name cannot be null or empty ");
        }
        this.institutionName = institutionName;
    }

    public String getInstitutionName() {
        return institutionName;
    }
    
    public void setCourseName(String courseName)
    {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty ");
        }
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseDuration(int courseDuration) {
        if (courseDuration <= 0) {
            throw new IllegalArgumentException("Course duration must be positive ");
        }
        this.courseDuration = courseDuration;
    }
    
    public int getCourseDuration() {
        return courseDuration;
    }

    @Override
    public double calculateMonthlyPayment()
    {
    	return calculateTotalRepayment()/loanTermMonths;
    }

	@Override
	public double calculateTotalRepayment() // returns total amount 
	{ 
		
		return principalAmount;
	}
	@Override
	public double getPrincipalAmount()
	{
		return this.principalAmount;
	}
	
	public int getLoanTermMonths()
	{
		return this.loanTermMonths;
	}
	public int getLoanTermYears()
	{
		return this.loanTermMonths/12;
	}

	@Override
	public String getLoanDetails() {
	    System.out.println("--- EDUCATION LOAN ---");
	    System.out.println("Institution: " + institutionName);
	    System.out.println("Course: " + courseName);
	    System.out.println("Course Duration: " + courseDuration + " years");
	    
	    System.out.printf("Amount: $%.2f%n" ,principalAmount); // i used printf intead of println to avoid more than 2 decimal number
	    System.out.println("Loan Term: " + getLoanTermYears() +" years"); // %n is used for newline character
	    System.out.printf("Monthly Payment: $%.2f%n" , calculateMonthlyPayment());
	    System.out.printf("Total Repayment: $%.2f%n" , calculateTotalRepayment()); 
	    System.out.println("-----------------");
	    
	    return "Education Loan Details Printed Above";
	}
}