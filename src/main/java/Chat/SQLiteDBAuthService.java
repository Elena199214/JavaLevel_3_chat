package Chat;

import java.sql.*;
import java.util.Optional;


public class SQLiteDBAuthService implements AuthService {

    private static Connection connection; // интерфейс для соединения с базой данных
    private static Statement statement; // интерфейс для отправки запросов в БД

    @Override
    public void start() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        statement = connection.createStatement();
        createTable();
        insertTable();
    }

    // создать таблицу, если ее нет
    public void createTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nick VARCHAR UNIQUE NOT NULL, " +
                "login TEXT UNIQUE NOT NULL" +
                "pass TEXT NOT NULL" +
                ")";
        try {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // проверить, что таблица пустая
    public boolean tableIsEmpty() {
        boolean result = false;
        String sqlCommand = "SELECT * FROM chat.users;";
        try (ResultSet resultSet = statement.executeQuery(sqlCommand)) {
            result = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return !result;
    }


    // заполнить таблицу
    public void insertTable() {
        if (tableIsEmpty()) {
            String sqlCommand =
                    "INSERT INTO chat.users (nick, login, pass) VALUES ('nick1', 'login1', 'pass1'), " +
                            "('nick2', 'login2', 'pass2'), " +
                            "('nick3', 'login3', 'pass3');";
            try {
                statement.executeUpdate(sqlCommand);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String pass) {
        return Optional.empty();
    }

}
    // получить ник пользователя





