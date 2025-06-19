package ru.t1.spring.dao;

import org.springframework.stereotype.Repository;
import ru.t1.spring.dto.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        createTable();
    }

    private void createTable() {
        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()) {
            // Удаляем таблицу, если она существует
            stmt.execute("DROP TABLE IF EXISTS users");
            // Создаем
            stmt.execute("CREATE TABLE users (" +
                    "id BIGSERIAL PRIMARY KEY, " +
                    "username VARCHAR(255) UNIQUE)");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при выполнении запроса:", e);
        }
    }

    public User create(User user) {
        String sql = "INSERT INTO users (username) VALUES (?) RETURNING id";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при выполнении запроса:", e);
        }
    }

    public User getById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("username")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при выполнении запроса: " + id, e);
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при выполнении запроса: " + id, e);
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("username")
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при выполнении запроса:", e);
        }
    }
}
