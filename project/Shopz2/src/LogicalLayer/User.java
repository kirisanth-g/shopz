package LogicalLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Backend.DBConnector;

public class User {
	
	//User
	private String username;
	private String name;
	private boolean isAdmin;
	
	//Address
	private String address;
	private String city;
	private String postal;
	private String country;
	
	//Payment
	private int cardnum;
	private String expDate;
	private int ccv;
	private String cardType;
	
	//Review
	private ArrayList<Review> reviews;
	
	//ShoppingCart
	private ArrayList<Item> cart;
	
	//DB connector
	private DBConnector con;
	
	
	public User(String username){
		
		this.username = username;
		//this needs to change
		con = new DBConnector("localhost", "3306", "root", "admin", "Shopz");
	}
	
	
	public void load(){
		
		ResultSet result;
		//get and store info from User
		con.sqlQuery(String.format("SELECT * FROM User WHERE username=%s", this.username));
		try {
			result = con.getResult();
			result.next();
			
			name = result.getString("name");
			isAdmin = result.getBoolean("isAdmin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//get and store info from Address
		con.sqlQuery(String.format("SELECT * FROM Address WHERE username=%s", this.username));
		try {
			result = con.getResult();
			result.next();
			
			address = result.getString("address");
			city = result.getString("city");
			postal = result.getString("postal");
			country = result.getString("country");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//get and store info from payment
	}
}
