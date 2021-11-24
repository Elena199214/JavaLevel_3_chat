package Chat;


import java.sql.SQLException;
import java.util.Optional;

/**
 * Сервис авторизации
 */
public interface AuthService {

    /**
     * запустить сервис
     */
    void start() throws SQLException;

    /**
     * Остановить сервис
     */

    void stop();

    /**
     * Получить никнейм
     * @return
     */
    Optional<String> getNickByLoginAndPass(String login, String pass);
}
