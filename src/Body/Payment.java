package Body;



public abstract class Payment 
{
	private double amount;
	private String paymentID;
	private static int paymentCounter=1;
	
	public Payment(double amount)
	{
		setAmount(amount);
		this.paymentID="PAY"+paymentCounter;
		paymentCounter++;
	}
	private void setAmount(double amount)
	{
		if(amount<=0)
		{
			throw new IllegalArgumentException("Payment must be positive");
		}
		this.amount=amount;
	}
	public double getAmount()
	{
		return amount;
	}
	public String getPaymentID()
	{
		return paymentID;
	}
	public abstract void processPayment();
	public abstract void getPaymentDetails();
}
