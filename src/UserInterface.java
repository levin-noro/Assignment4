import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInterface{
		
		private int currentPage; // the page number (P1...P10)
		
		public UserInterface(int page) {
			currentPage = page;
		}
		
		public int currentPage(int page) { // This method is for page navigation. Based on the values of the state variable, call different pages
			currentPage = page;
			return currentPage;
		}
		
		public int getCurrentPage() {return currentPage;}
		
		public void getReadables() {
			
		}
		
		public void showReadables() {
			
		}
		
		public void getAudioProducts() {
			
		}
		
		public void showAudioProducts() {
			
		}
		
		
		
	
		
		//
		
		/* UserInterface ui = new UserInterface();

			if(ui.getCurrentPage() == 1) {
  			ui.changeCurrentPage(2) */

		public int changeCurrentPage() {// This method is for page navigation. It should change to current page and show the content.
			boolean exitUI = false;
			Scanner scanner = new Scanner(System.in);
			do {
				
				switch (currentPage){
			        
					case 1:
						 
						
			            System.out.println("1.Sign in\n2.Sign up\n\nChoose your option:	");
			            int page = Integer.parseInt(scanner.next());
			           	if (page == 1) {currentPage(3);}
			        	if (page == 2) {currentPage(2);}
			        	break;
			        	
			        	
			            
			        case 2:
			        	
			        	System.out.println("Choose your username:\n"); 
			        	
			        	String newname = scanner.next();
			        	System.out.println("Your name is " + newname);
			        	
				        System.out.println("Added user.");
				        User newUser = new User(newname);
					    
						try {
							newUser.getUsername(newname, 1);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					   	currentPage(1);
					   	break;
			        	
			        	// System.out.println("Username successfully added		P2"); page numbers have to be added to output of getUsername
			        	
			            
			        case 3:
			        	System.out.println("Enter your username:"); System.out.println();
			        	String name = scanner.next();
			        	scanner.close();
			        	exitUI = true;
			        	//User currUser = new User(name);
			        	/*String userExists = currUser.getUsername(name, 2);
			        		        		 
			        	if (userExists == "Hello Mr." + name) {
			        		currentPage(4);
			        		changeCurrentPage();
			        	}
			        	else {
			        		currentPage(1);
			        		changeCurrentPage();
			        	}
			        	*/
			        	break;
			        	
			        
			            
			        default:
			            System.out.println("Default case");
			            break;
			            
			    
			    }
				
				
			}
			while (exitUI == false);
			scanner.close();
			return 0;
				            
			   
			
		}
}

/*		        
 case 3:
			        	System.out.println("Enter your username:"); System.out.println();
			        	String name = scanner.next();
			        	scanner.close();
			        	User currUser = new User();
			        	newUser.getUsername(name, 2);
			        	/*
			        	int found = 0;
			        	InputStream is;
			        	try {
			        		is = new FileInputStream("Users.txt");
			        	} catch (FileNotFoundException e) {
			        		// TODO Auto-generated catch block
			        		e.printStackTrace();
			        	}
			            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			            String line;

			            while ( (line = rd.readLine()) != null ){
			            				 // should we ignore case for usernames?
			            if(line.matches(name)){
			            	System.out.println("Hello Mr."+name+"		P3");
			        		found = 1;
			        		break;}
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
	
	System.out.println("1.Readables");
	System.out.println("2.Audio"); System.out.println();
	System.out.println("Choose your option:"); System.out.println();
	System.out.println("Press -1 to return to the previous menu");
	
	int option = Integer.parseInt(scanner.next());
	scanner.close();
	
	if (option == 1) {currentPage(6); changeCurrentPage();}
	if (option == 2) {currentPage(7); changeCurrentPage();}
	if (option == -1) {currentPage(1); changeCurrentPage();}
	
	break;
	
case 7:
	// view contents of shopping cart. should get method from shoppingcart.java
	System.out.println("Press -1 to go to the previous menu or press 0 to go to checkout.");
	System.out.println("Choose your option:"); System.out.println();
	int op = Integer.parseInt(scanner.next());
	if (op == -1) {currentPage(5); changeCurrentPage();}
	if (op == 0) {currentPage(10); changeCurrentPage();}
	
case 8:
	Readable r = new Readable();
	r.printListInfo();
	System.out.println();
	System.out.println("Choose your option:");
	int read = Integer.parseInt(scanner.next());
	System.out.println("Enter quantity:");
	int readQ = Integer.parseInt(scanner.next()); System.out.println();
	// update quantity variable for this item in eBooks or Books
	// update quantity variable for this item in MP3 or CD
	// get name of option
	System.out.println(readQ + " " + "name");
	
	
case 9:
	// audio
	Audio a = new Audio();
	a.printListInfo();
	System.out.println();
	System.out.println("Choose your option:");
	int aud = Integer.parseInt(scanner.next());
	System.out.println("Enter quantity:");
	int audQ = Integer.parseInt(scanner.next()); System.out.println();
	// update quantity variable for this item in eBooks or Books
	// update quantity variable for this item in MP3 or CD
	// get name of option
	System.out.println(audQ + " " + "name");
	
case 10:
	// checkout
	System.out.println("Choose your option:"); System.out.println();
*/
