package Menu;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import Customer.Customer;
import Rent.RentTitles;
import Title.BoxSet;
import Title.LiveConcertVideos;
import Title.Movie;
import Title.Music;

public class Menu {
	
	Customer customer = new Customer();
	Music music = new Music();
	LiveConcertVideos lcv = new LiveConcertVideos();
	Movie movie= new Movie();
	BoxSet box = new BoxSet();
	RentTitles rent = new RentTitles();
	
	public Menu() {//constructor method
		
		callMenu();
		
	}
	
	public void callMenu() {// method to display the MENU
		
		System.out.println("---------MENU----------");
		System.out.println("");
		System.out.println("1.Search Titles");
		System.out.println("2.Add titles");
		System.out.println("3.Search Customer / Update Customer");
		System.out.println("4.Add New Customer");		
		System.out.println("5.Check Rent");
		System.out.println("6.Register Rent");
		System.out.println("7.ReturnTitle");
		System.out.println("8.free Rent");
		System.out.println("");
		System.out.println("------------------------");
		menuInput();
	}
	
	//*********************************************************************************************************************************************
	public void menuInput() { // method to read the user input and send them to the right class/method.
		
		
		boolean valid = false;
		
		do {
			
		System.out.println("Please enter a number: ");			
	    String input = readInput();
	    
	    if(input.matches("[1-8]+")) {
	     	valid = true;
	    }  	
	    
		if(input.matches("1")){
			System.out.println("Search Titles");
			System.out.println("-------------");
			asksearchType();
		}
		
		if(input.matches("2")) {
		askTitleType();
		}
		
		if (input.matches("3")){
			 customer.searchCustomer();		
		} 
		
		if(input.matches("4")) {
			customer.insertCustomer();
			valid = true;
		}
	    if(input.matches("5")) {
	    	rent.checkRent();		 
				 
		}
		if(input.matches("6")) {
			rent.checkAmountRented();
					 
		}
		if(input.matches("7")) {
			rent.showReturnDate();
						 
		}
		if(input.matches("8")) {
			rent.checkFreeTitle();
			 
		}
			 	
		}while(valid == false); {
			
		}
		
	}
	
	//**********************************************************************************************************************************************
	public String readInput() {  // This method it will read the user input

		String input = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();

		}catch(Exception e ) {}
		return input;
	}
	//***********************************************************************************************************************************************
	public void askTitleType() { // this method it will select which title type the user want to register
		
		boolean valid = false;

		do {
			System.out.println(" ");
			System.out.println("(ML) Music ");
			System.out.println("(LCV) Live Concert Videos");
			System.out.println("(VL) Movie ");
			System.out.println("(TV) Box Set");
			System.out.println(" ");
			System.out.println("Which Type do you want add (ML,LCV,VL,TV)?");
			
			String askYesNot = readInput();
			askYesNot = askYesNot.toUpperCase();
			
			if(askYesNot.equals("ML")) { // if they select ML , it will send to Music class and the method to add Titles;
				valid = true;
				System.out.println("---MUSIC---");
				music.addTitles();// method to add the Music title
				
			} 
			else if(askYesNot.equals("LCV")) {
				valid = true;
				System.out.println("---LIVE CONCERT VIDEOS---");
				lcv.addTitles();
				
			}
			else if(askYesNot.equals("VL")) {
				valid = true;
				System.out.println("---MOVIE---");
				movie.addTitles();


		}else if(askYesNot.equals("TV")) {
			valid = true;
			System.out.println("---BOX SET---");
			box.addTitles();


	}}while(valid == false);{

		}
		
	
}
	//*********************************************************************************************************
	public void asksearchType() { // this method it will select which title type the user want to search
		
		boolean valid = false;

		do {
			System.out.println(" ");
			System.out.println("(ML) Music ");
			System.out.println("(LCV) Live Concert Videos");
			System.out.println("(VL) Movie ");
			System.out.println("(TV) Box Set");
			System.out.println(" ");
			System.out.println("What type of title do you want to research: (ML,LCV,VL,TV)?");
			
			String askYesNot = readInput();
			askYesNot = askYesNot.toUpperCase();
			
			if(askYesNot.equals("ML")) { // if they select ML , it will send to Music class and the method to add Titles;
				valid = true;
				System.out.println("---MUSIC---");
				music.searchTitle();// method to add the Music title
				
			} 
			else if(askYesNot.equals("LCV")) {
				valid = true;
				System.out.println("---LIVE CONCERT VIDEOS---");
				lcv.searchTitle();
				
			}
			else if(askYesNot.equals("VL")) {
				valid = true;
				System.out.println("---MOVIE---");
				movie.searchTitle();


		}else if(askYesNot.equals("TV")) {
			valid = true;
			System.out.println("---BOX SET---");
			box.searchTitle();


	}}while(valid == false);{

		}
	}
}