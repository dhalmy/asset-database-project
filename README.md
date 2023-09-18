ITMD4515 Lab 3

Before Submission
![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/1fbec3ad-c611-4170-8085-cd59d40e2676)

After Submission
![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/a25762c0-1fd8-4f40-ba3f-bda8ee79b4b9)

World DB after submission
![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/ff1fe682-d71d-40ef-8f50-4a2f3755e43b)


Improper data before attempted submission
![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/8525f5d2-7404-4793-9bac-8dd434c56c48)


Error messages after attempted submission
![image](https://github.com/itmd4515/itmd4515-f23-fp-dhalmy/assets/91496056/3a01bfb3-feb3-4f06-9a10-a144fc04123e)



**1.Your understanding of the difference between the forward and redirect operations.**
When forwarding a page, the browser keeps the client on the same URL while internally changing what the client sees on the page. Redirect sends a brand new URL to the client, meaning a new request and typically a new page. Both are very useful at completing different purposes.

**2. How would you be validating user submissions without the Bean Validation API 
standard?**
I would be writing custom code to check the user inputs. Such as checking if the number is > 0 and writing regular expressions to make sure the data fits the parameters I want.

**3. How do you think this MVC approach would scale to a real application with 100's of 
entities?**
This MVC approach seems to be fine at this current scale, but I think there would need to be changes made before scaling it up. There's a lot of work to be done here because we are developing the API from scratch, whereas when coding to scale for 100s of entities we'd be better off using a developed framework.

**4. Why didn't we need to include any additional dependencies (i.e. Bean Validation, 
JDBC) in this project?**
I didn't need to include any additional dependencies because these are included in Jakarta's standard libraries. The "jakarta.jakartaee-api" is automatically included in the auto-generated pom.xml file, which grants me access.
