usermanager
===========

A Simple user management application using Spring MVC, MongoDB, Morphia, and Maven.  

Notes from the project
----------------------

- Supports mongodb with authentication (set both username & password in usermanager.properties to enable authentication). 

- In order to use resources efficiently, a single MongoClient (which already has a connection pool), and a single Morphia instance is created (as advised by library programmers). 

- Powered by JQuery + JQueryUI at client side. Creation, update, and deletion services are invoked by Ajax.

- Server - Client communication protocol, including request parameter names and even regular expressions for validation are unified at server side. In addition to preventing code duplication, server and client side validations are virtually the same. 

- Uses kaptcha to generate captcha images (used in user creation form). Captcha characters, length, and case sensitivity option for validation can be set in properties file.

- Spring, Mongo Java Driver, and Morphia's logs' are centralized by slf4j, along with application log; so that all logging configuration can be managed at a single point. Logback is used as the slf4j implementation. 

- As an AOP practice, an aspect applied globally to all Spring controllers' service methods, implementing a centralized error handling mechanism over controllers. This way common error cases, like validation errors, are handled at a central place. 

- Application gives an id to any exception. This information is shared with the client as the error reference. This approach could be useful especially when scanning thru big log files. 

