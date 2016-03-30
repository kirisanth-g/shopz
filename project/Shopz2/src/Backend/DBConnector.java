package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**A wrapper for the JDBC so you can connect, disconnect, 
 * processing statements, etc.**/

public class DBConnector {
	
	Connection connection = null;
	private static final String driverName = "com.mysql.jdbc.Driver";
	private String url;
	private String user;
	private String pw;
	private Statement stmt; 
	private ResultSet result;
	private static DBConnector con;
	
	public DBConnector(String server, String port, String user, String pw, String db){
		DBConnector.con = this;
		this.pw = pw;
		this.user = user;
		this.url = "jdbc:mysql://" + server + ":" + port +
				"/" + db; //add "/dbname" to connect to db
		
		this.doConnection();
		try {
			this.stmt = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isOpened (){
        return connection != null;
    }
	
	public boolean doConnection(){
		try {
		// Load the JDBC driver
		Class.forName(driverName);
		// Create a connection to the database
		connection = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("ClassNotFoundException : "+e.getMessage());
			return false;
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public void close() throws SQLException{
		if(this.isOpened()){
			connection.close();
			stmt.close();
			result.close();
		}
	}
	
	public ResultSet getResult(){
		return result;
	}
	
	//this is only for create/insert/update statements
	public boolean DDLStatement(String query){
		try {
			this.stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//this is for doing queries on database
	public boolean sqlQuery(String query){
		result = null;
		try{
			result = this.stmt.executeQuery(query);
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//if you really need to do something with the connector
	public Connection getConnection(){
		return connection;
	}
}