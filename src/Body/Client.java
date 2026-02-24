package Body;

import java.util.ArrayList;

public class Client extends Person {
	
	private double monthlyIncome;
	private String employmentStatus; 
	private int creditScore;
	private static int counterID=1;
	private int clientID;
	private PaymentPlan currentPaymentPlan; // store the active payment plan for this client ( to track clients) 
	private ArrayList<LoanApplication> clientLoanHistory;
	
	public Client(String name,int age,String nationalid,String gender,String email, String address,String phone,
			double monthlyIncome , String employmentStatus , int creditScore)
	{
		super(name,age,nationalid,gender,email,address,phone);
		setMonthlyIncome(monthlyIncome);
	    setEmploymentStatus(employmentStatus);
	    setCreditScore(creditScore);     
	   this.clientLoanHistory = new ArrayList<>(); 
		this.clientID=counterID;
		counterID++;
	}
	public void setCurrentPaymentPlan(PaymentPlan plan) // setter for the payment plan
	{
		this.currentPaymentPlan=plan;
	}
	public PaymentPlan getCurrentPaymentPlan() // getter for the payment plan 
	{
		return currentPaymentPlan;
	}
	public boolean hasActivePaymentPlan() // check if client has an unpaid payment plan
	{
		if(currentPaymentPlan == null || currentPaymentPlan.isFullyPaid())
		{
			return false;
		}
		return true;
	}
	public void setMonthlyIncome(double monthlyIncome)
	{
		if(monthlyIncome<0)
		{
			throw new IllegalArgumentException("Monthly income cannot be negative");
		}
		this.monthlyIncome=monthlyIncome;
	}
	public double getMonthlyIncome()
	{
		return this.monthlyIncome;
	}
	public void setEmploymentStatus(String employmentStatus)
	{
		if(employmentStatus==null|| employmentStatus.trim().isEmpty())
		{
			 throw new IllegalArgumentException("Employment status cannot be null or empty");
		}
		 if (!employmentStatus.equalsIgnoreCase("employed") &&
		            !employmentStatus.equalsIgnoreCase("unemployed") &&
		            !employmentStatus.equalsIgnoreCase("self-employed")) {
		            throw new IllegalArgumentException("Employment status must be: employed, unemployed, or self-employed ");
		        }
		        this.employmentStatus = employmentStatus;
	}
	public String getEmploymentStatus()
	{
		return this.employmentStatus;
	}
	public void setCreditScore(int creditScore) 
	{
	        if (creditScore < 300 || creditScore > 850) 
	        {
	            throw new IllegalArgumentException("Credit score must be between 300 and 850");
	        }
	        this.creditScore = creditScore;
	    }
	public int getCreditScore()
	{
		return creditScore;
	}
	public ArrayList<LoanApplication> getLoanHistory()
	{
		return clientLoanHistory;
	}
	public void addLoanApplication(LoanApplication application)
	{
		if(application ==null)
		{
			  throw new IllegalArgumentException("Loan application cannot be null");
		}
		 clientLoanHistory.add(application);
	}
	public int getClientID()
	{
		return this.clientID;
	}
	public String getPovertyLevel()
	{
		double monthlyIncome=this.monthlyIncome;
		if(monthlyIncome<10000)
		{
			return "High"; // high poverty level
		}
		else if(monthlyIncome >=10000 && monthlyIncome <50000)
		{
			return "Mid"; 
		}
		else
		{
			return "Low"; // low poverty level
		}
	}
	 public void displayInfo() 
	 {
	        System.out.println("------- Client Information ------");
	        super.displayBasicInfo();
	        System.out.println("Client ID: " + clientID);
	        System.out.printf("Monthly Income: $%.2f%n" , monthlyIncome);
	        System.out.println("Employment Status: " + employmentStatus);
	        System.out.println("Credit Score: " + creditScore);
	        System.out.println("Poverty Level: "+ getPovertyLevel());
	        System.out.println("Loan Applications: " + clientLoanHistory.size());
	        System.out.println("---------------------------------");
	    }
	}