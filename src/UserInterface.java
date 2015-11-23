import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.File;



/* The UserInterface class contains methods that allow the user to navigate between pages and to generate a confirmation ID when a sale is processed*/
public class UserInterface{
		
		private int currentPage; // the page number (P1...P10)
		
		public UserInterface(int page) { // class constructor for Userinterface
			currentPage = page; // initializes currentPage to page
		}
		
		public int currentPage(int page) { // This method is for page navigation. Based on the values of the state variable, call different pages
			currentPage = page; // currentPage is changed to page
			return currentPage; // the currentPage is returned
		}
		
		public int getCurrentPage() {return currentPage;} // This function tells you what the currentpage is
		
		public int makeConfirmID() throws IOException { // This file generates new confirmation IDs for the checkout page
			
			File file = new File("ItemsBought.txt"); // initializes a new file object to access the contents of ItemsBought.txt
			int confirmID = 1000; // initialize the confirmation ID to 
			if (!file.exists()) { // if the file does not exist 
				PrintWriter writer = new PrintWriter("ItemsBought.txt", "UTF-8"); // create a new printwriter and a new file 
				writer.println("U" + confirmID + ","); // append the confirmation ID to the file
				writer.close(); // close the writer
			}
			else { // if the file exists
				Scanner sc; // declare a scanner
				
				String confirmStr; // declare a new string that will be assigned to the confirmation number
				String line; // declare a new string that will be assigned to the line in the file
		
					sc = new Scanner (file); // initialize the scanner to ItemsBought.txt
					sc.nextLine(); // this skips first line in ItemsBought.txt containing useless header info
					while (sc.hasNextLine()) { // while the scanner is still reading lines in the file
						
							line = sc.nextLine(); // assign the next line in the file to line
							String[] words = line.split(","); // split each line in ItemBought.txt
							
							confirmStr = words[0]; // extract first element
							confirmStr = confirmStr.substring(1); // remove U from ID
							confirmID = Integer.parseInt(confirmStr); // convert ID to integer
							confirmID = confirmID + 1; // increment

					}
					sc.close(); // close the scanner
					
					if (confirmID != 1000) { // Prints all confirmID greater than 1000
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ItemsBought.txt", true))); // create a new printwriter for the file to append
							out.println("U"+confirmID+","); // append the new confirmation ID
							out.close(); // close the printwriter
					} else { // prints first confirmID
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ItemsBought.txt", true))); // create a new printwriter for the file to append the confirmationID
						out.println("U1000,"); // append U1000 to the file 
						out.close(); // close the printwriter
					}
		
				
			}
			return confirmID; // return the confirmationID
			
		}
		
		public static boolean isInteger(String s) { // checks if the input is an integer
		    try { 
		        Integer.parseInt(s);  // try to parse the string into an integer
		    } catch(NumberFormatException e) { // if the string does not have the appropriate format to be converted to a numerical type
		        return false; // return false - that the string cannot be converted to an integer
		    } catch(NullPointerException e) { // if the string s is set to null
		        return false; // return false - that the string cannot be converted to an integer
		    }
		    
		    return true; // if the string can be parsed into an integer, return true
		}
		
