import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import HWK4_karskim.Item.Readable;

public class Book extends Readable {
		@Override
		public double getPrice(String serial, String filename) { // override to get the item price and add 2% (Environment Tax)
			double priceWithTax = 0;
			try {
				java.lang.String[] ar = {};
	            BufferedReader in = new BufferedReader(new FileReader(filename));
	            java.lang.String str;
	            while((str = in.readLine()) != null){
	            	ar = str.split(",");
	            	if(ar[0].equals(serial)){
	            		priceWithTax = Integer.parseInt(ar[3]) * 1.02;
	            	}
	            }
	            in.close();
		    } 
			catch (IOException e) {
	            System.out.println("File Read Error");
	        } 
			return priceWithTax;
		}
		
}