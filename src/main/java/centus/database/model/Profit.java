package centus.database.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Profit extends BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "PROFIT")
    private double amount;

    @DatabaseField(columnName = "DATE")
    private Date date;



    @DatabaseField(columnName = "USER_ID", canBeNull = false, foreign = true)
    private User user;



    @DatabaseField(
            columnName = "CATEGORY_PROFIT_ID",
            foreign = true,
            foreignAutoRefresh = true,
            foreignAutoCreate = true,
            canBeNull = false
    )
    private ProfitCategory profitCategory;

    public Profit() {
    }

    public Profit(double amount, Date date, ProfitCategory profitCategory, User user) {
        this.amount = amount;
        this.date = date;
        this.user = user;
        this.profitCategory = profitCategory;
    }

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

    public ProfitCategory getProfitCategory() {
        return profitCategory;
    }

    public void setProfitCategory(ProfitCategory profitCategory) {
        this.profitCategory = profitCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
