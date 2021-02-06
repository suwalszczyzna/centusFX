package centus.controllers;

import centus.utils.DialogUtils;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;
import centus.viewmodel.budgetModels.BudgetModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class LeftMenuController {
    @FXML
    private Label currentMonthLabel;
    @FXML
    private Label remainingBudgetValueLabel;
    @FXML
    private Label currentExpensesSumLabel;
    @FXML
    private Label currentBudgetValueLabel;
    @FXML
    private ToggleGroup menu;

    private MainController mainController;

    private BudgetModel budgetModel;

    public void initialize(){

        currentMonthLabel.setText(getBudgetLabel(new Date()));
    }

    private String getBudgetLabel(Date date) {
        LocalDate d1 = ConverterDate.convertToLocalDate(date);
        return d1.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()) + " " + d1.getYear();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void openExpenses() {
        mainController.setCenterView("/fxml/ExpensesPanel.fxml");
    }

    public void openProfits() {
        mainController.setCenterView("/fxml/ProfitPanel.fxml");
    }

    public void openMonthChart() {
        mainController.setCenterView("/fxml/MonthChartPanel.fxml");
    }

    public void openCategories() {
        mainController.setCenterView("/fxml/CategoryPanel.fxml");
    }

    public void openBudgets() {
        mainController.setCenterView("/fxml/BudgetPanel.fxml");
    }

    public void openYearChart() {
        mainController.setCenterView("/fxml/YearChartPanel.fxml");
    }

    public void openSummary() {
        try {
            budgetModel = new BudgetModel();
            budgetModel.initBudgetSummary();

            String contentText = "Budżet: " + budgetModel.getBudgetSummaryFx().getMainValue() + "\n" +
                    "Wydano: " + budgetModel.getBudgetSummaryFx().getSpentValue() + "\n" +
                    "Pozostało: " + budgetModel.getBudgetSummaryFx().getRestValue() + "\n";
            DialogUtils.dialogInfo(
                    "Informacje o budżecie",
                    getBudgetLabel(new Date()),
                    contentText
            );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
