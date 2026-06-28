package laszlo.dev.todo.repository;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class ConnectionManager {
    public Connection getConnection() throws SQLException {

        String host = System.getenv().getOrDefault("MYSQL_HOST", "localhost");
        final String url = "jdbc:mysql://" + host + ":3306/user_datas";
        String username = System.getenv("MYSQL_USERNAME");
        String password = System.getenv("MYSQL_PASSWORD");
        return DriverManager.getConnection(url, username, password);
    }
}
