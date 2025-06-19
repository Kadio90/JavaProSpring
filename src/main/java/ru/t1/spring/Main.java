package ru.t1.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.t1.spring.dto.User;
import ru.t1.spring.service.UserService;

import java.util.Optional;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        UserService userService = context.getBean(UserService.class);

        // Создание пользователей
        System.out.println("Создание пользователей...");
        User anton = userService.create("Антон");
        User alisa = userService.create("Алиса");
        System.out.println("Создан: " + anton);
        System.out.println("Создан: " + alisa);

        // Получение всех пользователей
        System.out.println("\nПолучение всех пользователей:");
        userService.getAllUsers().forEach(System.out::println);

        // Получение пользователя по ID
        System.out.println("\nПолучение пользователя по ID...");
        User foundUser = userService.getById(alisa.getId());
        Optional.ofNullable(foundUser).ifPresentOrElse(
                user -> System.out.println("Найден: " + user),
                () -> System.out.println("Не найден user с ID:"+ alisa.getId())
        );

        Long fakeId = Long.parseLong("987654");
        User notFoundUser = userService.getById(fakeId);
        Optional.ofNullable(notFoundUser).ifPresentOrElse(
                user -> System.out.println("Найден: " + user),
                () -> System.out.println("Не найден user с ID:"+ fakeId)
        );

        // Удаление пользователя
        System.out.println("\nУдаление пользователя...");
        boolean isDeleted = userService.deleteById(anton.getId());
        System.out.println("Пользователь '" + anton.getId() + "' удален: " + isDeleted);

        boolean isNoDeleted = userService.deleteById(fakeId);
        System.out.println("Пользователь '" + fakeId + "' удален: " + isNoDeleted);

        // Проверка удаления
        System.out.println("\nПроверка удаления пользователей...");
        User deletedUser = userService.getById(anton.getId());
        Optional.ofNullable(foundUser).ifPresentOrElse(
                user -> System.out.println("Пользователь существует: " + user),
                () -> System.out.println("Пользователь не найден (корректное удаление)")
        );

        context.close();
    }
}