package centus.controllers;

import centus.viewmodel.chartModels.MonthlyChartModel;
import centus.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthlyChartPanelController {

    @FXML
    private LineChart<String, Number> monthlyLineChart;
    @FXML
    private CategoryAxis daysAxis;
    @FXML
    private NumberAxis sumAxis;


    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");


    public void initialize() {
        MonthlyChartModel monthlyChartModel = new MonthlyChartModel(new Date());

        try {
            monthlyChartModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        XYChart.Series<String, Number> expensesSeries = new XYChart.Series<>();
        expensesSeries.setName("Wydatki");
        monthlyChartModel.getChartItems().forEach(data -> {
            expensesSeries.getData().add(new XYChart.Data<>(sdf.format(data.getDate()), data.getAmountExpenses()));
        });

        XYChart.Series<String, Number> profitsSeries = new XYChart.Series<>();
        profitsSeries.setName("Przychody");
        monthlyChartModel.getChartItems().forEach(data -> {
            profitsSeries.getData().add(new XYChart.Data<>(sdf.format(data.getDate()), data.getAmountProfits()));
        });


        monthlyLineChart.getData().addAll(expensesSeries, profitsSeries);

    }


}
