//DBContact.java - INSERT
INSERT INTO Contacts (phoneNo, name, address, zipCode, city, email, country) VALUES (?, ?, ?, ?, ?, ?, ?)

//DBCustomers.java - INSERT
INSERT INTO Customer (contactsKey, discount, isBusiness) VALUES (?, ?, ?)

//DBSupplier.java - INSERT
INSERT INTO Supplier (contactsKey, contactPerson, bankAccount) VALUES (?, ?, ?)

//DBProductCategory.java - INSERT
INSERT INTO ProductCategory (categoryName) VALUES (?)

//DBProduct.java - INSERT
INSERT INTO Products (contactsKey, categoryKey, name, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minimumStock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)

//DBProductData.java - INSERT
INSERT INTO ProductData (productKey, attribute, attributeValue) VALUES (?, ?, ?)

//DBSalesOrder.java - INSERT
INSERT INTO SalesOrder (contactsKey, deliveryKey, orderDate, deliveryDate) VALUES (?, ?, ?, ?)

//DBDeliveryStatus.java - INSERT
INSERT INTO DeliveryStatus (deliveryState) VALUES (?)

//DBOrderItems.java - INSERT
INSERT INTO OrderItems (orderKey, productKey, quantity, unitPrice) VALUES (?, ?, ?, ?)

//DBInvoice.java - INSERT
INSERT INTO Invoice (orderKey, invoiceNo, paymentDay) VALUES (?, ?, ?)