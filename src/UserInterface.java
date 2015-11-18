import java.util.Scanner;
import java.io.IOException;
import java.util.LinkedList;
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
		
		public void makeConfirmID() {
			
		}
		
		public int changeCurrentPage() throws IOException {// This method is for page navigation. It should change to current page and show the content.
			boolean exitUI = false;
			Scanner scanner = new Scanner(System.in);
			User currUser = null;
			ShoppingCart SC = null;
			do {
				
				switch (currentPage){
			        
					case 1:
						
			            System.out.println("1.Sign in\n2.Sign up\n\nChoose your option:	");
			            int page = Integer.parseInt(scanner.next());
			           	if (page == 1) {currentPage(3);}
			        	if (page == 2) {currentPage(2);}
			        	break;
			        	
			        	
			            
			        case 2:
			        	
			        	System.out.println("Choose your username:"); 
			        	String newname = scanner.next();
				        User newUser = new User(newname);
					    
						try {
							String newU = newUser.getUsername(newname, 2);
							System.out.println(newU + "\n");
						} catch (IOException e) {
							e.printStackTrace();
						}
					   	currentPage(1);
					   	break;
			        	
			   
			            
			        case 3:
			        	
			        	System.out.println("Enter your username:");
			        	String name = scanner.next();
			        	currUser = new User(name);
			        	SC = new ShoppingCart(currUser);
						
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
							e.printStackTrace();
						}
			        	break;
			        	
			        case 4:
			        	
			        	System.out.println("1.View Items By Category \n2.View Shopping Cart \n3.Sign Out \n\nChoose your option:");
			        	int op4 = Integer.parseInt(scanner.next());
			        	if (op4 == 1) {currentPage(5);}
			        	if (op4 == 2) {currentPage(6);}
			        	if (op4 == 3) {System.out.println("Signing you out."); exitUI = true; } // currentPage(1);
			        	
			        	break;
			        	
			        case 5:
			        	
			        	System.out.println("1.Readables \n2.Audio \n\nChoose your option:\nPress -1 to return to the previous menu");
			        	int option = Integer.parseInt(scanner.next());
			        	if (option == 1) {currentPage(7);}
			        	if (option == 2) {currentPage(8);}
			        	if (option == -1) {currentPage(4);}
			        	
			        	break;
			        	
			        case 6:
			        	
			        		        	
			        	System.out.print(SC.getContent());  
			        	System.out.println("Press -1 to go to the previous menu or press 0 to go to CheckOut.\nChoose your option:");
			        	int op = Integer.parseInt(scanner.next());
			        	if (op == -1) {currentPage(4);}
			        	if (op == 0) {currentPage(9);}
			        	
			        	break;
			        	
			        case 7:
			        	Readable r = new Readable();
			        	r.printListInfo();
			        	r.getReadableList();
			        	System.out.println();
			        	// ebooks and books are intialized as objects in a linkedlist
			        	System.out.println("Choose your option:");
			        	// user inputs the serial number of the item 	
			        	int read = Integer.parseInt(scanner.next());
			        	
			        	
			        	if (read == -1) {currentPage(5); break;}
			        	ListIterator<Readable> itrR = r.readList.listIterator();
			        	String Rname = null;
			        	Readable currR = null;
			        	// looks through the list of readable items and finds the one with a serial number that matches the one entered by the user
			        	// later include code to handle input of serial number that does not match any in the list
			        	while(itrR.hasNext()) {
			        		currR = itrR.next();
			        		if (currR.sNo == read) {
			        			Rname = currR.title;
			        			break;
			        		}
			        	}
			        	if (Rname.equals(null)) {
			        		System.out.println("Your option was not in the list. Please choose again.");
		        			currentPage(8);
			        	}
			        	System.out.println("Enter quantity:");
			        	int readQ = Integer.parseInt(scanner.next()); System.out.println();
			        	// once the quantity and the serial number have been obtained, the 
			        	// update quantity variable for this item in eBooks or Books
			       
			        	
			        	System.out.println(readQ + " " + Rname);
			        	SC.AddItem(currR, readQ);
			        	// for both case 7 and case 8, once the item is successfully added, the user will be prompted to 
			        	// select another item and to go to the previous menu
			        	System.out.println("Press -2 to continue shopping or press 0 to CheckOut");
			        	int op7 = Integer.parseInt(scanner.next());
			        	if (op7 == -2) {currentPage(5);}
			        	if (op7 == 0) {currentPage(9);}
			        	
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
			        	Audio currA = null;
			        	while(itrA.hasNext()) {
			        		currA = itrA.next();
			        		if (currA.sNo == aud) {
			        			Aname = currA.title;
			        			break;
			        		}
			        	}
			        	if (Aname.equals(null)) {
			        		System.out.println("Your option was not in the list. Please choose again.");
		        			currentPage(8);
			        	}
			        	
			        	System.out.println("Enter quantity:");
			        	int audQ = Integer.parseInt(scanner.next()); System.out.println();
			        	
			        	System.out.println(audQ + " " + Aname);
			        	// update quantity variable for this item in MP3 or CD
			        	SC.AddItem(currA, audQ);
			        	
			        	System.out.println("Press -2 to continue shopping or press 0 to CheckOut");
			        	int op8 = Integer.parseInt(scanner.next());
			        	if (op8 == -2) {currentPage(5);}
			        	if (op8 == 0) {currentPage(9);}
			        	
			        	break;
			        	
			        case 9:
			        	// checkout
			        	// we need to check what happens if the person does not want to pay. do you sign out, or do you go back to a previous menu. 
			        	// check how we're supposed to generate confirmation IDs, do we just increment the value by 1 (so the second user would get an ID of U1001)
			        	
			        	System.out.println("Billing Information:");
			        	System.out.println(String.format("%-30s","Name")+String.format("%-20s", "Quantity") + String.format("%-20s","Price"));
			        	
			        	
			        	LinkedList<Item> list =  SC.cartList;
			        	
			        	ListIterator<Item> iterator = list.listIterator();
			        	
			        	double total = 0;
			        	double total_w_etax = 0;
			        	
			        	while (iterator.hasNext())
			        	{
			        		Item item = iterator.next();

			        		System.out.println(String.format("%-30s", item.title.trim()) + String.format("%-20s", item.quantity) + String.format("%-20s",item.price));
			        		
			        		total += item.price * item.quantity;
			        		total_w_etax += item.getPrice() * item.quantity;
			        		
			        	}
			        	
			        	double etax = total_w_etax - total;
			        	System.out.println(String.format("%-30s","Environment Tax") + String.format("%-20s","2%") + String.format("%-20s", Double.toString(etax)));
			        	
			        	double hst = total * 0.13;
			        	
			        	System.out.println(String.format("%-23s","\tHST")+String.format("%-20s", "13%") + String.format("%-20s",Double.toString(hst)));
			        	
			        	double shipping = total * 0.10;
			        	
			        	System.out.println(String.format("%-23s","\tShipping") + String.format("%-20s", "10%") + String.format("%-20s",Double.toString(shipping)));
			        	
			        	double total_w_alltax = total + etax + hst + shipping;
			        	
			        	System.out.println(String.format("%-23s","\tTotal") + String.format("%-20s", " ") + String.format("%-20s", Double.toString(total_w_alltax) + "$"));
			        	
			        	System.out.println("Are you sure you want to pay? ");
			        	
			        	String P10choice = "";
			        	
			        	while (!P10choice.equals("yes") && !P10choice.equals("no")) 
			        	{
			        		P10choice = scanner.next();
			        	
				        	P10choice = P10choice.toLowerCase();
				        	
				        	if (P10choice.equals("no")) 
				        	{
				        		currentPage(4);
				        	}
				        	else if (P10choice.equals("yes")) 
				        	{
				        		// 1000 to be replaced by generated confirmation ID number
				        		System.out.println("Confirmation ID: U" + 1000);
				        		System.out.println("Items shipped to: Mr." + currUser.username);
				        		exitUI = true;
				        	}
				        	else {
				        		System.out.println("Please answer yes or no:");
				        	}
			        	}
			        	break;
			    }	
			}
			while (exitUI == false);
			scanner.close();
			return 0;
		}
}

