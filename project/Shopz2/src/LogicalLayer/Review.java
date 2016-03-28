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

	public void setReviewID(String reviewID) {
		this.reviewID = reviewID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public boolean isHelpful() {
		return helpful;
	}

	public void setHelpful(boolean helpful) {
		this.helpful = helpful;
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

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
}
