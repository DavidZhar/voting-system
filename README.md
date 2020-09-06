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

Admins can get, modify and delete users:  

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

---

