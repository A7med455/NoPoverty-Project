package Body;


public class BusinessLoan implements Loan {
    private String businessName;
    private String businessType; // food, retail, electronics, etc
    private int yearsInOperation;
    private float monthlyRevenue;
    private String businessPlanQuality; // perfect, good, medicore, poor
    private double businessExpenses; // to calculate if theyre possible to pay the loan
    private double principalAmount;
    private int loanTermMonths;
    
    
    
    public BusinessLoan( double principalAmount , int loanTermYears, String businessName, String businessType, int yearsInOperation,
            float monthlyRevenue, String businessPlanQuality, double businessExpenses) 
    {
    	setPrincipalAmount(principalAmount);
    	setLoanTerm(loanTermYears);
        setBusinessName(businessName);
        setBusinessType(businessType);
        setYearsInOperation(yearsInOperation);
        setMonthlyRevenue(monthlyRevenue);
        setBusinessExpenses(businessExpenses);
        setBusinessPlanQuality(businessPlanQuality);

    }

    public String getBusinessName() 
    {
        return businessName;
    }

    public void setBusinessName(String businessName) 
    {
        if (businessName == null || businessName.trim().isEmpty())
        {
            throw new IllegalArgumentException("Business name cannot be null or empty.");
        }
        this.businessName = businessName;
    }

    public String getBusinessType() 
    {
        return businessType;
    }

    public void setBusinessType(String businessType) 
    {
        if (businessType == null || businessType.trim().isEmpty()) {
            throw new IllegalArgumentException("Business type cannot be null or empty.");
        }
        if (!businessType.equalsIgnoreCase("food") &&
                !businessType.equalsIgnoreCase("retail") &&
                !businessType.equalsIgnoreCase("electronics") &&
                !businessType.equalsIgnoreCase("service") &&
                !businessType.equalsIgnoreCase("manufacturing")) {
            throw new IllegalArgumentException("Business type must be one of the predefined categories.");
        }
        this.businessType = businessType;
    }

    public int getYearsInOperation() 
    {
        return yearsInOperation;
    }

    public void setYearsInOperation(int yearsInOperation) 
    {
        if (yearsInOperation < 0) {
            throw new IllegalArgumentException("Years in operation cannot be negative.");
        }
        if (yearsInOperation > 100) {
            throw new IllegalArgumentException("Years in operation seems unrealistic.");
        }
        this.yearsInOperation = yearsInOperation;
    }

    public float getMonthlyRevenue() 
    {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(float monthlyRevenue)
    {
        if (monthlyRevenue < 0) {
            throw new IllegalArgumentException("Monthly revenue cannot be negative.");
        }
        if (monthlyRevenue > 10000000) {
            throw new IllegalArgumentException("Loan automatically denied due to high monthly revenue.");
        }
        this.monthlyRevenue = monthlyRevenue;
    }

    public String getBusinessPlanQuality() 
    {
        return businessPlanQuality;
    }

    public void setBusinessPlanQuality(String businessPlanQuality) 
    {
        if (businessPlanQuality == null || businessPlanQuality.trim().isEmpty()) {
            throw new IllegalArgumentException("Business plan quality cannot be null or empty.");
        }
        if (!businessPlanQuality.equalsIgnoreCase("perfect") &&
                !businessPlanQuality.equalsIgnoreCase("good") &&
                !businessPlanQuality.equalsIgnoreCase("mediocre") &&
                !businessPlanQuality.equalsIgnoreCase("poor")) {
            throw new IllegalArgumentException("Business plan quality must be one of the predefined categories.");
        }
        this.businessPlanQuality = businessPlanQuality;
    }

    public double getBusinessExpenses() 
    {
        return businessExpenses;
    }

    public void setBusinessExpenses(double businessExpenses) 
    {
        if (businessExpenses < 0){
            throw new IllegalArgumentException("Business expenses cannot be negative.");
        }
        if (businessExpenses > 1000000) {
            throw new IllegalArgumentException("Considered bankrupt; unable to take loan.");
        }
        this.businessExpenses = businessExpenses;
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
             throw new IllegalArgumentException("Loan term for business loans should be between 1 and 20 years ");
         }
    	this.loanTermMonths=loanTermYears*12; // convert from years to months
    }

	@Override
	public double calculateMonthlyPayment() 
	{
		return principalAmount/loanTermMonths;
	}

	@Override
	public double calculateTotalRepayment()
	{
		return principalAmount;
	}

	@Override
	public String getLoanDetails() 
	{
		 	System.out.println("--- BUSINESS LOAN ---");
	        System.out.println("Business: " + businessName);
	        System.out.println("Type: " + businessType);
	        System.out.println("Years Operating: " + yearsInOperation);
	        System.out.printf("Monthly Revenue: $%.2f%n", monthlyRevenue);
	        System.out.println("Business Plan: " + businessPlanQuality);
	        System.out.printf("Expenses: $%.2f%n", businessExpenses);
	        System.out.printf("Loan Amount: $%.2f%n", principalAmount);
	        System.out.println("Loan Term: " + (loanTermMonths/12) + " years");
	        System.out.printf("Monthly Payment: $%.2f%n", calculateMonthlyPayment());
	        System.out.printf("Total Repayment: $%.2f%n", calculateTotalRepayment());
	        System.out.println("Interest: ZERO (Charity Business Loan)");
	        System.out.println("-----------------");
	        return "Business Loan Details Printed Above";

	}

	@Override
	public int getLoanTermMonths() 
	{

		return loanTermMonths;
	}

	@Override
	public double getPrincipalAmount() 
	{
		return principalAmount;
	}

}
