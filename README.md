# Midnight Munchies: Sandwich Maker - GUI Database Project for OOP Class
<h2>Description</h2>
Do you get midnight cravings but never know what snack to make? With Sandwich Maker, you won't have to spend an hour digging through your cabinets to find the perfect combination of foods that would satisfy your hunger. Create AWESOME almost realistic virtual sandwiches out of products you probably have in your kitchen. You'll be able to save your favored creations for later review; make sure you give it an awesome name!

<h2>Overview</h2>
<img src="https://github.com/KodingKamp/projectOOP/raw/master/src/images/MidnightMunchiesSandwichMaker.gif" />
<br>
This Java based application shall allow users to create, view, edit, and delete customized sandwich options.<br>
Users shall be able to:
<ul>
  <li>select different bread options for the top and bottom bun individually,</li>
  <li>select various meat options,</li>
  <li>select whether or not they want cheese,</li> 
  <li>name their sandwich creation,</li>
  <li>save creations to a database,</li>
  <li>view saved creations,</li>
  <li>edit the name of saved creations, and</li>
  <li>delete saved creations.</li>
</ul>
Anytime during sandwich creation, users shall also be able to reset all the options to their default settings.
<h2>DataBase</h2>
The database is created through Apache's embedded Derby database driver and has one table named SANDWICHES that has 5 fields:
<ul>
  <li>A VarChar NAME</li>
  <li>an Integer TOPINDEX</li>
  <li>an Integer MIDINDEX</li>
  <li>an Integer BOTTOMINDEX</li>
  <li>a Boolean CHEESE.</li>
</ul>
The NAME field holds a string representing the name of the record. The TOPINDEX, MIDINDEX and BOTTOMINDEX fields holds integers that represent the index of respective arrays that store the url of associated food items. Finally, the CHEESE field holds a boolean the represents where the user's creation contains the cheese item. A database diagram is shown below.
<br>
<br>
<img src="https://github.com/KodingKamp/projectOOP/raw/master/src/images/Database_Diagram.PNG" />
<br>
<hr />
<h2>GUI Design Principles</h3>
Adhering to the GUI Design Principles, this program groups buttons into container menus to aesthetically please the user and directs their focus to important elements in the scene. The visual elements of every button interaction such as its displayed text and effects provide the user with clarity with the functionality of that button. The program has a simplistic layout and design that allows the user to easily predict and understand the functionality of the elements of each scene. The user would be able to learn what to do and how to do it with the help of visual queues and pop-up alerts to aid in the use of the program. Each scene shares the same characteristics maintaining a consistency amoung the elements in the scenes. The user has full control as all the functionality rely on the interaction between the user and the buttons on screen. Alerts inform users of their actions and adds a level of forgiveness if the user interacts with certain buttons out of curiousity or by mistake. Having taken common material from the real world, this program brings familiarity to the users and they are able to relate to the elements.
<hr />
<h2>Credit</h2>
Kamp Duong (Program Creator/Graphical Designer), Scott Vanselow (Code Contributor), Brandon Schultz (Code Contributer), Aleksandar Pasaric (Wallpaper Artist)
<hr />
This GUI application was created for educational purposes for use as a project for the Object Oriented Programming course taken at Florida Gulf Coast University. 
<br>
2018, Kamp Duong
