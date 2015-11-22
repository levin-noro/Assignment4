import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/* This class is an extension of the abstract class item. It inherits all object properties (sNo, title, price, quantity, itemtype) and the function getPrice().
 * The class methods include:
 *  getInfo, which initializes a Readable object with the specified serial number by checking the given file
 *  getFileInfo, which initializes all items stored in a specified file and adds them to a linked list of Readable objects
 *  getReadableList, which calls getFileInfo on Book.txt and eBook.txt. The result is a LinkedList of all Book and eBook objects.
 *  getListInfo, which prints every item in the specified file to the console
 *  printListInfo, prints a header for the list of Readable objects (sNo, Name, Artist, etc.), and then calls getListInfo to print information about every Book and eBook
 *  getPrice, which overrides the method in the Item Class */

public class Readable extends Item {
		protected String authorName; // declares authorName as a protected string
		protected LinkedList<Readable> readList; // declares a protected LinkedList of all Readable objects
		
		/* This function reads information about an item based on its serial number from book.txt or ebook.txt, 
		 * depending on which file is given to it. It initializes a book or eBook object and sets each object
		 * property (sNo, title, etc.) depending on what is in the file. */
		
		public Readable getInfo(String filename, int serial) {
			String[] ar = {}; // New array empty is initialized. This array will contain item info taken from the file.
			Readable read = null; // A new audio object is initialized to null
			try { // try this (if a file with the specified name exists)
	            BufferedReader in = new BufferedReader(new FileReader(filename)); // a new BufferedReader is initialized for the file given to the function
	            String str; // A new string is declared. This string will contain the current line read by the BufferedReader.
	            
	            while ((str = in.readLine()) != null){ // While the current line is not an empty string
		        	ar = str.split(","); // Parse the string into an array based on locations of commas
		        	
		        	if (Integer.parseInt(ar[0].trim()) == serial) { // if the first element is equal to the serial number, the function has found the correct item
		        		if (ar[5].equals("Book")) { // if the last element in the array is Book
		        			read = new Book(); // A Book object is created
		        		}
		        		if (ar[5].equals("eBook")) { // if the last element in the array is an eBook
		        			read = new eBook(); // an eBook object is created
		        		}
			        	read.sNo = Integer.parseInt(ar[0].trim()); // The item's serial number is set to the first element in the array
			        	read.title = ar[1]; // The item's title is set to the second element in the array
			        	read.authorName = ar[2]; // The item's artistName is set to the 3rd element in the array
			        	read.price = Integer.parseInt(ar[3].trim()); // The item's price is set to the 4th element in the array
			        	read.quantity = Integer.parseInt(ar[4].trim()); // The quantity in stock of the item is set to the 5th element in the array
			        	read.itemtype = ar[5]; // The item type is set to the 6th element in the array
			       
		        	}
		        
		        }
	            in.close(); // The buffered reader is closed
		        return read; // The newly created Readable object is returned
		    } 
			catch (IOException e) { // unless the file given to the function cannot be read
	            System.out.println("File Read Error."); // in which case the user gets the message that there was a file read error
	        }
			return read; // The Readable object is returned
		}
		
		/* This function reads a specified file, gets the serial number of each item in the file, calls getInfo with the serial number
		 * and initializes an Audio object with the specified serial number. It adds the object to the LinkedList. */
		
		public LinkedList<Readable> getFileInfo(LinkedList<Readable> readables, String filename) {
			String[] ar = {}; // A new empty array which will be used to hold string objects is initialized
			try { // Try this if the file can be read
	            BufferedReader in = new BufferedReader(new FileReader(filename)); // Initialize a new BufferedReader for the file
	            String str; // Declare a string which will be used to store a line in the file accessed by the BufferedReader
	            while((str = in.readLine()) != null){ // While the BufferedReader does not find an empty line
	            	ar = str.split(","); // Split the line based on the locations of commas into a String array
	               	int serial = Integer.parseInt(ar[0].trim()); // Get the serial number from the first element of array
	            	// call getInfo to retrieve info about the item with the specified serial number.
	            	// Creates a new object and appends it to the end of the LinkedList of Reabable Objects. 
	            	
	            	readables.add(getInfo(filename, serial)); 
	            }
	            in.close(); // close the BufferedReader
		    } 
			catch (IOException e) { // If the file cannot be read
	            System.out.println("File Read Error"); // Tell the user that there was a file read error
	        } 
			
			return readables; // return the LinkedList, now containing Readable objects from the file
		}
		
		/* This function calls getFileInfo and adds every item from book.txt and ebook.txt to the LinkedList of Readable objects*/
		
		public LinkedList<Readable> getReadableList() {
			this.readList = new LinkedList<Readable>(); // the LinkedList of Readable objects is initialized
			// initializes every item from books and ebooks as readable objects and adds them to a linkedList
			// used to access info about the objects from userinterface
			getFileInfo(readList, "books.txt"); // calls getFileInfo on book.txt to add Books to the LinkedList
			getFileInfo(readList, "eBooks.txt"); // calls getFileInfo on ebook.txt to add eBooks to the LinkedList
			return readList; // return the complete list of Readable objects
		}
		
		// This reads the specified file and prints its formatted contents line by line
		public void getListInfo(String filename){ 		
			String[] ar = {}; // A new empty array which will be used to hold string objects is initialized
			try { // Try this if the file can be read
	            BufferedReader in = new BufferedReader(new FileReader(filename)); // Initialize a new BufferedReader for the file
	            String str; // Declare a string which will be used to store a line in the file accessed by the BufferedReader
	            while((str = in.readLine()) != null){ // While the BufferedReader does not find an empty line
	            	ar = str.split(","); // Split the line based on the locations of commas into a String array
	            	// Each element of the array is formatted (left justified, with specified number of spaces) and printed       	
	            	System.out.print(String.format("%-10s", ar[0])+String.format("%-40s", ar[1])+String.format("%-24s",ar[2])+String.format("%-10s", ar[3])+String.format("%-10s",  ar[4])+String.format("%-16s",  ar[5]));
	    	        System.out.println(); // print a newline
	            }
	            in.close(); // close the BufferedReader
		    } 
			catch (IOException e) { // If the file cannot be read
	            System.out.println("File Read Error"); // Let the user know that there was a file read error
	        } 
		}
		/* This function prints column titles for each property of items that will be displayed
		 * and then calls getListInfo on CDs.txt and MP3.txt to print info about all CDs and MP3s*/
		public void printListInfo() {
			// Prints formatted header information for each column
			System.out.println(String.format("%-10s", "S.No")+	String.format("%-40s", "Name of the Book")+String.format("%-24s", "Author")+String.format("%-10s", "Price($)")+String.format("%-10s", "Quantity")+String.format("%-16s", "Type"));
			getListInfo("Books.txt"); // calls getListInfo to print all Books
			getListInfo("eBooks.txt"); // calls getListInfo to print all eBooks
		}
		
		/* This function overrides the method in the parent class Item to return the price of the Readable object*/
		@Override 
		public double getPrice() { 
			return this.price; // return the price of the Readable object
		}
			
	}
	