package centus.controllers;

import centus.utils.DialogUtils;
import centus.utils.exceptions.ApplicationException;
import centus.viewmodel.NumberTextField;
import centus.viewmodel.profitModels.ProfitCategoryFX;
import centus.viewmodel.profitModels.ProfitFX;
import centus.viewmodel.profitModels.ProfitModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ProfitController {
    @FXML
    private Button addNewProfitButton;
    @FXML
    private TableColumn<ProfitFX, LocalDate> dateColumn;
    @FXML
    private TableColumn<ProfitFX, String> amountColumn;
    @FXML
    private TableColumn<ProfitFX, ProfitCategoryFX> profitCategoryColumn;
    @FXML
    private NumberTextField profitValue;
    @FXML
    private DatePicker profitDate;
    @FXML
    private ComboBox<ProfitCategoryFX> categoryComboBox;
    @FXML
    private TableView<ProfitFX> profitTableVIew;

    private ProfitModel profitModel;

    public void initialize() {
        this.profitModel = new ProfitModel();
        try {
            this.profitModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        this.profitTableVIew.setItems(this.profitModel.getProfitFxObservableList());
        this.dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        this.amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        this.profitCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().profitCategoryProperty());

        this.categoryComboBox.setItems(this.profitModel.getProfitCategoryFxObservableList());

        this.profitModel.getProfitFxObjectProperty().amountProperty().bind(
                this.profitValue.textProperty()
        );
        this.profitModel.getProfitFxObjectProperty().dateProperty().bind(this.profitDate.valueProperty());
        this.profitModel.getProfitFxObjectProperty().profitCategoryProperty().bind(this.categoryComboBox.valueProperty());

        this.addNewProfitButton.disableProperty().bind(
                this.profitValue.textProperty().isEmpty()
                        .or(this.profitDate.valueProperty().isNull())
                        .or(this.categoryComboBox.valueProperty().isNull())
        );

    }

    public void addNewProfit() {
        try {
            this.profitModel.saveProfitToDb();
            this.profitValue.clear();
            this.profitDate.setValue(null);
            this.categoryComboBox.getSelectionModel().clearSelection();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
