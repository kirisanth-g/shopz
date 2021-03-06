package LogicalLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Backend.DBConnector;

public class User {
	public final int INVALID = -1; 
	
	
	//User
	private String username = "";
	private String name = "";
	private boolean isAdmin ;
	
	//Address
	private String address = "";
	private String city  = "";
	private String postal = "";
	private String country = "";
	
	//Payment
	private String payName = "";
	private int cardnum  = INVALID;
	private String expDate = "";
	private int ccv = INVALID;
	private String cardType = "";
	
	//ShoppingCart
	private Map<Item, Integer> cart = new HashMap<Item, Integer>();
	
	//DB connector
	private DBConnector con;
	
	
	public User(String username){
		this.username = username;
		this.load();
	}
	
	
	public void load(){
		
		ResultSet result;
		//get and store info from User
		con = DBConnector.startup();
		con.sqlQuery(String.format("SELECT * FROM User WHERE username='%s'", this.username));
		try {
			result = con.getResult();
			result.next();
			
			name = result.getString("name");
			isAdmin = result.getBoolean("isAdmin");
			System.out.println(result.getBoolean("isAdmin"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//get and store info from Address
		con.sqlQuery(String.format("SELECT * FROM Address WHERE user='%s'", this.username));
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
		con.sqlQuery(String.format("SELECT * FROM PaymentInfo WHERE username='%s'", this.username));
		try {
			result = con.getResult();
			result.next();
			
			payName = result.getString("name");
			cardnum = result.getInt("cardNumber");
			expDate = result.getString("expDate");
			ccv = result.getInt("ccv");
			cardType = result.getString("cardType");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get and store info from ShoppingCart
		con.sqlQuery(String.format("SELECT * FROM Item INNER JOIN "
				+ "(SELECT * FROM ShoppingCart WHERE Username='%s') AS T ON "
				+ "(T.item=Item.itemID)", this.username));
		try {
			result = con.getResult();
			while(result.next()){
				cart.put(new Item(Integer.toString(result.getInt("itemID")), result.getString("name"), result.getString("manufacturer"),
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
		con.DDLStatement(String.format("UPDATE User SET name='%s' WHERE username='%s'", 
				this.name, this.username));
		
		//Store Address Info
		con.DDLStatement(String.format("UPDATE Address SET name='%s', "
				+ "address='%s', city='%s', postal='%s',"
				+ "country='%s' WHERE user='%s'", 
				this.name, this.address, this.city, this.postal, this.country, 
				this.username));
		
		//Store Payment
		con.DDLStatement(String.format("UPDATE PaymentInfo SET name='%s', cardNumber=%d,"
				+ " expDate='%s', ccv=%d, cardType='%s' WHERE username='%s'", 
				this.payName, this.cardnum, this.expDate, this.ccv, this.cardType, 
				this.username));
		
		//Store Cart items and quantity
		for(Item key: cart.keySet()) {
			
			//need to check for new items added; check if exists
			con.sqlQuery(String.format("SELECT EXISTS(SELECT 1 FROM ShoppingCart WHERE Item=%d)", Integer.parseInt(key.getItemID())));
			try {
				ResultSet res = con.getResult();
				res.next();
				
				if(res.getBoolean(1)){
					//if its already in db, update
					con.DDLStatement(String.format("UPDATE ShoppingCart SET Quantity=%d WHERE Item=%d", 
							cart.get(key), Integer.parseInt(key.getItemID())));
				}
				
				else{
					//add if not, add
					con.DDLStatement(String.format("INSERT INTO ShoppingCart VALUES ('%s', %d, %d)", 
							this.username, Integer.parseInt(key.getItemID()), cart.get(key)));
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
			if(key.getItemID().equals(item.getItemID())){
				int old = cart.get(key);
				cart.remove(key);
				cart.put(item, old+quantity);
				this.store();
				return;
			}
			
		}
		//if it reaches here than that means the item was not in the
		//cached list
		cart.put(item, quantity);
		this.store();
	}
	
	/*remove a certain amount of that item and will check if difference more
	 * than quantity in cart. If it is, remove that item completely from cart*/
	public void removeFromCart(String itemID, int quantity){
		
		for(Item key: cart.keySet()){
			if(key.getItemID().equals(itemID)){
				if(cart.get(key)-quantity <= 1){
					con = DBConnector.startup();
					con.DDLStatement(String.format("DELETE FROM ShoppingCart WHERE username='%s' AND item=%d",
							this.username, Integer.parseInt(itemID)));
					cart.remove(key);
				}
				else{
					cart.put(key, cart.get(key)-quantity);
				}
				break;
			}
		}
		this.store();
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
	
	
	public String getPayName() {
		return payName;
	}


	public void setPayName(String name) {
		this.payName = name;
	}


	public boolean isAdmin() {
		return isAdmin;
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
	public boolean addItem(String name, String manu, String desc, 
			String categ, float price){
		//check if exists with same ID
//		con.sqlQuery(String.format("IF EXISTS(SELECT 1 FROM Item WHERE itemID=%d)", id));
//		try {
//			if(con.getResult().isBeforeFirst()){
//				//overwrite info
//				return con.DDLStatement(String.format("UPDATE Item SET name='%s', manufacturer='%s', "
//						+ "description='%s', category='%s', price=%.2f WHERE itemID='%s'", 
//						name, manu, desc, categ, price, id));
//			}
//			else{
//				return con.DDLStatement(String.format("INSERT INTO Item VALUES ('%s', '%s', '%s', '%s', '%s', %.2f)", 
//						id, name, manu, desc, categ, price));
//			}
//		} catch(SQLException e){
//			e.printStackTrace();
//		}
//		return false;
		
		return con.DDLStatement(String.format("INSERT INTO Item (name, manufacturer, description, category, price)"
				+ " VALUES ('%s', '%s', '%s', '%s', %.2f)", 
				name, manu, desc, categ, price));
	}
	
	//FOR ADMINS ONLY: delete item from Item table
	public boolean removeItem(String itemID){
		return con.DDLStatement(String.format("DELETE FROM Item WHERE itemID=%d", Integer.parseInt(itemID)));
	}

}
