package Body;



/*
 Class is Ready.
 */
public class LoanOfficer {
	
	private int approvalCounts;
	private int rejectionCounts;
	private static double charityBalance=0.0; // shared balance for all LoanOfficers
	
	public LoanOfficer()
	{
		this.approvalCounts=0;
		this.rejectionCounts=0;
	}
	public static double getCharityBalance()
	{
		return charityBalance;
	}
	public static void addDonation(double amount)
	{
		if(amount<=0)
		{
			  throw new IllegalArgumentException("Donation amount must be positive");
		}
		charityBalance+=amount;
	}
	public static boolean hasSufficientFunds(double requestedAmount)
	{
		if(charityBalance<requestedAmount)
		{
			return false;
		}
		return true;
	}
	public static void disburseLoan(double amount) // withdraw funds when loan approved
	{
		 if (amount <= 0) 
		 	{
	            throw new IllegalArgumentException("Loan amount must be positive");
	        }
	        if (!hasSufficientFunds(amount))
	        {
	            throw new IllegalArgumentException("Insufficient charity funds for loan");
	        }
	        charityBalance -= amount;
	        System.out.println("$ " + amount + " disbursed as loan.");
	        System.out.println("   Remaining charity balance: $" + charityBalance);
	}
	public static void addRepayment(double amount)
	{
		 if (amount <= 0) 
		 	{
	            throw new IllegalArgumentException("Repayment amount must be positive");
	        }
	        charityBalance += amount;
	        System.out.println("$ " + amount + " repaid to charity pool.");
	        System.out.println("   Total charity balance: $" + charityBalance);
	}
	public char getCreditRank(int creditScore)
	{
		 if (creditScore >= 750) 
		 {
			 return 'A';
		 }
		
		    else if (creditScore >= 700)
		    {
		    	return 'B';
		    }
		    else if (creditScore >= 650) 
		    {
		    	return 'C';
		    }
		    else if (creditScore >= 600) 
		    {
		    	return 'D';
		    }
		    else 
		    {
		    	return 'E';
		    }
	}
	
	private boolean isApplicationDataValid(LoanApplication application)
	{
		if(application==null)
		{
			System.out.println("Application Cannot be Null");
			return false;
		}
		
		int appicationID=application.getApplicantID();
		String name = application.getClient().getName();  
		double income=application.getIncome();
		String employmentStatus = application.getEmploymentStatus();
		String loanType = application.getLoanType();
		float requestAmount = application.getRequestAmount();
		int creditScore = application.getCreditScore();
		
		
		if(appicationID<=0)
		{
			System.out.println("Invalid Applicant ID");
			return false;
		}
		if(name==null || name.trim().isEmpty())
		{
			System.out.println("Invalid Applicant Name");
			return false;
		}
		if(income<=0)
		{
			System.out.println("Invalid Income");
			return false;
		}
		if(!isEmploymentStatusValid(employmentStatus)) 
		{
			System.out.println("Invalid Applicant Employment Status");
			return false;
		}
		if(!isLoanTypeValid(loanType)) 
		{
			System.out.println("Invalid Applicant Loan Type");
			return false;
		}
		if(requestAmount<=0)
		{
			System.out.println("Invalid Amount");
			return false;
		}
		if(creditScore < 300 || creditScore > 850) {
		    System.out.println("Invalid Credit Score");
		    return false;
		}
		return true;
	}
	
	private boolean isEmploymentStatusValid(String status)
	{
		if(status==null)
		{
			System.out.println("Employment Status Cannot be Null");
			return false;
		}
		if(status.trim().isEmpty())
		{
			System.out.println("Employment Status Cannot be Empty");
			return false;
		}
		if(!status.equalsIgnoreCase("employed") && !status.equalsIgnoreCase("unemployed")  && !status.equalsIgnoreCase("self-employed"))
		{
			System.out.println("Employment Status Invalid");
			return false;
		}
		return true;
	}
	private boolean isLoanTypeValid(String type)
	{
		if(type==null)
		{
			System.out.println("Loan Type Cannot be Null");
			return false;
		}
		if(type.trim().isEmpty())
		{
			System.out.println("Loan Type Cannot be Empty");
			return false;
		}
		if(!type.equalsIgnoreCase("business")  && !type.equalsIgnoreCase("education"))
		{
			System.out.println("Loan Type is Invalid");
			return false;
		}
		return true;
	}
	private boolean evaluateApplication(LoanApplication application)
	{
	    double income = application.getIncome();
	    float requestAmount = application.getRequestAmount();
	    int creditScore = application.getCreditScore();
	    String povertyLevel = application.getClient().getPovertyLevel();
	    char creditRank = getCreditRank(creditScore);
	    
	    // Print debug info to see what's happening
	    System.out.println("\n=== LOAN EVALUATION ===");
	    System.out.println("Income: $" + income);
	    System.out.println("Requested: $" + requestAmount);
	    System.out.println("Credit Score: " + creditScore + " (Rank: " + creditRank + ")");
	    System.out.println("Poverty Level: " + povertyLevel);
	    System.out.println("Income to Loan Ratio: " + (requestAmount/income));
	    
	    // More flexible approval criteria
	    switch(povertyLevel)
	    {
	    case "High":  // Income < 10000
	        // High poverty - more flexible
	        if (requestAmount <= income * 3 && (creditRank == 'D' || creditRank == 'E' || creditRank == 'C')) 
	        {
	            System.out.println("✅ High poverty - APPROVED");
	            approvalCounts++;   
	            return true;
	        }
	        break;
	        
	    case "Mid":  // Income 10000-50000
	        // Mid poverty - standard
	        if (requestAmount <= income * 2 && (creditRank == 'B' || creditRank == 'C' || creditRank == 'D')) 
	        {
	            System.out.println("✅ Mid poverty - APPROVED");
	            approvalCounts++;
	            return true;
	        }
	        break;
	        
	    case "Low":  // Income > 50000
	        // Low poverty - stricter but not too strict
	        if (requestAmount <= income * 1.5 && (creditRank == 'A' || creditRank == 'B' || creditRank == 'C')) 
	        {
	            System.out.println("✅ Low poverty - APPROVED");
	            approvalCounts++;
	            return true;
	        }
	        break;
	    }
	    
	    System.out.println("❌ REJECTED - Criteria not met");
	    rejectionCounts++;
	    return false;
	}
	