		public int changeCurrentPage() throws IOException {// This method is for page navigation. It should change to current page and show the content.
			boolean exitUI = false; // exitUI is intially set to false
			Scanner scanner = new Scanner(System.in); // a new scanner is opened that will take user input
			User currUser = null; // a new User is created and initialized to null
			ShoppingCart SC = null; // a new Shoppingcart object is created and initialized to null
			do { // do this (while exitUI is false)
				
				switch (currentPage){ // switches between pages in the User Interface based on the value of currentPage
			        
					case 1: // Page 1
						
			            System.out.println("1.Sign in\n2.Sign up\n\nChoose your option:	"); // The options for the user are printer to the console, sign in or sign up
			        	String choice1 = ""; // choice1 is initialized to an empty string 
			        	while (!isInteger(choice1) && !choice1.equals("1") && !choice1.equals("2")) // while the user has not entered a correct option
			        	{
			        		choice1 = scanner.next(); // assign next user input to scanner 
			        		if (choice1.equals("1")) {currentPage(3);} // if the user chooses 1, navigate to page 3
				        	else if (choice1.equals("2")) {currentPage(2);} // if the user chooses 2, go to page 2
				        	else { // if the user has not selected a correct option
				        		System.out.println("Please choose 1 or 2:"); // ask them to choose again
				        	}
			        	}
			        	
			        	System.out.println("\n\n\n"); break; // print newlines to separate this page from next page and break
			        	
			        	
			            
			        case 2: // Page 2
			        	
			        	System.out.println("Choose your username:"); // Prompts the user to choose a username
			        	String newname = scanner.next(); // newname will be assigned to the user's input
				        User newUser = new User(newname); // a new user will be initialized to the user's input
					    
						try { // try this if the file Users.txt can be accessed
							String newU = newUser.getUsername(newname, 2);
							System.out.println(newU + "\n"); // print the results of getUsername (username successfully added or user already exists) 
						} catch (IOException e) { // if the file cannot be accessed 
							e.printStackTrace(); // prints error message that specifies where the error took place
						}
					   	currentPage(1); // redirect user back to the first page
					   	System.out.println("\n\n\n"); break; // newlines to separate from next page and break
			        	
			   
			            
			        case 3: // Page 3 and 4 from the original OOP design have been combined
			        	
			        	System.out.println("Enter your username:"); // Prompts the user to enter their username to sign in
			        	String name = scanner.next(); // name is assigned to the user input
			        	currUser = new User(name); // new user is initialized with the user input
			        	SC = new ShoppingCart(currUser); // a new shopping cart is initialized for the user
						
						try { // try this if Users.txt can be accessed
							/* There should be another else if statement that checks if the user is ADMIN. If so it takes the user to a secret page that only
							 * the admin can see (page 10, so we don't have to change the numbers in all the other calls to currentPage)
							 * Page 10 could contain options for the admin*/
							String login = currUser.getUsername(name, 1); // call getUsername with the user's name and option 1 (sign in). Assign output to login
							System.out.println(login + "\n"); // print login (either Hello Mr. username, if the user was found, or No Access, if the username does not exist)
							if (login.equals("Hello Mr." + name)) { // if the user was found
								currentPage(4); // navigate to page 4
							}
							else if (login.equals("No Access")) { // if the username was not found
								currentPage(1); // redirect to first page
							}
							
						} catch (IOException e) { // if Users.txt could not be accessed
							e.printStackTrace(); // print an error message that indicates where the error took place
						}
						System.out.println("\n\n\n"); break; // print newlines to separate each page and break
			        	
			        case 4: // Page 4 (Page 5 in OOP design)
			        	
			        	System.out.println("1.View Items By Category \n2.View Shopping Cart \n3.Sign Out \n\nChoose your option:"); // Prompts the user to choose between viewing Items by category, to view their shopping case, or to sign out
	
			        	
			        	String choice4 = ""; // choice4 is initialized to an empty string 
			        	while (!isInteger(choice4) && !choice4.equals("1") && !choice4.equals("2") && !choice4.equals("3")) // while the user has not entered a correct option
			        	{
			        		choice4 = scanner.next(); // assign next user input to scanner 
			        		if (choice4.equals("1")) {currentPage(5);} // if the user chooses 1, navigate to page 5
				        	else if (choice4.equals("2")) {currentPage(6);} // if the user chooses 2, go to page 6
				        	else if (choice4.equals("3")) {System.out.println("Signing you out."); exitUI = true;} // If third option is chosen tell the user that they are being signed out and set exitUI to true to leave the user
				        	else { // if the user has not selected a correct option
				        		System.out.println("Please choose 1 or 2 or 3:"); // ask them to choose again
				        	}
			        	}
			        	System.out.println("\n\n\n"); break; // print newlines to separate pages and break
			        	
			        case 5: // Page 5 (Page 6 in OOP design)
			        	
			        	System.out.println("1.Readables \n2.Audio \n\nChoose your option:\nPress -1 to return to the previous menu"); // Prompts the user to either view readable or audio items or to return to the previous menu
			        	String choice5 = ""; // choice5 is initialized to an empty string 
			        	while (!isInteger(choice5) && !choice5.equals("1") && !choice5.equals("2") && !choice5.equals("-1")) // while the user has not entered a correct option
			        	{
			        		choice5 = scanner.next(); // assign next user input to scanner 
			        		if (choice5.equals("1")) {currentPage(7);} // if the user chooses 1, navigate to page 7
				        	else if (choice5.equals("2")) {currentPage(8);} // if the user chooses 2, go to page 8
				        	else if (choice5.equals("-1")) {currentPage(4);} // if the user chooses -1, go to page 4
				        	else { // if the user has not selected a correct option
				        		System.out.println("Please choose 1 or 2 or -1:"); // ask them to choose again
				        	}
			        	}
			        	System.out.println("\n\n\n"); break; // print newlines to separate pages and break
			        	
			        case 6: // Page 6 (7 in OOP design)
			        	
			        		        	
			        	System.out.print(SC.getContent()); // shoppingcart method getContent is accessed through shoppingcart object. Displays contents of shoppingcart  
			        	System.out.println("Press -1 to go to the previous menu or press 0 to go to CheckOut.\nChoose your option:"); // tells the user that they can go back to the previous page or to go to checkout
			        	String choice6 = ""; // choice6 is initialized to an empty string 
			        	while (!isInteger(choice6) && !choice6.equals("-1") && !choice6.equals("0")) // while the user has not entered a correct option
			        	{
			        		choice6 = scanner.next(); // assign next user input to scanner 
			        		if (choice6.equals("-1")) {currentPage(4);} // if the user chooses -1, navigate to page 4
				        	else if (choice6.equals("0")) {currentPage(9);} // if the user chooses to checkout, go to page 9
				        	else { // if the user has not selected a correct option
				        		System.out.println("Please choose -1 or 0:"); // ask them to choose again
				        	}
			        	}
			        	System.out.println("\n\n\n"); break; // print newlines to separate pages and break
			        	
			        case 7: // Page 7
			        	Readable r = new Readable(); // create a new readable object
			        	r.printListInfo(); // displays all readable objects
			        	r.getReadableList(); // gets a linkedlist of readable items
			        	System.out.println(); // prints a newline
			        	// ebooks and books are intialized as objects in a linkedlist
			        	System.out.println("Choose your option: \n\nPress -1 to return to previous menu"); // prompts the user to choose which item they want
			        	// user inputs the serial number of the item 	
			        	String Roption = ""; // Roption is set to the empty string
			        	String Rname = "not found"; // new string is initialized and set to null
			        	Readable currR = null; // a new readable object is set to null
			        	while (!isInteger(Roption) || Rname.equals("not found")) // while the user has not entered a correct option
			        	{
			        		Roption = scanner.next(); // assign next user input to scanner 
			        		
			        		if (isInteger(Roption)) { // if the option is an integer
			        			if (Roption == "-1") {Rname = ""; currentPage(5); break;} // if -1 is chosen the user is redirected to previous page (5)
			        			for (Readable read : r.readList) { // for each readable item in the readList
			        			
			        				if (read.sNo == Integer.parseInt(Roption)) { // if the serial number of the readable object is equal to the option given to the user
			        					currR = read; // the current readable object is set to currR
			        					Rname = read.title; // the title of the item is set to Rname
				        				
			        				}
				        		}
				        	}
			        		if (!isInteger(Roption) || Rname.equals("not found")) { // if the item was not found in the list
				        		System.out.println("Please choose one of the serial numbers in the list."); // tell the user that the item was not found and prompts them to choose again
				        		
				        	}
			        	}
			        	
			        
			        	// looks through the list of readable items and finds the one with a serial number that matches the one entered by the user
			        	// later include code to handle input of serial number that does not match any in the list
			        	
			        	
			        	System.out.println("Enter quantity:"); // if the item was found, the user is asked to choose how much of the item they want to buy
			        	String rquantity = "";
			        	while (!isInteger(rquantity)) // while the user has not entered a correct option
			        	{
			        		rquantity = scanner.next(); // assign next user input to scanner 
			        		if (!isInteger(rquantity)) {System.out.println("Please choose an integer value as a quantity:");} // ask them to choose again
			        	}
			        	int readQ = Integer.parseInt(rquantity); System.out.println(); // their option is assigned to readQ and a newline is printed
			        	// once the quantity and the serial number have been obtained, the 
			        	// update quantity variable for this item in eBooks or Books
			       
			        	
			        	System.out.println(readQ + " " + Rname); // display the quantity of the selected item and the title of the item
			        	SC.AddItem(currR, readQ); // add the item to the shoppingcart. If the item already exists, increment its quantity. Decrement the quantity in ebooks.txt or books.txt.
			        	// for both case 7 and case 8, once the item is successfully added, the user will be prompted to 
			        	// select another item and to go to the previous menu
			        	System.out.println("Press -2 to continue shopping or press 0 to CheckOut"); // tell the user to choose -2 to continue or - to checkout
			        	String choiceR = ""; // choiceR is initialized to an empty string 
			        	while (!isInteger(choiceR) && !choiceR.equals("-2") && !choiceR.equals("0")) // while the user has not entered a correct option
			        	{
			        		choiceR = scanner.next(); // assign next user input to scanner 
			        		if (choiceR.equals("-2")) {currentPage(5);} // if the user chooses -2, navigate to page 5
				        	else if (choiceR.equals("0")) {currentPage(9);} // if the user chooses to checkout, go to page 9
				        	else { // if the user has not selected a correct option
				        		System.out.println("Please choose -2 or 0:"); // ask them to choose again
				        	}
			        	}
			        	System.out.println("\n\n\n"); break; // print newlines to separate pages and break
			        	
			        	
			        case 8: // page 8
			      
			        	Audio a = new Audio(); // initializes a new Audio object
			        	a.printListInfo(); // displays all audio items
			        	a.getAudioList(); // gets a linkedlist of audio items
			        	System.out.println(); // prints a newline
			        	System.out.println("Choose your option: \n\nPress -1 to return to previous menu"); // prompts the user to make a selection
			        	String Aoption = ""; // String Aoption set to an empty string
			        	String Aname = "not found"; // new string is initialized and set to null
			        	Audio currA = null; // new audio object set to null
			        	while (!isInteger(Aoption) || Aname.equals("not found")) // while the user has not entered a correct option
			        	{
			        		Aoption = scanner.next(); // assign next user input to scanner 
			        		
			        		if (isInteger(Aoption)) { // if the option is an integer
			        			if (Aoption == "-1") {Aname = ""; currentPage(5); break;} // if -1 is chosen the user is redirected to previous page (5)
			        			for (Audio aud : a.audList) {
			        			
			        				if (aud.sNo == Integer.parseInt(Aoption)) { // if the serial number of the audio object is equal to the option given to the user
			        					currA = aud; // the current audio object is set to currA
			        					Aname = aud.title; // the title of the item is set to Aname
				        				
			        				}
				        		}
				        	}
			        		if (!isInteger(Aoption) || Aname.equals("not found")) { // if the item was not found in the list
				        		System.out.println("Please choose one of the serial numbers in the list."); // tell the user that the item was not found and prompts them to choose again
				        		
				        	}
			        	}
			        	
			        	System.out.println("Enter quantity:"); // if the user's option was in the list ask them how much of the item they want to buy
			        	String aquantity = "";
			        	while (!isInteger(aquantity)) // while the user has not entered a correct option
			        	{
			        		aquantity = scanner.next(); // assign next user input to scanner 
			        		if (!isInteger(aquantity)) {System.out.println("Please choose an integer value as a quantity:");} // ask them to choose again
			        	}
			        	
			        	int audQ = Integer.parseInt(aquantity); System.out.println(); // assign their option to audQ and print a newline
			        	
			        	System.out.println(audQ + " " + Aname); // display the amount the user selected and the title of the item
			        	// update quantity variable for this item in MP3 or CD
			        	SC.AddItem(currA, audQ); // adds item to the shopping cart, or increments the quantity of the item if it already existsd, decrements the quantity in CD.txt and MP3.txt
			        	
			        	System.out.println("Press -2 to continue shopping or press 0 to CheckOut"); // tells the user to select -2 to continue shopping or 0 to checkout
			        	String choiceA = ""; // choiceA is initialized to an empty string 
			        	while (!isInteger(choiceA) && !choiceA.equals("-2") && !choiceA.equals("0")) // while the user has not entered a correct option
			        	{
			        		choiceA = scanner.next(); // assign next user input to scanner 
			        		if (choiceA.equals("-2")) {currentPage(5);} // if the user chooses -2, navigate to page 5
				        	else if (choiceA.equals("0")) {currentPage(9);} // if the user chooses to checkout, go to page 9
				        	else { // if the user has not selected a correct option
				        		System.out.println("Please choose -2 or 0:"); // ask them to choose again
				        	}
			        	}
			        	
			        	System.out.println("\n\n\n"); break; // print newlines to separate pages and break
			        	
			        case 9: // page 9
			        	// checkout
			        	// we need to check what happens if the person does not want to pay. do you sign out, or do you go back to a previous menu. 
			        	// check how we're supposed to generate confirmation IDs, do we just increment the value by 1 (so the second user would get an ID of U1001)
			        	
			        	System.out.println("Billing Information:"); // prints billing information
			        	System.out.println(String.format("%-30s","Name")+String.format("%-20s", "Quantity") + String.format("%-20s","Price")); // prints headers for the items being purchased (name, quantity, price)
			        	
			        	
			        	LinkedList<Item> list =  SC.cartList; // initializes a new linkedlist for the list associated with the shoppingcart object
			        	
			        	ListIterator<Item> iterator = list.listIterator(); // creates an iterator for this list
			        	
			        	double total = 0; // declares a double total and sets it to zero
			        	double total_w_etax = 0; // declares a double total_w_tax and sets it to zero
			        	
			        	while (iterator.hasNext()) // while the iterator has not gone through the entire shopping cart list
			        	{
			        		Item item = iterator.next(); // the next item in the list is set to item

			        		System.out.println(String.format("%-30s", item.title.trim()) + String.format("%-20s", item.quantity) + String.format("%-20s",item.price)); // prints details about the item being purchased (the title, quantity, price)
			        		
			        		total += item.price * item.quantity; // increments total by the item's base price*quantity
			        		total_w_etax += item.getPrice() * item.quantity; // increments total_w_etax by the quantity*price returned by getPrice - base price for MP3 and eBook, baseprice*1.02 for CD and Book
			        		
			        	}
			        	
			        	double etax = total_w_etax - total; // subtract the total from total_w_etax to get just the environment tax that the user is paying
			        	System.out.println(String.format("%-30s","Environment Tax") + String.format("%-20s","2%") + String.format("%-20s", Double.toString(etax))); // prints the environment tax that the user will pay
			        	
			        	double hst = total * 0.13; // assigns total*0.13 to the hst that the user will pay
			        	
			        	System.out.println(String.format("%-23s","\tHST")+String.format("%-20s", "13%") + String.format("%-20s",Double.toString(hst))); // displays how much HST the user will pay
			        	
			        	double shipping = total * 0.10; // the user pays a 10% shipping fee on the base total. This amount is assigned to shipping.
			        	
			        	System.out.println(String.format("%-23s","\tShipping") + String.format("%-20s", "10%") + String.format("%-20s",Double.toString(shipping))); // displays the shipping fee
			        	
			        	double total_w_alltax = total + etax + hst + shipping; // assigns the total including the environment tax, the HST, and shipping to total_w_alltax
			        	
			        	System.out.println(String.format("%-23s","\tTotal") + String.format("%-20s", " ") + String.format("%-20s", Double.toString(total_w_alltax) + "$")); // displays the total that the user will pay
			        	
			        	System.out.println("Are you sure you want to pay? "); // Asks the user if they are sure they want to pay
			        	
			        	String P10choice = ""; // P10choice is initialized to an empty string 
			        	
			        	while (!P10choice.equals("yes") && !P10choice.equals("no")) // while the user has not entered yes or no
			        	{
			        		P10choice = scanner.next(); // assign next user input to scanner
			        	   	P10choice = P10choice.toLowerCase(); // convert the user's option to lowercase 
				           	if (P10choice.equals("no")) // if the user chooses no
				        	{currentPage(4);} // redirect the user to page 4
				        	else if (P10choice.equals("yes")) // if the user chooses yes
				        	{
				        		System.out.println("Confirmation ID: U" + makeConfirmID()); // display the confirmation ID for the sale
				        		System.out.println("Items shipped to: Mr." + currUser.username); // display that the items will be shipped to the user
				        		exitUI = true; // exit the UI
				        	}
				        	else { // if the user has not selected yes or no
				        		System.out.println("Please answer yes or no:"); // ask them to choose again
				        	}
			        	}
			        	System.out.println("\n\n\n"); break; // print newlines to separate pages and break
			        	
			        case 10:
			        	// admin Page
			        	break;
			        
			    }	
			}
			while (exitUI == false); // exit condition for the do/while loop is that exitUI is set to false
			scanner.close(); // close the scanner
			return 0; // return zero
		}
}

