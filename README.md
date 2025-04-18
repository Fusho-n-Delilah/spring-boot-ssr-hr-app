# Yogi HR
 A spring boot server-side rendered app using thymeleaf and bootstrap. This project is an attempt at reverse engineering a popular payroll software as a demo of my ability to work with Spring Boot and popular design patterns. I had a lot of fun tackling challenges such as implementing basic HTTP auth (including role based access, database password encryption, and custom authentication success handlers), implementing charts with Chart JS, Server-side rendering, and the multiplicity of database tables and trying to maintain 3NF. In the end I built something I'm proud of. Thank you for checking out my project!


![YogiHR](https://github.com/user-attachments/assets/58cf3cc8-ba6d-4e69-8d83-1fe06f97f6b9)

<h3>Setup</h3>
<br>
If you would like to play around with this app there are a few steps you must complete first.

Download MySql:
download the latest version at the time of publishing @ https://dev.mysql.com/downloads/installer/

Run the Scripts included:
In the SQL scripts folder you will find all the scripts to create and populate the tables to work with this application


<h3>Limitations</h3>
This applicaiton has a few inherant limitations: 
<ol>
 <li>It only supports basic timesheets. This was a design choice for ease of implementation</li>
 <li>This app only supports business in the following States: Alaska, Florida, Nevada, South Dakota, Tennessee, Texas, Washington, and Wyoming. These states have no income tax</li>
 <li>The application itself uses responsive bootstrap components. However, it has not currently been optimized everywhere for mobile use. It would generally be frowned upon to process payroll from a phone</li>
 <li>pagination will be added in the next iteration of this application</li>
 <li>The app would be better served implementing HTMX, and as i developed I realized this would be the approach I'd prefer for a server-side rendered application for the performance increase and ease of development</li>
</ol>

<h2>Walkthrough<h2/>

<h3>homepage:</h3>

when a regular employee logs in they are greeted with the homepage
![image](https://github.com/user-attachments/assets/66e62a30-81c8-4c00-96de-37c401a94416)

the navigation is based on role based access, and someone with an HR role sees the additional functionality
![image](https://github.com/user-attachments/assets/ec8d2a4c-41ab-4bf9-9eee-b6a0136bd7a3)

<h3>User Functionality:</h3>

a user is able to do all the general functions that you would expect such as: view their salary info, request time off, submit their time sheets, change their contact information, and view their pay stubs.


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


<h3>HR functionality:</h3>

HR is able to approve timesheets and Pto requests, edit all employee information, and process payroll.


![image](https://github.com/user-attachments/assets/c2e889cb-70a6-4614-9733-084d9087da79)
viewing employees

![image](https://github.com/user-attachments/assets/714691fd-8fc5-4002-9816-b548664b27bf)
a multi page form for editing an employee

![image](https://github.com/user-attachments/assets/159d3773-9201-4e0f-9cd5-4fe861e59802)
a user friendly way for HR to trigger a process in the service layer to process all approved timesheets











  
