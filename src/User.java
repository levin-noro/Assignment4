import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class User {
	String username;
	// create a class constructor that calls username
	//User (String name) {username = name;} //
	public User(String name) {this.username = name;}
	
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.Scanner;


	public class User {
		String username;
		// create a class constructor that calls username
		//User (String name) {username = name;} //
		public User(String name) {this.username = name;}
		
		public String getUsername(String name, int option) throws IOException { // stores the username.
		// usernames of all registered users are stored in the file Users.txt
			
			
			File inFile = new File("Users.txt");
			
			if (option == 1) 
			{
				
				if (searchFile(inFile, name)) 
				{
					return "Hello Mr." + name;
				}
				else 
				{
					return "No Access";
				}

				
			}
			
			else if (option == 2) 
			{
				String message = writeFile (inFile, name);
				return message;

			}
			
			else {	
				return "Invalid Option";
			}	
			 
		}
		
		private boolean searchFile (File inFile, String name) throws IOException {
			Scanner sc = new Scanner (inFile);
			FileWriter fWriter = new FileWriter (inFile,true);
			PrintWriter pWriter = new PrintWriter (fWriter);
			
			while (sc.hasNextLine())
			{
				String line = sc.nextLine();
				
				if (line.equals(name))  // if user exists
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
			
			if (searchFile(inFile, name)) 
			{
				return "Username already exists";
			}
			
			Scanner sc = new Scanner (inFile);
			FileWriter fWriter = new FileWriter (inFile,true);
			PrintWriter pWriter = new PrintWriter (fWriter);
			
			// Appends name to inFile
			pWriter.println(name);
			
			pWriter.close();
			fWriter.close();
			sc.close();
			
			return "Username successfully added";
		}
	}