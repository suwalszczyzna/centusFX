package centus.controllers;

import centus.utils.DialogUtils;
import centus.utils.users.UserSession;
import centus.utils.exceptions.ApplicationException;
import centus.viewmodel.NumberTextField;
import centus.viewmodel.budgetModels.BudgetFx;
import centus.viewmodel.budgetModels.BudgetModel;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.time.LocalDate;

public class BudgetPanelController {

    public NumberTextField budgetValue;
    public DatePicker budgetDate;
    public Button addNewBudgetButton;
    public TableView<BudgetFx> budgetTableView;
    public TableColumn<BudgetFx, LocalDate> dateBudgetColumn;
    public TableColumn<BudgetFx, String> amountBudgetColumn;

    private LeftMenuController leftMenuController;

    private BudgetModel budgetModel;



    public void initialize(){
        System.out.println(UserSession.getInstance("").getUserId());
        this.budgetModel = new BudgetModel();
        try {
            this.budgetModel.init();
            this.budgetModel.initBudgetSummary();
        } catch (ApplicationException | SQLException e) {
            e.printStackTrace();
        }

        this.budgetTableView.setItems(this.budgetModel.getBudgetFxObservableList());
        this.dateBudgetColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        this.amountBudgetColumn.setCellValueFactory(cellData -> cellData.getValue().budgetValueProperty());

        this.budgetModel.getBudgetObjectProperty().budgetValueProperty().bind(
                this.budgetValue.textProperty()
        );
        this.budgetModel.getBudgetObjectProperty().dateProperty().bind(
                this.budgetDate.valueProperty()
        );


        this.addNewBudgetButton.disableProperty().bind(
                this.budgetValue.textProperty().isEmpty()
                .or(this.budgetDate.valueProperty().isNull())
        );

    }

    public void addNewBudget() {
        try {
            this.budgetModel.saveBudgetToDb();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
