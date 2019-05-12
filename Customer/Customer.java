package Customer;

import Database.DBConnection;
import Menu.Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;


import javax.swing.JOptionPane;


public class Customer implements CustomerOperation {

	//private global variable
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String cardNumber;
	private String subscription;
	private int layoutPoint = 0;
	private String id;

	Menu menu;
	Connection conn = DBConnection.getDBConnection();// database

	//get and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getSubscription() {
		return subscription;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	public int getLayoutPoint() {
		return layoutPoint;
	}
	public void setLayoutPoint(int layoutPoint) {
		this.layoutPoint = layoutPoint;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//***********************************************************************************************************************************
	@Override
	public void insertCustomer() { // this method it will insert the customer in the database

		boolean valid = true;

		
			System.out.print("Please insert the customer First Name: ");//user insert the Name
			name = readInput();// read the input

			if(name.equals("")) {// validation to check if it is empty				 
				insertCustomer();// if it is empty it will start the method again
				valid =true;	
			}				 

			System.out.print("Please insert the customer Surname: ");// user insert the surname
			surname= readInput();//read Input

			if(surname.equals("")) {//validation to check if it is empty		
				System.out.println("--------------------------------- ");
				System.out.println("Sorry it is empty, Start again ! ");
				System.out.println("--------------------------------- ");
				insertCustomer();// if it is empty it will start the method again
				valid =true;	
			}				 
			
			System.out.print("Please insert the customer email(example@example.ie): ");//user insert the email
			email = readInput();//read the input
			
			if(email.equals("")||!email.matches ("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {//valition to check if the email is in the correct format
				System.out.println("--------------------------------- ");
				System.out.println("Sorry it is wrong , start again ! ");
				System.out.println("--------------------------------- ");
				insertCustomer();// if not ,it will start again
				valid =true;
			
			}

			System.out.print("Please insert the customer phone: ");//user insert  the phone
			phone = readInput();//read the input
			
			if(phone.equals("") || phone.length()>30 ){ //validation
				
				System.out.println("--------------------------------- ");
				System.out.println("Sorry it is empty, Start again ! ");
				System.out.println("--------------------------------- ");
				insertCustomer();
				phone = readInput();
				valid =true;

				}
			
			do {// the user it will pick which subscription the customer want to be
				
				System.out.println("");// it will display the options
				System.out.print("(ML) Music Lovers | ");
				System.out.print("(VL) Video Lovers |");
				System.out.print("(TV) TV Lover |");
				System.out.println("(PR) Premium |");
				System.out.print("Please insert the customer Subscription Type( ML ,VL,TV,PR): ");

				subscription = readInput();// read the user input
				subscription= subscription.toUpperCase(); 
				

				if(subscription .equals("ML")) {//validation to check if the user insert the right Options
						valid = true;
				
				} else if(subscription .equals("VL")) {
						valid = true;

				}else if(subscription .equals("TV")) {
						valid = true;
				}else if(subscription .equals("PR")) {
						valid = true;

				}else {// if the user doenst inser the options above it will display the messagem bellow
						valid=false;
						System.out.println("--------------------------------- ");
						System.out.println("Sorry you did not insert right subscription, Please Start again !");
						System.out.println("--------------------------------- ");
						insertCustomer();
				

				}while(valid == false);{
				}
				
				do {
					
					System.out.print("Please insert the customer Card Number: ");
					cardNumber = readInput();
					if(cardNumber.equals("") ||cardNumber.length()>30 ){ 
						
						System.out.println("--------------------------------- ");
						System.out.println("Sorry it is empty, start again ! ");
						System.out.println("--------------------------------- ");
						insertCustomer();
						cardNumber = readInput();
						valid =true;
					}while(valid == false);{
					}
					
					try {// insert in the database

							Statement statement = conn.createStatement();
							//query
							statement.executeUpdate("INSERT INTO customer(name,surname, email,phone,subscription,cardNumber,layoutPoint)VALUES ('" + name + "', '" + surname + "','" + email + "','"+phone+"','"+subscription+"','"+cardNumber+"','"+layoutPoint+"') ");
							// after the input was add in the database it will display the message below
							System.out.println("==========================");
							System.out.println("Register Data Successfully");
							System.out.println("==========================");
							System.out.println("");

							askYesNot();// it will send for the method to check if the user want to continue


						}catch(Exception e ) {//if something goes wrong, it will display the message below 
							System.out.println("Got error during connectin");

						}
					}while(valid==false); {

					}}while(valid==false); {

					}}
	


	//*************************************************************************************************************************************
	@Override
	public void updateCustomer() {// this method it will update customer

	
		boolean valid = true;
		System.out.println("UPDATE CUSTOMER");
		System.out.println("---------------");
		System.out.println("");

		System.out.println("Please insert the customer ID: ");
		id = readInput();

		System.out.println("UPDATE: Please insert the new NAME: ");
		name =readInput();

		System.out.println("UPDATE: Please insert the new SURNAME: ");
		surname =readInput();

		System.out.println("UPDATE: Please insert the new EMAIL: ");
		email =readInput();

		System.out.println("UPDATE: Please insert the new PHONE: ");
		phone =readInput();

		do {
			
			System.out.print("(ML) Music Lovers | ");
			System.out.print("(VL) Video Lovers |");
			System.out.print("(TV) TV Lover |");
			System.out.println("(PR) Premium |");
			System.out.print("UPDATE: Please insert the customer Subscription Type( ML ,VL,TV,PR): ");		
			subscription = readInput();
			subscription= subscription.toUpperCase(); 


			if(subscription .equals("ML")) {
				valid = true;

			} else if(subscription .equals("VL")) {
				valid = true;

			}else if(subscription .equals("TV)")) {
				valid = true;
			} else if(subscription .equals("PR")) {
				valid = true;

			}else {
				valid=false;
				System.out.println("--------------------------------- ");
				System.out.println("Sorry you did not insert right subscription, Please Start the update again !");
				System.out.println("--------------------------------- ");
				System.out.println(" ");


				updateCustomer();
			}while(valid == false);{

			}


			System.out.println("UPDATE: Please insert the BANK CARD NUMBER: ");
			cardNumber =readInput();

			try {// insert in the database

				Statement statement = conn.createStatement();
				//query
				statement.executeUpdate("UPDATE customer SET name ='" + name + "',surname ='" + surname + "',email ='" + email + "', phone ='" + phone + "',subscription = '" + subscription + "',cardNumber = '" + cardNumber + "' WHERE id='" +id+ "'");
				// after the input was update in the database it will display the message below
				System.out.println("==========================");
				System.out.println("CUSTOMER UPDATE");
				System.out.println("==========================");
				System.out.println("");

				askYesNot();


			}catch(Exception e ) {//if something goes wrong, it will display the message below 
				System.out.println("Got error during connectin");

			}}while(valid==false);{

			}

	}



	//***********************************************************************************************************************************	
	@Override
	public void searchCustomer() { // this method it will search for the customer and display the table from the databse

		String name3 = this.getName();

		System.out.print("Please insert the customer First Name: ");
		name3 = readInput();

		if(name3.equals("")) {
			System.out.println("");
			System.out.println("******************************************");
			System.out.println("SORRY YOU DID NOT INSERT ANY NAME,TRY AGAIN");
			System.out.println("******************************************");
			System.out.println("");
			askYesNot();

		} 

		try {
			String query = "SELECT * FROM `customer` WHERE name='" + name3 + "' ";
			Statement statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(query);

			while(resultset.next()) {
				System.out.println("==============================");
				System.out.println("ID: "+resultset.getString(1));
				System.out.println("NAME: "+resultset.getString(2));
				System.out.println("SURNAME: "+resultset.getString(3));
				System.out.println("EMAIL: "+resultset.getString(4));
				System.out.println("PHONE: "+resultset.getString(5));
				System.out.println("SUBSCRIPTION: "+resultset.getString(6));
				System.out.println("BANK CARD NUMBER: "+resultset.getString(7));
				System.out.println("LAYOUT POINT: "+resultset.getString(8));
				System.out.println("==============================");
				

			}
			askUpdate();

		}catch(Exception e ) {
			System.out.println("Got error during connectin");

		}

	}

	//************************************************************************************************************************************
	public String readInput() {  // This method it will read the user input

		String input = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();

		}catch(Exception e ) {}
		return input;
	}	

	//************************************************************************************************************************************
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
	//**********************************************************************************************************************************
	public void askUpdate() {// this method it is for ask if the user wannna update customer details

		boolean valid = false;

		do {

			System.out.println("Do you want update customer details? Y or N");
			String update = readInput();
			update= update.toUpperCase();
			if(update.equals("Y")) {
				valid = true;
				updateCustomer();
			} 


			else if(update.equals("N")) {
				valid = true;
				System.out.println("Thank you");;

			}


		}while(valid == false);{

		}

	}


}