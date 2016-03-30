package LogicalLayer;

import Backend.DBConnector;

public class Review {
	
	private String reviewID;
	private String title;
	private String publishDate;
	private int stars;
	private String description;
	private String itemID;
	private DBConnector con;
	
	public Review(String reviewID, String title, String pubDate, int stars,
			String desc, String itemID){
		
		this.reviewID = reviewID;
		this.title = title;
		this.publishDate = pubDate;
		this.stars = stars;
		this.description = desc;
		this.itemID = itemID;
		
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getReviewID() {
		return reviewID;
	}


	public String getTitle() {
		return title;
	}


	public String getPublishDate() {
		return publishDate;
	}

	public int getStars() {
		return stars;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemID() {
		return itemID;
	}

	public void editDesc(String text) {
		this.setDescription(text);
		con = DBConnector.startup();
		con.DDLStatement(String.format("UPDATE Review SET description='%s' WHERE reviewID=%d", 
				text, this.getItemID()));
	}

}
