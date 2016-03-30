package LogicalLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import Backend.DBConnector;

public class Login {
	private static  User currentlyLoggedin; 
	public static User getCurrentUser(){
		return currentlyLoggedin; 
	}
	//DB connector
	private static DBConnector con = new DBConnector("localhost", "3306", "root", "", "Shopz");
	
	public static boolean authenticate(String username, String password){
		//if you want to hash, this is where to do it
		//query the db for users, pass and do string comparison
		//also set the currentlyLogged in User for quick address
		ResultSet result;
		con.sqlQuery(String.format("SELECT * FROM User WHERE username='%s'", username));
		try {
			result = con.getResult();
			if(!result.next()){
				return false;
			}else if(password.equals(result.getString("password"))){
				currentlyLoggedin = new User(username);
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}
}
