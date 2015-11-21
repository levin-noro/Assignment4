/* This class is an extension of the class Readables. It contains only one method, getPrice(), which overrides the parent method.*/

public class eBook extends Readable{
			@Override // 
			public double getPrice() { // override and only call the parent's constructor to get the base price.
				return this.price;
			}
			// all info stored in EBooks.txt: S.No, name of ebook, author, artist, price, quantity in Store, and type
}