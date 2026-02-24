package Signing;
import java.util.HashMap;
public class AuthManager {
	
	
	private static HashMap<String,User> users=new HashMap<>();
	private static final String USER_FILE="users.txt";
	
	//static initializer 
	static {
		users=UserFileHandler.loadUsers(USER_FILE); // load users from file when program start
	}
	
	//register
	public static boolean register(String username,String password,
			String email,String role)
	{
		if(users.containsKey(username))
		{
			return false; // username taken
		}
		try
		{
		User newUser=new User(username, password, email, role); // create new user
		users.put(username, newUser); // add to hashmap
		
		UserFileHandler.saveUsers(users,USER_FILE); // save to the file
		
		return true; // registration successful
		}catch(IllegalArgumentException e)
		{
			  System.out.println("❌ Registration failed: " + e.getMessage());
	          return false;
		}
		
	}
	
	public static User login(String username, String password)
	{
		if(!users.containsKey(username)) // check if username exists
		{
			return null; // user not found 
		}
		User user=users.get(username);// get user object
		
		if(user.checkPassword(password))
		{
			return user;
		}else
		{
			return null; // wrong password
		}
	}
	
	

}
