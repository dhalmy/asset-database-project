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
Here's a demonstration through screenshots of a normal workflow in this project.
Signing in: 
Attempted to log in without filling information

![Login Page - fields need not empty](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/1de51a7e-b0f3-498b-b7b5-5141e1eea592)

Attempted to log in with incorrect credentials

![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/b716196d-5308-4abd-b39b-a1626f37f632)

First page I see after signing in

![Dashboard - right after ADMIN login](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/0ee28ae5-6e64-40ac-af13-de2589f98cd5)



Creating an employee:
Clicked on Create an Employee on the left side bar

![Create an employee page](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/466c415f-5ed2-4b42-939e-5af98f1bd8b1)

Employee successfully created, as reflected inside the Employees table

![employee hett tied created and visible under employees table](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/5d4e5441-cf42-461a-ac44-1d45c0bfbd78)

Employee searchable, too, through the search bar

![Search functionality](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/4b60203d-abb3-478d-a345-9f13fc0d4037)
![Search functionality2](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/6adac142-0813-4431-9ebf-29a77d9abb8a)

Creating a laptop:
New laptop was entered into the system

![Create a laptop page](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/e74f3469-1021-4bd1-ad58-7886d88171eb)

Success

![Search laptop](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/c8875839-9743-4256-b3a8-e021c84587b7)

Reading Employee Page information (no laptops assigned to them yet)

![Read an employee page](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/64d18e9e-53cf-49e8-bf84-63411dd35438)


Update laptop name since it was entered incorrectly

![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/e6967c05-d9bb-48a0-bac0-d666517031d5)



Assigning laptop to employee through Update Employee page

![Update employee page - assign new laptop](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/b5ff52a9-7400-44bd-9f00-4a471b1334bf)

Laptop successfully assigned to employee

![Laptop successfully assigned to employee](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/b974fd12-77d4-4a0b-8817-94214a9a0bd1)


Assigning the employee to a cubicle:
Viewing the Cubicle they will be assigned to:

![View Cubicle](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/9df72dc9-2115-4f30-b6ed-8174f64c398a)

Assigning the user to the cubicle through the cubicle's update page

![Assign employee to cubicle page](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/97d2bb8f-11ac-4857-b8a5-c713971760d4)

Employee successfully added to cubicle

![Employee successfully assigned to cubicle](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/0685d76f-3fea-44ab-8708-61c2d360ac3c)

Delete Employee

![delete employee page](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/d5b43f00-671d-4838-b4b6-d8fa5e984ab9)

Employee successfuly removed

![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/45dd1860-6043-4786-a6bf-dba7466b0e4d)





• Expected Results/Known Issues 
As far as functionality, most things currently work as intended. I spent probably a majority of the time of this project debugging errors that I would run into, as well as testing each functionality for errors. The only thing I can think of that doesn't work the way I want it to is when creating an employee, you cannot change the username from what is being autogenerated. This was changed as a redundancy fix in case the username doesn't update with the last or first name in case that needs to be updated. This would cause a problem in the rare case where someone has the same last name and the same first letter of their first name. I would implement the fix, however I am frankly just out of time now.
There is plenty to work on when it comes to design aspects, however. All of the pages other than the dashboard pages are pretty bland and could use some love.
When reading a single cubicles page, it shows the monitors that do not belong to the cubicles, but it shows them as unchecked boxes. While this isn't wrong, it would look better if it only showed the monitors that belonged to the single cubicle.
This project also lacks the client-side validation that I would like to implement.
Unfortunately, I did not get to creating the "create a user" page as instructed in the outline.



o Also, provide me with a known working test script to follow when I run your 
project:
For the sample test script, you can pretty much follow the pictures from above.
Log into the page with username: admin password: admin
Create an employee by clicking Create Employee. Enter the first name and last name of anyone you want. The employee's username and email should automatically fill, but it won't cause problems if it doesn't.
Choose any hire date in the past, and choose any employee type.

Create a laptop to give to this employee by clicking Create Laptop
Enter a tag beginning with F. ex: F02951
Enter a serial number consisting of numbers and letters. ex: 0F00Z0922423FB
Give a name to the laptop, following a specific convention of LT-(numbers)-(laptop make model abbreviation) ex: LT-0396-MS5
Enter the model of laptop ex: Microsoft Surface 5

Assign the laptop to the employee using the update button on the employee in the table.
Check the checkbox of the laptop you created and click update employee. 

Assign the employee to a cubicle by scrolling down to or searching the cubicle number you want to modify. Then click update, then add the employee to the cubicle by clicking their checkbox and click update cubicle.

Then delete the employee by searching or scrolling to them, then delete, then delete.

o Use this section to tell me anything you want about the project, and your 
design/development experience during the project.
I learned a lot about jakarta and MVC operations. I learned a lot about HTML and a little javascript as well. I also learned it's extra hard to deal with uniary relationships.
I learned plenty about debugging, especially using the logs in the console. I wish I had more time to spend on this project, but at least I got it to a good point.
