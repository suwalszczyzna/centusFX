package centus.viewmodel.chartModels;

import centus.database.dao.ExpenseDao;
import centus.database.dao.ProfitDao;
import centus.database.model.Expense;
import centus.database.model.Profit;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YearChartModel {

    private Date date;
    private List<ChartItemModel> chartItems = new ArrayList<>();

    public YearChartModel(Date date) {
        this.date = date;
    }

    public void init() throws ApplicationException {
        initDates();
        fillChartItems();
    }

    private void fillChartItems() throws ApplicationException {
        chartItems.forEach(i -> {
            try {
                i.setAmountExpenses(this.getSumOfExpenseByMonth(i.getDate()));
                i.setAmountProfits(this.getSumOfProfitsByMonth(i.getDate()));
            } catch (ApplicationException e) {
                e.printStackTrace();
            }
        });
    }


    private void initDates() {
        LocalDate localDate = ConverterDate.convertToLocalDate(this.date);

        int months = 12;
        for (int month = 1; month <= months; month++) {
            LocalDate tempDate = LocalDate.of(localDate.getYear(), month, 1);
            ChartItemModel itemModel = new ChartItemModel(ConverterDate.convertToDate(tempDate));
            this.chartItems.add(itemModel);
        }
    }

    private Double getSumOfExpenseByMonth(Date date) throws ApplicationException {
        ExpenseDao expenseDao = new ExpenseDao();
        LocalDate d1 = ConverterDate.convertToLocalDate(date);
        LocalDate fromDate = LocalDate.of(d1.getYear(), d1.getMonthValue(), 1);
        LocalDate upDate = LocalDate.of(d1.getYear(), d1.getMonthValue(), d1.getMonth().length(d1.isLeapYear()));

        return expenseDao.getExpensesSumBetweenDate(
                ConverterDate.convertToDate(fromDate),
                ConverterDate.convertToDate(upDate)
        );
    }

    private Double getSumOfProfitsByMonth(Date date) throws ApplicationException {
        ProfitDao profitDao = new ProfitDao();
        LocalDate d1 = ConverterDate.convertToLocalDate(date);
        LocalDate fromDate = LocalDate.of(d1.getYear(), d1.getMonthValue(), 1);
        LocalDate upDate = LocalDate.of(d1.getYear(), d1.getMonthValue(), d1.getMonth().length(d1.isLeapYear()));

        return profitDao.getProfitsSumBetweenDate(
                ConverterDate.convertToDate(fromDate),
                ConverterDate.convertToDate(upDate)
        );
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
