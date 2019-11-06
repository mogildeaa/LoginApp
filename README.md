*This program is designed to **replicate** a **login** and **register** functionality on a **web platform**.*

--- 
### Key elements:
- use of java Logger.
- use of java EmailValidator
- Java OOP concepts
- Java S.O.L.I.D. principles

### Scenario (Main.java):
1. A new user wants to register as a new user on a web platform.
2. He must fill up a register form (*Form.java*) - here the validation occurs (valid email, good password)
3. After validation, save the new user to the database. 
<br>

### Observation!
To be able to validate a user's login data, access to a database is required - therefore we will simulate the existence of one, without actually implementing a driver or having a management system installed (keeping things simple).
We will have a Database created with the use of **Java List** and **Java Map**. The initial database will be populated only by a **utility class** (*DatabaseData.java*).
The downside of not using a management system or implement a driver is that the Database is not stored, which means that a new one will be created at every run. This makes the app very **resource intensive** and it's not a good practice.
