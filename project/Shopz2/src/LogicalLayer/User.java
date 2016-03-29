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
	
	//ShoppingCart
	private ArrayList<Item> cart;
	
	//DB connector
	private DBConnector con;
	
	
	public User(String username){
		
		this.username = username;
		//this needs to change
		con = new DBConnector("localhost", "3306", "root", "", "Shopz");
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
		con.sqlQuery(String.format("SELECT * FROM Address WHERE user=%s", this.username));
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
		con.sqlQuery(String.format("SELECT * FROM PaymentInfo WHERE username=%s", this.username));
		try {
			result = con.getResult();
			result.next();
			
			cardnum = result.getInt("cardNumber");
			expDate = result.getString("expDate");
			ccv = result.getInt("ccv");
			cardType = result.getString("cardType");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get and store info from ShoppingCart
		con.sqlQuery(String.format("SELECT * FROM (Item INNER JOIN "
				+ "(SELECT * FROM ShoppingCart WHERE Username=%s)) ON "
				+ "(ShoppingCart.item=Item.itemID)", this.username));
		try {
			result = con.getResult();
			while(result.next()){
				cart.add(new Item(result.getString("itemID"), result.getString("name"), result.getString("manufacturer"),
						result.getString("description"), result.getString("category"), result.getFloat("price")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isAdmin() {
		return true;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostal() {
		return postal;
	}


	public void setPostal(String postal) {
		this.postal = postal;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public int getCardnum() {
		return cardnum;
	}


	public void setCardnum(int cardnum) {
		this.cardnum = cardnum;
	}


	public String getExpDate() {
		return expDate;
	}


	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}


	public int getCcv() {
		return ccv;
	}


	public void setCcv(int ccv) {
		this.ccv = ccv;
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public ArrayList<Item> getCart() {
		return cart;
	}


	public void setCart(ArrayList<Item> cart) {
		this.cart = cart;
	}


	public DBConnector getCon() {
		return con;
	}


	public void setCon(DBConnector con) {
		this.con = con;
	}

}
