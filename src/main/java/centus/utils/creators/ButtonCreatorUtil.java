package centus.utils.creators;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonCreatorUtil {
    public static Button createDeleteButton(){
        Button button = new Button();
        button.getStyleClass().add("delete_button");
        Image image = new Image(ButtonCreatorUtil.class.getResource("/icons/delete.png").toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }
}
