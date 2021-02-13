package centus.utils.users;

import centus.database.dao.UserDao;
import centus.database.model.User;
import centus.utils.DialogUtils;
import centus.utils.exceptions.ApplicationException;
import centus.utils.exceptions.LoginFailException;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.Optional;

public class UserUtils {

    public static User loginUser() throws SQLException, ApplicationException, LoginFailException {
        PasswordAuthentication auth = new PasswordAuthentication();
        Optional<Pair<String, String>> userInfo = DialogUtils.loginDialog("Logowanie");
        User user = null;
        if (userInfo.isPresent()) {
            Pair<String, String> pair = userInfo.get();

            UserDao userDao = new UserDao();

            user = userDao.getByUsername(pair.getKey());
            boolean passwordValid = auth.authenticate(pair.getValue(), user.getPassword());
            if (!passwordValid) {
                throw new LoginFailException("Nieprawidłowe hasło");
            }

        }
        return user;
    }

    public static User getLoggedUser() throws ApplicationException {
        UserDao userDao = new UserDao();
        return userDao.findById(User.class, Integer.parseInt(UserSession.getInstance("").getUserId()));
    }
}
