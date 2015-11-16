
public abstract class Item{
		
		//public abstract int getPrice(...);
		
		protected int sNo;
		protected String title;
		protected int price;
		protected int quantity;
		protected String itemtype;
		// might want to change this signature
//		public abstract String[] getInfo(String filename, int lineNumber);
		public abstract double getPrice();
		
}
	