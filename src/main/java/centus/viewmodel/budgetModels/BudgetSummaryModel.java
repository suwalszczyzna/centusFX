package centus.viewmodel.budgetModels;

import centus.database.dao.BudgetDao;
import centus.database.dao.ExpenseDao;
import centus.database.model.Budget;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BudgetSummaryModel {
    private ObjectProperty<BudgetSummaryFx> budgetSummaryFxObjectProperty = new SimpleObjectProperty<>(new BudgetSummaryFx());
    // private BudgetSummaryFx budgetSummaryFx = new BudgetSummaryFx();

    public void initBudgetSummary() throws SQLException, ApplicationException {
        Date today = new Date();

        BudgetDao budgetDao = new BudgetDao();
        List<Budget> budgetsList = budgetDao.getByDate(today);

        this.budgetSummaryFxObjectProperty.get().setMainValue("-");
        this.budgetSummaryFxObjectProperty.get().setSpentValue("-");
        this.budgetSummaryFxObjectProperty.get().setRestValue("-");

        if (!budgetsList.isEmpty()) {
            Budget budget = budgetsList.get(0);
            String value = String.valueOf(budget.getBudgetValue());

            ExpenseDao expenseDao = new ExpenseDao();

            double expensesSum = expenseDao.getExpensesSumBetweenDate(
                    ConverterDate.resetDate(today),
                    ConverterDate.lastDayOfMonth(today)
            );

            double rest = budget.getBudgetValue() - expensesSum;

            this.budgetSummaryFxObjectProperty.get().setMainValue(value);
            this.budgetSummaryFxObjectProperty.get().setSpentValue(String.valueOf(expensesSum));
            this.budgetSummaryFxObjectProperty.get().setRestValue(String.valueOf(rest));
            System.out.println(this.budgetSummaryFxObjectProperty.get().toString());
        }
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

}
