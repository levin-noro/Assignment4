public class Book extends Readable {
		@Override  
		public double getPrice() { // override to get the item price and add 2% (Environment Tax)
			return 1.02*this.price;
		}
		
}