import java.io.IOException;

//We rewrote serial numbers to make them unique to each item as follow:
// books start from 1; ebooks starts from 2; MP3 starts from 3; CDs starts from 4

/* This class contains the main method. Which starts the UserInterface at page 1.*/
public class HWK4_karskim {
	
	// Starts the UserInterface at 
	public static void main(String args[]) throws IOException { 
				
		UserInterface Inter = new UserInterface(1);
		Inter.currentPage(1);
		Inter.changeCurrentPage();
				
	}
}
