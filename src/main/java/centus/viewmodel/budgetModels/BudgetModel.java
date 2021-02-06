package centus.viewmodel.budgetModels;

import centus.database.dao.BudgetDao;
import centus.database.dao.ExpenseDao;
import centus.database.dao.ProfitDao;
import centus.database.model.Budget;
import centus.database.model.User;
import centus.utils.converters.ConverterBudget;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BudgetModel {
    private ObjectProperty<BudgetFx> budgetObjectProperty = new SimpleObjectProperty<>(new BudgetFx());
    private ObservableList<BudgetFx> budgetFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<BudgetSummaryFx> budgetSummaryFxObjectProperty = new SimpleObjectProperty<>(new BudgetSummaryFx());
    private BudgetSummaryFx budgetSummaryFx = new BudgetSummaryFx();

    public void init() throws ApplicationException, SQLException {
        this.initBudgetList();
        this.initBudgetSummary();
    }

    public void initBudgetList() throws ApplicationException {
        BudgetDao budgetDao = new BudgetDao();
        List<Budget> budgetList = budgetDao.queryForAll(Budget.class);

        this.budgetFxObservableList.clear();
        budgetList.forEach(b -> {
            BudgetFx budgetFx = ConverterBudget.convertToBudgetFx(b);
            this.budgetFxObservableList.add(budgetFx);
        });
    }

    public void initBudgetSummary() throws SQLException, ApplicationException {
        Date today = new Date();

        BudgetDao budgetDao = new BudgetDao();
        List<Budget> budgetsList = budgetDao.getByDate(today);

        this.budgetSummaryFx.setMainValue("-");
        this.budgetSummaryFx.setSpentValue("-");
        this.budgetSummaryFx.setRestValue("-");

        if(!budgetsList.isEmpty()) {
            Budget budget = budgetsList.get(0);
            String value = String.valueOf(budget.getBudgetValue());

            ExpenseDao expenseDao = new ExpenseDao();

            double expensesSum = expenseDao.getExpensesSumBetweenDate(
                    ConverterDate.resetDate(today),
                    ConverterDate.lastDayOfMonth(today)
            );

            double rest = budget.getBudgetValue() - expensesSum;

            this.budgetSummaryFx.setMainValue(value);
            this.budgetSummaryFx.setSpentValue(String.valueOf(expensesSum));
            this.budgetSummaryFx.setRestValue(String.valueOf(rest));
            System.out.println(this.budgetSummaryFx.toString());
        }
    }


    public void saveBudgetToDb() throws ApplicationException {
        Budget budget = ConverterBudget.convertToBudget(this.getBudgetObjectProperty());

        BudgetDao budgetDao = new BudgetDao();
        budgetDao.createOrUpdate(budget);
        this.initBudgetList();
    }

    public BudgetSummaryFx getBudgetSummaryFxObjectProperty() {
        return budgetSummaryFxObjectProperty.get();
    }

    public ObjectProperty<BudgetSummaryFx> budgetSummaryFxObjectPropertyProperty() {
        return budgetSummaryFxObjectProperty;
    }

    public void setBudgetSummaryFxObjectProperty(BudgetSummaryFx budgetSummaryFxObjectProperty) {
        this.budgetSummaryFxObjectProperty.set(budgetSummaryFxObjectProperty);
    }

    public BudgetSummaryFx getBudgetSummaryFx() {
        return budgetSummaryFx;
    }

    public void setBudgetSummaryFx(BudgetSummaryFx budgetSummaryFx) {
        this.budgetSummaryFx = budgetSummaryFx;
    }

    public BudgetFx getBudgetObjectProperty() {
        return budgetObjectProperty.get();
    }

    public ObjectProperty<BudgetFx> budgetObjectPropertyProperty() {
        return budgetObjectProperty;
    }

    public void setBudgetObjectProperty(BudgetFx budgetObjectProperty) {
        this.budgetObjectProperty.set(budgetObjectProperty);
    }

    public ObservableList<BudgetFx> getBudgetFxObservableList() {
        return budgetFxObservableList;
    }

    public void setBudgetFxObservableList(ObservableList<BudgetFx> budgetFxObservableList) {
        this.budgetFxObservableList = budgetFxObservableList;
    }
}
