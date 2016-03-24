ShoppingCart(Username(str),Item(str), Quantity(int), Total(float))


CREATE TABLE ShoppingCart(
	Username varchar(255), 
	Item varchar(255) NOT NULL, 
	Quantity int NOT NULL, 
	FOREIGN KEY (Username) REFERENCES User(username)
	);

CREATE TABLE Inventory(
	itemID varchar(255) NOT NULL, 
	locations varchar(255) NOT NULL, 
	stock int NOT NULL, 
	FOREIGN KEY (itemID) REFERENCES Item(itemID),  
	CONSTRAINT i_ID PRIMARY KEY (itemID, locations)
	); 

CREATE TABLE PaymentInfo(
	username varchar(255), 
	name varchar(255), 
	cardNumber int NOT NULL, 
	expDate varchar(255) NOT NULL, 
	ccv int NOT NULL, 
	cardType varchar(255) NOT NULL, 
	FOREIGN KEY (username) REFERENCES User(username)
	);