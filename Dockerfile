# базовый образ, содержащий java17
FROM openjdk:17-oracle

# директория приложения внутри контейнера
WORKDIR /app

# копируем JAR-файл приложения в контейнер
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# определение переменной среды
ENV EVENT_QUEUE_ENABLED=true

# команда для запуска приложения
CMD ["java", "-jar", "app.jar"]