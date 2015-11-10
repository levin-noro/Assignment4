public class eBook extends Readable{
			
			public double getPrice(int serial) { // override and only call the parent's constructor to get the base price.
				return 1.02*this.price;
			}
			// all info stored in EBooks.txt: S.No, name of ebook, author, artist, price, quantity in Store, and type
}