public class MP3 extends Audio {
		
		public double getPrice(int serial) { // override and only call the parent's constructor to get the base price.
			return 1.02*this.price;
		}
		// all info stored in MP3.txt: S.No, name of MP3, artist, price, quantity in Store, and type
}
	