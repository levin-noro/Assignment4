/*
 * Name: Magdalena Karski, Alla Abramova, Levin Noronha
 * MacID: karskim, abramova, noronl
 * Student Number: 001436728, 400039290, 001408964
 * Description: This class is an extension of the class Readables. It contains only one method, getPrice(), which overrides the parent method.
 * */



public class Book extends Readable {
		@Override  // override getPrice() in Readables
		public double getPrice() { 
			return 1.02*this.price; // to get the item price, add 2% (Environment Tax), and return the price+tax
		}
		
}