• Project Summary 
I created a fully functional asset tracking database for the IT department. This asset tracker acts as a portal for three different kinds of users: Admin users, IT users, and HR users. 
Admin users have full Create, Read, Update, and Delete functionality. IT users have Create, Read and Update permissions, while HR only has view permissions.
This database holds all the necessary information about employees, laptops, monitors, and docking stations.

• Design 
The project supports all CRUD operations on employees, laptops, and monitors. The project doesn't support creating cubicles or deleting cubicles since it doesn't support any needs.
You cannot physically create or delete cubicles, so no need to implement that ability.
There is a table for each main entity, including a "General View Table" which serves as a go-to place to look at for information about employees and the important information regarding them.
While the General View Table serves as the place for employee-centric information, the Cubicles table was designed for a Cubicle-centric view for information, displaying important information regarding each cube such as who sits there, what monitors are there, and what docking stations are there.
There is search functionality built into the project, where any searched item pulls from all the attributes and returns successful hits, in each respective table.
One neat design I implemented is when creating a new employee, the username auto-generates in the creation fields of 'username' and 'email', since username is a pre-set combination of first letter of first name and full last name.
Every detail put into this application was done through the interests of the users who would be using it, since it was designed to be something better than the tool I currently use to manipulate this data.
All of the important data fed into this project was carefully randomized, but based on real data.

• Requirements (Installation, Compile, Runtime, Database, etc) 
These are the accounts usernames designed to work with login, along with the permissions assigned to each. The password for each account is the same as the username.
iwitty HR
kreigster HR
jusionator ADMIN
gcolossalmer IT INTERN
sbarksalot ADMIN
apedrazzle ADMIN
dhalmy ADMIN

This project was all done in Apache NetBeans. To make it run, make sure you have MySQL 8.0 installed and running as well as Apache Netbeans 19 and Payara Server ver 6.2023.8.
Once in NetBeans, you will have to attatch the Payara server by going to Services, right click > add a server, click Payara server, and change the filepath to the download location of Payara 6.2023.8 and continue.
Then go to file > import project from ZIP > and import my project file.
Then, run the application by clicking the green arrow.

• Screen Captures 
o Include enough screen captures to illustrate your working project.
• Expected Results/Known Issues 
o Use this section to describe any known issues with your project. Nothing is ever 
perfect, and it is better to document issues than ignore them.
o Also, provide me with a known working test script to follow when I run your 
project. For example: 
▪ Login as user1 with password foo
▪ Enter a customer name of "Fred" in the search box
▪ etc
• Development Insights 
o Use this section to tell me anything you want about the project, and your 
design/development experience during the project.
o What did you learn?
o Was there something you would like to explore further?
o What did you like, or not like?