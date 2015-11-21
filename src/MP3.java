/* This class is an extension of the class Audio. It contains only one method, getPrice(), which overrides the parent method.*/

public class MP3 extends Audio {
		@Override
		public double getPrice() { // override and only call the parent's constructor to get the base price.
			return this.price;
		}
		// all info stored in MP3.txt: S.No, name of MP3, artist, price, quantity in Store, and type
}
	