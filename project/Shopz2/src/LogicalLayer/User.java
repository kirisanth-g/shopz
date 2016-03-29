package LogicalLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Backend.DBConnector;

public class User {
	
	//User
	private String username;
	private String name;
	private boolean isAdmin;
	
	//Address
	private String address;
	private String city;
	private String postal;
	private String country;
	
	//Payment
	private int cardnum;
	private String expDate;
	private int ccv;
	private String cardType;
	
	//ShoppingCart
	private Map<Item, Integer> cart = new HashMap<Item, Integer>();
	
	//DB connector
	private DBConnector con;
	
	
	public User(String username){
		
		this.username = username;
		//this needs to change
		con = new DBConnector("localhost", "3306", "root", "", "Shopz");
	}
	
	
	public void load(){
		
		ResultSet result;
		//get and store info from User
		con.sqlQuery(String.format("SELECT * FROM User WHERE username=%s", this.username));
		try {
			result = con.getResult();
			result.next();
			
			name = result.getString("name");
			isAdmin = result.getBoolean("isAdmin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//get and store info from Address
		con.sqlQuery(String.format("SELECT * FROM Address WHERE user=%s", this.username));
		try {
			result = con.getResult();
			result.next();
			
			address = result.getString("address");
			city = result.getString("city");
			postal = result.getString("postal");
			country = result.getString("country");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		//get and store info from payment
		con.sqlQuery(String.format("SELECT * FROM PaymentInfo WHERE username=%s", this.username));
		try {
			result = con.getResult();
			result.next();
			
			cardnum = result.getInt("cardNumber");
			expDate = result.getString("expDate");
			ccv = result.getInt("ccv");
			cardType = result.getString("cardType");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get and store info from ShoppingCart
		con.sqlQuery(String.format("SELECT * FROM (Item INNER JOIN "
				+ "(SELECT * FROM ShoppingCart WHERE Username=%s)) ON "
				+ "(ShoppingCart.item=Item.itemID)", this.username));
		try {
			result = con.getResult();
			while(result.next()){
				cart.put(new Item(result.getString("itemID"), result.getString("name"), result.getString("manufacturer"),
						result.getString("description"), result.getString("category"), result.getFloat("price")),
						result.getInt("Quantity"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void store(){
		//Store User info,
		con.DDLStatement(String.format("UPDATE User SET name=%s WHERE user=%s", 
				this.name, this.username));
		
		//Store Address Info
		con.DDLStatement(String.format("UPDATE Address SET name='%s', "
				+ "address='%s', city='%s', postal='%s',"
				+ "country='%s' WHERE user=%s", 
				this.name, this.address, this.city, this.postal, this.country, 
				this.username));
		
		//Store Payment
		con.DDLStatement(String.format("UPDATE PaymentInfo SET name=%s, cardNumber=%d,"
				+ " expDate='%s', ccv=%d, cardType='%s' WHERE user=%s", 
				this.name, this.cardnum, this.expDate, this.ccv, this.cardType, 
				this.username));
		
		//Store Cart items and quantity
		for(Item key: cart.keySet()) {
			
			//need to check for new items added; check if exists
			con.sqlQuery(String.format("IF EXISTS(SELECT 1 FROM ShoppingCart WHERE Item=%s)", key.getItemID()));
			try {
				if(con.getResult().isBeforeFirst()){
					//if its already in db, update
					con.DDLStatement(String.format("UPDATE ShoppingCart SET Quantity=%d WHERE Item=%s", 
							cart.get(key), key.getItemID()));
				}
				
				else{
					//add if not, add
					con.DDLStatement(String.format("INSERT INTO ShoppingCart VALUES (%s, %s, %d)", 
							this.username, key.getItemID(), cart.get(key)));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//should add to cached cart or update the quantity of it
	public void addToCart(Item item, int quantity){
		for(Item key: cart.keySet()){
			if(key.getItemID() == item.getItemID()){
				cart.put(key, cart.get(key)+quantity);
				return;
			}
			
		}
		//if it reaches here than that means the item was not in the
		//cached list
		cart.put(item, quantity);
	}
	
	/*remove a certain amount of that item and will check if difference more
	 * than quantity in cart. If it is, remove that item completely from cart*/
	public void removeFromCart(String itemID, int quantity){
		
		for(Item key: cart.keySet()){
			if(key.getItemID() == itemID){
				if(cart.get(key)-quantity <= 1){
					cart.remove(key);
				}
				else{
					cart.put(key, cart.get(key)-quantity);
				}
				break;
			}
		}
		
	}
	
	public String getUsername() {
		return username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isAdmin() {
		return true;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostal() {
		return postal;
	}


	public void setPostal(String postal) {
		this.postal = postal;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public int getCardnum() {
		return cardnum;
	}


	public void setCardnum(int cardnum) {
		this.cardnum = cardnum;
	}


	public String getExpDate() {
		return expDate;
	}


	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}


	public int getCcv() {
		return ccv;
	}


	public void setCcv(int ccv) {
		this.ccv = ccv;
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public Map<Item, Integer> getCart() {
		return cart;
	}


	public void setCart(Map<Item, Integer> cart) {
		this.cart = cart;
	}

	
	public void logout(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*FOR ADMINS ONLY: this will overwrite the info of the item associated to that ID so its like an add/edit
	  for Item table*/
	public boolean addItem(String id, String name, String manu, String desc, 
			String categ, float price){
		//check if exists with same ID
		con.sqlQuery(String.format("IF EXISTS(SELECT 1 FROM Item WHERE itemID=%s)", id));
		try {
			if(con.getResult().isBeforeFirst()){
				//overwrite info
				return con.DDLStatement(String.format("UPDATE Item SET name=%s, manufacturer=%s, "
						+ "description=%s, category=%s, price=%.2f WHERE itemID=%s", 
						name, manu, desc, categ, price, id));
			}
			else{
				return con.DDLStatement(String.format("INSERT INTO Item VALUES (%s, %s, %s, %s, %s, %.2f)", 
						id, name, manu, desc, categ, price));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	//FOR ADMINS ONLY: delete item from Item table
	public boolean removeItem(String itemID){
		return con.DDLStatement(String.format("DELETE FROM Item WHERE itemID=%s", itemID));
	}

}
