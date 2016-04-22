/*
 * Name: Magdalena Karski, Alla Abramova, Levin Noronha
 * MacID: karskim, abramova, noronl
 * Student Number: 001436728, 400039290, 001408964
 * Description: This class is an extension of the class Audio. It contains only one method, getPrice(), which overrides the parent method.
 * */



public class CD extends Audio {
	@Override // override getPrice() in Audio
	public double getPrice() { // override to get the item price and add 2% (Environment Tax)
		return 1.02*this.price; // get the price of the CD object and add a 2% environment tax. Return the price.
	}
	
}
