package LogicalLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Backend.DBConnector;

/**
 * Handles all search queries form the UI layer, into the db
 * @author Terence, Kiwi
 *
 */
public class Search {
	private static DBConnector con;
	private static ResultSet dbresults;
	private static List<Item> results;
	
	public static void search(String q){
		con = DBConnector.startup();
		results = new ArrayList<Item>(); 
		con.sqlQuery(String.format("SELECT * FROM Item WHERE name like '%s%%' OR "
				+ "Manufacturer LIKE '%s%%' OR Description LIKE '%s%%' OR "
				+ "Category LIKE '%s%%'" , q, q, q, q));
		
		try {
			dbresults = con.getResult();
			while(dbresults.next()){
				results.add(new Item(dbresults.getString("itemID"), dbresults.getString("name"), dbresults.getString("manufacturer"),
						dbresults.getString("description"), dbresults.getString("category"), dbresults.getFloat("price")));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Item> getResults(){
		return results;
	}
	
}
