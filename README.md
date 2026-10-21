Restaurant Operating System

A Java application for simulating a restaurant ordering system.

This project is for CS151 at San Jose State University during Fall 2026

Overview

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

Developers:
Eric Wang - Main, Customer, Restaurant
Maria Fediakina - MenuItem, Table, Server
Margarita Sergienko - OrderItem, README, Cook
Isaac Guevarra - OrderHandler, Employee

Design:

Classes:
- Customer: restaurant customer, the user
- MenuItem: menu item
- Order: stores order details and status
- OrderItem: Represents a menu item and its quantity within an order
- Employee: stores shared employee information
- Server: handles customer orders
- Cook: prepares food orders
- Table:
- OrderHandler:
- Restaurant: 
- Main:

Installation Instructions

1. Install the Java Development Kit (JDK).
2. Clone the repository:
git clone (url)
3. Navigate to the project directory:
cd (repository directory)
4. Open the project in a Java-compatible IDE, such as IntelliJ IDEA, or compile and run it using the command line.
5. Follow instructions in the terminal

Usage
1. Run the application's main class.
2. Follow the console prompts to interact with the restaurant system.
3. Select an available table and interact with the assigned server.
4. Browse the menu and select items and quantities.
5. Follow the order as it is assigned to a cook, prepared, and marked ready.

Contributions

All team members contributed to the development of the Restaurant Operating System as part of the CS151 course project. Everyone: 
- Used descriptive commit messages to document changes.
- Created a branch for new features or fixes when appropriate.
- Submited changes through pull requests for review before merging into the main branch.