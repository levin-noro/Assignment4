/* This class is an extension of the class Readables. It contains only one method, getPrice(), which overrides the parent method.*/

public class eBook extends Readable{
			@Override // Override the method getPrice() in Readables 
			public double getPrice() { // override to get the base price.
				return this.price; // return the base price of the eBook object
			}
			
}