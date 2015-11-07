//import HWK4_karskim.Item;
//import HWK4_karskim.Item.String;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.*;

public class Readable extends Item {
		protected String authorName;
		
		public String getInfo(String filename, int lineNumber) {
			java.lang.String[] ar = {};
			try {
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            
	            for(int cntr = 1; (str = in.readLine()) != null; cntr++){
	            	if(cntr == lineNumber){
		            	//System.out.println(str);
		            	ar = str.split(",");
		            	authorName = ar[2];
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
			System.out.println(String.format("%-10s", "S.No")+	String.format("%-40s", "Name of the Book")+String.format("%-24s", "Author")+String.format("%-10s", "Price")+String.format("%-10s", "Quantity")+String.format("%-16s", "Type"));
			getListInfo("Books.txt");
			getListInfo("eBooks.txt");
		}
			//this is a comment
	}
		//@Override
		//public int getPrice(...) { //override 
			
		//}
		
