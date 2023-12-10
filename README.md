# Rococo

Web приложение для сохранения фотографий картин, музеев и художников

## Технологии, использованные в Rococo:
- [Spring Boot: web, oauth2, data-jpa](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle 8.0](https://docs.gradle.org/8.0/userguide/userguide.html)
- [Docker](https://www.docker.com/get-started)

## Микросервисы:
- [Rococo-auth]() - *Сервис авторизации*
- [Rococo-gateway]() - *Api-шлюз*
- [Rococo-geo]() - *Сервис для работы с гео данными*
- [Rococo-content]() - *Сервис для работы с основным контентом*
- [Rococo-userData]() - *Сервис для работы с профилями пользователей*


## Локальный запуск приложения

#### 1. Запустить фронт Rococo, для этого перейти в соответсвующий каталог rococo-client

```
npm i
npm run dev
```

Фронт стартанет на порту 3000: http://127.0.0.1:3000/
Обрати внимание! Надо использовать именно 127.0.0.1, а не localhost

#### 2. Запустить базу данных MySQL:
```
docker pull mysql:8.2.0
docker run --name rococo-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=secret -v mysqlrococo:/var/lib/mysql -d mysql:8.2.0
```

#### 3. Установить run конфигурацию для всех сервисов бэкенда 'local'

Для этого зайти в меню Run -> Edit Configurations -> выбрать main класс -> указать Active profiles: local

#### 4. Установить run конфигурацию для всех сервисов бэкенда 'local'

#### 5. Запустить сервисы бэкенда начиная с rococo-auth  
Сервисы запустятся на следующих портах:
- [Rococo-auth]() - *9000*
- [Rococo-gateway]() - *8080*
- [Rococo-geo]() - *8093*
- [Rococo-content]() - *8091*
- [Rococo-userData]() - *8092*

#### 6. Схема микросервисов

![Текст с описанием картинки](\readme\img\1.svg)
