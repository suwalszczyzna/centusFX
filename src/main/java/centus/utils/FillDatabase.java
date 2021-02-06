package centus.utils;


import centus.database.dao.UserDao;
import centus.database.model.ExpenseCategory;
import centus.database.model.User;
import centus.utils.exceptions.ApplicationException;


public class FillDatabase {
    public static void fillDatabase() {
        User damian = new User();
        damian.setName("damian");
        damian.setPassword("test");

        User janusz = new User();
        janusz.setName("janusz");
        janusz.setPassword("test");

        UserDao userDao = new UserDao();
        try {
            userDao.createOrUpdate(janusz);
            userDao.createOrUpdate(damian);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        ExpenseCategory expenseCategory1 = new ExpenseCategory();
        expenseCategory1.setName("Produkty spożywcze");

        ExpenseCategory expenseCategory2 = new ExpenseCategory();
        expenseCategory2.setName("Chemia");

        ExpenseCategory expenseCategory3 = new ExpenseCategory();
        expenseCategory3.setName("Edukacja");

        ExpenseCategory expenseCategory4 = new ExpenseCategory();
        expenseCategory4.setName("Elektronika");

        ExpenseCategory expenseCategory5 = new ExpenseCategory();
        expenseCategory5.setName("Hobby");
    }
}
