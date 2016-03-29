package LogicalLayer;

public class Login {
	private static  User currentlyLoggedin = new User("username"); 
	public static User getCurrentUser(){
		return currentlyLoggedin; 
	}
	public static boolean authenticate(String username, String password){
		//if you want to hash, this is where to do it
		//query the db for users, pass and do string comparison
		//also set the currentlyLogged in User for quick address
		return true; 
	}
}