	public boolean loanStatus(LoanApplication application)
	{
		if(application ==null)
		{
			System.out.println("Application Rejected, No Applicant Provided");
			rejectionCounts++;
			return false;
		}
		
		if(!isApplicationDataValid(application))
		{
			System.out.println("Application Rejected , Data Missing");
			rejectionCounts++;
			return false;
		}
		float requestedAmount =application.getRequestAmount();
		if(!hasSufficientFunds(requestedAmount))
		{
			 System.out.println("Application Rejected: Insufficient charity funds");
			 rejectionCounts++;
			 return false;
		}
		
		return evaluateApplication(application);
	}
	public int getApprovalCounts()
	{
		return approvalCounts;
	}
	public int getRejectionCounts()
	{
		return rejectionCounts;
	}
	public int getTotalCounts()
	{
		return rejectionCounts+approvalCounts;
	}
	public double getSuccessRate()
	{
		int total=getTotalCounts();
		if(total==0)
		{
			return 0.0;
		}
		double result=(approvalCounts/(double)total)*100;
		return result;
	}
	public double getFailureRate()
	{
		int total=getTotalCounts();
		if(total==0)
		{
			return 0.0;
		}
		double result=(rejectionCounts/(double)total)*100;
		return result;
	}
	private Loan createLoanForApplication(LoanApplication application)
	{
		if(application instanceof EducationApplication)
		{
			EducationApplication eduApp=(EducationApplication)application;
			return new EducationLoan( eduApp.getRequestAmount(),
		            eduApp.getLoanTermYears(),
		            eduApp.getInstitutionName(),
		            eduApp.getCourseName(),
		            eduApp.getCourseDuration());
		}
		else if(application instanceof BusinessApplication)
		{
			BusinessApplication BusApp= (BusinessApplication) application;
			return new BusinessLoan( BusApp.getRequestAmount(),
		            BusApp.getLoanTermYears(),
		            BusApp.getBusinessName(),
		            BusApp.getBusinessType(),
		            BusApp.getYearsInOperation(),
		            BusApp.getMonthlyRevenue(),
		            BusApp.getBusinessPlanQuality(),
		            BusApp.getBusinessExpenses());
		}
		else
		{
			throw new IllegalArgumentException("Cannot create loan: Need specialized application with details");
		}
	}
	public void displayInfo()
	{
	    System.out.println("------- Automatic Loan Decision ------");
	    System.out.println("Approvals: " + approvalCounts + " | Rate: " + String.format("%.1f", getSuccessRate()) + "%");
	    System.out.println("Rejections: " + rejectionCounts + " | Rate: " + String.format("%.1f", getFailureRate()) + "%");
	    System.out.println("Total Applications Reviewed: " + getTotalCounts());
	}
	
	public void displayApplicationResult(LoanApplication application)
	{
		boolean decision=loanStatus(application);
		int creditScore = application.getCreditScore();
		char creditRank = getCreditRank(creditScore);
		    
		 System.out.println("------- Automatic Loan Decision ------");
	     System.out.println("Applicant ID: " + application.getApplicantID());
	     System.out.println("Client Name: " + application.getClient().getName());
	     System.out.println("Income: " + application.getIncome()+" $");
	     System.out.println("Employment: " + application.getEmploymentStatus());
	     System.out.println("Loan Type: " + application.getLoanType());
	     System.out.println("Amount Requested: $" + application.getRequestAmount());
	     System.out.println("Credit Score: " + application.getCreditScore());
	     System.out.println("Credit Rank: "+creditRank);
	     
	     if (decision) 
	     {
	    	 System.out.println("DECISION: APPROVED");
	    	 disburseLoan(application.getRequestAmount());
	    	 if(application instanceof EducationApplication||application instanceof BusinessApplication)
	    	 {
	    		 Loan approvedLoan=createLoanForApplication(application);
	    		 application.setCurrentLoan(approvedLoan);
	    		 
	    		 PaymentPlan paymentplan = new PaymentPlan(approvedLoan);
	    		 application.getClient().setCurrentPaymentPlan(paymentplan);
	    		 
	    		 System.out.println(" === Loan Contract Created ===");
	             approvedLoan.getLoanDetails();
	             System.out.println("Loan added to client's history.");
	    	 }
	    	 else
	    	 {
	    		 System.out.println("Loan approved but no contract created ");
	             System.out.println("Use EducationApplication or BusinessApplication for full details ");
	    	 }
	     }else
	     {
	    	 System.out.println("DECISION: REJECTED");
	     }
	        System.out.println("----------------------------");
	}
	
		
}
	