package centus.viewmodel.expenseModels;

import centus.database.dao.ExpenseCategoryDao;
import centus.database.dao.ExpenseDao;
import centus.database.model.Expense;
import centus.database.model.ExpenseCategory;
import centus.utils.converters.ConverterExpenseCategory;
import centus.utils.converters.ConverterExpense;
import centus.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ExpenseModel {

    private ObjectProperty<ExpenseFX> expenseFxObjectProperty = new SimpleObjectProperty<>(new ExpenseFX());
    private ObservableList<ExpenseFX> expenseFXObservableList = FXCollections.observableArrayList();
    private ObservableList<ExpenseCategoryFX> expenseCategoryFXObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        initExpensesList();
        initExpensesCategoryList();
    }

    private void initExpensesList() throws ApplicationException {
        ExpenseDao expenseDao = new ExpenseDao();
        List<Expense> expenses = expenseDao.queryForAll(Expense.class);
        this.expenseFXObservableList.clear();

        expenses.forEach(expense -> {
            this.expenseFXObservableList.add(ConverterExpense.convertToExpenseFx(expense));
        });
    }

    private void initExpensesCategoryList() throws ApplicationException {
        ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDao();
        List<ExpenseCategory> categoryList = expenseCategoryDao.queryForAll(ExpenseCategory.class);

        this.expenseCategoryFXObservableList.clear();
        categoryList.forEach(cat -> {
            ExpenseCategoryFX expenseCategoryFX = ConverterExpenseCategory.convertToExpenseCategoryFx(cat);
            this.expenseCategoryFXObservableList.add(expenseCategoryFX);
        });
    }

    public void saveExpenseToDb() throws ApplicationException {
        Expense expense = ConverterExpense.convertToExpense(this.getExpenseFxObjectProperty());
        ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDao();
        ExpenseCategory expenseCategory = expenseCategoryDao.findById(
                ExpenseCategory.class, this.getExpenseFxObjectProperty().getExpenseCategory().getId());
        expense.setExpenseCategory(expenseCategory);

        ExpenseDao expenseDao = new ExpenseDao();
        expenseDao.createOrUpdate(expense);
        initExpensesList();
    }


    public ObservableList<ExpenseFX> getExpenseFxObservableList() {
        return expenseFXObservableList;
    }

    public void setExpenseFxObservableList(ObservableList<ExpenseFX> expenseFXObservableList) {
        this.expenseFXObservableList = expenseFXObservableList;
    }

    public ObservableList<ExpenseCategoryFX> getExpenseCategoryFxObservableList() {
        return expenseCategoryFXObservableList;
    }

    public void setExpenseCategoryFxObservableList(ObservableList<ExpenseCategoryFX> expenseCategoryFXObservableList) {
        this.expenseCategoryFXObservableList = expenseCategoryFXObservableList;
    }

    public ExpenseFX getExpenseFxObjectProperty() {
        return expenseFxObjectProperty.get();
    }

    public ObjectProperty<ExpenseFX> expenseFxObjectPropertyProperty() {
        return expenseFxObjectProperty;
    }

    public void setExpenseFxObjectProperty(ExpenseFX expenseFXObjectProperty) {
        this.expenseFxObjectProperty.set(expenseFXObjectProperty);
    }
}
