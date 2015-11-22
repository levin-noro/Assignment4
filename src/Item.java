// This abstract class Item is a base for subclasses Readable and Audio. 

public abstract class Item{
	
	// these are properties common to all Item Objects
		protected int sNo; // sNo is the serial number of the item
		protected String title; // the title of the item
		protected double price; // the base price of the item
		protected int quantity; // the quantity of the item
		protected String itemtype; // the type of the item (can be CD, MP3, Book, eBook)
		public abstract double getPrice(); // gets the price of the item
		
}
	