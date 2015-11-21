/* This class is an extension of the class Audio. It contains only one method, getPrice(), which overrides the parent method.*/

public class CD extends Audio {
	@Override // override getPrice() in Audio
	public double getPrice() { // override to get the item price and add 2% (Environment Tax)
		return 1.02*this.price; // get the price of the CD object and add a 2% environment tax. Return the price.
	}
	
}
