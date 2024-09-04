I have deployed the springboot application on Elastic Bean Stalk. 
These are steps I followed to deploy and run the application.

### CREATE A EXECUTABLE JAR FILE FOR THE SPRING BOOT PROJECT
Run the following command on the terminal
>mvn clean package
This will create a Jar in the target folder

### LOGIN TO AWS IAM CONSOLE AND SELECT Elastic Bean Stalk service.
1.Create Application and name it TrakingNumberGeneratorApp
2.Create Environment for the application
    2.1 Select Web server Environment
    2.2 Select Platform as Java
    2.3 Select Upload your code 
    2.3 Provide a version number, lets say v1
    2.4 Select Local File option
    2.5 Upload the Spring boot executable Jar from the local disk and click next
3.For service role, select already created Roles for both Bean stalk anf EC2.
4.Click Next
5.Click Submit
6.AWS will setup the environment and provide us with a domain
7.We can access the application using this domain

The deployed application url is
http://trackingnumbergenerator-env.eba-hh7up97k.us-east-2.elasticbeanstalk.com:8080/api/next-tracking-number?origin_country_id=IN&destination_country_id=US&weight=50&customer_id=285c3c48-5831-4453-8b6f-15f8a0a4eeb6&created_at=2024-09-04T14:30:00&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics


     

