import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class ShoppingCart extends User {
		
		private File cartFile;
		LinkedList<Item> cartList = new LinkedList<Item>();
		
		public ShoppingCart(User currUser) {
			super(currUser.username);
			
			
			try {
	    		 
				this.cartFile = new File("Cart_" + currUser.username + ".txt");
				cartFile.createNewFile();  
		
		    	} catch (IOException e) {
			      e.printStackTrace();
		    	}
		// TODO Auto-generated constructor stub
	}

		// private String[] content; // LinkedList instead
		
		public String getContent() throws IOException { // return the content of the shopping cart
			
		//	File inFile = new File("Users.txt");
			String output = "";
		/*	
			boolean found = searchFile("Users.txt",name);
			
			if (found) {
				
				File cart = new File("Cart_" + name + ".txt");
		*/
			Scanner sc = new Scanner (this.cartFile);
				
			while (sc.hasNextLine())
			{
				String line = sc.nextLine();
				output += line + "\n";
				
				
			}
			sc.close();
			return output;
		/*	
		}
			else {
				return "Cart_" + name + ".txt not found";
			}
		*/
			
		}
		
		public void AddItem(Item addthis, int decQuantity) throws IOException
		// maybe rewrite this function so that it only takes the object (type Item? I'm assuming that will work for 
		// both readable and audio types) and the quantity
		
		// content of the shopping cart is stored in Cart.txt with the following fields: S.No, product name, date added, quantity
		
		// content should also be stored in a linked list (maybe containing arrays or linked lists, 1st element would be the object
		// so we have convenient access to the object properties, the second element would be the date that the item was added to the shopping cart
		// this would make it easier to get item info for the checkout page of userinterface
		
		// could use simpledateformat to get the date in dd/mm/yyy format
		
		// perhaps we should intialize a shopping cart in the user class because it's supposed to be associated with each user anyway
		// might not be necessary to look through the list of users before using the shopping cart because the shopping cart should be
		// associated with an existing user anyway
		
		{
			// Checks if user exits
			//boolean userExists = searchFile("Users.txt",name);
			
			//if (userExists) {
				
				String filename = "";
				switch (addthis.itemtype) // Options for item types
				{
					case ("Book") :// Once item is given
						filename = "Books.txt";
						break;
					case ("eBook") :
						filename = "eBooks.txt";
						break;
					case ("CD") :
						filename = "CDs.txt";
						break;
					case ("MP3") :
						filename = "MP3.txt";
						break;
				}
						// Decrements item from Books.txt
				// might not need to store properties in an array
				
				String added = updateItemFile(filename, Integer.toString(addthis.sNo), decQuantity);
				
				//update cart checks if the item is already in the shopping cart
				// if it is, it modifies the quantity in both the file and the LinkedList
				// if it isn't, it adds the item to the file and to the LinkedList
				if (added.equals("The amount you requested of this item exceeds the amount we have in stock.")) {System.out.println(added);}
				else if (added.equals("Item updated")) {
						
				/* 
				 if the item exists in the cart, the quantity of the item is incremented in the file 
				 if it does not exist, a line containing sNo, title, date, quantity is appended to the end of the cartFile
				 every time the page readable or audio shopping page is called a new readable object is created, along with a 
				 linkedList containing up to date item info. 
				 
				 */
					
					updateCart(addthis, decQuantity);
				}
						
						
				
				
			//} else System.out.println("Oops! User name does not exist.");
			
			
		}
		private static int numberOfLines(File infile) throws FileNotFoundException
		{
			Scanner sc = new Scanner (infile);
			int counter = 0;
			while (sc.hasNextLine() ) {
				counter = counter + 1;
			}
			sc.close();
			return counter;
		}
		
		private static LinkedList<String> storeLines (LinkedList<String> list,File infile) throws FileNotFoundException {
			
			Scanner sc = new Scanner (infile);
			while(sc.hasNextLine()) {
				list.add(sc.nextLine());
			}
			sc.close();
			return list;
		}
		// maybe updateItemFile should receive as parameters the object and the quantity.
		// the serial number would be accessible through the object. 
		// the correct filename would be chosen based on the value of itemtype. 
		
		
		private String updateItemFile(String filename, String serial, int decQuantity) throws IOException {
			
			File textfile = new File(filename); // Opens textfile for Books
			//Scanner sc = new Scanner (textfile); // Opens scanner which will read through textfile
			
			//int numberOfItems = numberOfLines(textfile);  // Counts number of items/lines in Books.txt
			
			LinkedList<String> lines = new LinkedList<String>(); // Create String array that to store each line in textfile
			storeLines(lines,textfile); // Stores each line in textfile as an element in a String LinkedList
			
			LinkedList<String> updatedLines = new LinkedList<String>(); // String that will stores existing lines along with any modified lines
			
			//String[] updatedItemLine = null;
			ListIterator<String> itrUL = lines.listIterator();
			while(itrUL.hasNext()) {
        		String line = itrUL.next();
			
				String [] properties = line.split(","); // Splits line into its properties
				
				if (serial.equals(properties[0])) { // Checks if given serial number matches the serial number on this line
					Integer quantity = Integer.parseInt(properties[4].trim()); // Stores current quantity
					
					// if the filename given to this function is the cart file, the quantity is incremented
					if (filename.equals("Cart_" + this.username + ".txt")) {

						quantity = quantity + decQuantity; // decrements the quantity property of this line/item
						// increment the item quantity in the linkedList
						ListIterator<Item> itrC = cartList.listIterator();
			        	while(itrC.hasNext()) {
			        		Item currI = itrC.next();
			        		if (currI.sNo == Integer.parseInt(serial)) {
			        			// could change updateItemFile so that it accepts 
			        			currI.quantity = quantity;
			        			break;
			        			}
			        		// also want to include something here to add the item to the shopping cart	
			        		}
					}
					else {
						
					
						
						if (quantity < decQuantity) {
							return "The amount you requested of this item exceeds the amount we have in stock.";
						}
						quantity = quantity - decQuantity; // decrements the quantity property of this line/item
						
					}
					
					properties[4] = " "+quantity.toString(); // Updates the new quantity in the array
					
					String newline = String.join(",",properties); // joins updated String array into a String
					updatedLines.add(newline); // stores full line into array
					
			//		updatedItemLine = properties;
				}
				else {
					updatedLines.add(line); // Stores existing line into array without making any changes
				}
				
			}
			
			PrintWriter updated;
			
			updated = new PrintWriter (new BufferedWriter(new FileWriter(filename)));  //clears file every time
			
			ListIterator<String> itrUpdate = updatedLines.listIterator();
			while(itrUpdate.hasNext()) {
        		String updatedLine = itrUpdate.next(); // iterates through the linkedlist updatedLines and appends them to the file
				
				updated.println(updatedLine); 
			}
			
			updated.close();
			
			
			//return updatedItemLine;
			return "Item updated";
		}
		// this needs to be updated
		private void updateCart(Item addthis, int decQuantity) throws IOException{
			
			if (cartList.isEmpty()) {
				cartList.add(addthis);
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    			Date today = Calendar.getInstance().getTime();        
    			String reportDate = df.format(today);
    			
    			String newItem = Integer.toString(addthis.sNo) + ", " + addthis.title + ", " + reportDate + ", " + Integer.toString(decQuantity);
    					
    			Writer output;
    			output = new BufferedWriter(new FileWriter(this.cartFile));  //clears file every time
    			
    			
    			output.append(newItem);
    			output.close();
			}
			else {
				
			
				ListIterator<Item> itrC = cartList.listIterator();
	        	
		       	
	        	while(itrC.hasNext()) {
	        		Item currI = itrC.next();
	        		if (currI.sNo == addthis.sNo) {
	        			// could change updateItemFile so that it accepts 
	        			updateItemFile("Cart_" + this.username + ".txt", Integer.toString(addthis.sNo), decQuantity);
	        			break;
	        		// also want to include something here to add the item to the shopping cart	
	        		}
	        		else {
	        			// add the item to the LinkedList
	        			this.cartList.add(addthis);
	        			
	        			// This next section creates a string containing the sNo, title, date, and quantity 
	        			// This string will be appended to cartFile
	        			
	        			// Create an instance of SimpleDateFormat used for formatting 
	        			// the string representation of date (month/day/year)
	        			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	        			
	        			// Get the date today using Calendar object.
	        			Date today = Calendar.getInstance().getTime();        
	        			// Using DateFormat format method we can create a string 
	        			// representation of a date with the defined format.
	        			String reportDate = df.format(today);
	        			
	        			String newItem = Integer.toString(addthis.sNo) + ", " + addthis.title + ", " + reportDate + ", " + Integer.toString(decQuantity);
	        					
	        			Writer output;
	        			output = new BufferedWriter(new FileWriter(this.cartFile));  //clears file every time
	        			
	        			
	        			output.append(newItem);
	        			output.close();
	        			// add the item to the file
	        		}
	        	}
			}
		}
		
}