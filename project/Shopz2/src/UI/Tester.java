package UI;

import Backend.DBConnector;

public class Tester {


	public static void main(String[] args){
		DBConnector.setup("localhost", "3306", "root", "", "Shopz");
		ViewController.init(); 
	}

}