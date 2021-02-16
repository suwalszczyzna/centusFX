package centus;

import centus.database.dbutils.DbManager;
import centus.database.model.User;
import centus.utils.DialogUtils;
import centus.utils.FillDatabase;
import centus.utils.FxmlUtils;
import centus.utils.exceptions.ApplicationException;
import centus.utils.exceptions.LoginFailException;
import centus.utils.users.UserSession;
import centus.utils.users.UserUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        DbManager.initDatabase();
//        FillDatabase.fillDatabase();
        FillDatabase.createTestUser();

        try {
            User user = UserUtils.loginUser();
            UserSession.getInstance(String.valueOf(user.getId()));

            Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);
            Scene scene = new Scene(borderPane);

            scene.getStylesheets().add(getClass().getResource("/stylesheets/app.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("CentuśFX");
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/icons/main_ico.png")));
            primaryStage.show();


        } catch (SQLException | ApplicationException | LoginFailException e) {
            DialogUtils.errorDialog(e.getMessage());
        } catch (NullPointerException ne) {
            Platform.exit();
            System.exit(0);
        }
    }

}
