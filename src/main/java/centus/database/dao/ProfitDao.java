package centus.database.dao;

import centus.database.model.Profit;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;
import centus.utils.users.UserSession;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;

public class ProfitDao extends CommonDao{
    public ProfitDao() {
        super();
    }

    public double getProfitsSumBetweenDate(Date minDate, Date maxDate) throws ApplicationException{
        double result = 0.0;

        try {
            Dao<Profit, Integer> dao = getDao(Profit.class);
            result = dao.
                    queryRawValue(
                            String.format("SELECT SUM(PROFIT) FROM PROFIT WHERE DATE >= '%s' AND DATE <= '%s' AND USER_ID=%s;",
                                    ConverterDate.formatDateToDBQuery(minDate),
                                    ConverterDate.formatDateToDBQuery(maxDate),
                                    UserSession.getInstance("").getUserId()
                            ));
            System.out.println(result);
            System.out.println("minDate = " + minDate + ", maxDate = " + maxDate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeDbConnection();
        }
        return result;
    }
}
