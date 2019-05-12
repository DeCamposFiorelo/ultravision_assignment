package Title;

import java.sql.Connection;

import Database.DBConnection;
import Menu.Menu;

public abstract class Title {

	//private global 
	private int id;
	private String titleName;
	private int year;
	private String genre;
	private String formats;
	
	
	Menu menu;
	Connection conn = DBConnection.getDBConnection();
	//abstract methods
	public abstract void addTitles();
	public abstract void searchTitle() ;
	
	//set and get
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getFormats() {
		return formats;
	}
	public void setFormats(String formats) {
		this.formats = formats;
	}
	
}
