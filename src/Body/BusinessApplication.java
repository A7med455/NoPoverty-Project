package Body;



public class BusinessApplication extends LoanApplication 
{
    private String businessName;
    private String businessType;
    private int yearsInOperation;
    private float monthlyRevenue;
    private String businessPlanQuality;
    private double businessExpenses;
    private int loanTermYears;
    
    public BusinessApplication(Client client, float requestAmount,
                              String businessName, String businessType, 
                              int yearsInOperation, float monthlyRevenue,
                              String businessPlanQuality, double businessExpenses,
                              int loanTermYears) 
    {
        super(client, "business", requestAmount);
        setBusinessName(businessName);
        setBusinessType(businessType);
        setYearsInOperation(yearsInOperation);
        setMonthlyRevenue(monthlyRevenue);
        setBusinessPlanQuality(businessPlanQuality);
        setBusinessExpenses(businessExpenses);
        setLoanTermYears(loanTermYears);
    }
    public String getBusinessName() 
    { 
    	return businessName; 
    }
    public String getBusinessType() 
    { 
    	return businessType; 
    }
    public int getYearsInOperation() 
    { 
    	return yearsInOperation; 
    }
    public float getMonthlyRevenue() 
    { 
    	return monthlyRevenue;
    }
    public String getBusinessPlanQuality() 
    { return businessPlanQuality;
    }
    public double getBusinessExpenses() 
    { 
    	return businessExpenses; 
    }
    public int getLoanTermYears() 
    { 
    	return loanTermYears; 
    }
    public void setBusinessName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Business name cannot be empty");
        }
        this.businessName = name;
    }
    
    public void setBusinessType(String type)
    {
        if (type == null || type.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Business type cannot be empty");
        }
        if (!type.equalsIgnoreCase("food") && 
            !type.equalsIgnoreCase("retail") &&
            !type.equalsIgnoreCase("electronics") && 
            !type.equalsIgnoreCase("service") &&
            !type.equalsIgnoreCase("manufacturing")) 
        {
            throw new IllegalArgumentException("Business type must be: food, retail, electronics, service, or manufacturing");
        }
        this.businessType = type;
    }
    
    public void setYearsInOperation(int years) 
    {
        if (years < 0)
        {
            throw new IllegalArgumentException("Years in operation cannot be negative");
        }
        if (years > 100)
        {
            throw new IllegalArgumentException("Years in operation seems unrealistic");
        }
        this.yearsInOperation = years;
    }
    
    public void setMonthlyRevenue(float revenue) 
    {
        if (revenue < 0) {
            throw new IllegalArgumentException("Monthly revenue cannot be negative");
        }
        if (revenue > 10000000) {
            throw new IllegalArgumentException("Monthly revenue too high for charity loan");
        }
        this.monthlyRevenue = revenue;
    }
    
    public void setBusinessPlanQuality(String quality) {
        if (quality == null || quality.trim().isEmpty()) {
            throw new IllegalArgumentException("Business plan quality cannot be empty");
        }
        if (!quality.equalsIgnoreCase("perfect") &&
            !quality.equalsIgnoreCase("good") &&
            !quality.equalsIgnoreCase("mediocre") &&
            !quality.equalsIgnoreCase("poor")) {
            throw new IllegalArgumentException("Business plan quality must be: perfect, good, mediocre, or poor");
        }
        this.businessPlanQuality = quality;
    }
    
    public void setBusinessExpenses(double expenses) {
        if (expenses < 0) {
            throw new IllegalArgumentException("Business expenses cannot be negative");
        }
        if (expenses > 1000000) {
            throw new IllegalArgumentException("Business expenses too high");
        }
        this.businessExpenses = expenses;
    }
    
    public void setLoanTermYears(int years) {
        if (years < 1 || years > 20) {
            throw new IllegalArgumentException("Loan term must be 1-20 years");
        }
        this.loanTermYears = years;
    }
 
    @Override
    public void displayInfo() {
        System.out.println("=== Business Loan Application ===");
        System.out.println("Application ID: " + getApplicantID());
        System.out.println("Client: " + getClient().getName());
        System.out.printf("Amount Requested: $%.2f%n", getRequestAmount());
        System.out.println("Business Name: " + businessName);
        System.out.println("Business Type: " + businessType);
        System.out.println("Years in Operation: " + yearsInOperation);
        System.out.printf("Monthly Revenue: $%.2f%n", monthlyRevenue);
        System.out.println("Business Plan Quality: " + businessPlanQuality);
        System.out.printf("Monthly Expenses: $%.2f%n", businessExpenses);
        System.out.println("Proposed Loan Term: " + loanTermYears + " years");
        System.out.println("==================================");
    }
}