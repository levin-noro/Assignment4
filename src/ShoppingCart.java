import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class ShoppingCart extends User {
		
		private File cartFile; // file that contains details about items (sNo, title, date purchased, quantity) that the user wants to purchase 
		LinkedList<Item> cartList = new LinkedList<Item>(); // LinkedList of Items where items that the user wants to purchase will be stored
		private ListIterator<Item> itrC = cartList.listIterator(); // iterator over this list
		
		public ShoppingCart(User currUser) { // constructor for ShoppingCart class, takes a User object as input
			super(currUser.username); // initializes username as the User's username
			
			
			try {
	    		 
				this.cartFile = new File("Cart_" + currUser.username + ".txt"); // initializes cartFile as a file with name Cart_username.txt
				cartFile.createNewFile();  // creates the file
		
		    	} catch (IOException e) {
			      e.printStackTrace();
		    	}
	
	}

		
		public String getContent() throws IOException { // return the content of the shopping cart
			
			String output = ""; // String output initialized as an empty string
			Scanner sc = new Scanner (this.cartFile); // a new scanner is initialized for the cartFile 
				
			while (sc.hasNextLine()) // do the following while there are still lines in the file
			{
				String line = sc.nextLine(); // initialize the String line as the next line of the file read by the scanner
				output += line + "\n"; // add a newline character to the end of the line (so that the string output can be printed line-by-line as in the file
	
				
				
			}
			sc.close(); // close the scanner
			return output; // return the String output
				
		}
		// add 
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
			
				
				String filename = ""; // initializes an empty string called filename. 
				switch (addthis.itemtype) // choose a case based on the type of the item
				{
					case ("Book") : // if the item is a book
						filename = "Books.txt"; // Books.txt will be updated
						break;
					case ("eBook") : // if the item is an ebook
						filename = "eBooks.txt"; // eBooks.txt will be updated
						break;
					case ("CD") : // if the item is a CD
						filename = "CDs.txt"; // CDs.txt will be updated
						break;
					case ("MP3") : // if the item is an MP3
						filename = "MP3.txt"; // MP3.txt will be updated
						break;
				}
						
				// updateFileitem is called with the filename of the item, the serial number of the item, and the quantity requested by the user
				String added = updateItemFile(filename, Integer.toString(addthis.sNo), decQuantity); // the result of updateItemFile is stored in a string Added
				
				
				// if the quantity requested by the user exceeds the amount in stock, an error message is printed
				if (added.equals("The amount you requested of this item exceeds the amount we have in stock.")) {System.out.println(added);}
				else if (added.equals("Item updated")) { // if there was enough of the item in stock and the file containing stock info was updated
						
				
				/*
				update cart checks if the item is already in the shopping cart
				if it is, it modifies the quantity in both the file and the LinkedList
				if it isn't, it adds the item to the file and to the LinkedList 
				if the item exists in the cart, the quantity of the item is incremented in the file 
				if it does not exist, a line containing sNo, title, date, quantity is appended to the end of the cartFile
				every time the page readable or audio shopping page is called a new readable object is created, along with a 
				linkedList containing up to date item info. 
				 
				 */
					
					updateCart(addthis, decQuantity);
				}
	
		}
		
		// this function reads a file and stores each line of the file in a LinkedList. It throws an exception if the file does not exist.
		private static LinkedList<String> storeLines (LinkedList<String> list,File infile) throws FileNotFoundException {
			
			Scanner sc = new Scanner (infile); // new scanner initialized for the file
			while(sc.hasNextLine()) { // while there are still lines in the file
				list.add(sc.nextLine()); // add the line to the list
			}
			sc.close(); // close the scanner
			return list; // return the linked list containing the lines from the file
		}
		// maybe updateItemFile should receive as parameters the object and the quantity.
		// the serial number would be accessible through the object. 
		// the correct filename would be chosen based on the value of itemtype. 
		
		// this function updates either the cartFile or the stock file (ex. Books.txt) depending on which filename is passed
		// if it is the cartFile, the quantity of the item is incremented (if the item exists) or the item is added to the cartList and cartFile
		// if it is the stock file, the quantity of the item is decremented, unless there is not enough of it in stock, in which case it returns an error message
		private String updateItemFile(String filename, String serial, int decQuantity) throws IOException { // an exception is thrown if the file passed to the function does not exist
			
			File textfile = new File(filename); // Opens textfile for file passed to the function
						
			LinkedList<String> lines = new LinkedList<String>(); // Create String array that to store each line in textfile
			storeLines(lines,textfile); // Stores each line in textfile as an element in a String LinkedList
			
			LinkedList<String> updatedLines = new LinkedList<String>(); // String that will stores existing lines along with any modified lines
			
			ListIterator<String> itrUL = lines.listIterator(); // iterator to iterate over the linkedlist containing the lines of the file
			while(itrUL.hasNext()) { // while there are still elements in the linkedList
        		String line = itrUL.next(); // a String line is initialized with the next element in the LinkedList accessed by the iterator
			
				String [] properties = line.split(","); // Splits line into its properties
				
				if (serial.equals(properties[0])) { // Checks if given serial number matches the serial number on this line
					Integer quantity = Integer.parseInt(properties[4].trim()); // Stores current quantity
					
					// if the filename given to this function is the cart file, the quantity is incremented
					if (filename.equals("Cart_" + this.username + ".txt")) {

						quantity = quantity + decQuantity; // decrements the quantity property of this line/item
				
			        	while(itrC.hasNext()) {
			        		Item currI = itrC.next();
			        		if (currI.sNo == Integer.parseInt(serial)) {
			        		
			        			currI.quantity = quantity;
			        			break;
			        			}
			        		
			        		}
			        	itrC = cartList.listIterator();
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
			itrUL = lines.listIterator();
			itrUpdate = updatedLines.listIterator();
			updated.close();
			
			
			//return updatedItemLine;
			return "Item updated";
		}
		
		private void updateCart(Item addthis, int decQuantity) throws IOException{
			
			if (cartList.isEmpty()) {
				Item add = deepCopyItem(addthis);
    			add.quantity = decQuantity;
    			
    			
				cartList.add(add);
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    			Date today = Calendar.getInstance().getTime();        
    			String reportDate = df.format(today);
    			
    			String newItem = Integer.toString(addthis.sNo) + ", " + addthis.title + ", " + reportDate + ", " + Integer.toString(decQuantity);
    					
    			BufferedWriter output;
    			output = new BufferedWriter(new FileWriter(this.cartFile));  //clears file every time
    			
    			
    			output.append(newItem); output.newLine();
    			
    			output.close();
			}
			else {
				LinkedList<Item> temp = deepCopyList(cartList);
				ListIterator<Item> itr = temp.listIterator();
				boolean found = false;
				while (itr.hasNext()) {
	        	
		       	
	        	
	        		Item currI = itr.next();
	        		
	        		if (currI.sNo == addthis.sNo) {
	        			// could change updateItemFile so that it accepts
	        			found = true;
	        			updateItemFile("Cart_" + this.username + ".txt", Integer.toString(addthis.sNo), decQuantity);
	        			break;
	        		// also want to include something here to add the item to the shopping cart	
	        		}
	        		
	        	}
				if (!found) {
        			// add the item to the LinkedList
        			Item add = deepCopyItem(addthis);
        			add.quantity = decQuantity;
        			this.cartList.add(add);
        			
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
        			
        			String aItem = Integer.toString(addthis.sNo) + ", " + addthis.title + ", " + reportDate + ", " + Integer.toString(decQuantity);
        			
        			PrintWriter outputA = new PrintWriter(new BufferedWriter(new FileWriter(this.cartFile, true)));  
        			
        			outputA.println(aItem);
        			
        			outputA.close();
        			// add the item to the file
        		}
	      //  	itrC = cartList.listIterator();
			}
		}
		
		public static Item deepCopyItem(Item copythis) {
			Item temp = null;
			if (copythis.itemtype.equals("Book")) {
				temp = new Book();
				temp.price = copythis.price;
				temp.itemtype = copythis.itemtype;
				temp.sNo = copythis.sNo;
				temp.title = copythis.title;
			}
			if (copythis.itemtype.equals("eBook")) {
				temp = new eBook();
				temp.price = copythis.price;
				temp.itemtype = copythis.itemtype;
				temp.sNo = copythis.sNo;
				temp.title = copythis.title;
			}
			if (copythis.itemtype.equals("CD")) {
				temp = new CD();
				temp.price = copythis.price;
				temp.itemtype = copythis.itemtype;
				temp.sNo = copythis.sNo;
				temp.title = copythis.title;
			}
			if (copythis.itemtype.equals("MP3")) {
				temp = new MP3();
				temp.price = copythis.price;
				temp.itemtype = copythis.itemtype;
				temp.sNo = copythis.sNo;
				temp.title = copythis.title;
			}
			
			return temp;
			
			
		}
		
		public static LinkedList<Item> deepCopyList(LinkedList<Item> a) {
			LinkedList<Item> temp = new LinkedList<Item>();
			ListIterator<Item> itrA = a.listIterator();
			while(itrA.hasNext()) {
				Item iT = itrA.next();
				temp.add(iT);
			}
			return temp;
		}
		
}