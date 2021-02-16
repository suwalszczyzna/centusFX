package centus.database.dbutils;

import centus.database.model.*;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./centusDB";
    private static final String USER = "admin";
    private static final String PASS = "admin";

    private static ConnectionSource connectionSource;

    public static void initDatabase() {
        createConnectionSource();
        //dropTable();
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Expense.class);
            TableUtils.createTableIfNotExists(connectionSource, ExpenseCategory.class);
            TableUtils.createTableIfNotExists(connectionSource, ProfitCategory.class);
            TableUtils.createTableIfNotExists(connectionSource, Profit.class);
            TableUtils.createTableIfNotExists(connectionSource, Budget.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Expense.class, true);
            TableUtils.dropTable(connectionSource, ExpenseCategory.class, true);
            TableUtils.dropTable(connectionSource, Profit.class, true);
            TableUtils.dropTable(connectionSource, ProfitCategory.class, true);
            TableUtils.dropTable(connectionSource, Budget.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}