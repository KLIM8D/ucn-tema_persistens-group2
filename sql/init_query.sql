CREATE TABLE "Contacts"  ( 
	"phoneNo"	int NOT NULL,
	"name"   	varchar(255) NOT NULL,
	"address"	varchar(255) NOT NULL,
	"zipCode"	int NOT NULL,
	"city"   	varchar(255) NOT NULL,
	"email"  	varchar(255) NULL,
	"country"	varchar(2) NOT NULL,
	CONSTRAINT "PK_Contacts" PRIMARY KEY ("phoneNo")
	)
GO
CREATE TABLE "Customer"  ( 
	"contactsKey"	int NOT NULL,
	"discount"   	decimal(15,5) NOT NULL,
	"isBusiness" 	bit NOT NULL 
	)
GO
CREATE TABLE "DeliveryStatus"  ( 
	"deliveryId"   	int IDENTITY(1,1) NOT NULL,
	"deliveryState"	varchar(255) NOT NULL,
	CONSTRAINT "PK_DeliveryStatus" PRIMARY KEY ("deliveryId")
	)
GO
CREATE TABLE "Invoice"  ( 
	"orderKey"   	int NOT NULL,
	"invoiceNo"  	int NOT NULL,
	"paymentDate"	date NOT NULL 
	)
GO
CREATE TABLE "OrderItems"  ( 
	"orderKey"  	int NOT NULL,
	"productKey"	int NOT NULL,
	"quantity"  	int NOT NULL,
	"unitPrice" 	decimal(15,5) NOT NULL 
	)
GO
CREATE TABLE "ProductCategory"  ( 
	"categoryId"  	int IDENTITY(1,1) NOT NULL,
	"categoryName"	varchar(255) NOT NULL,
	CONSTRAINT "PK_ProductCategory" PRIMARY KEY ("categoryId")
	)
GO
CREATE TABLE "ProductData"  ( 
	"productKey"    	int NOT NULL,
	"attribute"     	varchar(255) NOT NULL,
	"attributeValue"	varchar(255) NOT NULL 
	)
GO
CREATE TABLE "Products"  ( 
	"productId"      	int IDENTITY(1,1) NOT NULL,
	"contactsKey"    	int NOT NULL,
	"categoryKey"    	int NOT NULL,
	"name"           	varchar(255) NOT NULL,
	"purchasePrice"  	decimal(15,5) NOT NULL,
	"salesPrice"     	decimal(15,5) NOT NULL,
	"rentPrice"      	decimal(15,5) NOT NULL,
	"countryOfOrigin"	varchar(2) NOT NULL,
	"minimumStock"   	int NOT NULL,
	CONSTRAINT "PK_Products" PRIMARY KEY ("productId")
	)
GO
CREATE TABLE "SalesOrder"  ( 
	"orderId"     	int IDENTITY(100,1) NOT NULL,
	"contactsKey" 	int NOT NULL,
	"deliveryKey" 	int NOT NULL,
	"orderDate"   	datetime NOT NULL,
	"deliveryDate"	date NOT NULL,
	CONSTRAINT "PK_SalesOrder" PRIMARY KEY ("orderId")
	)
GO
CREATE TABLE "Supplier"  ( 
	"contactsKey"  	int NOT NULL,
	"contactPerson"	varchar(255) NOT NULL,
	"bankAccount"  	varchar(255) NOT NULL 
	)
GO
ALTER TABLE "Supplier"
	ADD CONSTRAINT "Contacts_Supplier"
	FOREIGN KEY("contactsKey")
	REFERENCES "Contacts"("phoneNo")
GO
ALTER TABLE "Customer"
	ADD CONSTRAINT "Contacts_Customer"
	FOREIGN KEY("contactsKey")
	REFERENCES "Contacts"("phoneNo")
GO
ALTER TABLE "SalesOrder"
	ADD CONSTRAINT "Contacts_SalesOrder"
	FOREIGN KEY("contactsKey")
	REFERENCES "Contacts"("phoneNo")
GO
ALTER TABLE "Products"
	ADD CONSTRAINT "Contacts_Products"
	FOREIGN KEY("contactsKey")
	REFERENCES "Contacts"("phoneNo")
GO
ALTER TABLE "SalesOrder"
	ADD CONSTRAINT "DeliveryStatus_SalesOrder"
	FOREIGN KEY("deliveryKey")
	REFERENCES "DeliveryStatus"("deliveryId")
GO
ALTER TABLE "Products"
	ADD CONSTRAINT "ProductCategory_Products"
	FOREIGN KEY("categoryKey")
	REFERENCES "ProductCategory"("categoryId")
GO
ALTER TABLE "ProductData"
	ADD CONSTRAINT "Products_ProductsData"
	FOREIGN KEY("productKey")
	REFERENCES "Products"("productId")
GO
ALTER TABLE "OrderItems"
	ADD CONSTRAINT "Products_OrderItems"
	FOREIGN KEY("productKey")
	REFERENCES "Products"("productId")
GO
ALTER TABLE "Invoice"
	ADD CONSTRAINT "SalesOrder_Invoice"
	FOREIGN KEY("orderKey")
	REFERENCES "SalesOrder"("orderId")
GO
ALTER TABLE "OrderItems"
	ADD CONSTRAINT "SalesOrder_OrderItems"
	FOREIGN KEY("orderKey")
	REFERENCES "SalesOrder"("orderId")
GO