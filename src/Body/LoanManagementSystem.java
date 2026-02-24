package Body;

import java.util.ArrayList;
import java.util.Scanner;

public class LoanManagementSystem 
{
    private static ArrayList<Donor> donors = new ArrayList<>(); //we  used arraylist due to its dynamic size and static bec main is static and needs to access them 
    private static ArrayList<Client> clients = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); // scanner to read user input
    
    public static void main(String[] args)
    {
        System.out.println("=== CHARITY LOAN SYSTEM ===");
        
        // Load saved balance
        double savedBalance = FileHandler.loadBalance(); // load the balance in case there was from previous run
        if (savedBalance > 0) // check first , in case no balance then there is no need to add to charity balance
        {
            LoanOfficer.addDonation(savedBalance); // after loading it we add it to LoanOfficer charity balance
            System.out.println("Balance: $" + savedBalance); // inform the user with the previous balance
        }
        
        // Main menu loop
        while(true) //infinite loop till  the user enter Exit or 5
        {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Donor");
            System.out.println("2. Client");
            System.out.println("3. Loan");
            System.out.println("4. View Balance & Summary");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            
            int choice = scanner.nextInt(); // variable to read the choice the user picked
            scanner.nextLine();
            
            if (choice == 1) 
            	{
            		donorMenu(); // invoke donorMenu method that contains options for the donor
            	}
            else if (choice == 2) 
            {
            	clientMenu(); // invoke clientMenu method that has several options for client
            }
            else if (choice == 3) 
            {
            	loanMenu(); // invoke loanMenu so client can take loan if he wants
            }
            else if (choice == 4) 
            {
            	viewBalance(); // invoke viewBalance that show the total balance of the charity
            }
            else if (choice == 5)
            {
                FileHandler.saveAll(donors, clients); // save every data belongs to donor or client using file handler
                System.out.println("Goodbye!");
                break; // exit the program
            }
        }
    }
    
    // ------DONOR MENU -------
    private static void donorMenu() // a static method so we can call it in main(static) or use another which is creating objects
    {
        while(true) // infinite loop till user choose 4 to go back to main menu and break this menu
        {
            System.out.println("\n--- DONOR ---");
            System.out.println("1. Register Donor");
            System.out.println("2. Make Donation");
            System.out.println("3. View Donors");
            System.out.println("4. Back");
            System.out.print("Choose: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) 
            {
            	registerDonor(); // invoke register donor method 
            }
            else if (choice == 2) 
            {
            	makeDonation(); //invoke make donation method
            }
            else if (choice == 3) 
            {
            	viewDonors(); // invoke viewDonors method
            }
            else if (choice == 4) 
            {
            	break; // choose to end the loop
            }
        }
    }
    
    private static void registerDonor() 
    {
        System.out.println("\n--- Register Donor ---");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("National ID (14 digits): ");
        String nationalId = scanner.nextLine();
        
        System.out.print("Gender (Male/Female): ");
        String gender = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Address: ");
        String address = scanner.nextLine();
        
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        
        try // here we try to  create donor object
        {
            Donor donor = new Donor(name, age, nationalId, gender, email, address, phone);
            donors.add(donor); // if successful , add to list
            System.out.println(" Donor registered! ID: " + donor.getDonorID());
        } 
        catch (Exception e) // catch any error( exception thrown ) that might appear 
        { // it does not continue if there was an exception
            System.out.println("❌ " + e.getMessage()); // show message for the user and program continues after catch 
        }
    }
    
    private static void makeDonation() // another method used in main / static so we can use it in main(Static)
    {
        if (donors.isEmpty()) // check  if there was a donor registered first 
        {
            System.out.println("❌ No donors!");
            return;
        }
        
        System.out.println("\n--- Make Donation ---");
        
        // step 1:select which donor wants to make a donation 
        System.out.println("\nStep 1: Select Donor");
        for (int i = 0; i < donors.size(); i++) 
        {
            System.out.println((i+1) + ". " + donors.get(i).getName() + " (ID: " + donors.get(i).getDonorID() + ")");
        }
        System.out.print("\nChoose donor: ");
        int donorId = scanner.nextInt(); // the user enter which donor he wants
        scanner.nextLine();
        
        if (donorId < 1 || donorId > donors.size())  // check if user entered wrong id number
        {
            System.out.println("❌ Invalid!");
            return;
        }
        
        // Step 2: Enter Amount the donor want to donate
        System.out.println("\nStep 2: Enter Amount");
        System.out.print("Amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        // Step 3: the donor choose the Payment Method he wants
        System.out.println("\nStep 3: Payment Method");
        System.out.println("1. Credit Card");
        System.out.println("2. Online Bank Transfer");
        System.out.print("Choose payment method: ");
        int paymentMethod = scanner.nextInt(); // scan the choice user entered 
        scanner.nextLine();
        
        try 
        {
            Payment payment;
            
            if (paymentMethod == 1) 
            {
                // Credit Card Payment
                System.out.print("Card Number (16 digits): ");
                String card = scanner.nextLine();
                payment = new CreditCardPayment(amount, card);
                
            } 
            else if (paymentMethod == 2) 
            {
                // Online Bank Transfer
                System.out.print("Bank Name: ");
                String bankName = scanner.nextLine();
                
                System.out.print("Account Number (8-20 digits): ");
                String accountNumber = scanner.nextLine();
                payment = new OnlinePayment(amount, accountNumber, bankName); // create an object from online payment class
                
            } 
            else 
            {
                System.out.println("❌ Invalid payment method!");
                return;
            }
            
            donors.get(donorId-1).makeDonation(payment);
            System.out.println("✅ Donation successful!");
            
        } 
        catch (Exception e) // wait for any exception appears 
        {
            System.out.println("❌ " + e.getMessage());
        }
    }
    
    private static void viewDonors() 
    {
        if (donors.isEmpty()) // check if there is no donors first
        {
            System.out.println("No donors registered.");
            return;
        }
        
        System.out.println("\n=== ALL DONORS ===");
        for (Donor d : donors) // using for each to loop on all the donors (objects) and show each one's data
        {
            System.out.println("\nDonor ID: " + d.getDonorID());
            System.out.println("Name: " + d.getName());
            System.out.println("Age: " + d.getAge());
            System.out.println("Email: " + d.getEmail());
            System.out.println("Phone: " + d.getPhone());
            System.out.printf("Total Donated: $%.2f%n", d.getTotalDonated());
            System.out.println("Donations Made: " + d.getDonationHistory().size());
        }
    }
    
    // ------ CLIENT MENU -------
    private static void clientMenu() 
    {
        while(true) // infinite loop til user choose 4 to go back (end)
        {
            System.out.println("\n--- CLIENT ---");
            System.out.println("1. Register Client");
            System.out.println("2. View All Clients");
            System.out.println("3. View Client Payment Details");
            System.out.println("4. Back");
            System.out.print("Choose: ");
            
            int choice = scanner.nextInt(); // read the user choice
            scanner.nextLine();
            
            if (choice == 1) 
            {
            	registerClient();
            }
            else if (choice == 2) 
            {
            	viewClients();
            }
            else if (choice == 3)
            {
            	viewClientPaymentDetails();
            }
            else if (choice == 4) 
            {
            	break;
            }
        }
    }
    
    private static void registerClient() 
    {
        System.out.println("\n--- Register Client ---");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("National ID (14 digits): ");
        String nationalId = scanner.nextLine();
        
        System.out.print("Gender (Male/Female): ");
        String gender = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Address: ");
        String address = scanner.nextLine();
        
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Monthly Income: $");
        double income = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Employment Status (employed/unemployed/self-employed): ");
        String employment = scanner.nextLine();
        
        System.out.print("Credit Score (300-850): ");
        int score = scanner.nextInt();
        scanner.nextLine();
        
        try // we try to create object 
        {
            Client client = new Client(name, age, nationalId, gender, email, address, phone,
                                      income, employment, score);
            clients.add(client);
            System.out.println("✅ Client registered! ID: " + client.getClientID());
            System.out.println("Poverty Level: " + client.getPovertyLevel());
        } 
        catch (Exception e)  // wait for any exception appears to act
        {
            System.out.println("❌ " + e.getMessage());
        }
    }
    
    private static void viewClients() 
    {
        if (clients.isEmpty())  // check if there is no clients
        {
            System.out.println("No clients");
            return;
        }
        
        System.out.println("\n=== ALL CLIENTS ===");
        for (Client c : clients) // for each that loops on all the clients objects and show their data
        {
            System.out.println("\nID: " + c.getClientID());
            System.out.println("Name: " + c.getName());
            System.out.println("Age: " + c.getAge());
            System.out.println("National ID: " + c.getNationalID());
            System.out.println("Gender: " + c.getGender());
            System.out.println("Email: " + c.getEmail());
            System.out.println("Phone: " + c.getPhone());
            System.out.printf("Monthly Income: $%.2f%n", c.getMonthlyIncome());
            System.out.println("Employment: " + c.getEmploymentStatus());
            System.out.println("Credit Score: " + c.getCreditScore());
            System.out.println("Poverty Level: " + c.getPovertyLevel());
            System.out.println("Loans: " + c.getLoanHistory().size());
            
            if (c.hasActivePaymentPlan()) // this condition check if he already has an active loan
            {
                double balance = c.getCurrentPaymentPlan().getRemainingBalance();
                System.out.printf("Active Loan Balance: $%.2f%n", balance);
            }
            System.out.println("---");
        }
    }
    
    private static void viewClientPaymentDetails() 
    {
        if (clients.isEmpty()) // check if client exist before anything 
        {
            System.out.println("No clients");
            return;
        }
        
        System.out.println("\n=== CLIENT PAYMENT DETAILS ===");
        
        for (int i = 0; i < clients.size(); i++)  // loop using index i as the client place to show all the data
        {
            Client c = clients.get(i);
            System.out.println((i+1) + ". " + c.getName() + " (ID: " + c.getClientID() + ")");
        }
        
        System.out.print("\nSelect client: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        
        if (clientId < 1 || clientId > clients.size()) {
            System.out.println("❌ Invalid!");
            return;
        }
        
        Client selectedClient = clients.get(clientId-1);
        
        System.out.println("\n=== PAYMENT DETAILS FOR " + selectedClient.getName().toUpperCase() + " ===");
        
        // Show basic client info
        selectedClient.displayInfo();
        
        // Check if client has active payment plan
        if (selectedClient.hasActivePaymentPlan()) 
        {
            PaymentPlan plan = selectedClient.getCurrentPaymentPlan(); 
            System.out.println("\n ACTIVE PAYMENT PLAN:");
            plan.displayPaymentPlan();
            
            // Show upcoming payments
            System.out.println("\n PAYMENT SCHEDULE:");
            double monthlyPayment = plan.getMonthlyPayment();
            double remainingBalance = plan.getRemainingBalance();
            int remainingPayments = plan.getRemainingPayments();
            
            System.out.printf("Monthly Payment: $%.2f%n", monthlyPayment);
            System.out.printf("Remaining Balance: $%.2f%n", remainingBalance);
            System.out.println("Remaining Payments: " + remainingPayments);
            
            if (remainingPayments > 0) 
            {
            	 	int paymentsToShow = remainingPayments;
            	    if (remainingPayments > 6)
            	    {
            	        paymentsToShow = 6;
            	    }
            	    
            	    System.out.println("\nNext " + paymentsToShow + " payments:");
            	    for (int i = 1; i <= paymentsToShow; i++)
            	    {
            	        System.out.printf("  Month %d: $%.2f%n", i, monthlyPayment);
            	    }
            	    
            	    if (remainingPayments > 6) 
            	    {
            	        System.out.println("  ... and " + (remainingPayments - 6) + " more payments");
            	    }
            }
            
            // Generate Charity Report if client has a loan
            if (!selectedClient.getLoanHistory().isEmpty())  // check if client has a loan or not before anything
            {
                LoanApplication latestApp = selectedClient.getLoanHistory().get(selectedClient.getLoanHistory().size() - 1);
                
                if (latestApp.hasActiveLoan()) 
                {
                    Loan loan = latestApp.getCurrentLoan();
                    CharityReport report = new CharityReport(loan, plan, selectedClient);
                    
                    System.out.println("\n CHARITY LOAN REPORT:");
                    System.out.println(report.generateReport());
                }
            }
            
        } 
        else 
        {
            System.out.println("\n No active payment plan.");
            System.out.println("Loan History: " + selectedClient.getLoanHistory().size() + " loan(s)");
            
            if (!selectedClient.getLoanHistory().isEmpty()) 
            {
                System.out.println("\n LOAN HISTORY:");
                for (int i = 0; i < selectedClient.getLoanHistory().size(); i++) 
                {
                    LoanApplication app = selectedClient.getLoanHistory().get(i);
                    System.out.println((i+1) + ". " + app.getLoanType() + " Loan - $" + app.getRequestAmount());
                    if (app.hasActiveLoan()) 
                    {
                        System.out.println("   Status: ACTIVE");
                    } 
                    else 
                    {
                        System.out.println("   Status: COMPLETED");
                    }
                }
            }
        }
    }
    
    // ------ LOAN MENU ------
    private static void loanMenu() 
    {
        while(true) 
        {
            System.out.println("\n--- LOAN ---");
            System.out.println("1. Apply for Loan");
            System.out.println("2. Make Repayment");
            System.out.println("3. View Loan Statistics");
            System.out.println("4. Back");
            System.out.print("Choose: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) 
            {
            	applyForLoan();
            }
            else if (choice == 2)
            {
            	makeRepayment();
            }
            else if (choice == 3) 
            {
            	viewLoanStats();
            }
            else if (choice == 4) 
            {
            	break;
            }
        }
    }
    
    private static void applyForLoan() 
    {
        if (clients.isEmpty()) 
        {
            System.out.println("❌ No clients!");
            return;
        }
        
        System.out.println("\n--- Apply for Loan ---");
        
        System.out.println("Select client:");
        for (int i = 0; i < clients.size(); i++) 
        {
            System.out.println((i+1) + ". " + clients.get(i).getName() + " (ID: " + clients.get(i).getClientID() + ")");
        }
        
        System.out.print("\nChoose client: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        
        if (clientId < 1 || clientId > clients.size())
        {
            System.out.println("❌ Invalid!");
            return;
        }
        
        Client client = clients.get(clientId-1);
        
        System.out.print("Loan Type (1=Education, 2=Business): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Amount: $");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        
        try
        {
            LoanApplication app;
            if (type == 1) {
                // EDUCATION LOAN DETAILS
                System.out.println("\n--- Education Loan Details ---");
                System.out.print("Institution Name: ");
                String institution = scanner.nextLine();
                
                System.out.print("Course Name: ");
                String course = scanner.nextLine();
                
                System.out.print("Course Duration (years): ");
                int duration = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("Loan Term (years): ");
                int term = scanner.nextInt();
                scanner.nextLine();
                
                app = new EducationApplication(client, amount, institution, course, duration, term);
            }
            else 
            {
                // BUSINESS LOAN DETAILS
                System.out.println("\n--- Business Loan Details ---");
                System.out.print("Business Name: ");
                String businessName = scanner.nextLine();
                
                System.out.print("Business Type (food/retail/electronics/service/manufacturing): ");
                String businessType = scanner.nextLine();
                
                System.out.print("Years in Operation: ");
                int years = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("Monthly Revenue: $");
                float revenue = scanner.nextFloat();
                scanner.nextLine();
                
                System.out.print("Business Plan Quality (perfect/good/mediocre/poor): ");
                String planQuality = scanner.nextLine();
                
                System.out.print("Business Expenses: $");
                double expenses = scanner.nextDouble();
                scanner.nextLine();
                
                System.out.print("Loan Term (years): ");
                int term = scanner.nextInt();
                scanner.nextLine();
                
                app = new BusinessApplication(client, amount, businessName, businessType, years, revenue, planQuality, expenses, term);
            }
            
            LoanOfficer officer = new LoanOfficer();
            officer.displayApplicationResult(app);
        } 
        catch (Exception e) 
        {
            System.out.println("❌ " + e.getMessage());
        }
    }
    
    private static void makeRepayment() 
    {
        if (clients.isEmpty()) 
        {
            System.out.println("❌ No clients!");
            return;
        }
        
        // Find clients with active loans
        ArrayList<Client> clientsWithLoans = new ArrayList<>();
        for (Client c : clients) 
        {
            if (c.hasActivePaymentPlan()) 
            {
                clientsWithLoans.add(c);
            }
        }
        
        if (clientsWithLoans.isEmpty())
        {
            System.out.println("❌ No active loans!");
            return;
        }
        
        System.out.println("\n--- Make Repayment ---");
        System.out.println("Select client with active loan:");
        
        for (int i = 0; i < clientsWithLoans.size(); i++) 
        {
            Client c = clientsWithLoans.get(i);
            double balance = c.getCurrentPaymentPlan().getRemainingBalance();
            System.out.println((i+1) + ". " + c.getName() + " (ID: " + c.getClientID() + ", Owes: $" + balance + ")");
        }
        System.out.print("\nChoose client: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        
        if (clientId < 1 || clientId > clientsWithLoans.size()) 
        {
            System.out.println("❌ Invalid!");
            return;
        }
        
        Client client = clientsWithLoans.get(clientId-1);
        PaymentPlan plan = client.getCurrentPaymentPlan();
        System.out.println("\n=== PAYMENT DETAILS ===");
        System.out.println("Client: " + client.getName());
        System.out.println("Client ID: " + client.getClientID());
        System.out.printf("Remaining Balance: $%.2f%n", plan.getRemainingBalance());
        System.out.printf("Monthly Payment: $%.2f%n", plan.getMonthlyPayment());
        System.out.println("Payments Made: " + plan.getPaymentsMade());
        System.out.printf("Total Paid So Far: $%.2f%n", plan.getTotalPaid());
        System.out.println("Remaining Payments: " + plan.getRemainingPayments());
        System.out.print("\nEnter Payment Amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        try 
        {
            plan.makePayment(amount);
            
            // Show updated details
            System.out.println("\n PAYMENT SUCCESSFUL!");
            System.out.printf("New Balance: $%.2f%n", plan.getRemainingBalance());
            System.out.printf("Total Paid: $%.2f%n", plan.getTotalPaid());
            System.out.println("Payments Made: " + plan.getPaymentsMade());
            
            if (plan.isFullyPaid()) 
            {
                System.out.println(" LOAN FULLY PAID OFF!");
            }
            
        } 
        catch (Exception e)
        {
            System.out.println("❌ " + e.getMessage());
        }
    }
    
    private static void viewLoanStats() 
    {
        LoanOfficer officer = new LoanOfficer();
        
        System.out.println("\n=== LOAN OFFICER STATISTICS ===");
        System.out.println("Total Applications: " + officer.getTotalCounts());
        System.out.println("Approved: " + officer.getApprovalCounts());
        System.out.println("Rejected: " + officer.getRejectionCounts());
        System.out.printf("Approval Rate: %.1f%%%n", officer.getSuccessRate());
        System.out.printf("Rejection Rate: %.1f%%%n", officer.getFailureRate());
        
        // Count active loans
        int activeLoans = 0;
        double totalOwed = 0;
        for (Client c : clients) 
        {
            if (c.hasActivePaymentPlan()) {
                activeLoans++;
                totalOwed += c.getCurrentPaymentPlan().getRemainingBalance();
            }
        }
        
        System.out.println("\nActive Loans: " + activeLoans);
        System.out.printf("Total Amount Owed: $%.2f%n", totalOwed);
    }
    
    private static void viewBalance() 
    {
        System.out.println("\n CHARITY SYSTEM SUMMARY");
        System.out.println("=============================");
        System.out.printf("Charity Balance: $%.2f%n", LoanOfficer.getCharityBalance());
        System.out.println("Total Donors: " + donors.size());
        System.out.println("Total Clients: " + clients.size());
        
        // Count active loans
        int activeLoans = 0;
        double totalOwed = 0;
        for (Client c : clients) 
        {
            if (c.hasActivePaymentPlan()) 
            {
                activeLoans++;
                totalOwed += c.getCurrentPaymentPlan().getRemainingBalance();
            }
        }
        
        System.out.println("Active Loans: " + activeLoans);
        System.out.printf("Total Amount Owed: $%.2f%n", totalOwed);
        
        // Show all donors
        System.out.println("\n=== DONORS ===");
        for (Donor d : donors)
        {
            System.out.println(d.getName() +" (ID: " + d.getDonorID() + ") - Donated: $" + d.getTotalDonated());
        }
        // Show clients with active loans
        if (activeLoans > 0) 
        {
            System.out.println("\n=== CLIENTS WITH ACTIVE LOANS ===");
            for (Client c : clients)
            {
                if (c.hasActivePaymentPlan()) 
                {
                    double balance = c.getCurrentPaymentPlan().getRemainingBalance();
                    System.out.println(c.getName() +  " (ID: " + c.getClientID() + ") - Owes: $" + balance);
                }
            }
        }
        System.out.println("========================");
    }
}