package centus.controllers;

import centus.utils.DialogUtils;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;
import centus.viewmodel.budgetModels.BudgetSummaryModel;
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
    private Label budgetValueLabel;
    @FXML
    private Label spentValueLabel;
    @FXML
    private Label restValueLabel;
    @FXML
    private Label currentMonthLabel;
    @FXML
    private ToggleGroup menu;

    private MainController mainController;

    private BudgetSummaryModel budgetSummaryModel;

    public void initialize(){
        budgetSummaryModel = new BudgetSummaryModel();
        try {
            budgetSummaryModel.initBudgetSummary();
        } catch (SQLException | ApplicationException throwables) {
            throwables.printStackTrace();
        }

        currentMonthLabel.setText(getBudgetLabel(new Date()));
        initLabels();

    }

    public void initLabels(){
        this.spentValueLabel.textProperty().bindBidirectional(this.budgetSummaryModel.getBudgetSummaryFxObjectProperty().spentValueProperty());
        this.budgetValueLabel.textProperty().bindBidirectional(this.budgetSummaryModel.getBudgetSummaryFxObjectProperty().mainValueProperty());
        this.restValueLabel.textProperty().bindBidirectional(this.budgetSummaryModel.getBudgetSummaryFxObjectProperty().restValueProperty());
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
            budgetSummaryModel.initBudgetSummary();

            String contentText = "Budżet: " + budgetSummaryModel.getBudgetSummaryFxObjectProperty().getMainValue() + "\n" +
                    "Wydano: " + budgetSummaryModel.getBudgetSummaryFxObjectProperty().getSpentValue() + "\n" +
                    "Pozostało: " + budgetSummaryModel.getBudgetSummaryFxObjectProperty().getRestValue() + "\n";
            DialogUtils.dialogInfo(
                    "Informacje o budżecie",
                    getBudgetLabel(new Date()),
                    contentText
            );

        } catch (SQLException | ApplicationException throwables) {
            throwables.printStackTrace();
        }
    }

    public BudgetSummaryModel getBudgetSummaryModel() {
        return budgetSummaryModel;
    }
}
