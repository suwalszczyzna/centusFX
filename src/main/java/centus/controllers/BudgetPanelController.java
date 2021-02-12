package centus.controllers;

import centus.utils.DialogUtils;
import centus.utils.exceptions.ApplicationException;
import centus.utils.users.UserSession;
import centus.viewmodel.NumberTextField;
import centus.viewmodel.budgetModels.BudgetFx;
import centus.viewmodel.budgetModels.BudgetModel;
import centus.viewmodel.expenseModels.ExpenseFX;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;
import java.time.LocalDate;

public class BudgetPanelController {

    public NumberTextField budgetValue;
    public DatePicker budgetDate;
    public Button addNewBudgetButton;
    public TableView<BudgetFx> budgetTableView;
    public TableColumn<BudgetFx, LocalDate> dateBudgetColumn;
    public TableColumn<BudgetFx, String> amountBudgetColumn;
    public TableColumn<BudgetFx, BudgetFx> deleteColumn;

    private LeftMenuController leftMenuController;

    private BudgetModel budgetModel;


    public void initialize() {
        System.out.println(UserSession.getInstance("").getUserId());
        this.budgetModel = new BudgetModel();
        try {
            this.budgetModel.init();
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

        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.deleteColumn.setCellFactory(param -> new TableCell<BudgetFx, BudgetFx>(){
            Button button = createDeleteButton();
            @Override
            protected void updateItem(BudgetFx item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                    return;
                }

                if(!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            budgetModel.deleteBudget(item);
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });

    }

    private Button createDeleteButton(){
        Button button = new Button();
        Image image = new Image(this.getClass().getResource("/icons/delete.png").toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }


    public void addNewBudget() {
        try {
            this.budgetModel.saveBudgetToDb();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
