import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInterface{
		private int currentPage; // the page number (P1...P10)
		
		public int currentPage(int page) { // This method is for page navigation. Based on the values of the state variable, call different pages
			currentPage = page;
			return currentPage;
		}
		
		public int changeCurrentPage(int currentPage) {// This method is for page navigation. It should change to current page and show the content.
			Scanner scanner = new Scanner(System.in); 
			switch (currentPage){
		        
				case 1:
		            System.out.println("1.Sign in	P1");
		            System.out.println("2.Sign up");
		            System.out.print("Choose your option: ");
		        	int page = Integer.parseInt(scanner.next());
		        	if (page == 1) {currentPage(2);}
		        	if (page == 2) {currentPage(3);}
		        	scanner.close();
		        	break;
		            
		        case 2:
		        	System.out.println("Choose your username:");
		        	User newUser = new User(scanner.next());
		        	getUsername(newUser.username);
		        	System.out.println("Username successfully added");
		        	scanner.close();
		            break;
		        
		        case 3:
		        	System.out.println("Enter your username:");
		        	String name = scanner.next());
		        	// include code to check if username is in the file
		        	InputStream is = //--open an input stream from file--
		        			 BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		        			 String line;

		        			 while ( (line = rd.readLine()) != null ){
		        				 // should we ignore case for usernames?
		        			     if(line.matches(".*TestString.*")){ //--regex of what to search--
		        			         //--do something---
		        			    	 System.out.println("Username successfully added		P2");
		        			    	 break; //--if not want to search further--
		        			     }
		        			 }
		        			 
		        	
		        	scanner.close();
		            break;
		            
		        default:
		            System.out.println("Default case");
		            break;
		    
		    }
			return changeCurrentPage(currentPage);              
			   
			
		}
}