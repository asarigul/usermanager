usermanager
===========

A Simple user management application using Spring MVC, MongoDB, Morphia, and Maven.  

Notes from the project
----------------------

- Supports mongodb with authentication (set username & password in usermanager.properties for authentication). 

- Powered by JQuery + JQueryUI at client side. All CRUD services are called  using Ajax.

- Server - Client communication protocol, including request parameter names and even validation expressions is unified at server side. In addition to preventing code duplication, server and client side validations are virtually the same. 

- Uses kaptcha to generate captcha (only in create form). Captcha characters, length, and case sensitivity option for validation can be set in properties file.

- Spring, Mongo Java Driver, and Morphia's logs' centralized by slf4j, along with application log. Logback is used as the slf4j implementation. 

- Spring controllers are annotation driven. As an AOP practice, an aspect applied globally to all controllers, implementing a centralized error handling mechanism over controllers. 

- In order to use resources efficiently, A single MongoClient (which already has a connection pool), and a single Morphia instance is created. 

- Application gives an id to any exception. This information is shared with the client as the error reference. This approach could be useful when scanning log files.

