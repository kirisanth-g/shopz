package LogicalLayer;

public class Review {
	
	private String reviewID;
	private String title;
	private String publishDate;
	private int stars;
	private boolean helpful;
	private String description;
	private String itemID;
	
	public Review(String reviewID, String title, String pubDate, int stars, 
			boolean helpful, String desc, String itemID){
		
		this.reviewID = reviewID;
		this.title = title;
		this.publishDate = pubDate;
		this.stars = stars;
		this.helpful = helpful;
		this.description = desc;
		this.itemID = itemID;
		
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

	public boolean isHelpful() {
		return helpful;
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

}
