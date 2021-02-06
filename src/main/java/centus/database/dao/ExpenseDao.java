package centus.database.dao;

import centus.database.model.Expense;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;
import centus.utils.users.UserSession;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;

public class ExpenseDao extends CommonDao {
    public ExpenseDao() {
        super();
    }

    public double getExpensesSumBetweenDate(Date minDate, Date maxDate) throws ApplicationException {
        double result = 0.0;
        try {
            Dao<Expense, Integer> dao = getDao(Expense.class);
            result = dao.
                    queryRawValue(
                            String.format("SELECT SUM(AMOUNT) FROM EXPENSE WHERE DATE >= '%s' AND DATE <= '%s' AND USER_ID=%s;",
                                    ConverterDate.formatDateToDBQuery(minDate),
                                    ConverterDate.formatDateToDBQuery(maxDate),
                                    UserSession.getInstance("").getUserId()
                            ));
            System.out.println(result);
            System.out.println("minDate = " + minDate + ", maxDate = " + maxDate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            this.closeDbConnection();
        }
        return result;
    }
}
