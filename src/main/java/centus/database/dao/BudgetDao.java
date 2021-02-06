package centus.database.dao;

import centus.database.model.Budget;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;
import centus.utils.users.UserSession;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BudgetDao extends CommonDao {
    public BudgetDao() {
        super();
    }


    public List<Budget> getByDate(Date date) throws ApplicationException, SQLException {
        Date resetDate = ConverterDate.resetDate(date);
        Dao<Budget, Integer> dao = getDao(Budget.class);
        QueryBuilder<Budget, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq("DATE", resetDate).and().eq("USER_ID", UserSession.getInstance("").getUserId()).queryForFirst();
        List<Budget> result = dao.query(queryBuilder.prepare());
        result.forEach(i -> {
            System.out.println(i.toString());
        });
        return result;
    }
}
