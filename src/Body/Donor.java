package Body;

import java.util.ArrayList;

public class Donor extends Person
{
	private static int donorCounterID = 1;
	private int donorID;
	private double totalDonated;
	private ArrayList<Payment> donationHistory;
	
	
	public Donor(String name, int age, String nationalid, String gender, String email, String address, String phone) 
	{
		super(name, age, nationalid, gender, email, address, phone);
		this.totalDonated=0.0;
		this.donationHistory = new ArrayList<>();
		this.donorID=donorCounterID;
		donorCounterID++;
	}
	
	public double getTotalDonated()
	{
		return totalDonated;
	}
	public int getDonorID()
	{
		return this.donorID;
	}
	
	public ArrayList<Payment> getDonationHistory()
	{
		return donationHistory;
	}
	
	public void makeDonation(Payment payment)
	{
		 if (payment == null) 
		 {
		        throw new IllegalArgumentException("Payment cannot be null");
		 }
			payment.processPayment();
			totalDonated+=payment.getAmount();
			donationHistory.add(payment);
			LoanOfficer.addDonation(payment.getAmount());
			System.out.println("Donation of $" + payment.getAmount() + " received!");
		    System.out.println("Total donated: $" + totalDonated);
	}
	public void displayInfo()
	{
		 	System.out.println("=== Donor Information ===");
	        super.displayBasicInfo();
	        System.out.println("Donor ID: " + donorID);
	        System.out.printf("Total Donated: $ %.2f%n",totalDonated);
	        System.out.println("Number of Donations: " + donationHistory.size());
	        System.out.println("=========================");
	}
	public void displayDonationHistory()
	{
		 System.out.println("=== Donation History for " + getName() + " ===");
		 if(donationHistory.isEmpty())
		 {
			 System.out.println("No donations yet ");
		 }else
		 {
			 for(Payment payment : donationHistory)
			 {
				 payment.getPaymentDetails();
			 }
			 System.out.printf("Total: $ %.2f%n", totalDonated);
		 }
	}
	
	
	
	
}
