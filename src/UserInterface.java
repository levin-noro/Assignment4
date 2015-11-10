import java.util.Scanner;
import java.io.IOException;
import java.util.ListIterator;

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
				        User newUser = new User(newname);
					    
						try {
							String newU = newUser.getUsername(newname, 2);
							System.out.println(newU + "\n");
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
			        	
			        	
			        	User currUser = new User(name);
			        	ShoppingCart SC = new ShoppingCart(currUser.username);
						
						try {
							String login = currUser.getUsername(name, 1);
							System.out.println(login + "\n");
							if (login.equals("Hello Mr." + name)) {
								currentPage(4);
							}
							else if (login.equals("No Access")) {
								currentPage(1);
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	   		 
						
			            break;
			        	
			        case 4:
			        	
			        	System.out.println("1.View Items By Category \n2.View Shopping Cart \n3.Sign Out \n\nChoose your option: \n\n ");
			        	
			        	switch(Integer.parseInt(scanner.next())) {
			        	
			        	case 1:
			        		currentPage(5);
			        		break;
			        	
			        	case 2:
			        		currentPage(6);
			        		break;
			        		
			        	case 3:
			        		currentPage(1);
			           		break;		        		
			        	}
			        	
			        	break;
			        	
			        case 5:
			        	
			        	System.out.println("1.Readables \n2.Audio \n\nChoose your option:\n\nPress -1 to return to the previous menu");
			        	
			        	int option = Integer.parseInt(scanner.next());
			        	
			        	if (option == 1) {currentPage(7);}
			        	if (option == 2) {currentPage(8);}
			        	if (option == -1) {currentPage(1);}
			        	
			        	break;
			        	
			        case 6:
			        	// view contents of shopping cart. should get method from shoppingcart.java
			        	// should be in the getContent method of ShoppingCart?
			        	// perhaps shopping cart contents of cart in a 2D array? 1st element could contain object, 2nd element could contain date accessed
			        	
			        	// SC.getContent();  
			        	
			        	System.out.println("Press -1 to go to the previous menu or press 0 to go to checkout.");
			        	System.out.println("Choose your option:"); System.out.println();
			        	int op = Integer.parseInt(scanner.next());
			        	if (op == -1) {currentPage(5);}
			        	if (op == 0) {currentPage(9);}
			        	break;
			        	
			        case 7:
			        	Readable r = new Readable();
			        	r.printListInfo();
			        	r.getReadableList();
			        	System.out.println();
			        	// ebooks and books should be initialized as objects into an array
			        	// this can be done by a function in readable 
			        	System.out.println("Choose your option:");
			        	// read gets the serial number of the readable item. 	
			        	int read = Integer.parseInt(scanner.next());
			        	
			        	
			        	if (read == -1) {currentPage(5); break;}
			        	ListIterator<Readable> itr = r.readList.listIterator();
			        	String Rname = null;
			        	// looks through the list of readable items and finds the one with a serial number that matches the one entered by the user
			        	// later include code to handle input of serial number that does not match any in the list
			        	while(itr.hasNext()) {
			        		Readable currR = itr.next();
			        		if (currR.sNo == read) {
			        			Rname = currR.title;
			        			// want to include something here to add the item to the shopping cart	
			        			break;
			        		
			        		}
			        	}
			        	System.out.println("Enter quantity:");
			        	int readQ = Integer.parseInt(scanner.next()); System.out.println();
			        	// once the quantity and the serial number have been obtained, the 
			        	// update quantity variable for this item in eBooks or Books
			        	// update quantity variable for this item in MP3 or CD
			        	
			        	System.out.println(readQ + " " + Rname);
			        	// for both case 7 and case 8, once the item is successfully added, the user will be prompted to 
			        	// select another item and to go to the previous menu
			        	exitUI = true;
			        	break;
			        	
			        	
			        case 8:
			        	// audio
			        	Audio a = new Audio();
			        	a.printListInfo();
			        	a.getAudioList();
			        	System.out.println();
			        	System.out.println("Choose your option:");
			        
			        	int aud = Integer.parseInt(scanner.next());
			        	if (aud == -1) {currentPage(5); break;}
			        	ListIterator<Audio> itrA = a.audList.listIterator();
			        	String Aname = null;
			        	// looks through the list of readable items and finds the one with a serial number that matches the one entered by the user
			        	
			        	while(itrA.hasNext()) {
			        		Audio currA = itrA.next();
			        		if (currA.sNo == aud) {
			        			Aname = currA.title;
			        			break;
			        		// also want to include something here to add the item to the shopping cart	
			        		}
			        		else {
			        			System.out.println("Your option was not in the list. Please choose again.");
			        			aud = Integer.parseInt(scanner.next());
			        		}
			        	}
			        	System.out.println("Enter quantity:");
			        	int audQ = Integer.parseInt(scanner.next()); System.out.println();
			        	// update quantity variable for this item in eBooks or Books
			        	// update quantity variable for this item in MP3 or CD
			        	// get name of option
			        	System.out.println(audQ + " " + Aname);
			        	
			        	exitUI = true;
			        	break;
			        	
			        case 10:
			        	// checkout
			        	System.out.println("Choose your option:"); System.out.println();
			        	exitUI = true;
			        	
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
			        	

	

	

	

	

*/
