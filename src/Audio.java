import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class Audio extends Item {
		protected String artistName;
		protected LinkedList<Audio> audList;
		
		/* This function reads information about an item based on its serial number from CD.txt or MP3.txt, 
		 * depending on which file is given to it. It initializes a CD or MP3 object and sets each object
		 * property (sNo, title, etc.) depending on what is in the file. */
		 
		public Audio getInfo(String filename, int serial) {
			java.lang.String[] ar = {};
			Audio listen = null;
			try {
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            
	            while ((str = in.readLine()) != null){
		        	ar = str.split(",");
		        			        	
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
		        	}
		        
		        }
	            in.close();
		        return listen;
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error with squirrels");
	        }
			return listen;
			
					
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
	            }
	            in.close();
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error");
	        } 
			
			return audio;
		}
		
		@Override
		
		public double getPrice() { //override 
			return this.price;
		}
		
		public void getListInfo(String filename){ 		
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
			
	}
