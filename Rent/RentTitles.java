package Rent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import Database.DBConnection;
import Menu.Menu;

public class RentTitles  {
	
	
	Connection conn = DBConnection.getDBConnection();//databse connection
	Scanner sc=new Scanner(System.in);// to read integer from the user
	Menu menu;// Menu 
	
	//global variable
	private int customerID2;
	private String titleID;
	private String sub;
	private int IDReturn;
	private String dateReturn;
	private String answer;
	
	ArrayList<String> ar=new ArrayList<String>(); // array list it will store the database resultset

	//set and gettes
	public int getIDReturn() {
		return IDReturn;
	}

	public void setIDReturn(int iDReturn) {
		IDReturn = iDReturn;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public int getCustomerID2() {
		return customerID2;
	}

	public void setCustomerID2(int customerID2) {
		this.customerID2 = customerID2;
	}
	

	public String getTitleID() {
		return titleID;
	}

	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}


	//**********************************************************************************************************************************************
	public void checkRent() { // this method it will display all the id of the rent and the IDcustomer and IDtitle 
		
 		try {
		String query = "SELECT * FROM `rent`";// it will select everyting from the Rent table in the Database
		Statement statement = conn.createStatement();
		ResultSet resultset = statement.executeQuery(query); // execute the query

		while(resultset.next()) { // it will display The TITLE ID/CUSTOMER ID/TITLE ID/Date was Rented and Date was returned
			System.out.println("----------------------------------------------------");
			System.out.println("Titles rented");
			System.out.print("ID : "+resultset.getString(1));
			System.out.print(" | CUSTOMER ID : "+resultset.getString(2));
			System.out.print(" | TITLE ID  : "+resultset.getString(3));
			System.out.print(" | DATE THE TITLE WAS RENTED : "+resultset.getString(4));
			System.out.println(" | DATE THE TITLE WAS RETURNED : "+resultset.getString(5));
			System.out.println("----------------------------------------------------");	

		}
		

	    }catch(Exception e ) {
		System.out.println("Got error during connectin");

	    }
 		askYesNot();// it will call the method to ask if the user want to return the Menu or Logout
 		
}
//*************************************************************************************************************************************************
	public void checkAmountRented() {// this method it will check how much titles the client rent already and if he will be able to rent again

		try {
			
			System.out.println("Please insert the customer ID:  ");
			customerID2=sc.nextInt();//read Input
			
			}catch(InputMismatchException exception){			 		
				//Print "This is not a year"
				//when user put other than integer
				System.out.println("This is not a year(Number),Please start again");
			}
		
		try {
			//query
			String query = "SELECT COUNT(*) FROM rent WHERE FK_idCustomer = '"+customerID2+"' and returnDate='' ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {
				//it will print the currently amount of the user rent 
				System.out.println("--------------------------------------------------");
				System.out.println("You currently have "+resultset.getInt(1)+" titles rented at the moment. ");
				int rs = resultset.getInt(1);//save the result in a variable
				
				if(rs<=4) {//validation to check if the user has less than 4 titles rented in the moment
					
					System.out.println("You are allowed to rent title");
					System.out.println("--------------------------------------------------");
					askTitleType();
					
				}else { // if the user has more than 4 it wont be allowed to continue
					
					System.out.println("Sorry but customer is entitled to rent up to 4 titles, Please return the titles you are rented! Thanks ");
					askYesNot();
				}
			
			}
	 		
		}catch(Exception e ) {
			System.out.println("Got error during connectin");

		}
	}
	
		
	
