package Body;


public class EducationApplication extends LoanApplication
{
	    private String institutionName;
	    private String courseName;
	    private int courseDuration; // in years
	    private int loanTermYears;
	    
	    public EducationApplication(Client client, float requestAmount,
	                               String institutionName, String courseName, 
	                               int courseDuration, int loanTermYears)
	    {
	        super(client, "education", requestAmount);
	        setInstitutionName(institutionName);
	        setCourseName(courseName);
	        setCourseDuration(courseDuration);
	        setLoanTermYears(loanTermYears);
	    }
	    
	    public String getInstitutionName() 
	    { 
	    	return institutionName; 
	    }
	    public String getCourseName() 
	    {
	    	return courseName; 
	    }
	    public int getCourseDuration() 
	    { 
	    	return courseDuration; 
	    }
	    public int getLoanTermYears() 
	    {
	    	return loanTermYears;
	    }
	    public void setInstitutionName(String name) 
	    {
	        if (name == null || name.trim().isEmpty()) 
	        {
	            throw new IllegalArgumentException("Institution name cannot be empty ");
	        }
	        this.institutionName = name;
	    }
	    
	    public void setCourseName(String name) 
	    {
	        if (name == null || name.trim().isEmpty())
	        {
	            throw new IllegalArgumentException("Course name cannot be empty ");
	        }
	        this.courseName = name;
	    }
	    
	    public void setCourseDuration(int years) 
	    {
	        if (years <= 0 || years > 10)
	        {
	            throw new IllegalArgumentException("Course duration must be 1-10 years ");
	        }
	        this.courseDuration = years;
	    }
	    
	    public void setLoanTermYears(int years) 
	    {
	        if (years < 1 || years > 20) 
	        {
	            throw new IllegalArgumentException("Loan term must be 1-20 years ");
	        }
	        this.loanTermYears = years;
	    }
	    
	    @Override
	    public void displayInfo() 
	    {
	        System.out.println("=== Education Loan Application ===");
	        System.out.println("Application ID: " + getApplicantID());
	        System.out.println("Client: " + getClient().getName());
	        System.out.printf("Amount Requested: $%.2f%n", getRequestAmount());
	        System.out.println("Institution: " + institutionName);
	        System.out.println("Course: " + courseName);
	        System.out.println("Course Duration: " + courseDuration + " years");
	        System.out.println("Proposed Loan Term: " + loanTermYears + " years");
	        System.out.println("===================================");
	    }
}
