package LogicalLayer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Backend.DBConnector;

/**
 * Handles all search queries form the UI layer, into the db
 * @author Terence
 *
 */
public class Search {
	private DBConnector con;
	private ResultSet dbresults;
	private List<Item> results = new ArrayList<Item>(); 
	
	public Search(){
		con = new DBConnector("localhost", "3306", "root", "admin", "Shopz");
	}
	
	
}
