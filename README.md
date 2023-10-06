
# java-explore-with-me-microservices
"Java-Explore-With-me" это сервис, который позволит пользователям делиться информацией об
интересных событиях и находить компанию для участия в них. "Java-Explore-With-me-microservices"
развивает проект "Java-Explore-With-me" в направлении масштабирования отдельных сервисов.\
В проекте "Java-Explore-With-me" эндпойнты уже сгруппированы по классам пользователей. Свои
группы эндпойнтов имеют администратор приложения, авторизированный пользователь и обычные
пользователи. Низко нагруженной группой эндпойнтов можно считать группу администратора,
а высоко нагруженная группа относится к обычным пользователям. Разделив приложение на
микросервисы по группам пользователей можно масштабировать приложение для высоко нагруженных
микросервисов.

# Стек технологий
Java 11, Spring Boot, Maven, Git, REST Api, Lombok, PostgresSQL , Spring Data JPA, Docker.

# Архитектура проекта
Проект включает в себя следующие новые микросервисы:
  * Маршрутизатор gateway_server
  * Регистратор ресурсов discovery_server
  * Сервис администратора приложения admin-microservice
  * Сервис авторизированного пользователя private-microservice
  * Сервис обычного пользователя public-microservice
  
Модуль common включает в себя общие сервисные функции для микросервисов, таких как обработка ошибок 
и информационные сообщения.\
Сервис "Java-Explore-With-me" включал в себя два модуля. В модуле ewm-service были  реализованы 
все сервисные функции приложения. Модуль stats-service выполняет функции накопления статистики.
На базе модуля ewm-service сформированы три новых модуля - admin-microservice, private-microservice,
public-microservice. Управление модулями реализовано через Docker-compose.

Основной сервис работает на порту 8080. API основного сервиса и разделен на три части:

API сервиса обычного пользователя public-microservice (доступен для всех пользователей) позволяет:
  * просматривать список событий с краткой информацией по ним, в соответствии с выбранной сортировкой(по количеству просмотров событий, по дате событий или по рейтингу событий)
  * просматривать подробную информацию о выбранном событии
  * просматривать все имеющиеся категории событий
  * просматривать подборки событий, составленные администратором

API сервиса авторизированного пользователя private-microservice (доступен только для зарегистрированных
пользователей) позволяет:
  * добавлять в приложение новые мероприятия, редактировать их и просматривать после добавления
  * подавать заявки на участие в интересующих мероприятиях
  * подтверждать заявки, которые отправили другие пользователи сервиса (доступно только для организаторов событий)
  * работать с комментариями пользователей

API сервиса администратора приложения admin-microservice (доступен только для администратора проекта)
позволяет:
  * управлять категориями для событий - добавлять, изменять и удалять категории
  * управлять подборками мероприятий - добавлять, удалять и закреплять на главной странице
  * модерировать события, размещённые пользователями, — публикация или отклонение
  * управлять пользователями — добавлять, просматривать и удалять

Сервис статистики работает на порту 9090 и предоставляет:
  * количество обращений пользователей к спискам событий
  * количество запросов к подробной информации о событии



# Запуск приложения
* склонировать проект на свой компьютер
* выполнить команду mvn clean package -Dmaven.plugin.validation=VERBOSE
* выполнить команду docker-compose up -d