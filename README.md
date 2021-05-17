# General Description:
This application aims to help manage the items in the shop and also help customers purchase them online.
# Registration:
The user needs to first register into the application by selecting one of the 3 roles: customer, shop employee or administrator. 
Each role require a unique username, a password, full name and phone number. 
### Administrator: 
After the administrator logs in, he can add, edit or delete items from the shop. 
An item should contain its name, price a picture and optionally a description. 
### Customer:
A customer needs to login into the application where he will be able to see a list with all the items.
A logged in customers can select any quantity for any item, and create an order containing the selected items. 
Also he can see a list of past orders, with their status (accepted, rejected, pending or delivered). 
If the order is rejected, the rejection reason will also be displayed. 
If the order has been accepted, the estimated delivery time will also be displayed. 
### Shop Employee:
After a shop employee logs in, he can process orders, assure home delivery, update order's status and getting customer's delivery-time feedback.

# To run this project you need:

## oracle db xe 11g

# Run in console:

## mvn -N io.takari:maven:wrapper

## ./mvnw clean install 

## cd target

## java -jar MarketingApp.jar
