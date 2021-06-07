This app is an Authorization Server config for the Resource Server app found here:
https://github.com/Ginitoru/ResourceServer.git.

1.Framework used: Spring with SpringBoot 2.3.11.

2. Dependecies: CloudOauth,  Spring JPA, Spring web, Spring security, mysql driver, lombok.

4. Functionality: 

The app uses REST API to store users and clients to a mySQL database.
Using authorization_code grant_type and a simetric key, the autorization server generates a 
JWT token after the user authenticates on the server. This JWT token is used on the Resource 
Server to access the endpoints depending on the users authorization.
