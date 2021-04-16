# Application Development - Order Computerparts.
#### Data 1600 - Final Project

### Assignment
---
In this assignment we were tasked with creating a system for ordering computer parts. The assignment demanded that we had a login system that differentiated between regular and admin users. Admin users would get a page where they could add new components and manage other users. While regular users, or customers, would get a page where they could select computerparts and buy them. 

It also tasked us with writing to and reading from file as a option to a fully fledged DB solution.

### Restrictions
---
We were restricted to use JavaFX to create a minimal user interface. JavaFX however does not support serialization which is needed to save and load files with information. Because of this we had to manualy serialize many of the java classes. 

### Solution
---
The solution we made uses files to save and load both registered users and shopping carts. We also found some nice ways to utilize generic java classes, where we wanted to do the same thing for both users and shopping cart. 
