package Body;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    
    // ============================================
    // 1. SAVE DONORS TO FILE
    // ============================================
    public static void saveDonors(ArrayList<Donor> donors)
    {
        // FIRST: Check if donors list is valid (if-else check)
        if (donors == null) 
        {
            System.out.println(" Error: Cannot save : donors list is null");
            return;  // Stop here
        }
        
        // SECOND: Try to write to file (need try-catch for file errors)
        try 
        {
            FileWriter writer = new FileWriter("donors.txt");
            
            for (Donor donor : donors) 
            {
                // Check each donor before saving
                if (donor == null)
                {
                    System.out.println(" Warning: Found null donor, skipping");
                    continue;  // Skip to next donor
                }
                
                // Write donor data: Name, ID, TotalDonated
                writer.write(donor.getName() + "," + donor.getDonorID() + "," + donor.getTotalDonated() + "\n");
            }
            
            writer.close();  // IMPORTANT: Close the file
            System.out.println(" Donors saved to donors.txt");
            
        } 
        catch (IOException e) 
        {
            // File error 
            System.out.println(" System error: Could not save donors");
            System.out.println("   Reason: " + e.getMessage()); // show error type
        }
    }
    
    // ============================================
    // 2. SAVE CLIENTS TO FILE
    // ============================================
    public static void saveClients(ArrayList<Client> clients)
    {
        // Check if clients list is valid
        if (clients == null) 
        {
            System.out.println(" Error: Cannot save : clients list is null");
            return;
        }
        
        try 
        {
            FileWriter writer = new FileWriter("clients.txt");
            
            for (Client client : clients) 
            {
                if (client == null)
                {
                    System.out.println(" Warning: Found null client, skipping");
                    continue;
                }
                
                // Write: Name, ID, MonthlyIncome, CreditScore
                writer.write(client.getName() + "," + client.getClientID() + "," + client.getMonthlyIncome() + "," + client.getCreditScore() + "\n");
            }
            
            writer.close();
            System.out.println(" Clients saved to clients.txt");
            
        } 
        catch (IOException e) 
        {
            System.out.println(" System error: Could not save clients");
            System.out.println("   Reason: " + e.getMessage());
        }
    }
    
    // ============================================
    // 3. SAVE CHARITY BALANCE
    // ============================================
    public static void saveBalance()
    {
        try
        {
            FileWriter writer = new FileWriter("balance.txt");
            
            // Get current charity balance from LoanOfficer
            double balance = LoanOfficer.getCharityBalance();
            
            // Write just the number
            writer.write(String.valueOf(balance));
            
            writer.close();
            System.out.println(" Charity balance saved: $" + balance);
            
        } 
        catch (IOException e) 
        {
            System.out.println(" System error: Could not save balance");
            System.out.println("   Reason: " + e.getMessage());
        }
    }
    
    // ============================================
    // 4. LOAD CHARITY BALANCE (MOST IMPORTANT!)
    // ============================================
    public static double loadBalance() 
    {
        // Check if file exists first
        File file = new File("balance.txt");
        
        if (!file.exists()) 
        {
            System.out.println(" No balance file found. Starting with $0.");
            return 0.0;  // First time running program
        }
        
        try 
        {
            FileReader reader = new FileReader("balance.txt");
            BufferedReader br = new BufferedReader(reader);
            
            // Read the balance (one line in file)
            String line = br.readLine();
            
            br.close();  // Close the file
            
            if (line != null && !line.trim().isEmpty()) 
            {
                // Convert text to number: "15000.0" → 15000.0
                return Double.parseDouble(line.trim());
            }
            
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println(" Balance file not found. Starting with $0.");
        } 
        catch (IOException e) 
        {
            System.out.println(" Error reading balance file: " + e.getMessage());
        } 
        catch (NumberFormatException e) 
        {
            System.out.println(" Balance file corrupted. Starting with $0.");
        }
        
        return 0.0;  // Default value if anything goes wrong
    }
    
    // ============================================
    // 5. SAVE ALL DATA (MAIN METHOD)
    // ============================================
    public static void saveAll(ArrayList<Donor> donors, ArrayList<Client> clients)
    {
        System.out.println("\n Saving all program data...");
        
        // Save donors (if list exists)
        if (donors != null)
        {
            saveDonors(donors);
        } 
        else
        {
            System.out.println(" Skipping donors: list is null");
        }
        
        // Save clients (if list exists)
        if (clients != null) 
        {
            saveClients(clients);
        } else {
            System.out.println(" Skipping clients: list is null");
        }
        
        // Always save balance
        saveBalance();
        
        System.out.println(" All data saved successfully!\n");
    }
}