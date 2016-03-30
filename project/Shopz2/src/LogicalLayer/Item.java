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
	private DBConnector con;
	
	
	public Item(String itemID, String name, String manu, String desc, String category, float price){
		this.itemID = itemID;
		this.name = name;
		this.manu = manu;
		this.desc = desc;
		this.categ = category;
		this.price = price;
		
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

	public List<Review> getReviews(){
		return reviews;
	}
	
	public void writeReview(String title, String pubDate, int stars,
			String desc){
		con = DBConnector.startup();
		con.sqlQuery("SELECT count(*) FROM Review");
		try {
			ResultSet result = con.getResult();
			result.next();
			String reviewID = String.valueOf(result.getInt("count(*)"));
			reviews.add(new Review(reviewID, title, pubDate, stars, desc, this.itemID));
			con.DDLStatement(String.format("INSERT INTO Review VALUES ('%s', '%s', '%s', %d, '%s', '%s')", 
					reviewID, title, pubDate, stars, desc, this.itemID));
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
