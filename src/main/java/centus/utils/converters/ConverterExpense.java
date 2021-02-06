package centus.utils.converters;

import centus.database.model.Expense;
import centus.utils.exceptions.ApplicationException;
import centus.utils.users.UserUtils;
import centus.viewmodel.expenseModels.ExpenseFX;

public class ConverterExpense {

    public static Expense convertToExpense(ExpenseFX expenseFX) throws ApplicationException {
        Expense expense = new Expense();
        expense.setId(expenseFX.getId());
        expense.setDate(ConverterDate.convertToDate(expenseFX.getDate()));
        expense.setAmount(Double.parseDouble(expenseFX.getAmount().replace(',', '.')));
        expense.setExpenseCategory(ConverterExpenseCategory.convertToExpenseCategory(expenseFX.getExpenseCategory()));
        expense.setUser(UserUtils.getLoggedUser());
        return expense;
    }

    public static ExpenseFX convertToExpenseFx(Expense expense){
        ExpenseFX expenseFX = new ExpenseFX();
        expenseFX.setId(expense.getId());
        expenseFX.setDate(ConverterDate.convertToLocalDate(expense.getDate()));
        expenseFX.setAmount(String.valueOf(expense.getAmount()));
        expenseFX.setExpenseCategory(ConverterExpenseCategory.convertToExpenseCategoryFx(expense.getExpenseCategory()));
        return expenseFX;
    }

}
