# multipart-application
This pet project is a set of simple modules for working with recordings.

## Установка среды

Для работы с проектом необходимы следующие зависимости:

- JDK 11 или выше
- Git
- Gradle (опционально, используется встроенный gradle wrapper)

### Установка зависимостей

1. Клонируйте репозиторий:
   ```bash
   git clone <URL_РЕПОЗИТОРИЯ>
   cd multipart-application
   ```

## Сборка проекта

Это многомодульный Gradle-проект на Kotlin. Проект состоит из следующих модулей:
- application: основное приложение
- sort_strategies: стратегии сортировки
- data: работа с данными
- data_orm: ORM-слои для работы с данными

### Сборка всего проекта

Для сборки всех модулей используйте встроенный gradle wrapper:

```bash
./gradlew build
```

На Windows:
```bash
gradlew.bat build
```

### Сборка отдельных модулей

Для сборки конкретного модуля:
```bash
./gradlew :module-name:build
```

Например:
```bash
./gradlew :application:build
```

### Запуск приложения

Для запуска основного приложения:
```bash
./gradlew :application:run
```

### Очистка проекта

Для очистки результатов сборки:
```bash
./gradlew clean
```

### Другие полезные команды

Список доступных задач:
```bash
./gradlew tasks
```
