package yuno.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Displays a chatbot message together with the sender's profile image.
 */
public class DialogBox extends HBox {

    /**
     * Creates a right-aligned dialog box with the specified message and profile image.
     *
     * @param message Message to display.
     * @param image Profile image of the message sender.
     */
    public DialogBox(String message, Image image) {
        Label text = new Label(message);
        ImageView displayPicture = new ImageView(image);
        getChildren().addAll(text, displayPicture);

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Reverses the child order and left-aligns this dialog box.
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(children);
        getChildren().setAll(children);
    }

    /**
     * Returns a right-aligned dialog box for a user message.
     *
     * @param message User message to display.
     * @param image User's profile image.
     * @return Dialog box containing the user message.
     */
    public static DialogBox getUserDialog(String message, Image image) {
        return new DialogBox(message, image);
    }

    /**
     * Returns a left-aligned dialog box for a Yuno message.
     *
     * @param message Yuno message to display.
     * @param image Yuno's profile image.
     * @return Dialog box containing the Yuno message.
     */
    public static DialogBox getYunoDialog(String message, Image image) {
        DialogBox dialogBox = new DialogBox(message, image);
        dialogBox.flip();
        return dialogBox;
    }
}
