import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import HWK4_karskim.Item;
import HWK4_karskim.Item.String;

public class Audio extends Item
	{
		protected String artistName;
		public String getInfo(...){ } // Returns sNo Name, Artist name ,etc in a string
		@Override
		public int getPrice(int sNo) { // override to get the item price and add 2% environment tax
		// check how info is going to be provided
			float price = super.getPrice(int sNo);
			price = price*1.02;
			
		}
		public void getListInfo(String filename) { //Based on the value of Type(eBook or Book) print the list of Items
		// changed return type to void from int (int was specified in the assignment instructions). Check if this is ok. 
			try {
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            str = in.readLine();
	            while ((str = in.readLine()) != null) {
	              //  System.out.println(str);
	            	java.lang.String[] ar = str.split(",");
	            }
	            in.close();
	        } catch (IOException e) {
	            System.out.println("File Read Error");
	        } 
		}
		
		public void printListInfo() {
			System.out.println("S.No	Name of the Book	Author	Price	Quantity	Type");
			getListInfo("Books.txt");
			getListInfo("eBooks.txt");
		}
		//this is a comment
	}