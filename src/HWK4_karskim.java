import java.io.IOException;

//We rewrote serial numbers to make them unique to each item as follow:
// books start from 1; ebooks starts from 2; MP3 starts from 3; CDs starts from 4

/* This class contains the main method. Which starts the UserInterface at page 1.*/
public class HWK4_karskim {
	
	// Starts the UserInterface at page 1
	public static void main(String args[]) throws IOException { // The methods called through UserInterface access files. The IO exception handles cases where these files cannot be accessed. 
				
		UserInterface Inter = new UserInterface(1); // Initializes a new UserInterface object and sets currentPage to 1
		Inter.changeCurrentPage(); // Navigates to the currentPage
				
	}
}
