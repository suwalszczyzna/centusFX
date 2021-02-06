package centus.viewmodel.chartModels;

import java.util.Date;

public class ChartItemModel {
    private Date date;
    private double amountExpenses;
    private double amountProfits;

    public ChartItemModel() {
    }

    public ChartItemModel(Date date) {
        this.date = date;
    }

    public ChartItemModel(double amountExpenses, double amountProfits) {
        this.amountExpenses = amountExpenses;
        this.amountProfits = amountProfits;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmountExpenses() {
        return amountExpenses;
    }

    public void setAmountExpenses(double amountExpenses) {
        this.amountExpenses = amountExpenses;
    }

    public double getAmountProfits() {
        return amountProfits;
    }

    public void setAmountProfits(double amountProfits) {
        this.amountProfits = amountProfits;
    }
}