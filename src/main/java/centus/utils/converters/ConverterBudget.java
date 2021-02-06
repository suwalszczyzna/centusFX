package centus.utils.converters;

import centus.database.dao.UserDao;
import centus.database.model.Budget;
import centus.database.model.User;
import centus.utils.exceptions.ApplicationException;
import centus.utils.users.UserSession;
import centus.utils.users.UserUtils;
import centus.viewmodel.budgetModels.BudgetFx;

public class ConverterBudget {
    public static BudgetFx convertToBudgetFx(Budget budget){
        BudgetFx budgetFx = new BudgetFx();
        budgetFx.setId(budget.getId());
        budgetFx.setBudgetValue(String.valueOf(budget.getBudgetValue()));
        budgetFx.setDate(ConverterDate.convertToLocalDate(budget.getDate()));
        return budgetFx;
    }

    public static Budget convertToBudget(BudgetFx budgetFx) throws ApplicationException {
        Budget budget = new Budget();
        budget.setId(budgetFx.getId());
        budget.setBudgetValue(Double.parseDouble(budgetFx.getBudgetValue()));
        budget.setDate(ConverterDate.convertToDate(budgetFx.getDate()));
        budget.setUser(UserUtils.getLoggedUser());

        return budget;
    }
}
