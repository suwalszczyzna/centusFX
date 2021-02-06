package centus.viewmodel.chartModels;

import centus.database.dao.ExpenseDao;
import centus.database.dao.ProfitDao;
import centus.database.model.Expense;
import centus.database.model.Profit;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthlyChartModel {

    private Date date;
    private List<ChartItemModel> chartItems = new ArrayList<>();

    public void init() throws ApplicationException {
        initDates();
        fillChartItems();
    }

    public MonthlyChartModel(Date date) {
        this.date = date;
    }

    private void fillChartItems() throws ApplicationException {
        ExpenseDao expenseDao = new ExpenseDao();
        List<Expense> expenses = expenseDao.queryForAll(Expense.class);

        ProfitDao profitDao = new ProfitDao();
        List<Profit> profits = profitDao.queryForAll(Profit.class);

        chartItems.forEach(i -> {
            i.setAmountExpenses(this.getSumOfExpenseByDate(i.getDate(), expenses));
            i.setAmountProfits(this.getSumOfProfitsByDate(i.getDate(), profits));
        });
    }


    private void initDates() {
        LocalDate localDate = ConverterDate.convertToLocalDate(this.date);
        Month month = localDate.getMonth();
        for (int i = 1; i <= month.length(localDate.isLeapYear()); i++) {
            LocalDate tempDate = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), i);
            ChartItemModel itemModel = new ChartItemModel(ConverterDate.convertToDate(tempDate));
            this.chartItems.add(itemModel);
        }
    }

    private Double getSumOfExpenseByDate(Date date, List<Expense> expenses) {
        double sum = 0.0;

        for (Expense i : expenses) {
            LocalDate date1 = ConverterDate.convertToLocalDate(i.getDate());
            LocalDate date2 = ConverterDate.convertToLocalDate(date);
            if (date1.equals(date2)) {
                sum += i.getAmount();
            }
        }
        return sum;
    }

    private Double getSumOfProfitsByDate(Date date, List<Profit> profits) {
        double sum = 0.0;

        for (Profit j : profits) {
            LocalDate date1 = ConverterDate.convertToLocalDate(j.getDate());
            LocalDate date2 = ConverterDate.convertToLocalDate(date);
            if (date1.equals(date2)) {
                sum += j.getAmount();
            }
        }
        return sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ChartItemModel> getChartItems() {
        return chartItems;
    }

    public void setChartItems(List<ChartItemModel> chartItems) {
        this.chartItems = chartItems;
    }
}
