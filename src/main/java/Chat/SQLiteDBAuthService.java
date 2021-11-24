package Chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;


public class SQLiteDBAuthService implements AuthService{
    //JDBC
    //Connection
    //Statement

    private static Connection connection; // интерфейс для соединения с базой данных
    private static Statement statement; // интерфейс для отправки запросов в БД

//    public static void main(String[] args) {
//
//        try {
//            connect();
//        } catch (SQLException trowables) {
//            trowables.printStackTrace();
//        }
//        finally {
//            disconnect();
//        }
//    }


    public static void disconnect(){
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }

// создать таблицу, если ее нет
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS users ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "nick VARCHAR UNIQUE NOT NULL, "+
                "login TEXT UNIQUE NOT NULL"+
                "pass TEXT NOT NULL"+
                ")";
    }

    @Override
    public void start() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        statement = connection.createStatement();
        createTable();
        insertTable();
    }

    private void insertTable() {
    }

    @Override
    public void stop() {

    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String pass) {
        return Optional.empty();
    }
}
