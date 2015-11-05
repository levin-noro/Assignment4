import HWK4_karskim.Item;
import HWK4_karskim.Item.String;

public class Audio extends Item
	{
		protected String artistName;
		public String getInfo(...){...} // Returns sNo Name, Artist name ,etc in a string
		@Override
		public int getPrice(...) { // override to get the item price and add 2% environment tax
		}
		public int getListInfo(...) { //Based on the value of Type(CD or MP3) print the list of Items
		}
	}