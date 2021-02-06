package centus.viewmodel.expenseModels;

import javafx.beans.property.*;

import java.time.LocalDate;

public class ExpenseFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty amount = new SimpleStringProperty();
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private ObjectProperty<ExpenseCategoryFX> expenseCategory = new SimpleObjectProperty<>();

    public ExpenseFX() {
    }

    public ExpenseFX(String amount, LocalDate date, ExpenseCategoryFX expenseCategory) {
        this.amount = new SimpleStringProperty(amount);
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.expenseCategory = new SimpleObjectProperty<ExpenseCategoryFX>(expenseCategory);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ExpenseCategoryFX getExpenseCategory() {
        return expenseCategory.get();
    }

    public ObjectProperty<ExpenseCategoryFX> expenseCategoryProperty() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategoryFX expenseCategory) {
        this.expenseCategory.set(expenseCategory);
    }
}
