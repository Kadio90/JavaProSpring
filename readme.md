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
Hibernate: 
    insert 
    into
        users
        (username) 
    values
        (?)
Hibernate: 
    insert 
    into
        users
        (username) 
    values
        (?)
Создан: User{id=4, username='Антон'}
Создан: User{id=5, username='Алиса'}

Получение всех пользователей:
Hibernate: 
    select
        u1_0.id,
        u1_0.username 
    from
        users u1_0
User{id=1, username='Алексей'}
User{id=2, username='Анастасия'}
User{id=3, username='Артем'}
User{id=4, username='Антон'}
User{id=5, username='Алиса'}

Получение пользователя по ID...
Hibernate: 
    select
        u1_0.id,
        u1_0.username 
    from
        users u1_0 
    where
        u1_0.id=?
Найден: User{id=4, username='Антон'}

Удаление пользователя...
Hibernate: 
    select
        u1_0.id,
        u1_0.username 
    from
        users u1_0 
    where
        u1_0.id=?
Hibernate: 
    delete 
    from
        users 
    where
        id=?
Пользователь удален: true

Проверка удаления:
Hibernate: 
    select
        u1_0.id,
        u1_0.username 
    from
        users u1_0 
    where
        u1_0.id=?
Пользователь не найден (корректное удаление)
```

