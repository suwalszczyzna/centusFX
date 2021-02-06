package centus.controllers;

import centus.viewmodel.chartModels.MonthlyChartModel;
import centus.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthlyChartPanelController {
    @FXML
    private BarChart<String, Number> monthlyChart;
    @FXML
    private CategoryAxis daysAxis;
    @FXML
    private NumberAxis sumAxis;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");


    public void initialize(){
        MonthlyChartModel monthlyChartModel = new MonthlyChartModel(new Date());

        try {
            monthlyChartModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }



        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Wydatki");
        monthlyChartModel.getChartItems().forEach(i -> series1.getData().add(new XYChart.Data(sdf.format(i.getDate()), i.getAmountExpenses())));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Przychody");
        monthlyChartModel.getChartItems().forEach(j -> series2.getData().add(new XYChart.Data(sdf.format(j.getDate()), j.getAmountProfits())));

        monthlyChart.getData().addAll(series1, series2);
    }


}