//**************************************************************************************************************************************************
	public void askTitleType() { // this method it will select which title type the user can rent
		
		try {
			String query = "SELECT * FROM `customer` WHERE id ='"+customerID2+"' ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) { // it will print the results from the database
		
				System.out.print("ID : "+resultset.getString(1));
				System.out.print(" | Customer Name : "+resultset.getString(2));
				System.out.println(" |Subscription : "+resultset.getString(6));
			
				System.out.println("--------------------------------------------------");
				
				sub = resultset.getString(6);
					if(sub.matches("ML")) { // if the user is a Music lovers it will send to the method to show all the titles they are able to rent
						System.out.println("**********************************************************");
						System.out.println("Music Lovers can only rent A Music and Live Concert Videos");
						System.out.println("**********************************************************");
						showMusicLoversTitle();
						
					}else if(sub.matches("VL")) {//if the user is a Video lovers it will send to the method to show all the titles they are able to rent
						System.out.println("**********************************");
						System.out.println("Videos Lovers can only rent Movie");
						System.out.println("**********************************");
						showVideosLoversTitle();
						
					
					}else if(sub.matches("TV")) {//if the user is a tv lovers it will send to the method to show all the titles they are able to rent
						System.out.println("**********************************");
						System.out.println("TV Lovers can only rent Box set");
						System.out.println("**********************************");
						showTVLoversTitle();
						
					
					}else if(sub.matches("PR")) {//if the user is a Premium it will send to the method to show all the titles they are able to rent
						System.out.println("**********************************");
						System.out.println("Premium can rent any title");
						System.out.println("**********************************");
						showPremiumTitle();
				
			
					}else {
						System.out.println("Error");
					}
		 		
			}
			
	 		
		}catch(Exception e ) {
			System.out.println("Got error during connectin");

		}
	}
	
	//*******************************************************************************************************************************************
	public void showMusicLoversTitle() {// this method it will show the music  titles which they are able to rent
		
			
		try {
			
			String query = "SELECT * FROM `title` WHERE titleTypes ='MUSIC'OR titleTypes='LIVE CONCERT VIDEOS' ORDER BY `id` ASC";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {
				int strprodvalue = resultset.getInt("id");
				System.out.print("ID:"+ strprodvalue);
				System.out.print(" | TITLE TYPE : "+resultset.getString(2));
				System.out.println(" | TITLE NAME : "+resultset.getString(3));
				ar.add(String.valueOf(strprodvalue)); // it will save in the array variable					

			}

			}catch(Exception e ) {
				System.out.println("Got error during connectin");

			}
	     		rentTitle();// it will send to the method to rent the title and save in the database
	     		
		}
		
		//******************************************************************************************************************************************
	public void showVideosLoversTitle() {// this method it will show the videos lovers  titles which they are able to rent
			
			
		try {
			String query = "SELECT * FROM `title` WHERE titleTypes ='MOVIE'ORDER BY `id` ASC ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {
				int strprodvalue = resultset.getInt("id");
				System.out.print("ID:"+ strprodvalue);
				System.out.print(" | TITLE TYPE : "+resultset.getString(2));
				System.out.println(" | TITLE NAME : "+resultset.getString(3));	
				ar.add(String.valueOf(strprodvalue));// it will save in the array variable	
				
				}	

			}catch(Exception e ) {
				System.out.println("Got error during connectin");

			}
	     		rentTitle();// it will send to the method to rent the title and save in the database
	     		
		}
		
		//*****************************************************************************************************************************************
	public void showTVLoversTitle() {// this method it will show the TVLOVERS  titles which they are able to rent
			
	   try {
			String query = "SELECT * FROM `title` WHERE titleTypes ='BOXSET'ORDER BY `id` ASC ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {
				int strprodvalue = resultset.getInt("id");
				System.out.print("ID:"+ strprodvalue);
				System.out.print(" | TITLE TYPE : "+resultset.getString(2));
				System.out.println(" | TITLE NAME : "+resultset.getString(3));
			    ar.add(String.valueOf(strprodvalue));// it will save in the array variable
						
					}
					
				}catch(Exception e ) {
					System.out.println("Got error during connectin");

				}
		     		rentTitle();// it will send to the method to rent the title and save in the database
		     		
			}
		//*****************************************************************************************************************************************
	public void showPremiumTitle() {// this method it will show the Premium titles which they are able to rent
				
		try {
			String query = "SELECT * FROM `title`ORDER BY `id` ASC ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {
				int strprodvalue = resultset.getInt("id");
				System.out.print("ID:"+ strprodvalue);
				System.out.print(" | TITLE TYPE : "+resultset.getString(2));
				System.out.println(" | TITLE NAME : "+resultset.getString(3));
				 ar.add(String.valueOf(strprodvalue));// it will save in the array variable
						
				}
					
				}catch(Exception e ) {
					System.out.println("Got error during connectin");

				}
		     		rentTitle();// it will send to the method to rent the title and save in the database
		     		
			}

	//**********************************************************************************************************************************************
	public void rentTitle() { // this method it will rent the title
		
		int idtitle=0;
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");//the currently time type
		
		System.out.println("Please insert the title ID: ");// the user it will insert the title id
		idtitle=sc.nextInt();//read the input	
		
		for(String myArrayInt: ar) { // if the user insert the one of the id rent which it was store in the array from the databse , it will the code bellow
			if(idtitle == Integer.valueOf(myArrayInt)) {
			
		
		try {// insert in the database
			
			System.out.println("Total: 6€");
			System.out.println("The title must be returned within 3-days");
	
			Statement statement = conn.createStatement();
					//query
			statement.executeUpdate("INSERT INTO rent (FK_idCustomer, FK_idTitle, rentDate, returnDate) VALUES('"+customerID2+"','"+idtitle+"','"+ ft.format(dNow)+"','')");
			statement.executeUpdate("UPDATE customer SET LayoutPoint = LayoutPoint +10 WHERE id ='"+customerID2+"'");
					// after the input was add in the database it will display the message below
			System.out.println("==========================");
			System.out.println("Title Rented Successfully");
			System.out.println("==========================");
			System.out.println("");
			
			askYesNot();// after insert in the database it will send to the method to ask the user if they want to continue
			break;
			}
		catch(Exception e ) {//if something goes wrong, it will display the message below 
					System.out.println("*******************************************");
					System.out.println("*** Title or Customer does not exist ******");
					System.out.println("*******************************************");
					askYesNot();

				}	
	}
		
	}
		System.out.println("==========================");
		System.out.println("Title or Customer does not exist");
		System.out.println("==========================");
		askYesNot();
	}

	
	//**********************************************************************************************************************************************
	public void checkFreeTitle() {//this method it will check if the user is able to rent a FREE TITLE	
		
	
		try {
			
			System.out.println("Please insert the customer ID:  ");
			customerID2=sc.nextInt();
			
			}catch(InputMismatchException exception){			 		
				
				System.out.println("This is not a year(Number),Please start again");
				askYesNot();
				
			}
		
		
		try {//Database
			String query = "SELECT * FROM `customer` WHERE id ='"+customerID2+"'ORDER BY `id` ASC ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {// it will print the customer information
		
				System.out.print("ID : "+resultset.getString(1));
				System.out.print(" | Customer Name : "+resultset.getString(2));
				System.out.print(" |Subscription : "+resultset.getString(6));
				System.out.println(" |Layout Point : "+resultset.getString(8));
				System.out.println("-----------------------------------------------------------------------");
				
				int point = resultset.getInt(8);// it will store in the variable how many point the user has in the layoutpoint
				sub = resultset.getString(6);// it will store in the variable which subscription the user is
				
				if(point>=100){// if the user has more or equals 100 points than to that
					if(sub.matches("ML")) {// if the subscription is ML it will send for the method which will show the customer rent options
						musicLoversFreeRent();
						
						
					}else if(sub.matches("VL")) {// if the subscription is VL it will send for the method which will show the customer rent options
						videoLoversFreeRent();
						
					}else if(sub.matches("TV")) {// if the subscription is TV it will send for the method which will show the customer rent options
						 tvLoversFreeRent() ;
						
					}else if(sub.matches("PR")) {// if the subscription is PR it will send for the method which will show the customer rent options
						prLoversFreeRent() ;
					
				}}else {//IF the user has less than 100 points they wont be able to rent 
					System.out.println("Sorry the customer Layout is less than 100 Points");
					askYesNot();// ask if the user want to continue
				}
		 		
			}
	 		
		}catch(Exception e ) {
			System.out.println("Sorry but you did not insert a number,Please start again!");
		

		}}
	
    //**********************************************************************************************************************************************	
	public void musicLoversFreeRent() {// this method it will show the titles available for music lovers
		
		
		try {
			String query = "SELECT * FROM `title` WHERE titleTypes ='MUSIC'ORDER BY `id` ASC ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			
			while(resultset.next()) {
				int strprodvalue = resultset.getInt("id");
				System.out.print("ID:"+ strprodvalue);
				System.out.print(" | SUBSCRIPTION : "+resultset.getString("titleTypes"));
				System.out.println(" | TITLE NAME: "+resultset.getString("title"));
			    ar.add(String.valueOf(strprodvalue));// save in the array the Title ID

			}
		}catch(Exception e ) {
			System.out.println("Got error during connectin");
			checkFreeTitle();
			

		}
     		rentTitleFree();// it will send to the rent method
     		
	}
	//****************************************************************************************************************
	public void videoLoversFreeRent() {// this method it will show the titles available for videos lovers
		
		
		try {
			String query = "SELECT * FROM `title` WHERE titleTypes ='MOVIE'ORDER BY `id` ASC ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			
			while(resultset.next()) {
				int strprodvalue = resultset.getInt("id");
				System.out.print("ID:"+ strprodvalue);
				System.out.print(" | SUBSCRIPTION : "+resultset.getString("titleTypes"));
				System.out.println(" | TITLE NAME: "+resultset.getString("title"));
			    ar.add(String.valueOf(strprodvalue));// save in the array the Title ID

			}
		}catch(Exception e ) {
			System.out.println("Got error during connectin");
			checkFreeTitle();
			

		}
     		rentTitleFree();// it will send to the rent method
     		
	}
	//****************************************************************************************************************
	public void tvLoversFreeRent() {// this method it will show the titles available for TVc lover
		
		try {
		    String query = "SELECT * FROM `title` WHERE titleTypes ='BOXSET'ORDER BY `id` ASC ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);
				
			while(resultset.next()) {
				int strprodvalue = resultset.getInt("id");
				System.out.print("ID:"+ strprodvalue);
				System.out.print(" | SUBSCRIPTION : "+resultset.getString("titleTypes"));
				System.out.println(" | TITLE NAME: "+resultset.getString("title"));
	            ar.add(String.valueOf(strprodvalue));// save in the array the Title ID

			}
		}catch(Exception e ) {
			System.out.println("Got error during connectin");
			checkFreeTitle();
				

			}
	     	rentTitleFree();// it will send to the rent method
	     		
		}
	//****************************************************************************************************************
	public void prLoversFreeRent() {// this method it will show the titles available for Premium lovers
							
					
		try {
			String query = "SELECT * FROM `title` ORDER BY `id` ASC ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);
						
			while(resultset.next()) {
				int strprodvalue = resultset.getInt("id");
					System.out.print("ID:"+ strprodvalue);
					System.out.print(" | SUBSCRIPTION : "+resultset.getString("titleTypes"));
					System.out.println(" | TITLE NAME: "+resultset.getString("title"));	    
					ar.add(String.valueOf(strprodvalue));

				}
			}catch(Exception e ) {
				System.out.println("Got error during connectin");
				checkFreeTitle();		

				}
			 rentTitleFree();
			     		
	}
	//*********************************************************************************************************************************************
	public void rentTitleFree() {// method to free rent
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat (" yyyy.MM.dd");
		
		int idtitle=0;
		
		System.out.println("Please insert the title ID: ");
		idtitle=sc.nextInt();
		
		for(String myArrayInt: ar) {
			if(idtitle == Integer.valueOf(myArrayInt)) {
			
		
		try {// insert in the database
			Statement statement = conn.createStatement();
					//query
			statement.executeUpdate("INSERT INTO rent (FK_idCustomer, FK_idTitle, rentDate, returnDate) VALUES('"+customerID2+"','"+idtitle+"','"+ ft.format(dNow)+"','')");
			statement.executeUpdate("UPDATE customer SET LayoutPoint = LayoutPoint -100 WHERE id ='"+customerID2+"'");
					// after the input was add in the database it will display the message below
			System.out.println("==========================");
			System.out.println("Title Rented Successfully");
			System.out.println("==========================");
			System.out.println("");
			
			askYesNot();
			break;
			}
		catch(Exception e ) {//if something goes wrong, it will display the message below 
				
		}	
		
	}
		}
		System.out.println("==========================");
		System.out.println("Title or Customer does not exist");
		System.out.println("==========================");
		askYesNot();
		
		
		
	}	
		
	//***********************************************************************************************************
	public void showReturnDate() {
			
		try {
				
			System.out.println("Please insert the RENT ID: ");
			IDReturn=sc.nextInt();
				
		}catch(InputMismatchException exception){			 		
			//Print "This is not a year"
			//when user put other than integer
				System.out.println("This is not a year(Number),Please start again");
				askTitleType();
					
			}
		try {
			
			String query = "SELECT * FROM `rent` where idRent='"+IDReturn+"' and returnDate='' ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {
				System.out.print("ID RENT:"+ resultset.getString(1));
				System.out.print(" | CUSTOMER ID : "+resultset.getString(2));
				System.out.print(" | TITLE ID : "+resultset.getString(3));
				System.out.println("|DATE WAS RENTED : "+resultset.getString(4));
				System.out.println("--------------------------------------------------------");
				dateReturn = resultset.getString(4);
					
				checkPenalty();	 

				}
				
				
			}catch(Exception e ) {
				System.out.println("Got error during connectin");

			}
	     		System.out.println("Sorry but nothing was found or it was already returned, Try again!");
	     		askYesNot();;
	     		
		}
		
	//************************************************************************************************************************************************
	public void checkPenalty() {// this method it will check if the customer has to pay penalty before they return the title
			
		int idReturn=this.getIDReturn();
		boolean valid = false;
		System.out.println("");
		System.out.println("Please insert the date it is returned(yyyy-MM-dd): ");
		answer = readInput();
		    
		  //Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateReturn );//date get from the database
		LocalDate dateAfter = LocalDate.parse(answer);
				
		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
				
		//displaying the number of days
		System.out.println("The title was Ranted for : "+noOfDaysBetween +" days");
			
		if(noOfDaysBetween>3) {//if the title was rented for more than 3 days, they will have to pay 1 euro por day
			System.out.println("€1 per day as penalty after 3 days");
			long penault = noOfDaysBetween-3;				
			System.out.println("The client has to Pay:"+penault+" Euro");
			System.out.println("---------------------------------------");
			
			System.out.println("Payed and Return the title : Y or N ?");
			String payed=readInput();
			payed=payed.toUpperCase();
			
			if(payed.matches("Y")) {// if the customer want to pay the penault , it will send to the return title method
					returnTitle();
					
			}else {// if the customer dont pay, he wont be able to return the title
				System.out.println("Title is not Returned! End.....");
			}
			}else {// if the customer dont have penaulty, it will send to the method to return the title
				System.out.println("No Penault , The title can be returned! ");
				returnTitle();
			}		
		     		
			}
		
    //*************************************************************************************************

	public void returnTitle() {// method to return the method
		
		try {// insert in the database
			Statement statement = conn.createStatement();
					//query
			statement.executeUpdate("UPDATE rent SET returnDate ='"+ answer+"' WHERE idRent='"+IDReturn+"'");
					// after the input was add in the database it will display the message below
			System.out.println("==========================");
			System.out.println("Returned title Successfully");
			System.out.println("==========================");
			System.out.println("");
			askYesNot();
			
			}
		catch(Exception e ) {//if something goes wrong, it will display the message below 
					System.out.println("Got error during connectin");

				}	
	}
	
	//******************************************************************************************************************************************
	public String readInput() {  // This method it will read the user input

	String input = "";
	try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();

	}catch(Exception e ) {}
	return input;
   }
	//*****************************************************************************************************************************************
	public void askYesNot() {// this method it is for ask to the user if they want to back to the Main Menu or Not( it will show the Logout message)

		boolean valid = false;

		do {

			System.out.println("Do you want back to the main menu?( Select Y(yes) or N(Logout))?");
			String askYesNot = readInput();
			askYesNot = askYesNot.toUpperCase();
			
			if(askYesNot.equals("Y")) {
				valid = true;
				menu = new Menu();
			} else if(askYesNot.equals("N")) {
				valid = true;
				System.out.println("Thank you");;
			}

		}while(valid == false);{

		}
	}


}