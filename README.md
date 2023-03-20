# SpringBootSecurity

Spring Boot Security is a framework for implementing security in a Spring Boot application. It provides a comprehensive security solution for securing both the frontend and backend of an application. It has a wide range of features such as authentication, authorization, role-based access control, password hashing, encryption, and more. It also provides a secure way to store and access user credentials. Spring Boot Security is easy to configure and provides a secure environment for applications.

 ## Table of Contents
1. [WebSecurityConfigurerAdapter](#webSecurityConfigurerAdapter)
2. [Configure method](#configureMethod)
3. [JWT](#JWT)


### webSecurityConfigurerAdapter
***
Spring Security provides an implementation of the WebSecurityConfigurerAdapter class that can be used to configure web-based security for specific http requests. This class is used to apply security controls to specific http requests based on the specified URL patterns. The WebSecurityConfigurerAdapter class provides methods to configure authentication and authorization for web applications.

The WebSecurityConfigurerAdapter class allows for configuration of authentication and authorization for specific URLs. It also provides methods for configuring roles and access control for specific URLs. The WebSecurityConfigurerAdapter class can be used to protect against cross-site request forgery (CSRF) attacks by configuring CsrfTokenRepository. It also provides methods for configuring encryption, such as HTTPS and TLS.

The WebSecurityConfigurerAdapter class can be used to provide additional security features such as filtering, session management, and access control. It also provides methods for configuring authentication providers and authorization constraints. Furthermore, the WebSecurityConfigurerAdapter class can be used to provide custom security rules for specific URLs.

### configureMethod
***
The configure(WebSecurity) method in the WebSecurityConfigurerAdapter class is used to specify the security configuration for the application. The configure(WebSecurity) method can be overridden in order to customize the security configuration as needed. This allows developers to specify custom authentication providers, configure access control rules, and more. By overriding the configure(WebSecurity) method, developers can tailor security configuration to the specific needs of the application.


### JWT
***
JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. In Spring Boot applications, JWT is used to secure communication between client and server by using digitally signed tokens. The token is sent as an Authorization header in each request, and is used to authenticate the user and authorize them to access certain API endpoints.


  <!-- 
  ## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Collaboration](#collaboration)
5. [FAQs](#faqs)
### General Info
***
Write down general information about your project. It is a good idea to always put a project status in the readme file. This is where you can add it. 
### Screenshot
![Image text](https://www.united-internet.de/fileadmin/user_upload/Brands/Downloads/Logo_IONOS_by.jpg)
## Technologies
***
A list of technologies used within the project:
* [Technology name](https://example.com): Version 12.3 
* [Technology name](https://example.com): Version 2.34
* [Library name](https://example.com): Version 1234
## Installation
***
A little intro about the installation. 
```
$ git clone https://example.com
$ cd ../path/to/the/file
$ npm install
$ npm start
```
Side information: To use the application in a special environment use ```lorem ipsum``` to start
## Collaboration
***
Give instructions on how to collaborate with your project.
> Maybe you want to write a quote in this part. 
> Should it encompass several lines?
> This is how you do it.
## FAQs
***
A list of frequently asked questions
1. **This is a question in bold**
Answer to the first question with _italic words_. 
2. __Second question in bold__ 
To answer this question, we use an unordered list:
* First point
* Second Point
* Third point
3. **Third question in bold**
Answer to the third question with *italic words*.
4. **Fourth question in bold**
| Headline 1 in the tablehead | Headline 2 in the tablehead | Headline 3 in the tablehead |
|:--------------|:-------------:|--------------:|
| text-align left | text-align center | text-align right |
 -->

