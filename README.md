# HostTestProject

Для запуска UI тестов:
gradlew test -PsuiteFile=src\test\resources\testng\indicators_tests.xml
Для запуска API тестов:
gradlew test -PsuiteFile=src\test\resources\testng\indicators_api_tests.xml

Для генерации отчета:
.//gradlew allureReport

Для просмотра отчета:
.//gradlew allureServe



