package Body;


public class CreditCardPayment extends Payment
{
	private String cardNumber;
	
	public CreditCardPayment(double amount,String cardNumber) {
		super(amount);
		setCardNumber(cardNumber);
	}
	
	private void setCardNumber(String cardNumber) 
	{
	    if (cardNumber == null) {
	        throw new IllegalArgumentException("Card Number Cannot be Null");
	    }
	    
	    cardNumber = cardNumber.trim();
	    
	    if (cardNumber.length() != 16) {
	        throw new IllegalArgumentException("Card Number Must be 16 Digits");
	    }
	    
	    for (int i = 0; i < cardNumber.length(); i++) {
	        if (!Character.isDigit(cardNumber.charAt(i))) {
	            throw new IllegalArgumentException("Card Number Must Contain Digits Only");
	        }
	    }
	    
	    this.cardNumber = cardNumber;  // Only assign if valid
	}
	public String getCardNumber()
	{
		return cardNumber;
	}

	@Override
	public void processPayment() 
	{	
		System.out.println("Processing Credit Card Payment...");
        System.out.println("Amount: $" + getAmount());
        System.out.println("Payment ID: " + getPaymentID());
        System.out.println("Payment Completed Successfully!");	
	}

	@Override
	public void getPaymentDetails() 
	{
		System.out.println("=== Credit Card Payment ===");
        System.out.println("Payment ID: " + getPaymentID());
        System.out.println("Amount: $" + getAmount());
        if(cardNumber!=null && cardNumber.length()>=4)
        {
        	String lastFour="";
        	for(int i=12;i<16;i++)
        	{
        		 lastFour+=cardNumber.charAt(i);
        	}
        	System.out.println(" **** **** **** "+ lastFour );
        }
        
        System.out.println("========================");
		
	}
	
}
