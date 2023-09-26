ITMD4515 Lab 4
![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/9d500f02-ca6e-440d-9656-5db27630312c)

My createTest() test function creates data then verifies it was created in the database.

    @Test
    public void createTest(){}
<hr>
   
   My readTest() function reads back sample data, verifies it was retrieved, and assets if it matches the expected outcome.

    @Test
        public void readTest(){}
<hr>
My updateTest() function creates a query to select the employee with a username of ssmith, and sets their first name to Samuel, then merges and commits.

    @Test
    public void updateTest(){}
<hr>
My deleteTest() function creates test data, verifies its in the database, then removes the data it just created, then verifies that it was actually removed from the database.

    @Test
    public void deleteTest(){}
<hr>
![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/fb2caedf-756c-466f-8dff-63fe1df89a0b)

My validateValidEmployee() function creates a valid employee John Doe with all correct information, then makes sure there's no violation.

    @Test
    public void validateValidEmployee(){}
<hr>
My validateInvalidFirstName() function sets John's name to John123 and since numbers aren't allowed in names, the function checks to make sure there was a violation.

    @Test
    public void validateInvalidFirstName(){}
<hr>
My validateInvalidUsername() function creates a test scenario where the username violates constraints, and then checks to ensure there is a violation.

    @Test
    public void validateInvalidUsername(){}
<hr>
My validateInvalidHireDate() function creates a test scenario with an invalid hire date (in the future), and checks to make sure there is a violation.

    @Test
    public void validateInvalidHireDate(){}
<hr>


    
           
