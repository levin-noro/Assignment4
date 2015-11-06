import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInterface{
		private int currentPage; // the page number (P1...P10)
		
		public int currentPage(int page) { // This method is for page navigation. Based on the values of the state variable, call different pages
			currentPage = page;
			return currentPage;
		}
		
		public int changeCurrentPage() {// This method is for page navigation. It should change to current page and show the content.
			Scanner scanner = new Scanner(System.in); 
			switch (currentPage){
		        
				case 1:
		            System.out.println("1.Sign in	P1");
		            System.out.println("2.Sign up"); System.out.println();
		            System.out.print("Choose your option: ");
		        	int page = Integer.parseInt(scanner.next());
		        	if (page == 1) {currentPage(2);}
		        	if (page == 2) {currentPage(3);}
		        	scanner.close();
		        	break;
		            
		        case 2:
		        	System.out.println("Choose your username:"); System.out.println();
		        	String newname = scanner.next()
		        	User newUser = new User(newname);
		        	User.getUsername(newname); // change
		        	System.out.println("Username successfully added		P2");
		        	scanner.close();
		            break;
		        
		        case 3:
		        	System.out.println("Enter your username:"); System.out.println();
		        	String name = scanner.next();
		        	scanner.close();
		        	int found = 0;
		        	InputStream is = new FileInputStream("Users.txt");
		        			BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		        			 String line;

		        			 while ( (line = rd.readLine()) != null ){
		        				 // should we ignore case for usernames?
		        			     if(line.matches(name)){
		        			    	 System.out.println("Hello Mr."+name+"		P3");
		        			    	 found = 1;
		        			    	 break; 
		        			     }
		        			 }
		        			 
		        	if (found == 0) {
		        		currentPage(4);
		        		changeCurrentPage();
		        	}
		        	
		        	currentPage(5);
	        		changeCurrentPage();
		        	
	        		// structure specified in assignment says that pages 3 and 4 are supposed to be separate but it seems like it would make sense to merge them
		            break;
		            
		        case 4:
		        	System.out.println("No Access");
		        	currentPage(1);
	        		changeCurrentPage();
	        		break;
		        	
		        case 5:
		        	
		        	System.out.println("1.View Items By Category");
		        	System.out.println("2.View Shopping Cart");
		        	System.out.println("3.Sign Out"); System.out.println();
		        	
		        	System.out.println("Choose your option:");
		        	System.out.println("							P5");
		        	
		        	switch(Integer.parseInt(scanner.next())) {
		        	
		        	case 1:
		        		currentPage(6);
		        		changeCurrentPage();
		        		break;
		        	
		        	case 2:
		        		currentPage(7);
		        		changeCurrentPage();
		        		break;
		        		
		        	case 3:
		        		currentPage(1);
		        		changeCurrentPage();
		        		break;		        		
		        	}
		        	scanner.close();
		        	break;
		        	
		        case 6:
		        case 7:
		        	System.out.println("1.Readables");
		        	System.out.println("2.Audio"); System.out.println();
		        	System.out.println("Choose your option:"); System.out.println();
		        	System.out.println("Press -1 to return to the previous menu");
		        	// change
		        /*	switch(Integer.parseInt(scanner.next())) {
		        	
		        	case 1:
		        		currentPage(6);
		        		changeCurrentPage();
		        		break;
		        	
		        	case 2:
		        		currentPage(7);
		        		changeCurrentPage();
		        		break;
		        		
		        	case 3:
		        		currentPage(1);
		        		changeCurrentPage();
		        		break;		        		
		        	}
		        	scanner.close();
		        	break;
		        	*/
		        	
		        case 8:
		        
		            
		        default:
		            System.out.println("Default case");
		            break;
		    
		    }
			return changeCurrentPage();              
			   
			
		}
}