# Yogi HR
 A springboot server-side rendered app using thymeleaf and bootstrap. This project is an attempt at reverse engineering a popular payroll software as a demo of my ability to work with Spring Boot and popular design patterns. I had a lot of fun tackling challenges such as implementing basic HTTP auth (including role based access, database password encryption, and custom authentication success handlers), implementing charts with Chart JS, Server-side rendering, and the multiplicity of database tables and trying to maintain 3NF. In the end I built something I'm proud of. Thank you for checking out my project!


![YogiHR](https://github.com/user-attachments/assets/58cf3cc8-ba6d-4e69-8d83-1fe06f97f6b9)

#Setup
If you would like to play around with this app there are a few steps you must complete first.

Download MySql:
download the latest version at the time of publishing @ https://dev.mysql.com/downloads/installer/

Run the Scripts included:
In the SQL scripts folder you will find all the scripts to create and populate the tables to work with this application


#Limitations
This applicaiton has a few inherant limitations: 
  1.) It only supports basic timesheets. This was a design choice for ease of implementation
  2.) This app only supports business in the following States: Alaska, Florida, Nevada, South Dakota, Tennessee, Texas, Washington, and Wyoming. These states have no income tax
  3.) The application itself uses responsive bootstrap components. However, it has not currently been optimized everywhere for mobile use. It would generally be frowned upon to process payroll from a phone.

#Walkthrough

homepage:

when a regular employee logs in they are greeted with the homepage
![image](https://github.com/user-attachments/assets/66e62a30-81c8-4c00-96de-37c401a94416)

the navigation is based on role based access, and someone with an HR role sees the additional functionality
![image](https://github.com/user-attachments/assets/ec8d2a4c-41ab-4bf9-9eee-b6a0136bd7a3)

User Functionality:

a user is able to do all the general functions that you would expect such as: view their salary info, request time off, submit their time sheets, change their contact information, and view their pay stubs

![image](https://github.com/user-attachments/assets/fee02dda-e009-413c-b44e-a718c8a33448)
submitting a timesheet. The ability to change their timesheet is prevented if they have an approved timesheet for the current pay period

![image](https://github.com/user-attachments/assets/a99bd51d-2947-4f06-8b18-69b93eac6aa3)
Submitting a pto request. Users can see their current requests

![image](https://github.com/user-attachments/assets/8c6eb77b-f806-4072-bcd7-2588587dce88)
Changing their contact information.

![image](https://github.com/user-attachments/assets/f2720fa4-2530-4137-974d-d3bce56344e5)
Pay stubs is a tabbed layout that the user can filter by year

![image](https://github.com/user-attachments/assets/52f6d701-d7a8-4676-97f6-fda52020ddd3)
The user can view their year-to-date totals easily

![image](https://github.com/user-attachments/assets/cd61e047-798b-4cdd-9301-21306c2ba2ce)
The user can view a breakdown of their current pay stub. a doughnut chart shows an easy to understand breakdown of the totals







  
