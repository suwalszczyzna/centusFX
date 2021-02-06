package centus.utils;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Optional;

public class DialogUtils {
    public static void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Coś poszło nie tak :(");

        Label label = new Label(error);
        label.setTextFill(Color.web("#E40321"));
        errorAlert.getDialogPane().setContent(label);

        errorAlert.showAndWait();
    }

    public static Optional<ButtonType> dialogExitConfirmation() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Wyjście z aplikacji");
        confirmation.setHeaderText("Czy na pewno chcesz wyjść?");
        return confirmation.showAndWait();
    }

    public static void dialogAboutApp() {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle("O programie");
        information.setHeaderText("CentuśFX - program do zarządzania domowym budgetem");
        information.setContentText("Projekt zaliczeniowy - Programowanie w języku JAVA, WSPA 2021\nDamian Suwała, Informatyka PUW III sem.");
        information.showAndWait();
    }

    public static void dialogInfo(String title, String header, String content) {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle(title);
        information.setHeaderText(header);
        information.setContentText(content);
        information.showAndWait();
    }

    public static Optional<Pair<String, String>> loginDialog() {

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Logowanie");
        dialog.setHeaderText("Wpisz nazwę użytkownika i hasło");

//      dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        ButtonType loginButtonType = new ButtonType("Zaloguj", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Nazwa użytkownika");
        PasswordField password = new PasswordField();
        password.setPromptText("Hasło");

        grid.add(new Label("Nazwa użytkownika:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Hasło:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(username::requestFocus);

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        return result;
    }
}
