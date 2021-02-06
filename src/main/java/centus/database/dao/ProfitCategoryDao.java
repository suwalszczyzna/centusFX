package centus.database.dao;

import centus.database.model.BaseModel;
import centus.utils.exceptions.ApplicationException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ProfitCategoryDao extends CommonDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfitCategoryDao.class);
    public ProfitCategoryDao() {
        super();
    }

    @Override
    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Nie znaleziono w DB");
        } finally {
            this.closeDbConnection();
        }
    }
}
