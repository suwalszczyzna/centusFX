package centus.utils.converters;

import centus.database.model.ExpenseCategory;
import centus.viewmodel.expenseModels.ExpenseCategoryFX;

public class ConverterExpenseCategory {

    public static ExpenseCategory convertToExpenseCategory(ExpenseCategoryFX expenseCategoryFX){
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setId(expenseCategoryFX.getId());
        expenseCategory.setName(expenseCategoryFX.getName());
        return expenseCategory;
    }

    public static ExpenseCategoryFX convertToExpenseCategoryFx(ExpenseCategory expenseCategory){
        ExpenseCategoryFX expenseCategoryFX = new ExpenseCategoryFX();
        expenseCategoryFX.setId(expenseCategory.getId());
        expenseCategoryFX.setName(expenseCategory.getName());
        return expenseCategoryFX;
    }
}
