package centus.database.model;

import centus.utils.converters.ConverterDate;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Budget extends BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "VALUE")
    private double budgetValue;

    @DatabaseField(columnName = "DATE", uniqueIndexName = "unique_budget_date_and_user")
    private Date date;

    @DatabaseField(columnName = "USER_ID", uniqueIndexName = "unique_budget_date_and_user", canBeNull = false, foreign = true)
    private User user;

    public Budget() {
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", budgetValue=" + budgetValue +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBudgetValue() {
        return budgetValue;
    }

    public void setBudgetValue(double budgetValue) {
        this.budgetValue = budgetValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = ConverterDate.resetDate(date);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
