import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CD extends Audio {
	//@Override
	public double getPrice() { // override to get the item price and add 2% (Environment Tax)
		/*double priceWithTax = 0;
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
        } */ 
		return this.price*1.02;
	}
	// // all info stored in CDs.txt: S.No, name of CD, artist, artist, price, quantity in Store, and type
}
