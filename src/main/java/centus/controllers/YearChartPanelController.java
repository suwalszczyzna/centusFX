package centus.controllers;

import centus.database.dao.ProfitDao;
import centus.utils.exceptions.ApplicationException;
import centus.viewmodel.chartModels.YearChartModel;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YearChartPanelController {
    @FXML
    private BarChart<String, Number> yearChart;
    @FXML
    private CategoryAxis daysAxis;
    @FXML
    private NumberAxis sumAxis;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");


    public void initialize(){
        YearChartModel yearChartModel = new YearChartModel(new Date());

        try {
            yearChartModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Wydatki");
        yearChartModel.getChartItems().forEach(i -> series1.getData().add(new XYChart.Data(sdf.format(i.getDate()), i.getAmountExpenses())));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Przychody");
        yearChartModel.getChartItems().forEach(j -> series2.getData().add(new XYChart.Data(sdf.format(j.getDate()), j.getAmountProfits())));

        yearChart.getData().addAll(series1, series2);
    }


}
