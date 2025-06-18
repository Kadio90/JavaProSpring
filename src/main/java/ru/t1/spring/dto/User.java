package ru.t1.spring.dto;

public class User {
    private Long id;
    private String username;

    // Конструкторы, геттеры, сеттеры
    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User(long id, String username) {
        this.username = username;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "'}";
    }
}
