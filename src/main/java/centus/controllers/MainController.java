package centus.controllers;

import centus.Main;
import centus.database.model.User;
import centus.utils.DialogUtils;
import centus.utils.FxmlUtils;
import centus.utils.exceptions.ApplicationException;
import centus.utils.exceptions.LoginFailException;
import centus.utils.users.UserSession;
import centus.utils.users.UserUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class MainController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private LeftMenuController leftMenuController;

    @FXML
    private void initialize() {
        leftMenuController.setMainController(this);
    }

    public void setCenterView(String fxmlPath) {
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));

        try {
            this.leftMenuController.getBudgetSummaryModel().initBudgetSummary();
        } catch (SQLException | ApplicationException throwables) {
            throwables.printStackTrace();
        }
    }

    public void topMenuChangeUser() {
        try {
            UserSession.getInstance("").cleanUserSession();
            User user = null;
            user = UserUtils.loginUser();
            UserSession.getInstance(String.valueOf(user.getId()));
        } catch (SQLException | ApplicationException | LoginFailException e) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void topMenuCloseApp() {
        Optional<ButtonType> result = DialogUtils.dialogExitConfirmation();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void topMenuAddExpense() {
        this.setCenterView("/fxml/ExpensesPanel.fxml");
    }

    public void topMenuAddProfit() {
        this.setCenterView("/fxml/ProfitPanel.fxml");
    }

    public void topMenuManageCategories() {
        this.setCenterView("/fxml/CategoryPanel.fxml");
    }

    public void topMenuAboutApp() {
        DialogUtils.dialogAboutApp();
    }
}
