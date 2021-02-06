package centus.database.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Expense extends BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "AMOUNT")
    private double amount;

    @DatabaseField(columnName = "DATE")
    private Date date;

    @DatabaseField(columnName = "USER_ID", canBeNull = false, foreign = true)
    private User user;

    @DatabaseField(
            columnName = "CATEGORY_EXPENSE_ID",
            foreign = true,
            foreignAutoRefresh = true,
            foreignAutoCreate = true,
            canBeNull = false
    )

    private ExpenseCategory expenseCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
