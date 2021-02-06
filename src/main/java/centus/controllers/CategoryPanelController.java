package centus.controllers;

import centus.utils.DialogUtils;
import centus.utils.exceptions.ApplicationException;
import centus.viewmodel.CategoryPanelModel;
import centus.viewmodel.expenseModels.ExpenseCategoryFX;
import centus.viewmodel.profitModels.ProfitCategoryFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoryPanelController {

    @FXML
    private TextField newExpenseCategoryTextField;

    @FXML
    private Button addNewExpenseCategoryButton;

    @FXML
    private TableView<ExpenseCategoryFX> expenseCategoryTableView;

    @FXML
    private TableColumn<ExpenseCategoryFX, String> expenseNameColumn;

    @FXML
    private TextField newProfitCategoryTextField;

    @FXML
    private Button addNewProfitCategoryButton;

    @FXML
    private TableView<ProfitCategoryFX> profitCategoryTableView;

    @FXML
    private TableColumn<ProfitCategoryFX, String> profitNameColumn;

    CategoryPanelModel categoryPanelModel;

    public void initialize() {
        categoryPanelModel = new CategoryPanelModel();
        try {
            categoryPanelModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        expenseCategoryTableView.setItems(categoryPanelModel.getExpenseCategoryFXObservableList());
        expenseNameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());

        profitCategoryTableView.setItems(categoryPanelModel.getProfitCategoryFXObservableList());
        profitNameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());

        addNewExpenseCategoryButton.disableProperty().bind(newExpenseCategoryTextField.textProperty().isEmpty());
        addNewProfitCategoryButton.disableProperty().bind(newProfitCategoryTextField.textProperty().isEmpty());

        categoryPanelModel.getExpenseCategoryFXObjectProperty().nameProperty().bind(newExpenseCategoryTextField.textProperty());
        categoryPanelModel.getProfitCategoryFXObjectProperty().nameProperty().bind(newProfitCategoryTextField.textProperty());
    }


    @FXML
    void addNewExpenseCategory() {
        try {
            this.categoryPanelModel.saveNewExpenseCategoryToDB();
            this.newExpenseCategoryTextField.clear();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    @FXML
    void addNewProfitCategory(ActionEvent event) {
        try {
            this.categoryPanelModel.saveNewProfitCategoryToDB();
            this.newProfitCategoryTextField.clear();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
