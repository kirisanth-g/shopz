package UI;

import Backend.DBConnector;

public class Tester {


	public static void main(String[] args){
		new DBConnector("localhost", "3306", "root", "", "Shopz");
		ViewController.init(); 
	}

}