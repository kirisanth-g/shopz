package LogicalLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Backend.DBConnector;

public class Item {
	
	private String itemID;
	private String name;
	private String manu;
	private String desc;
	private String categ;
	private float price;
	private List<Review> reviews;
	private static DBConnector con;
	
	private static Item itemSelected; 
	
	
	public Item(String itemID, String name, String manu, String desc, String category, float price){
		this.itemID = itemID;
		this.name = name;
		this.manu = manu;
		this.desc = desc;
		this.categ = category;
		this.price = price;
		
		this.pullReview();
	}

	public List<Review> getReviews(){
		return reviews;
	}
	
	public void writeReview(String title, int stars,
			String desc){
		con = DBConnector.startup();
		con.DDLStatement(String.format("INSERT INTO Review (title, stars, description, item) VALUES ('%s', %d, '%s', %d)", 
				title, stars, desc, Integer.parseInt(this.itemID)));
		this.pullReview();
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void insertItem(String name, String manu, String desc, String category, float price){
		con = DBConnector.startup();
		
		con.DDLStatement(String.format("INSERT INTO Item (name, manufacturer, description, category, price) VALUES ('%s', '%s', '%s', '%s', %.2f)", 
				name, manu, desc, category, price));
	}
	
	public String getItemID() {
		return itemID;
	}


	public void setItemID(String itemID) {
		this.itemID = itemID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getManu() {
		return manu;
	}


	public void setManu(String manu) {
		this.manu = manu;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getCateg() {
		return categ;
	}


	public void setCateg(String categ) {
		this.categ = categ;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}
	
	public static Item getItemSelected(){
		return itemSelected;
	}
	
	public static void setItemSelected(Item item ){
		itemSelected = item ;
	}
	
	public void pullReview(){
		reviews = new ArrayList<Review>();
		
		con = DBConnector.startup();
		con.sqlQuery(String.format("SELECT * FROM Review WHERE item='%s'" , itemID));
		try {
			ResultSet dbresults = con.getResult();
			while(dbresults.next()){
				reviews.add(new Review(dbresults.getString("reviewID"),dbresults.getString("title"),
						dbresults.getString("publishDate"), dbresults.getInt("stars"),
						dbresults.getString("description"), dbresults.getString("item")));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
