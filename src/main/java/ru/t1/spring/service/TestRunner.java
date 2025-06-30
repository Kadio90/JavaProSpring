package ru.t1.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.t1.spring.entity.User;

@Service
@AllArgsConstructor
public class TestRunner implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args){
        System.out.println("Создание пользователей...");
        User user1 = userService.create("Антон");
        User user2 = userService.create("Алиса");
        System.out.println("Создан: " + user1);
        System.out.println("Создан: " + user2);

        System.out.println("\nПолучение всех пользователей:");
        userService.getAllUsers().forEach(System.out::println);

        System.out.println("\nПолучение пользователя по ID...");
        userService.getById(user1.getId())
                .ifPresentOrElse(
                        user -> System.out.println("Найден: " + user),
                        () -> System.out.println("Не найден")
                );

        System.out.println("\nУдаление пользователя...");
        boolean isDeleted = userService.deleteById(user1.getId());
        System.out.println("Пользователь удален: " + isDeleted);

        System.out.println("\nПроверка удаления:");
        userService.getById(user1.getId())
                .ifPresentOrElse(
                        user -> System.out.println("Пользователь существует: " + user),
                        () -> System.out.println("Пользователь не найден (корректное удаление)")
                );
    }
}
