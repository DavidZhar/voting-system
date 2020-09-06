# voting-system

Graduation project for https://github.com/JavaOPs/topjava  

Voting system for deciding where to have lunch.
 * 2 types of users: admins and regular users.
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price).
 * Menu changes each day (admins do the updates).
 * Users can vote on which restaurant they want to have lunch at.
 * Only one vote counted per user.
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed.
    
 Stack: Java 8/Maven/REST/Spring MVC/Spring Security/Spring DATA JPA/Hibernate/HSQLDB
 
-----------------------------

### REST API
(To use these cURL examples deploy the app to localhost:8080 with empty application context.)

New users can register:  

`curl -X POST -H "Content-Type: application/json" localhost:8080/register -d 
'{
    "email": "new@mail.ru",
    "password": "password",
    "roles": ["USER"]
}'`

Admins can get, update and delete users:  

`curl localhost:8080/rest/admin/users --user admin@mail.ru:admin`

`curl localhost:8080/rest/admin/users/100000 --user admin@mail.ru:admin`

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/admin/users/100000 --user admin@mail.ru:admin -d
'{
        "id": 100000,
        "email": "updated@mail.ru",
        "password": "updated",
        "roles": [
            "USER", "ADMIN"
        ]
}'`

`curl -X DELETE localhost:8080/rest/admin/users/100000 --user admin@mail.ru:admin`

`curl 'localhost:8080/rest/admin/users/byemail?email=user@mail.ru' --user admin@mail.ru:admin`

Registered users can get, update and delete themselves:

`curl localhost:8080/rest/profile --user user@mail.ru:password`

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/profile --user user@mail.ru:password -d
'{
    "email": "user@mail.ru",
    "password": "updated",
    "roles": ["ADMIN"]
}'`

`curl -X DELETE localhost:8080/rest/profile --user user@mail.ru:password`

Users can get restaurants without dishes by id and also with dishes by date (with menu for that day):

`curl localhost:8080/rest/restaurants --user user@mail.ru:password`

`curl localhost:8080/rest/restaurants/100002 --user user@mail.ru:password`

`curl 'localhost:8080/rest/restaurants?date=2020-02-02' --user user@mail.ru:password`

Admins can get, update and delete restaurants:

`curl -X POST -H "Content-Type: application/json" localhost:8080/rest/admin/restaurants --user admin@mail.ru:admin -d
'{
    "description": "Restaurant NEW"
}'`

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/admin/restaurants/100002 --user admin@mail.ru:admin -d 
'{
    "id": 100002,
    "description": "Restaurant1 UPDATED"
}'`

`curl -X DELETE localhost:8080/rest/admin/restaurants/100002 --user admin@mail.ru:admin`

Admins can get, create, update and delete dishes for specific date and restarant:

`curl 'localhost:8080/rest/admin/restaurants/100002/dishes?date=2020-01-01' --user admin@mail.ru:admin` 

`curl -X POST -H "Content-Type: application/json" localhost:8080/rest/admin/restaurants/100002/dishes --user admin@mail.ru:admin -d
'{
    "description": "Restaurant1_Date1_Dish_NEW",
    "price": 1000,
    "date": "2020-03-03"
}'`

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/admin/restaurants/100002/dishes/100004 --user admin@mail.ru:admin -d
'{
    "id": 100004,
    "description": "Restaurant1_Date1_Dish_NEW",
    "price": 1000,
    "date": "2020-03-03"
}'`

`curl -X DELETE localhost:8080/rest/admin/restaurants/100002/dishes/100006 --user admin@mail.ru:admin`

Users can vote for restaurant:

`curl -X POST localhost:8080/rest/votes/ --user user@mail.ru:password -d
'{
    "restaurant": {
        "id": 100002
    }
}'`

Users can change their vote (before 11:00):

`curl -X PUT localhost:8080/rest/votes/ --user user@mail.ru:password -d
'{
    "restaurant": {
        "id": 100003
    }
}'`

Admins can get and delete votes by id and by restaurant/date:

`curl localhost:8080/rest/admin/votes/ --user admin@mail.ru:admin`

`curl localhost:8080/rest/admin/votes/100009 --user admin@mail.ru:admin`

`curl 'localhost:8080/rest/admin/votes?date=2020-01-01' --user admin@mail.ru:admin`

`curl 'localhost:8080/rest/admin/votes/restaurant/100002?date=2020-01-01' --user admin@mail.ru:admin`

`curl -X DELETE localhost:8080/rest/admin/votes/ --user admin@mail.ru:admin`
