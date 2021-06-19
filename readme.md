###
в Controller вся логика по обработке http

###
По умолчанию приложение открывается на порту 8080 (ex. http://localhost:8080/users/)

###
cmd + f9 -  hot reload
cmd + n - generate resources

###
mysql -u root -p - запускаю mySql

###
!!! Обязательно создать таблицу spring_main !!!
create database spring_main;
use spring_main;

### 
Создал бд -> 
сходил в спринг https://spring.io/guides/gs/accessing-data-mysql/ подключил зависимости для mysql -> 
проапдейтил application.properties ->
создал repository(для работы с бд) и использовал в UserController ->
в постмане отправил http://localhost:8080/users с боди 
{
"username": "user",
"password": "pass"
} 
->
создал сервис (в сервисах должна лежать вся логика) (вынес логику обработки ошибки из пост) и exception (саму ошибку) для User
->
model - нужно для работы с моделью которую возвращаю на клиент
->
в репо создаю классы которые экстендятся от CrudRepository и имплементят все CRUD методы
->
compileOnly "org.flywaydb:flyway-core:7.10.0" - использую для миграции БД
Создаю обязательную папку db и в ней папку migration (тоже обязательно)
внутри обязательно указываю V1__ (V затем версию затем __) и название миграции