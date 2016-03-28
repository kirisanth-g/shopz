package Backend;

import java.sql.*;

public class Admin {
	
	private String fname;
	private String lname;
	private DBConnector con;
	private String username;

	
	public Admin(String username, String first, String last){
		fname = first;
		lname = last;
		this.username = username;
		
		con = new DBConnector("localhost", "3306", "root", "admin", "Shopz");
		
	}
	
	/*ITEM BLOCK*/
	public boolean itemAdd(String id, String name, String manu, String desc, 
			String categ, float price){
		return con.DDLStatement(String.format("INSERT INTO Item VALUES (%s, %s, %s, %s, %s, %.2f)", 
				id, name, manu, desc, categ, price));
		
	}
	
	//can only edit one attribute of an item for now
	public boolean editItem(String itemID, String attrib, String value){
		return con.DDLStatement(String.format("UPDATE Item SET %s=%s WHERE itemID=%s", 
				attrib, value, itemID));
	}
	
	public boolean removeItem(String itemID){
		return con.DDLStatement(String.format("DELETE FROM Item WHERE itemID=%s", itemID));
	}
	
	
	/*INVENTORY BLOCK*/
	public boolean addInv(String itemID, String loc, int stock) {
		return con.DDLStatement(String.format("INSERT INTO Inventory VALUES (%s, %s, %d)", 
				itemID, loc, stock));
	}
	
	public boolean editInv(String itemID, String attrib, String value) {
		return con.DDLStatement(String.format("UPDATE Inventory SET %s=%s WHERE itemID=%s", 
				attrib, value, itemID));
	}
	
	public boolean removeInv(String itemID){
		return con.DDLStatement(String.format("DELETE FROM Inventory WHERE itemID=%s", itemID));
	}
	
	/*REVIEW BLOCK*/
	public boolean editRev(String reviewID, String attrib, String value) {
		return con.DDLStatement(String.format("UPDATE Review SET %s=%s WHERE itemID=%s", 
				attrib, value, reviewID));
	}
	
	public boolean removeRev(String reviewID) {
		return con.DDLStatement(String.format("DELETE FROM Review WHERE itemID=%s", reviewID));
	}
}
