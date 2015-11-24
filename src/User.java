import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Name: Magdalena Karski, Alla Abramova, Levin Noronha
 * MacID: karskim, abramova, noronl
 * Student Number: 001436728, 400039290, 001408964
 * Description: This class contains methods to manage new and existing users.   
 * */

public class User {
	String username; // each user's name is assigned to this string 
	// create a class constructor that calls username
	public User(String name) {this.username = name;} // class constructor, creates a new user, intializes the username to the string given to the constructor
	
	public User() {};
	public String getUsername(String name, int option) throws IOException { // checks if the username already exists, if not it stores the username 
	// usernames of all registered users are stored in the file Users.txt
		
		
		File inFile = new File("Users.txt"); // new file object to access content of Users.txt
		
		if (option == 1) // option 1 is "Sign in" from UserInterface
		{	
			if (searchFile(inFile, name)) // if the name is found in Users.txt by searchfile
			{
				return "Hello Mr." + name; // return Hello + the username
			}
			else 
			{
				return "No Access"; // If the name is not found return "No Access"
			}
		}
		
		else if (option == 2) // If option 2 ("Sign Up") is selected
		{
			String message = writeFile (inFile, name); // add the User's name to Users.txt
			return message; // return the output of writeFile (User successfully added)
		}
		
		else {	
			return "Invalid Option"; // if option 1 or 2 are no selected return "Invalid option"
		}	
	}
	
	private boolean searchFile (File inFile, String name) throws IOException { // This file searches through Users.txt to check if the User exists
		Scanner sc = new Scanner (inFile); // initializes a new scanner for the file passed to the function
		FileWriter fWriter = new FileWriter (inFile,true); // intializes a file writer to for the file
		PrintWriter pWriter = new PrintWriter (fWriter); // initializes a printwriter for the file
		
		while (sc.hasNextLine()) // while the scanner is still able to access a line in the file
		{
			String line = sc.nextLine(); // intialize the string line to the next line in the file
			
			if (line.equals(name))  // if user exists
			{		
					    pWriter.close(); // close the printwriter
					    fWriter.close(); // close the file writer
					    sc.close(); // close the scanner
					    return true; // return Hello <user>
			}
		}
		pWriter.close(); // close the printwriter
		fWriter.close(); // close the file writer
		sc.close(); // close the scanner
		return false; // return false (that the user was not found in Users.txt)
	}
	
	private String writeFile (File inFile, String name ) throws IOException { // This function adds Users to the file Users.txt
		
		if (searchFile(inFile, name))  // If the User already exists
		{
			return "Username already exists"; // return that a user with the specified name already exists in Users.txt
		}
		
		Scanner sc = new Scanner (inFile); // open a new scanner for Users.txt
		FileWriter fWriter = new FileWriter (inFile,true); // open a new filewriter for the file
		PrintWriter pWriter = new PrintWriter (fWriter); // open a new printwriter for the file
		
		// Appends name to inFile
		pWriter.println(name);
		
		pWriter.close(); // close the printwriter
		fWriter.close(); // close the filewriter
		sc.close(); // close the scanner
		
		return "Username successfully added"; // return that the username was successfully appended to Users.txt
	}
}