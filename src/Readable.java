//import HWK4_karskim.Item;
//import HWK4_karskim.Item.String;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
//import java.util.*;

// might rewrite a bunch of this and combine functions, lots of code repetition. 

public class Readable extends Item {
		protected String authorName;
		protected LinkedList<Readable> readList;
		
		
		public Readable getInfo(String filename, int serial) {
			java.lang.String[] ar = {};
			Readable read = null;
			try {
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            
	            while ((str = in.readLine()) != null){
		        	ar = str.split(",");
		        	// temporary, might be able to combine these later
		        	
		        	if (Integer.parseInt(ar[0]) == serial) {
		        		if (ar[5].equals("Book")) {
		        			read = new Book();
		        		}
		        		if (ar[5].equals("eBook")) {
		        			read = new eBook();
		        		}
			        	read.sNo = Integer.parseInt(ar[0]);
			        	read.title = ar[1];
			        	read.authorName = ar[2];
			        	read.price = Integer.parseInt(ar[3].trim());
			        	read.quantity = Integer.parseInt(ar[4].trim());
			        	read.itemtype = ar[5];
			        	
		        		//System.out.println(str);

		            	//System.out.println(authorName);
		            	//break;
		        	}
		        
		        }
	            in.close();
		        return read;
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error.");
	        }
			return read;
			
			//System.out.println(ar[1]);
			
		} //Returns sNo, Name, Author name, etc. in a string
		
		// test function to store readable objects in a linked list
		
		public LinkedList<Readable> getReadableList() {
			this.readList = new LinkedList<Readable>();
			// initializes every item from books and ebooks as readable objects and adds them to a linkedList
			// used to access info about the objects from userinterface
			getFileInfo(readList, "books.txt");
			getFileInfo(readList, "eBooks.txt");
			return readList;
		}
		
		
		public LinkedList<Readable> getFileInfo(LinkedList<Readable> readables, String filename) {
			java.lang.String[] ar = {};
			try {
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            while((str = in.readLine()) != null){
	            	ar = str.split(",");
	            	// getting the serial number from the first element of array
	            	int serial = Integer.parseInt(ar[0]);
	            	// calls getInfo to retrieve info about the item with the specified serial number.
	            	// Creates a new object and appends it to the end of the LinkedList of Reabable Objects. 
	            	
	            	readables.add(getInfo(filename, serial)); 
	            }
	            in.close();
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error");
	        } 
			
			return readables;
		}
		
		@Override
		// might want to change the signatures of getPrice. 
		public double getPrice() { //override 
			return this.price;
			/*
			try {
				java.lang.String[] ar = {};
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            while((str = in.readLine()) != null){
	            	ar = str.split(",");
	            	if(Integer.parseInt(ar[0]).equals(serial)){
	            		priceWithTax = 0.00;
	            	}
	            }
	            in.close();
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error");
	        } 
	        */
			
		}
		
		public void getListInfo(String filename){ 		//changed the return type from int to string and ask prof
			java.lang.String[] ar = {};
			try {
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            while((str = in.readLine()) != null){
	            	ar = str.split(",");
	            	// getting the serial number from the first element of ar       	
	            	System.out.print(String.format("%-10s", ar[0])+String.format("%-40s", ar[1])+String.format("%-24s",ar[2])+String.format("%-10s", ar[3])+String.format("%-10s",  ar[4])+String.format("%-16s",  ar[5]));
	    	        System.out.println();
	            }
	            in.close();
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error");
	        } 
		}
		
		public void printListInfo() {
			System.out.println(String.format("%-10s", "S.No")+	String.format("%-40s", "Name of the Book")+String.format("%-24s", "Author")+String.format("%-10s", "Price($)")+String.format("%-10s", "Quantity")+String.format("%-16s", "Type"));
			getListInfo("Books.txt");
			getListInfo("eBooks.txt");
		}
			//this is a comment
	}
		//@Override
		//public int getPrice(...) { //override 
			
		//}
		
