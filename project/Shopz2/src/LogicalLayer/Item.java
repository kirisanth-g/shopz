package Backend;

public class Item {
	
	private String itemID;
	private String name;
	private String manu;
	private String desc;
	private String categ;
	private float price;
	
	
	public Item(String itemID, String name, String manu, String desc, String category, float price){
		this.itemID = itemID;
		this.name = name;
		this.manu = manu;
		this.desc = desc;
		this.categ = category;
		this.price = price;
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
