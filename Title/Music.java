package Title;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import Database.DBConnection;
import Menu.Menu;

public class Music extends Title {
	

	Connection conn = DBConnection.getDBConnection();//database
	
	//private global variable
	private String band;
	//getters and setters
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}

	//**********************************************************************************************************
	@Override
	public void addTitles() {// this method it will add the Music Title in the Databse
		
		String titleName2 = this.getTitleName();
		int year2 = this.getYear();
		String genre2 =this.getGenre();		
		String band2 = this.getBand();
		String format2 = this.getFormats();
		
		Scanner sc=new Scanner(System.in);

		boolean valid = true;

		System.out.print("Please insert the title Name: ");
		titleName2 = readInput();

		if(titleName2.equals("")) {				 
			askYesNot();
			valid =true;	
			}

		System.out.print("Please insert the Band Name: ");
		band2 = readInput();

		if(band2.equals("")) {				 
			askYesNot();
			valid =true;	
		}			
		
		
		try {
			System.out.print("Please insert the Year of release: ");
			year2 = sc.nextInt();

		}catch(InputMismatchException exception){			 		
			//Print "This is not a year"
			//when user put other than integer
			System.out.println("This is not a year(Number),Please start again");
			askYesNot();
		}

		System.out.print("Please insert title genre : ");
		genre2  = readInput();

		if(genre2 .equals("")) {

			System.out.println("--------------------------------- ");
			System.out.println("Sorry it is wrong , start again ! ");
			System.out.println("--------------------------------- ");
			askYesNot();
			valid =true;
		}
			

		 do {
			 System.out.print("Please insert the format title : CD ,DVD, BLU-RAY: ");
			 format2 = readInput();
			 format2= format2.toUpperCase(); 

			 if(format2 .equals("CD")) {
				valid = true;
					}
			 else if(format2 .equals("DVD")) {
				valid = true;
					}
			 else if(format2 .equals("BLU-RAY")) {
				valid = true;
					}
			 else {
				 
				 valid=false;
				 System.out.println("--------------------------------- ");
				 System.out.println("Sorry you did not insert right FORMAT, Please Start again !");
				 System.out.println("--------------------------------- ");
				 askYesNot();

			}while(valid == false);{}

		   try {// insert in the database
				Statement statement = conn.createStatement();
						//query
				statement.executeUpdate("INSERT INTO title(titleTypes,	title, year,genre,director,band,formats,venue,ageRating,actors) VALUES('MUSIC','" + titleName2 + "', '" + year2 + "','" + genre2  + "','NOT REQUIRED','"+band2+"','"+format2+"','NOT REQUIRED','0','NOT REQUIRED')");
						// after the input was add in the database it will display the message below
				System.out.println("==========================");
				System.out.println("Register Data Successfully");
				System.out.println("==========================");
				System.out.println("");
				askYesNot();
				
				}
			catch(Exception e ) {//if something goes wrong, it will display the message below 
						System.out.println("Got error during connectin");

					}
			}while(valid==false); {

			}while(valid==false); {
			}
			}
			
	
	//*********************************************************************************************************
	@Override
	public void searchTitle() { // this method it will search for the title and display the table from the database
		
        try {
			String query = "SELECT * FROM `title` WHERE titleTypes='MUSIC' ORDER BY `title` ASC  ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {
				System.out.println("==============================");
				System.out.println("ID: "+resultset.getString(1));
				System.out.println("TITLE TYPE: "+resultset.getString(2));
				System.out.println("TITLE NAME: "+resultset.getString(3));
				System.out.println("YEAR OF RELEASE: "+resultset.getString(4));
				System.out.println("GENRE: "+resultset.getString(5));
				System.out.println("BAND: "+resultset.getString(7));
				System.out.println("FORMATS: "+resultset.getString(8));
				System.out.println("==============================");
					
				}
				
				askYesNot();
			}catch(Exception e ) {
				System.out.println("Got error during connectin");

			}	

		}
		
	//**********************************************************************************************************
	public String readInput() {  // This method it will read the user input

		String input = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();

		}catch(Exception e ) {}
		return input;
	}
	
	//*********************************************************************************************************
	public void askYesNot() {// this method it is for ask to the user if they want to back to the Main Menu or Not( it will show the Logout message)

		boolean valid = false;

		do {

			System.out.println("Do you want back to the main menu?( Select Y(yes) or N(Logout))?");
			String askYesNot = readInput();
			askYesNot = askYesNot.toUpperCase();
			if(askYesNot.equals("Y")) {
				valid = true;
				menu = new Menu();
			} 
			else if(askYesNot.equals("N")) {
				valid = true;
				System.out.println("Thank you");;

			}


		}while(valid == false);{

		}
	}

}
