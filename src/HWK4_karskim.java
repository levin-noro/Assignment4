//We rewrote serial numbers to make them unique to each item as follow:
// books start from 1; ebooks starts from 2; MP3 starts from 3; CDs starts from 4

public class HWK4_karskim {
	
	public static void main(String args[]) { 
		Readable x = new Readable();
//		x.getInfo("Books.txt");
//		x.getListInfo("Books.txt");
		x.printListInfo();
		System.out.println();
		Audio y = new Audio();
		y.printListInfo();
		
		UserInterface Inter = new UserInterface();
		Inter.currentPage(1);
		Inter.changeCurrentPage();
	}
}
