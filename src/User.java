import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class User {
	private String username;
	// create a class constructor that calls username
	User (String name) {username = name;} //
	
	public String getUsername(String name, int option) throws IOException { // stores the username.
	// usernames of all registered users are stored in the file Users.txt
		
		File inFile = new File("Users.txt");
		
		if (option == 1) {
			
			if (searchFile(inFile, name)) {
				return "Hello Mr." + name;
			}
			else {
				return "No Access";
			}

			
		}
		else if (option == 2) {
			// add case where user name already exists
			//pWriter.println(name);
			String message = writeFile (inFile, name);
//			pWriter.close();
//			fWriter.close();
//			sc.close();
			return message;
		}
		
		else
		{	
			return "Invalid Option";
		}	
		// Sign-up	
			// return username successfully added
			
		
			
				
			// else
				// return access
		
		 
		 
	}
	
	private boolean searchFile (File inFile, String name) throws IOException {
		Scanner sc = new Scanner (inFile);
		FileWriter fWriter = new FileWriter (inFile,true);
		PrintWriter pWriter = new PrintWriter (fWriter);
		
		while (sc.hasNextLine())
		{
			String line = sc.nextLine();
			
			if (line.equals(name)) // if user exists
					{
										    
					    pWriter.close();
					    fWriter.close();
					    sc.close();
					    return true; // return Hello <user>
						
					}
		
		}
		pWriter.close();
		fWriter.close();
		sc.close();
		return false;
	}
	
	private String writeFile (File inFile, String name ) throws IOException {
		
		if (searchFile(inFile, name)) {
			return "Username already exists";
		}
		
		Scanner sc = new Scanner (inFile);
		FileWriter fWriter = new FileWriter (inFile,true);
		PrintWriter pWriter = new PrintWriter (fWriter);
		
		
		pWriter.println(name);
		
		pWriter.close();
		fWriter.close();
		sc.close();
		return "Username successfully added";
	}
}
