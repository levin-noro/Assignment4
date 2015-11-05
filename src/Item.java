import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public abstract class Item{
		
		public abstract int getPrice(...);
		
		protected int sNo;
		protected String artist;
		protected int price;
		protected int quantity;
		protected String itemtype;
		
		public String getInfo(String filename) {
/*		try {
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
	        } */
		}
}
	