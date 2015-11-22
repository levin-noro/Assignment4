/* This class is an extension of the class Audio. It contains only one method, getPrice(), which overrides the parent method.*/

public class MP3 extends Audio {
		@Override // override getPrice() in Audio
		public double getPrice() { // override to get base price of the MP3 object
			return this.price; // return the base price of the MP3 object
		}
		
}
	