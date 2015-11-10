import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//import java.util.Scanner;

public class ShoppingCart extends User {

		public ShoppingCart(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

		private String[] content; // array of items
		
		public String getContent(String name) throws IOException { // return the content of the shopping cart
			
			File inFile = new File("Users.txt");
			String output = "";
			
			boolean found = searchFile("Users.txt",name);
			
			if (found) {
				
				File cart = new File("Cart_" + name + ".txt");
				Scanner sc = new Scanner (cart);
				
				while (sc.hasNextLine())
				{
					String line = sc.nextLine();
					output += line + "\n";
					
					
				}
				sc.close();
				return output;
			}
			else {
				return "Cart_" + name + ".txt not found";
			}
			
		}
		
		public void AddItem(String type, String serial, String name, int decQuantity) throws IOException 
		// content of the shopping cart is stored in Cart.txt with the following fields: S.No, product name, date added, quantity
		{
			// Checks if user exits
			boolean userExists = searchFile("Users.txt",name);
			
			if (userExists) {
				switch (type) // Options for item types
				{
					case ("Books") :// Once item is given
						
						// Decrements item from Books.txt
						String[] properties = updateItemFile("Books.txt",serial,decQuantity);
					
						// If it already exists in Cart, increment the quantity in Cart
						if (properties
						updateCart("Cart_" + name + ".txt");
						
				}
			} else System.out.println("Oops! User name does not exist.");
			
			
		}
		private static int numberOfLines(File infile) throws FileNotFoundException
		{
			Scanner sc = new Scanner (infile);
			int counter = 0;
			while (sc.hasNextLine() ) {
				counter = counter + 1;
			}
			sc.close();
			return counter;
		}
		
		private static String[] storeLines (String[] array,File infile) throws FileNotFoundException {
			Scanner sc = new Scanner (infile);
			for (int i = 0; sc.hasNextLine(); i++) {
				array[i] = sc.nextLine();
			}
			sc.close();
			return array;
		}
		
		private static String[] updateItemFile(String filename, String serial, int decQuantity) throws IOException {
			File textfile = new File(filename); // Opens textfile for Books
			//Scanner sc = new Scanner (textfile); // Opens scanner which will read through textfile
			
			int numberOfItems = numberOfLines(textfile);  // Counts number of items/lines in Books.txt
			
			String[] lines = new String[numberOfItems]; // Create String array that to store each line in textfile
			storeLines(lines,textfile); // Stores each line in textfile as an element in a String array
			
			String[] updatedLines = new String[numberOfItems]; // String that will stores existing lines along with any modified lines
			
			String[] updatedItemLine = null;
			
			for (int i = 0; i < lines.length; i++ ) { // For loop that iterates through lines in String array
				
				String line = lines[i]; // Stores current line
				String [] properties = line.split(","); // Splits line into its properties
				
				if (serial.equals(properties[0])) { // Checks if given serial number matches the serial number on this line
					
					Integer quantity = Integer.parseInt(properties[4]); // Stores current quantity
					quantity = quantity - decQuantity; // decrements the quantity property of this line/item
					properties[4] = quantity.toString(); // Updates the new quantity in the array
					String newline = String.join(",",properties); // joins updated String array into a String
					updatedLines[i] = newline; // stores full line into array
					
					updatedItemLine = properties;
				}
				else {
					updatedLines[i] = line; // Stores existing line into array without making any changes
				}
				
			}
			
			FileWriter fWriter = new FileWriter (textfile);
			PrintWriter pWriter = new PrintWriter (fWriter);
			
			for (int i = 0; i < updatedLines.length; i++ ) { // For loop that iterates through updated lines and writes them to file
				pWriter.println(updatedLines[i]);
			}
			
			pWriter.close();
			fWriter.close();
			
			return updatedItemLine;
		}
		
		private static void updateCart(String filename){
			
		}
		
}