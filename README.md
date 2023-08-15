# java-explore-with-me
Java-explore-with-me это сервис, который позволит пользователям делиться информацией об интересных событиях и находить компанию для участия в них.
# Стек технологий
Java 11, Spring Boot, Maven, Git, REST Api, Lombok, PostgresSQL , Spring Data JPA, Docker.
# Архитектура проекта
Сервис включает в себя два модуля. В модуле ewm-service реализованы все сервисные функции приложения. Модуль stats-service выполняет функции накопления статистики. Обмен данными между модулями ewm-service и tats-service реализован по технологии REST. Управление модулями реализовано через Docker-compose. 

Основной сервис работаеат на порту 8080. API основного сервиса и разделен на три части:

* Публичный (доступен для всех пользователей)

* API для работы с событиями
* API для работы с категориями
* API для работы с подборками событий

* Приватный (доступен только для зарегистрированных пользователей)

* API для работы с событиями
* API для работы с запросами на участие в событиях
* API для работы с комментариями пользователей

* Административный (доступен только для администратора проекта)

* API для работы с событиями
* API для работы с категориями
* API для работы с пользователями
* API для работы с подборками событий


Сервис статистики работает на порту 9090:
* API для работы со статистикой посещений




# Запуск приложения
склонировать проект на свой компьютер
выполнить команду mvn clean package
выполнить команду docker-compose up -d

