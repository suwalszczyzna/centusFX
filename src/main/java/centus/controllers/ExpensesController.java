package centus.controllers;

import centus.utils.DialogUtils;
import centus.utils.exceptions.ApplicationException;
import centus.viewmodel.NumberTextField;
import centus.viewmodel.expenseModels.ExpenseCategoryFX;
import centus.viewmodel.expenseModels.ExpenseFX;
import centus.viewmodel.expenseModels.ExpenseModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ExpensesController {

    @FXML
    private Button addNewExpenseButton;
    @FXML
    private TableColumn<ExpenseFX, LocalDate> dateColumn;
    @FXML
    private TableColumn<ExpenseFX, String> amountColumn;
    @FXML
    private TableColumn<ExpenseFX, ExpenseCategoryFX> expenseCategoryColumn;
    @FXML
    private NumberTextField expenseValue;
    @FXML
    private DatePicker expenseDate;
    @FXML
    private ComboBox<ExpenseCategoryFX> categoryComboBox;
    @FXML
    private TableView<ExpenseFX> expenseTableVIew;

    private ExpenseModel expenseModel;

    public void initialize() {
        this.expenseModel = new ExpenseModel();
        try {
            this.expenseModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        this.expenseTableVIew.setItems(this.expenseModel.getExpenseFxObservableList());
        this.dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        this.amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        this.expenseCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().expenseCategoryProperty());

        this.categoryComboBox.setItems(this.expenseModel.getExpenseCategoryFxObservableList());

        this.expenseModel.getExpenseFxObjectProperty().amountProperty().bind(
                this.expenseValue.textProperty()
        );
        this.expenseModel.getExpenseFxObjectProperty().dateProperty().bind(this.expenseDate.valueProperty());
        this.expenseModel.getExpenseFxObjectProperty().expenseCategoryProperty().bind(this.categoryComboBox.valueProperty());

        this.addNewExpenseButton.disableProperty().bind(
                this.expenseValue.textProperty().isEmpty()
                        .or(this.expenseDate.valueProperty().isNull())
                        .or(this.categoryComboBox.valueProperty().isNull())
        );

    }

    public void addNewExpense() {
        try {
            this.expenseModel.saveExpenseToDb();
            this.expenseValue.clear();
            this.expenseDate.setValue(null);
            this.categoryComboBox.getSelectionModel().clearSelection();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
