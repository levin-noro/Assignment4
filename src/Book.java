import HWK4_karskim.Item.Readable;

public class Book extends Readable {
		@Override
		public int getPrice(...) { // override to get the item price and add 2% (Environment Tax)
		}
		
		public int getListInfo(...) { //Based on the value of the Type(Book or eBook) print the list of Items
		}
		// all info stored in Books.txt: S.No, name of book, author, artist, price, quantity in Store, and type
}