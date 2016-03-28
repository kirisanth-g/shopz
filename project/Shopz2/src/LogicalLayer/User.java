package LogicalLayer;

import java.sql.SQLException;

import Backend.DBConnector;

public class User {
	
	//from user
	private String username;
	private String fname;
	private String lname;
	private boolean isAdmin;
	
	//Address
	private String address;
	private String city;
	private String postal;
	private String country;
	
	//
	
	private DBConnector con;
	
	
	public User(String user, String first, String last){
		fname = first;
		lname = last;
		this.username = user;
		
		//this needs to change
		con = new DBConnector("localhost", "3306", "root", "admin", "Shopz");
	}
	
	/*ADDRESS BLOCK*/
	public boolean addAdd(String name, String address, String city, String postal, String country) {
		
		con.DDLStatement(String.format("IF EXISTS(SELECT 1 FROM Address WHERE user=%s)", username));
		
		try {
			//if user doesn't have an address already saved
			if(con.getResult().isBeforeFirst()){
				//add
				return con.DDLStatement(String.format("INSERT INTO Address VALUES (%s, %s, %s, %s, %s)", 
						this.username, name, address, city, postal, country));
			}			
			//else, overwrite it
			this.removeAdd();
			return con.DDLStatement(String.format("INSERT INTO Address VALUES (%s, %s, %s, %s, %s)", 
					this.username, name, address, city, postal, country));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean editAdd(String attrib, String value) {
		return con.DDLStatement(String.format("UPDATE Address SET %s=%s WHERE user=%s", 
				attrib, value, this.username));
	}
	
	public boolean removeAdd(){
		
		return con.DDLStatement(String.format("DELETE FROM Address WHERE user=%s", username));
	}
	
	//PAYMENT INFO BLOCK
	public boolean addPayment(String name, int cardno, String expDate, int ccv, String type) {
		
	}
}
