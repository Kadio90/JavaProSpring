### Запуск PG:
```
time="2025-06-18T15:52:11+05:00" level=warning msg="C:\\Users\\anton\\IdeaProjects\\JavaProSpring\\docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion"
[+] Running 2/2
 ✔ Network javaprospring_default  Created                                                                                                                                                                                      0.1s
 ✔ Container pg_container         Started
 ```

### Запуск приложения:
```
Создание пользователей...
Создан: User{id=1, username='Антон'}
Создан: User{id=2, username='Алиса'}

Получение всех пользователей:
User{id=1, username='Антон'}
User{id=2, username='Алиса'}

Получение пользователя по ID...
Найден: User{id=2, username='Алиса'}
Не найден user с ID:987654

Удаление пользователя...
Пользователь '1' удален: true
Пользователь '987654' удален: false

Проверка удаления пользователей...
Пользователь существует: User{id=2, username='Алиса'}
```

