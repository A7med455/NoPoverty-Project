package Signing;

public class User {
	
	// private fields 
	private String username;
	private String password;
	private String email;
	private String role;
	
	//Parameterized  constructor
	public User(String username,String password,String email,String role)
	{
		  	setUserName(username);
	        setPassword(password);
	        setEmail(email);
	        setRole(role);
	}
	
	// getters
	public String getUserName()
	{
		return this.username;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getEmail()
	{
		return this.email;
	}
	public String getRole()
	{
		return this.role;
	}
	
	//setters
	
	public void setUserName(String username)
	{
		if(username==null||username.trim().isEmpty())
		{
			throw new IllegalArgumentException("Username Cannot be empty");
		}
		this.username=username.trim();
	}
	public void setPassword(String password)
	{
		if(password==null || password.trim().isEmpty())
		{
			throw new IllegalArgumentException("Password Cannot be empty");
		}
		if(password.length()<6)
		{
			throw new IllegalArgumentException("Password must be at least 6 characters");
		}
		this.password=password.trim();
	}
	public void setEmail(String email)
	{
		if(email==null || email.trim().isEmpty())
		{
			throw new IllegalArgumentException("Email Cannot be empty");
		}
		if(!email.contains("@"))
		{
			throw new IllegalArgumentException("Invalid Email Format");
		}
		this.email=email.trim();
	}
	public void setRole(String role)
	{
		if(role==null ||role.isEmpty())
		{
			throw new IllegalArgumentException("Role Cannot be empty");
		}
		role=role.toLowerCase().trim();
		if(isDonor(role)==false && isClient(role)==false)
		{
			throw new IllegalArgumentException("Role must be either donor or client ");
		}
		this.role=role;
	}
	public boolean checkPassword(String inputpassword)
	{
		if(!password.equals(inputpassword))
		{
			return false;
		}
		return true;
	}
	
	
	
	public static boolean isDonor(String donorRole)
	{
		if(!donorRole.equals("donor"))
		{
			return false;
		}
		return true;
	}
	public static boolean isClient(String ClientRole)
	{
		if(!ClientRole.equals("client"))
		{
			return false;
		}
		return true;
	}
	
	
	
	public boolean isDonor() 
	{
        return "donor".equals(this.role);
    }
    
    public boolean isClient() 
    {
        return "client".equals(this.role);
    } 
	
	
	
	@Override
	public String toString()
	{
		return "User: "+username+" ("+role+")";
	}
	
	

}
