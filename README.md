# CRUD REST API Music Streaming App

###

Это проект CRUD API для стримингового сервиса для прослушивания музыки, создан с использованием фреймворка Spring Boot на языке Java. Данная API поддерживает операции создания, обновления, удаления и получения для следующих сущностей: треки, исполнители, альбомы, пользователи. 

### Сущности:

- User: содержит информацию об обычных пользователях сервиса
- Artist: содержит информацию об артистах, альбомах и треках их авторства.
- Album: содержит информацию об альбомах, их содержимом и их авторах.
- Track: содержит информацию о треках, связанных с ними альбомах и авторов.

## Установка

### Требования

- Java 21 и выше
- Gradle
- PostgreSQL
- Spring Boot 3.3.4
- Spring Data JPA


### Клонирование репозитория

Склонируйте этот репозиторий на ваш компьютер:

```bash
https://github.com/chequecode/stream_app_api.git
```

## Установка базы данных

Настройте параметры связи в файле application.properties:

```bash
spring.application.name=streamapi
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
