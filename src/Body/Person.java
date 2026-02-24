
package Body;

public  class Person 
{
	private String name;
	private int age;
	private String nationalId;
	private String gender;
	private String email;
	private String address;
	private String phone;

	public Person(String name,int age,String nationalid,String gender,String email,String address,String phone)
	{
		this.setName(name);
		this.setAge(age);
		this.setNationalID(nationalid);
		this.setGender(gender);
		this.setAddress(address);
		this.setEmail(email);
		this.setPhone(phone);
	}
	public void setName(String name)
	{
		if(name==null)
		{
			throw new IllegalArgumentException("Name Cannot be Null,Try again"); 
		}
		
		name = name.trim();
		
		if(name.isEmpty())
		{
			throw new IllegalArgumentException("Name Cannot be Empty, Try again");
			
		}
		else
		{
			boolean valid =true;
			
			for(int i=0;i<name.length();i++)
			{
				char c=name.charAt(i);
				if(!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' '))
				{
					valid=false;
					break;
				}
			}
			
			if(valid)
			{
				this.name=name;
			}
			else
			{
				throw new IllegalArgumentException("Invalid Name,Try again");
			}
		}
	}
	public String getName()
	{
		return this.name;
	}
	public void setAge(int age)
	{
		if(age>=18 && age <120)
		{
			this.age=age;
		}else 
		{
			throw new IllegalArgumentException(" Age is wrong , try again");
			//Add exception to disallow letters (only numbers);
			
		}
	}
	public int getAge()
	{
		return this.age;
	}
	public void setNationalID(String id)
	{
	
		if(id==null)
		{
			throw new IllegalArgumentException("National ID Cannot be Null,Try Again");
		}
		if(id.isEmpty())
		{
			throw new IllegalArgumentException("National ID Cannot be Empty,Try Again");
		}
		
		if(id.length()!=14)
		{
			throw new IllegalArgumentException("National ID must be 14 Digits,Try again");
		}
		
		boolean valid =true;
		for(int i=0;i<id.length();i++)
		{
			if(!Character.isDigit(id.charAt(i)))
			{
				valid=false;
				break;
			}
		}
		
		if(valid)
		{
			this.nationalId=id;
		}
		else
		{
			throw new IllegalArgumentException("National ID must contain Digits only,Try again");
		}
	}
	public String getNationalID()
	{
		return this.nationalId;
	}
	public void setGender(String gender)
	{
		if(gender==null)
		{
			throw new IllegalArgumentException("Gender Cannot be Null,Try Again");
		}
		
		gender=gender.trim();
		
		if(gender.isEmpty())
		{
			throw new IllegalArgumentException("Gender Cannot be Empty,Try Again");
		}
		
		if( gender.equalsIgnoreCase("Male")|| gender.equalsIgnoreCase("Female"))
		{
			this.gender=gender;
		}
		else
		{
			throw new IllegalArgumentException("Invalid Gender ,Try again");
		}
	}
	public String getGender()
	{
		return this.gender;
	}
	public void setPhone(String phone)
	{
		if(phone==null)
		{
			throw new IllegalArgumentException("Phone Number Cannot be Null");
		}
		if(phone.isEmpty())
		{
			throw new IllegalArgumentException("Phone Number Cannot be Empty");
		}
		if(phone.length()!=11)
		{
			throw new IllegalArgumentException("Phone Number Must be 11 Digits");
		}
		
		boolean valid=true;
		for(int i=0;i<phone.length();i++)
		{
			if(!Character.isDigit(phone.charAt(i)))
			{
				valid=false;
				break;
			}
		}
		if(valid)
		{
				this.phone=phone;
		}
		else
		{
			throw new IllegalArgumentException("Phone Number Must be Digits only, Try again");
		}
	}
	public String getPhone()
	{
		return this.phone;
	}
	public void setEmail(String email) 
	{
		if(email==null)
		{
			throw new IllegalArgumentException("Email Cannot be Null,Try Again");
		}
		
		email=email.trim();
		
		if(email.isEmpty())
		{
			throw new IllegalArgumentException("Email Cannot be Empty,Try Again");
		}
		
		int counter=0;
		for(int i=0;i<email.length();i++)
		{	
			if(email.charAt(i) == '@')
			{
				counter++;
			}
		}
		if(counter!= 1)
		{
			throw new IllegalArgumentException("Email must include one @, Try again");
		}
			
		if(!email.endsWith("@gmail.com"))
		{
			throw new IllegalArgumentException("Email must contain @gmail.com at the end ,Try again");
		}
		
		this.email=email;
		
	}
	public String getEmail()
	{
		return this.email;
	}
	public void setAddress(String address)// not finished (Exception Handling)
	{
		 
		if(address==null)
		{
			throw new IllegalArgumentException("Address Cannot be Null,Try Again");
		}
		
		address=address.trim();
		
		if(address.isEmpty())
		{
			throw new IllegalArgumentException("Address Cannot be Empty,Try Again");
		}
		
		address=address.replaceAll("\\s+"," "); //  (\\s+ ) means one or more space character 
		
		this.address=address;
	}
	public String getAddress()
	{
		return this.address;
	}
	public void displayBasicInfo()
	{
		 System.out.println("Name: " + name);
		 System.out.println("Age: " + age);
		 System.out.println("National ID: " + nationalId);
		 System.out.println("Gender: " + gender);
		 System.out.println("Email: " + email);
		 System.out.println("Phone: " + phone);
		 System.out.println("Address: " + address);
	}
}
