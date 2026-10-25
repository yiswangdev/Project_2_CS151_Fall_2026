Restaurant Operating System

A Java application for simulating a restaurant ordering system.

This project is for CS151 at San Jose State University during Fall 2026

# Overview

The Restaurant Operating System simulates the process of ordering and serving food at a restaurant. Customers can select menu items and quantities, while servers and cooks handle orders throughout the food preparantion process.
Features:
- Create and manage customer's orders
- Select menu items and their quantities
- Assign serves and cooks to orders
- Track order status

Workflow:
1. A customer is seated and greeted with their server
2. A customer creates an order
3. A server submits the order to the kitchen
4. A cook prepares the order
5. The order is marked ready and delivered by the server

# Developers:
Eric Wang - Main, Customer, Restaurant
Maria Fediakina - MenuItem, Table, Server
Margarita Sergienko - OrderItem, README, Cook
Isaac Guevarra - OrderHandler, Employee, Order

# Design:

Classes:
- Customer: restaurant customer, the user
- MenuItem: menu item
- Order: stores order details and status
- OrderItem: Represents a menu item and its quantity within an order
- Employee: stores shared employee information
- Server: handles customer orders
- Cook: prepares food orders
- Table: Represents a restaurant table, including its table number and availability, and the customer or party seated at it
- OrderHandler: An interface that defines the methods employees use to handle orders, such as accepting orders, updating their status, and finishing orders
- Restaurant: Manages the overall restaurant system, coordinating components such as tables, the menu, employees, and customer orders
- Main: Contains the program's entry point and starts the application

# Installation Instructions

1. Install the Java Development Kit (JDK).
2. Clone the repository:
git clone (url)
3. Navigate to the project directory:
cd (repository directory)
4. Open the project in a Java-compatible IDE, such as IntelliJ IDEA, or compile and run it using the command line.
5. Follow instructions in the terminal

# Usage
1. Run the application's main class.
2. Follow the console prompts to interact with the restaurant system.
3. Select an available table and interact with the assigned server.
4. Browse the menu and select items and quantities.
5. Follow the order as it is assigned to a cook, prepared, and marked ready.

# Contributions

All team members contributed to the development of the Restaurant Operating System as part of the CS151 course project. Everyone: 
- Used descriptive commit messages to document changes.
- Created a branch for new features or fixes when appropriate.
- Submited changes through pull requests for review before merging into the main branch.


Eric Wang: 
- fixed folder name and package conflicts
- Completed core functionality of Main, Customer, Restaraunt
    -> added options to main, added import for interface
- made all files compatabile with each other 

Maria Fediakina:
- Completed core functionality of MenuItem, Table, Server
    - fixed bugs and made sure they were compatible with main
- added toString() to each class
- enabled junit tests
- finalized README.md
- removed deadcode and polished final code

Margarita Sergienko: 
- add Order class variables getters constructor
- add Server class methods and order handling
- Completed core functionality of OrderItem, Cook
- worked on core of README
- implemented block of duplicate objects
- added try and catch blocks in main 

Isaac Guevarra: 
- Completed core functionality of OrderHandler, Employee, Order 
- fixed bug with package in Table.java 
- changed and implemented methods for order 
- fixed main() logic accordingly 
    - fixed issues with overlapping output messages in main 
    - readded missing scannerHelper into main 
- added utils/ClassInstanceLimit.java to deal with max instances 
- added exceptions/InstanceLimitException.java 
- added 'exit' feature in main  
- made sure methods in Cook, Customer, Order were modifying properties of itself or other classes 


notes:
- we used Claude a couple times to help with merges as we had a had challenge working with git 
- we all helped each other with contributions  
