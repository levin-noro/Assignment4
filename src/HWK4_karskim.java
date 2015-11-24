/*
 * Name: Magdalena Karski, Alla Abramova, Levin Noronha
 * MacID: karskim, abramova, noronl
 * Student Number: 001436728, 400039290, 001408964
 * Description: This program takes a user through a shopping environment that allows a user to 
 * buy Books, eBooks, CDs, MP3s. The main program accesses the user interface. UserInterface contains methods that allows
 * the user to navigate between different pages in the shopping environment. The ShoppingCart class allows users to modify contents
 * of a shopping cart that is created for them when they sign into the system. Item is an abstract class that contains several properties that
 * are shared classes that inherit from it and the method getPrice(). Readable class extends Item class and contains methods specific to 
 * readable objects. Book and eBook inherit from Readable. Audio also inherits from Item. CD and MP3 extend Audio. 
 * */

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
