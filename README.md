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
 
_________________________

### REST API

#### User
Anonymous:
- POST /register - register as a new user

User:
- GET /rest/profile - get profile
- UPDATE /rest/profile - update profile
- DELETE /rest/profile - delete profile

Admin:
- GET /rest/admin/users - get all users
- GET /rest/admin/users/{userId} - get user by id
- PUT /rest/admin/{userId} - update user by id
- DELETE /rest/admin/{userId} - delete user by id
- GET /rest/admin/users/byemail?email={email} - get user by email

#### Restaurant
User:
- GET / - get all restaurants with dishes for today
- GET /rest/restaurants - get all restaurants
- GET /rest/restaurants/{restaurantId} - get restaurant by id
- GET /rest/restaurants?date={date} - get restaurants with dishes for specific date

Admin:
- GET /rest/admin/restaurants - get all restaurants
- POST /rest/admin/restaurants - create restaurant
- PUT /rest/admin/restaurants/{restaurantId} - update restaurant by id
- DELETE /rest/admin/restaurants/{restaurantId} - delete restaurant by id

#### Dish
Admin:
- GET /rest/admin/restaurants/{restaurantId}/dishes - get all dishes by restaurant id
- GET /rest/admin/restaurants/{restaurantId}/dishes/{dishId} - get dish by id
- POST /rest/admin/restaurants/{restaurantId}dishes - create dish for restaurant
- PUT /rest/admin/restaurants/{restaurantId}/dishes/{dishId} - update dish by id
- DELETE /rest/admin/restaurants/{restaurantId}/dishes/{dishId} - delete dish by id

#### Vote
User:
- POST /rest/votes - vote for a restaurant by id
- PUT /rest/votes - change vote for another restaurant by id
- GET /rest/votes - get authorized user's vote for today

Admin:
- GET /rest/admin/votes - get all votes
- GET /rest/admin/votes/{voteId} - get vote by id
- GET /rest/admin/votes?date={date} - get all votes for specific date
- GET /rest/admin/votes/restaurant/{restaurantId}?date={date} - get all votes for specific restaurant and date

_________________________
### cURL examples
(To use these cURL examples deploy the app to localhost:8080 with empty application context.)

##### Register new user

`curl -X POST -H "Content-Type: application/json" localhost:8080/register -d 
'{
    "email": "new@mail.ru",
    "password": "password",
    "roles": ["USER"]
}'`

_________________________
#### Registered users can:

##### Get profile

`curl localhost:8080/rest/profile --user user@mail.ru:password`

##### Update profile

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/profile --user user@mail.ru:password -d
'{
    "email": "user@mail.ru",
    "password": "updated",
    "roles": ["ADMIN"]
}'`

##### Delete profile

`curl -X DELETE localhost:8080/rest/profile --user user@mail.ru:password`

_________________________
#### Admins can:

##### Get all users

`curl localhost:8080/rest/admin/users --user admin@mail.ru:admin`

##### Get user with id 100000

`curl localhost:8080/rest/admin/users/100000 --user admin@mail.ru:admin`

##### Update user with id 100000

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/admin/users/100000 --user admin@mail.ru:admin -d
'{
        "id": 100000,
        "email": "updated@mail.ru",
        "password": "updated",
        "roles": [
            "USER", "ADMIN"
        ]
}'`

##### Delete user with id 100000

`curl -X DELETE localhost:8080/rest/admin/users/100000 --user admin@mail.ru:admin`

##### Get user with email "user@mail.ru"

`curl 'localhost:8080/rest/admin/users/byemail?email=user@mail.ru' --user admin@mail.ru:admin`

_________________________
#### Registered users can:

##### Get all restaurants with dishes for today

`curl localhost:8080 --user user@mail.ru:password`

##### Get all restaurants

`curl localhost:8080/rest/restaurants --user user@mail.ru:password`

##### Get restaurant with id 100002

`curl localhost:8080/rest/restaurants/100002 --user user@mail.ru:password`

##### Get all restaurants with dishes for date "2020-02-02"

`curl 'localhost:8080/rest/restaurants?date=2020-02-02' --user user@mail.ru:password`

_________________________
#### Admins can:

##### Create restaurant

`curl -X POST -H "Content-Type: application/json" localhost:8080/rest/admin/restaurants --user admin@mail.ru:admin -d
'{
    "description": "Restaurant NEW"
}'`

##### Update restaurant with id 100002

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/admin/restaurants/100002 --user admin@mail.ru:admin -d 
'{
    "id": 100002,
    "description": "Restaurant1 UPDATED"
}'`

##### Delete restaurant with id 100002

`curl -X DELETE localhost:8080/rest/admin/restaurants/100002 --user admin@mail.ru:admin`

##### Get all dishes (dish history) for restaurant with id 100002

`curl 'localhost:8080/rest/admin/restaurants/100002/dishes' --user admin@mail.ru:admin` 

##### Get all dishes for restaurant with id 100002 for date "2020-01-01"

`curl 'localhost:8080/rest/admin/restaurants/100002/dishes?date=2020-01-01' --user admin@mail.ru:admin` 

##### Create dish for restaurant with id 100002 for date "2020-03-03"

`curl -X POST -H "Content-Type: application/json" localhost:8080/rest/admin/restaurants/100002/dishes --user admin@mail.ru:admin -d
'{
    "description": "Restaurant1_Date1_Dish_NEW",
    "price": 1000,
    "date": "2020-03-03"
}'`

##### Update dish with id 100004 for restaurant with id 100002

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/admin/restaurants/100002/dishes/100004 --user admin@mail.ru:admin -d
'{
    "id": 100004,
    "description": "Restaurant1_Date1_Dish_NEW",
    "price": 1000,
    "date": "2020-03-03"
}'`

##### Delete dish with id 100006 for restaurant with id 100002

`curl -X DELETE localhost:8080/rest/admin/restaurants/100002/dishes/100006 --user admin@mail.ru:admin`

_________________________
#### Registered users can:

##### Vote for restaurant with id 100002

`curl -X POST -H "Content-Type: application/json" localhost:8080/rest/votes/ --user user@mail.ru:password -d
'{
    "restaurant": {
        "id": 100002
    }
}'`

##### Change vote for restaurant with id 100003 (works before 11:00)

`curl -X PUT -H "Content-Type: application/json" localhost:8080/rest/votes/100003 --user user@mail.ru:password -d
'{
    "restaurant": {
        "id": 100003
    }
}'`

##### Get their vote for today

`curl localhost:8080/rest/votes/ --user user@mail.ru:password`

_________________________
#### Admins can:

##### Get all votes

`curl localhost:8080/rest/admin/votes/ --user admin@mail.ru:admin`

##### Get vote with id 100009

`curl localhost:8080/rest/admin/votes/100009 --user admin@mail.ru:admin`

##### Get all votes for date "2020-01-01"

`curl 'localhost:8080/rest/admin/votes?date=2020-01-01' --user admin@mail.ru:admin`

##### Get all votes for restaurant with id 100003 for date "2020-01-01"

`curl 'localhost:8080/rest/admin/votes/restaurant/100002?date=2020-01-01' --user admin@mail.ru:admin`
