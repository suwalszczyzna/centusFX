package centus.database.dao;

import centus.database.model.Budget;
import centus.database.model.User;
import centus.utils.exceptions.ApplicationException;
import centus.utils.exceptions.LoginFailException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class UserDao extends CommonDao{
    public UserDao() {
        super();
    }

    public User getByUsername(String username) throws ApplicationException, SQLException, LoginFailException {
        Dao<User, Integer> dao = getDao(User.class);
        QueryBuilder<User, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq("NAME", username).queryForFirst();
        List<User> result = dao.query(queryBuilder.prepare());
        result.forEach(i -> {
            System.out.println(i.toString());
        });
        if (!result.isEmpty()){
            return result.get(0);
        }else{
            throw new LoginFailException("Brak użytkownika '" + username + "' w bazie.");
        }
    }
}
