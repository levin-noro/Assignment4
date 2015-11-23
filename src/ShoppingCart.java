import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
		
		
		public ShoppingCart(User currUser) { // constructor for ShoppingCart class, takes a User object as input
			super(currUser.username); // initializes username as the User's username
			
			
			try {
	    		 
				this.cartFile = new File("Cart_" + currUser.username + ".txt"); // initializes cartFile as a file with name Cart_username.txt
				BufferedWriter writer = Files.newBufferedWriter(Paths.get("Cart_" + currUser.username + ".txt"), StandardCharsets.UTF_8); // creates a new file if it doesn't exist, erases existing file if it does
				writer.close(); // closes the writer
		
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
					
					
					Integer quantity; // Stores current quantity
					
					// if the filename given to this function is the cart file, the quantity is incremented
					if (filename.equals("Cart_" + this.username + ".txt")) {
						quantity = Integer.parseInt(properties[3].trim());
						quantity = quantity + decQuantity; // decrements the quantity property of this line/item
						properties[3] = " "+quantity.toString(); // Updates the new quantity in the array
				
			        	for (Item currI : cartList) { // while the iterator has not gone through the list
			        		
			        		if (currI.sNo == Integer.parseInt(serial)) { // if the item's serial number matches the serial number given to the function
			        		
			        			currI.quantity = quantity; // the quantity of the item is set to the quantity given to the function
			        			break; // stop looking through the list
			        			}
			        		
			        		}
			        	
					}
					else { 
						
					
						quantity = Integer.parseInt(properties[4].trim());
						if (quantity < decQuantity) { // if the quantity given to the function exceeds the amount of the quantity available in stock
							return "The amount you requested of this item exceeds the amount we have in stock."; // tell the user they have requested more than is available
						}
						quantity = quantity - decQuantity; // decrements the quantity property of this line/item
						properties[4] = " "+quantity.toString(); // Updates the new quantity in the array
						
					}
					

					
					String newline = String.join(",",properties); // joins updated String array into a String
					updatedLines.add(newline); // stores full line into array
					
			//		updatedItemLine = properties;
				}
				else {
					updatedLines.add(line); // Stores existing line into array without making any changes
				}
				
			}
			
			PrintWriter updated; // declares a new printwriter
			
			updated = new PrintWriter (new BufferedWriter(new FileWriter(filename)));  // initializes it with the filename 
			
			ListIterator<String> itrUpdate = updatedLines.listIterator(); // iterator to iterate over the list of items 
			while(itrUpdate.hasNext()) { // while the iterator has not gone through the list
        		String updatedLine = itrUpdate.next(); // iterates through the linkedlist updatedLines and appends them to the file
				
				updated.println(updatedLine); // appends the line to the file
			}
			itrUL = lines.listIterator(); // resets the iterator
			itrUpdate = updatedLines.listIterator(); // resets the iterator
			updated.close(); // closes the printwriter
			return "Item updated"; // returns that the item was updated
		}
		
		private void updateCart(Item addthis, int decQuantity) throws IOException{ // this function updates the shopping cart
			
			if (cartList.isEmpty()) { // if there are no items in the cartList
				Item add = deepCopyItem(addthis); // create a copy of the item 
    			add.quantity = decQuantity; // set the item's quantity to the quantity requested by the user
    			
    			
				cartList.add(add); // add the item to the shopping cart
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); // sets a new format for the date
    			Date today = Calendar.getInstance().getTime(); // gets the date that the user added the item       
    			String reportDate = df.format(today); // formats the date into MM/DD/yyy format
    			
    			String newItem = Integer.toString(addthis.sNo) + ", " + addthis.title + ", " + reportDate + ", " + Integer.toString(decQuantity); // create a string with the item's properties and the date, to be added to the shopping cart file
    					
    			BufferedWriter output; // declares a new bufferedwriter
    			output = new BufferedWriter(new FileWriter(this.cartFile));  // initializes it with the shopping cart file for the user
    			
    			
    			output.append(newItem); output.newLine(); // appends the string for the new item and prints a newline
    			
    			output.close(); // closes the bufferedwriter
			}
			else { // if the cartList is not empty
				LinkedList<Item> temp = deepCopyList(cartList); // creates a copy of the cartList
				ListIterator<Item> itr = temp.listIterator(); // and an iterator for the copy
				boolean found = false; // sets found to false
				while (itr.hasNext()) { // while there are still items in the list
	        	
	        		Item currI = itr.next(); // set the item being accessed by the iterator to currI
	        		
	        		if (currI.sNo == addthis.sNo) { // if the serial number matches the serial number of the item in the list
	        			
	        			found = true; // set found to true
	        			updateItemFile("Cart_" + this.username + ".txt", Integer.toString(addthis.sNo), decQuantity); // update the shopping cart file for the user and add the item
	        			break; // stop looking through the list
	        		// also want to include something here to add the item to the shopping cart	
	        		}
	        		
	        	}
				if (!found) { // if the item was found
        			// add the item to the LinkedList
        			Item add = deepCopyItem(addthis); // copy the item
        			add.quantity = decQuantity; // set the quantity to decQuantity
        			this.cartList.add(add); // add it to the linkedlist
        			
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
        			
        			String aItem = Integer.toString(addthis.sNo) + ", " + addthis.title + ", " + reportDate + ", " + Integer.toString(decQuantity); // creates a string with the item's serial number, title, date it was added and quantity 
        			
        			PrintWriter outputA = new PrintWriter(new BufferedWriter(new FileWriter(this.cartFile, true)));  // creates a new printwriter for the shopping cart file
        			
        			outputA.println(aItem); // adds the string representation of the item to the file
        			
        			outputA.close(); // close the print writer
        			// add the item to the file
        		}
	  
			}
		}
		// this function creates a copy of the item passed to it, correct constructor based on the itemtype
		public static Item deepCopyItem(Item copythis) {
			Item temp = null; // new Item is declared and set to null
			if (copythis.itemtype.equals("Book")) { // if the item is a Book
				temp = new Book(); // call the book constructor
				temp.price = copythis.price; // set the price of the book to the price of the new book
				temp.itemtype = copythis.itemtype; // set the itemtype of the book to the itemtype of the new book
				temp.sNo = copythis.sNo; // set the serial number of the book to the serial number of the new book
				temp.title = copythis.title; // set the title of the book to the title of the new book
			}
			if (copythis.itemtype.equals("eBook")) { // if the item is an eBook
				temp = new eBook();  // call the ebook constructor
				temp.price = copythis.price; // set the price of the eBook to the price of the new ebook
				temp.itemtype = copythis.itemtype; // set the itemtype of the ebook to the itemtype of the new ebook
				temp.sNo = copythis.sNo; // set the serial number of the ebook to the serial number of the new ebook
				temp.title = copythis.title;  // set the title of the ebook to the title of the new ebook
			}
			if (copythis.itemtype.equals("CD")) { // if the item is a CD
				temp = new CD(); // call the CD constructor
				temp.price = copythis.price; // set the price of the CD to the price of the new CD
				temp.itemtype = copythis.itemtype; // set the itemtype of the CD to the itemtype of the new CD
				temp.sNo = copythis.sNo; // set the serial number of the CD to the serial number of the new CD
				temp.title = copythis.title;  // set the title of the CD to the title of the new CD
			}
			if (copythis.itemtype.equals("MP3")) { // if the item is an MP3
				temp = new MP3(); // call the MP3 constructor
				temp.price = copythis.price; // set the price of the MP3 to the price of the new MP3
				temp.itemtype = copythis.itemtype; // set the itemtype of the MP3 to the itemtype of the new MP3
				temp.sNo = copythis.sNo; // set the serial number of the MP3 to the serial number of the new MP3
				temp.title = copythis.title;  // set the title of the Mp3 to the title of the new MP3
			}
			
			return temp; // return the new item
			
			
		}
		
		// creates a copy of a list passed to this function 
		public static LinkedList<Item> deepCopyList(LinkedList<Item> a) {
			LinkedList<Item> temp = new LinkedList<Item>(); // creates a new linkedlist
			ListIterator<Item> itrA = a.listIterator(); // creates a new iterator for the list
			while(itrA.hasNext()) { // while there are still items in the list
				Item iT = itrA.next(); // the current item accessed by the iterator is assigned to iT
				temp.add(iT);  // add the item to the list
			}
			return temp; // return the list
		}
		
}