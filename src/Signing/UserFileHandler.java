package Signing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public class UserFileHandler {
	
	
	public static void saveUsers(HashMap<String,User> users,String filename)
	{
		try(FileWriter writer=new FileWriter(filename))
		{
			for(User user:users.values())
			{
				writer.write(user.getUserName() + "," +
			user.getPassword() + "," +
						user.getEmail() + "," +
			user.getRole() + "\n");
				
			}
			System.out.println("Users saved to "+filename);
		}catch(IOException e)
		{
			System.out.println("Error saving users: "+e.getMessage());
		}
	}
	
	public static HashMap<String,User> loadUsers(String filename)
	{
		HashMap<String,User> loadedUsers =new HashMap<>();
		File file=new File(filename);
		if(!file.exists())
		{
			System.out.println("No User file found. Starting Fresh");
			return loadedUsers;
		}
		
		try
		{
			Scanner sc=new Scanner(new File(filename));
			
			while(sc.hasNextLine())
			{
				String line=sc.nextLine().trim(); // read and trim line
				
				if(line.isEmpty()) // skip empty lines
				{
					continue;
				}
				String [] parts=line.split(",");
				
				if(parts.length==4)
				{
					String username=parts[0];
					String password = parts[1];
	                String email = parts[2];
	                String role = parts[3];
	                
	                User user=new User(username,password,email,role);
	                
	               loadedUsers.put(username, user);
				}else
				{
					System.out.println("Skipping Invalid line: "+line);
				}
			}
			sc.close();
			System.out.println("Loaded " + loadedUsers.size() + " users from " + filename);
		}catch(FileNotFoundException e)
		{
			System.out.println("User file not found:  "+e.getMessage());
		}catch(IllegalArgumentException e)
		{
			System.out.println("Error in user data:   "+e.getMessage());
		}
		
		return loadedUsers;
	}

}
