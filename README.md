
![cinema3](images/cinema3.jpg)

#   <center>Cinema application</center>  

Let me introduce you to my second project. You can use this code to create a WEB application or as a part of a complex cinema-app. So, 

# Project description

This application contains the most important part of the full cinema management program. The following code provides such possibilities:

  *  New user registration via email
  *  User login and authentication
  *  RBAC (role based access control) to create the privileged Admin role 
  *  Displaying all movies, cinema halls, movie sessions, user orders and tickets on it
  *  Adding movie to the database and get information about movies
  *  Adding cinema hall to the database and get information about cinema halls
  *  Adding a movie session to the database and get information about available movie sessions
  *  Creating movie show tickets
  *  The user has a shopping cart, where tickets can be added to the order
  *  User can update and complete the order or clear the shopping cart
  *  User can get information about cinema halls, movies, orders
  *  User can find available movie session for actual time
  *  Admin can create cinema halls and movie sessions, add new movies, update movie sessions, delete movie sessions
  *  Admin can get information about movies, cinema halls, available movie sessions, user by email

This app build using **SOLID** principles. All operations with the database implemented using **Hibernate** framework. Application use **Spring** framework and **Project Lombok** library.
  
#   <center>Realization details</center> 
 
To create the service I was used following technologies: Spring Framework 5.2.2.RELEASE, Spring Web MVC, Spring Security, Project Lombok Log4j2 for logging, MySQL 8 server as database management system and MySQL Workbench as a shell, Hibernate framework 5.6.10 Final version, Apache Maven 3.8 as development tool package. 

The project uses following N-tier architecture:
  
  * Data Access Layer (DAO tier)
  * Application layer (service tier)
  * Security level (security tier)

# The database structure is here

![database](images/database.jpg)

#   <center>Installation and using</center>

  * Must have: JDK (Intellij IDEA Ultimate, Eclipse e.t.c), Git, Apache Maven, Postman API to create HTTP requests, MySQL or any other server and (optional) MySQL Workbench. Use guides and Google search for troubleshoot apps installation issues.
  * Configure inner logger, use log4j2.xml file from   src/main/resources/
  * Standard path to log file is   logs/
  * Maybe you need to change it to absolute path like   "D:/Projects/cinema-application/logs/"
  * Also You can change log messages and log levels for messages in your code.
  * Create a database. I'm use a local MySQL database
  * In a src/main/resources/db.properties fill in your database credentials
  * The code has no Main.class. To start an application, use a WebServer like Apache Tomcat. I'm use 9.0.65 version
  * Add TomcatLocal to JDK configuration. Use default port 8080. Do not forget to specify Tomcat installation folder and select taxi_service:war exploded in the fix window, also change the initial path from   /web_security_war_exploded   to   /
 
#   <center>Control endpoints list</center>

  * Run Tomcat Local
  * Use HTTP protocol for requests. All URL starts with localhost:8080
  * For request body blocks please use JSON data format
  * Here is the list of ends for URL address, what uses for:


/login - the standard login page from Spring Security. Please, use 
login: user@gmail.com password: 1234 for register as a User or 
login: admin@gmail.com password: 1234 to register as an Admin. 
Yes, I know that using these awful credentials is not a good idea, but it's a test project. Please, never do that on your real projects!

/ - Congrats! Login complete, you can make requests to use the application.

/register - POST request for registration of new user. Here you don't need it, because User and Admin are already registered in your database.

/movies - POST request for add movie to database. Params: 
Authorization: Basic Auth, credentials are equal to your login credentials
Headers: Content-Type  application/json
Body: {"title":"Movie_title", "description":"Movie_description"}

/movies - GET request returns actual list of movies. Params: 
Authorization: Basic Auth, credentials are equal to your login credentials

/cinema-halls -  POST request for add cinema hall to database. Params: 
Authorization: Basic Auth, credentials are equal to your login credentials
Headers: Content-Type  application/json
Body: {"capacity":"int value of cinema hall capacity", "description":"Cinema hall description"}

/cinema-halls - GET request returns actual list of cinema halls. Params: 
Authorization: Basic Auth, credentials are equal to your login credentials

/movie-sessions - POST request for adding a new movie session to the database. Params: 
Authorization: Basic Auth, credentials are equal to your login credentials
Headers: Content-Type  application/json
Body: {"movieId":"int movie id", "cinemaHallId":"int cinema hall id", "showTime":"YYYY-mm-dd HH:mm"}

/movie-sessions/available?movieId={movie id}&date={dd.mm.YYYY} - GET request returns actual list of movie sessions for current date & time.

/movie-sessions/{id} - PUT request for update movie session with {int movie session id} in database. Params: 
Authorization: Basic Auth, credentials are equal to your login credentials
Headers: Content-Type  application/json
Body: {"movieId":"int movie id", "cinemaHallId":"int cinema hall id", "showTime":"YYYY-mm-dd HH:mm"}

/movie-sessions/{id} - DELETE request for delete movie session with {int movie session id} from database.

/users/by-email?email={user email} - GET request returns information about the user with {user email}.

/shopping-carts/movie-sessions?userId={int user id}&movieSessionId={int movie session id} - PUT request for add new movie session to current user shopping cart. Params: 
Authorization: Basic Auth, credentials for User role only!

/shopping-carts/by-user - GET request returns information about the shopping cart of the current user. Params: 
Authorization: Basic Auth, credentials for User role only!

/orders - GET request returns order history for current user. Params: 
Authorization: Basic Auth, credentials for User role only!

/orders/complete - POST request for complete order to current user. Params: 
Authorization: Basic Auth, credentials for User role only!
