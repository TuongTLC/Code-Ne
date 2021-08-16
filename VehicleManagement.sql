use VehicleManagement;
GO
--Login
create table tblRoles(
	roleID int  primary key not null,
	roleName nvarchar(50) not null,
	description nvarchar(200),
);
create table tblUsers(
	userID int primary key not null,
	userName nvarchar(50) not null,
	userFullName nvarchar(50) not null,
	password nvarchar(50) not null,
	roleID int not null,
	FOREIGN KEY (roleID) REFERENCES tblRoles(roleID)
);
--CRUD
create table tblBrands(
	brandID int primary key not null,
	brandName nvarchar(50) not null,
	description nvarchar(200),
);
create table tblProducts(
	productID int primary key not null,
	productName nvarchar(50) not null,
	productPrice float not null,
	description nvarchar(200),
	brandID int not null,
	quantity int not null,
	sold int not null,
	FOREIGN KEY (brandID) REFERENCES tblBrands(brandID)
);
--Order
create table tblOrders(
	orderID int primary key not null,
	userID int not null,
	orderDate date not null,
	shipAddress nvarchar(50) not null,
	total float not null,
	FOREIGN KEY (userID) REFERENCES tblUsers(userID)
);
create table tblOrderItems(
	orderID int not null,
	productID int not null,
	quantity int not null,
	PRIMARY KEY (orderID,productID),
	FOREIGN KEY (orderID) REFERENCES tblOrders(orderID),
	FOREIGN KEY (productID) REFERENCES tblProducts(productID),
);