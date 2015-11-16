//import HWK4_karskim.Item;
//import HWK4_karskim.Item.String;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
//import java.util.*;

// might rewrite a bunch of this and combine functions, lots of code repetition. 

public class Audio extends Item {
		protected String artistName;
		protected LinkedList<Audio> audList;
		
		
		public Audio getInfo(String filename, int serial) {
			java.lang.String[] ar = {};
			Audio listen = null;
			try {
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            
	            while ((str = in.readLine()) != null){
		        	ar = str.split(",");
		        	// temporary, might be able to combine these later
		        	
		        	if (Integer.parseInt(ar[0]) == serial) {
		        		if (ar[5].equals("CD")) {
		        			listen = new CD();
		        		}
		        		if (ar[5].equals("MP3")) {
		        			listen = new MP3();
		        		}
			        	listen.sNo = Integer.parseInt(ar[0]);
			        	listen.title = ar[1];
			        	listen.artistName = ar[2];
			        	listen.price = Integer.parseInt(ar[3].trim());
			        	listen.quantity = Integer.parseInt(ar[4].trim());
			        	listen.itemtype = ar[5];
			        	
		        		//System.out.println(str);

		            	//System.out.println(authorName);
		            	//break;
		        	}
		        
		        }
	            in.close();
		        return listen;
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error with squirrels");
	        }
			return listen;
			
			//System.out.println(ar[1]);
			
		} //Returns sNo, Name, Author name, etc. in a string
		
		// test function to store readable objects in a linked list
		
		public LinkedList<Audio> getAudioList() {
			this.audList = new LinkedList<Audio>();
			// initializes every item from books and ebooks as readable objects and adds them to a linkedList
			// used to access info about the objects from userinterface
			getFileInfo(audList, "CDs.txt");
			getFileInfo(audList, "MP3.txt");
			return audList;
		}
		
		
		public LinkedList<Audio> getFileInfo(LinkedList<Audio> audio, String filename) {
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
	            	
	            	audio.add(getInfo(filename, serial)); 
	       //     	System.out.print(String.format("%-10s", ar[0])+String.format("%-40s", ar[1])+String.format("%-24s",ar[2])+String.format("%-10s", ar[3])+String.format("%-10s",  ar[4])+String.format("%-16s",  ar[5]));
	    //	        System.out.println();
	            }
	            in.close();
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error");
	        } 
			
			return audio;
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
			System.out.println(String.format("%-10s", "S.No")+	String.format("%-40s", "Name")+String.format("%-24s", "Artist")+String.format("%-10s", "Price($)")+String.format("%-10s", "Quantity")+String.format("%-16s", "Type"));
			getListInfo("CDs.txt");
			getListInfo("MP3.txt");
		}
			//this is a comment
	}
		//@Override
		//public int getPrice(...) { //override 
			
		//}
		







/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Audio extends Item {
	protected String artistName;
	public String[] getInfo(String filename, int serial) {
		java.lang.String[] ar = {};
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
	        java.lang.String str;
	        while ((str = in.readLine()) != null){
	        	ar = str.split(",");
	        	if (Integer.parseInt(ar[0]) == serial) {
	        		
	        		super.sNo = Integer.parseInt(ar[0]);
	        		super.title = ar[1];
	        		artistName = ar[2];
	        		super.price = Integer.parseInt(ar[3]);
	        		super.quantity = Integer.parseInt(ar[4]);
	        		super.itemtype = ar[5];
	            	
	        		//System.out.println(str);
	            	
	            	//System.out.println(authorName);
	            	break;
	        	}
	        }
	        in.close();
	    } 
		catch (IOException e) {
	        System.out.println("File Read Error");
	    } 
		//System.out.println(ar[1]);
		return ar.toString();
	} //Returns sNo, Name, Author name, etc. in a string
	
	@Override
	public double getPrice(String serial, String filename) { // override to get the item price and add 2% environment tax
	// check how info is going to be provided
		double priceWithTax = 0;
		try {
			java.lang.String[] ar = {};
            BufferedReader in = new BufferedReader(new FileReader(filename));
            java.lang.String str;
            while((str = in.readLine()) != null){
            	ar = str.split(",");
            	if(ar[0].equals(serial)){
            		priceWithTax = 0.00;
            	}
            }
            in.close();
	    } 
		catch (IOException e) {
            System.out.println("File Read Error");
        } 
		return priceWithTax;	
	}
	
	
	public void getListInfo(String filename){ 		//changed the return type from int to string and ask prof
		java.lang.String[] ar = {};
		try {
	        BufferedReader in = new BufferedReader(new FileReader(filename));
	        java.lang.String str;
	        while((str = in.readLine()) != null){
	        	ar = str.split(",");
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
		System.out.println(String.format("%-10s", "S.No")+	String.format("%-40s", "Name")+String.format("%-24s", "Artist")+String.format("%-10s", "Price($)")+String.format("%-10s", "Quantity")+String.format("%-16s", "Type"));
		getListInfo("CDs.txt");
		getListInfo("MP3.txt");
	}
		//this is a comment
}*/