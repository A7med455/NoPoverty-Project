package Body;



public class OnlinePayment extends Payment 
{

	private String accountNumber;
	private String bankName;
	
	public OnlinePayment(double amount,String accountNumber,String bankName)
	{
		super(amount);
		setAccountNumber(accountNumber);
		setBankName(bankName);
	}
	private void setAccountNumber(String accountNumber)
	{
		if(accountNumber==null)
		{
			 throw new IllegalArgumentException("Account Number Cannot be Null ");
		}
		accountNumber=accountNumber.trim();
		if(accountNumber.length()<8 || accountNumber.length()>20)
		{
			  throw new IllegalArgumentException("Account Number Must be 8 to 20 Digits ");
		}
		for(int i=0;i<accountNumber.length();i++)
		{
			if(!Character.isDigit(accountNumber.charAt(i)))
			{
				 throw new IllegalArgumentException("Account Number Must Contain Digits Only");
			}
		}
		this.accountNumber=accountNumber;
	}
	private void setBankName(String bankName)
	{
		if(bankName==null)
		{
			  throw new IllegalArgumentException("Bank Name Cannot be Null ");
		}
		bankName=bankName.trim();
		if(bankName.isEmpty())
		{
			   throw new IllegalArgumentException("Bank Name Cannot be Empty ");
		}
		this.bankName=bankName;
	}
	@Override
	public void processPayment() 
	{
		 	System.out.println("Processing Online Bank Transfer...");
	        System.out.println("Bank: " + bankName);
	        System.out.println("Amount: $" + getAmount());
	        System.out.println("Payment ID: " + getPaymentID());
	        System.out.println("Payment Completed Successfully!");	
	}
	@Override
	public void getPaymentDetails() {
			System.out.println("=== Online Bank Transfer ===");
	        System.out.println("Payment ID: " + getPaymentID());
	        System.out.println("Amount: $" + getAmount());
	        System.out.println("Bank: " + bankName);
	        String lastFour="";
	        int length=accountNumber.length();
	        for(int i=length-4;i<length;i++)
	        {
	        	lastFour+=accountNumber.charAt(i);
	        }
	        System.out.println("Account: ****" + lastFour);
	        System.out.println("===========================");
	        
	}
	

}
