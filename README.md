# Проект по автоматизации тестирования API сервиса Reqres
<img  src="images/screens/reqresin.jpg">

## :man_student:: Содержание:

- [Стек технологий](#earth_africa-Стек-технологий)
- [Реализованные проверки](#earth_africa-Реализованные-проверки)
- [Сборка в Jenkins](#earth_africa-Jenkins-job)
- [Запуск из терминала](#earth_africa-Запуск-тестов-из-терминала)
- [Allure отчет](#earth_africa-Allure-отчет)
- [Интеграция с Allure TestOps](#earth_africa-Интеграция-c-Allure-TestOps)
- [Уведомления в Telegram](#earth_africa-Уведомление-в-Telegram-при-помощи-бота)

## 🧰: Стек технологий

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://https://qameta.io/"><img src="images/logo/Allure_TO.svg" width="50" height="50"  alt="Allure_TO"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://https://telegram.org/"><img src="images/logo/Telegram.svg" width="50" height="50"  alt="Telegram"/></a>
</p>

## :male_detective:: Реализованные проверки

- ✓ POST/api/users - создание учетной записи пользователя
- ✓ GET/api/users/2 - получение информации о пользователе
- ✓ PATCH/api/users/2 - редактирование учетной записи пользователя
- ✓ DELETE/api/users/2 - удаление учетной записи пользователя
- ✓ GET/api//users?page=2 - получение списка пользователей
- ✓ POST/api/login - вход в систему
-  ✓ POST/api/register - регистрация в системе

## <img src="images/logo/Jenkins.svg" width="25" height="25"  alt="Jenkins"/></a> Сборка <a target="_blank" href="https://jenkins.autotests.cloud/job/017-dumyka-itavia/"> Jenkins </a>
<p align="center">
<a href="https://jenkins.autotests.cloud/job/ApiDiplomDumyka/"><img src="images/screens/AllureReport.jpg" alt="Jenkins1"/></a>
</p>

## 🧪: Пример авто-тест кейса
<p align="center">
<img title="AllureSuite" src="images/screens/AllureTC.jpg">
</p>

## :rocket:: Запуск тестов из терминала
Локальный запуск:
```
gradle clean test
```
При необходимости можно переопределить параметры запуска:
```
gradle clean
test/positive/negative - все тесты/позитивные/негативные
```

Удаленный запуск:
```
clean test
```

При необходимости можно переопределить параметры запуска:
```
clean
test/positive/negative - все тесты/позитивные/негативные
```

## <img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure"/></a> Allure отчет <a target="_blank" href="https://jenkins.autotests.cloud/job/IBS_test/allure/"></a>

## ⛅: Основной отчет
<p align="center">
<img title="Allure" src="images/screens/AllureReport.jpg">
</p>

## <img src="images/logo/Allure.svg" width="25" height="25"  alt="Allure_TO"/></a> Интеграция с Allure TestOps <a target="_blank" href="https://allure.autotests.cloud/project/1858/dashboards"></a>

## :bar_chart:: Доска
<p align="center">
<img title="AllureDashboard" src="images/screens/Dashboard.jpg">
</p>
