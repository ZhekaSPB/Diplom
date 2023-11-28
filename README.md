## Дипломный проект профессии «Тестировщик»

### Документы

1. План
2. Отчет по итоагм тестирования 
3. Отчет по итогам автоматизации 

## Подготовительный этап
1. Установить и запустить IntelliJ IDEA;
2. Установать и запустить Docker Desktop;
3. Скопировать репозиторий с Github https://github.com/ZhekaSPB/Diplom.git
4. Открыть проект в IntelliJ IDEA.

## Запуск тестового приложения
1. Запустить MySQL, PostgreSQL, NodeJS через терминал командой: 
    docker-compose up 

2. В новой вкладке терминала запустить тестируемое приложение:
   Для MySQL:
   java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar
   
   Для PostgreSQL:
   java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar
  
   Убедиться в готовности системы. Приложение должно быть доступно по адресу:
   http://localhost:8080/
  
3. Запуск тестов
   В новой вкладке терминала запустить тесты:

Для MySQL:
./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app

Для PostgreSQL:
./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app

## Перезапуск тестов и приложения
Для остановки приложения в окне терминала нужно ввести команду Ctrl+С и повторить необходимые действия из предыдущих разделов.

## Формирование отчёта о тестировании
В новой вкладке терминала вводим команду

gradlew allureServe



