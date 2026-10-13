# Task Tracker: Tests and GitHub CI

## Цель

- Покрыть поведение приложения автотестами
- Настроить автоматический запуск тестов через GitHub Actions

Стартовое состояние проекта — финальная версия предыдущего этапа.

Предыдущий этап: [task-tracker-01-spring-boot](https://github.com/konstantin-bushuev/task-tracker-01-spring-boot)

## Стек

* Java 21
* Spring Boot
* Gradle (Kotlin DSL)
* JUnit 5
* Mockito
* MockMvc
* GitHub Actions

## План

* [x] `create project`
* [x] `add test dependencies`
* [x] `add TaskService unit tests`
* [x] `add TaskController integration tests`
* [x] `add validation and error handling tests`
* [x] `configure GitHub Actions CI`
* [x] `verify CI workflow`
* [x] `complete README`

## Запуск приложения

1. Требуется установленная Java 21
2. Клонировать репозиторий

3. Запуск приложения:

```bash
./gradlew bootRun
```

Приложение будет доступно по адресу http://localhost:8080

## Запуск тестов

```bash
./gradlew test
```

## API


| Метод | Endpoint | Описание |
|---|---|---|
| GET | `/api/tasks` | Получить все задачи |
| GET | `/api/tasks/{id}` | Получить задачу по id |
| POST | `/api/tasks` | Создать задачу |
| PUT | `/api/tasks/{id}` | Обновить задачу |
| DELETE | `/api/tasks/{id}` | Удалить задачу |
| PATCH | `/api/tasks/{id}/complete` | Завершить задачу |

Для `POST` и `PUT` используется JSON:

```json
{
  "title": "Task title",
  "description": "Task description"
}
```

- `title` — обязательное поле, максимум 30 символов.
- `description` — необязательное поле, максимум 100 символов.

Новые задачи создаются со статусом `TODO`.

## Архитектура

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
In-memory storage (List<Task>)
```


## Структура проекта

```text
src/main/java/io/github/konstantinbushuev/tasktracker02testsci/
├── model
│   ├── Task.java                           # модель данных
│   └── TaskStatus.java                     # статусы задач   
├── dto
│   ├── TaskRequestDTO.java                 # входные данные для запросов
│   └── ErrorResponse.java                  # формат ошибок  
├── repository
│   └── TaskRepository.java                 # хранение задач в памяти
├── service
│   └── TaskService.java                    # бизнес-логика
├── controller
│   └── TaskController.java                 # HTTP endpoints
└── exception/                       
    ├── GlobalExceptionHandler.java         # глобальная обработка ошибок API
    └── TaskNotFoundException.java          # исключение для ненайденной задачи    
    
src/test/java/io/github/konstantinbushuev/tasktracker02testsci/    
├── service
│   └── TaskServiceTest.java                # TaskService unit tests  
└── controller
    ├── TaskControllerTest.java             # TaskController integration tests
    └── TaskControllerValidationTest.java   # Validation and error handling tests  
        
```


## Следующий этап

[task-tracker-03-postgresql](https://github.com/konstantin-bushuev/task-tracker-03-postgresql)

Замена временного репозитория на БД
