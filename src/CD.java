/* This class is an extension of the class Audio. It contains only one method, getPrice(), which overrides the parent method.*/

public class CD extends Audio {
	@Override
	public double getPrice() { // override to get the item price and add 2% (Environment Tax)
		return 1.02*this.price;
	}
	// all info stored in CDs.txt: S.No, name of CD, artist, artist, price, quantity in Store, and type
}
