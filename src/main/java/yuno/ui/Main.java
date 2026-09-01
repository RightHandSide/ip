package yuno.ui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import yuno.Yuno;
import yuno.exception.FileStorageException;

/**
 * Displays and manages Yuno's JavaFX user interface.
 */
public class Main extends Application {
    /** Time for which a final message remains visible before the window closes. */
    private static final Duration WINDOW_CLOSE_DELAY = Duration.seconds(5);

    /** Profile image displayed beside user messages. */
    private final Image userImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/User.jpg")));
    /** Profile image displayed beside Yuno messages. */
    private final Image yunoImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Yuno.jpg")));

    private Ui ui;
    private Yuno yuno;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Stage stage;

    /**
     * Creates Yuno's JavaFX application.
     */
    public Main() {
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        createInterface();
        initializeYuno();
    }

    /**
     * Initializes Yuno, displays its greeting, and reports any storage error.
     */
    private void initializeYuno() {
        ui = new Ui();
        ui.printGreeting();
        showYunoMessage(ui.getResponse());
        try {
            yuno = new Yuno(ui);
        } catch (FileStorageException exception) {
            ui.printException(exception.getMessage());
            showYunoMessage(ui.getResponse());
            scheduleWindowClose();
        }
    }

    /**
     * Creates and displays the JavaFX controls used by the chatbot.
     */
    private void createInterface() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        userInput.setOnAction(_ -> handleUserInput());
        sendButton.setOnAction(_ -> handleUserInput());

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Formats the window to match the chatbot layout.

        stage.setTitle("Yuno");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener(
                _ -> scrollPane.setVvalue(1.0));
    }

    /**
     * Processes the current user input and displays both sides of the exchange.
     */
    private void handleUserInput() {
        String userText = userInput.getText().strip();
        if (userText.isEmpty() || yuno == null) {
            return;
        }

        boolean shouldContinue = yuno.handleCommand(userText);
        String yunoText = ui.getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getYunoDialog(yunoText, yunoImage));
        userInput.clear();

        if (!shouldContinue) {
            scheduleWindowClose();
        }
    }

    /**
     * Displays a message from Yuno in the conversation area.
     *
     * @param message Message to display.
     */
    private void showYunoMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getYunoDialog(message, yunoImage));
    }

    /**
     * Disables further input and closes the window after a short delay.
     */
    private void scheduleWindowClose() {
        userInput.setDisable(true);
        sendButton.setDisable(true);

        PauseTransition closeDelay = new PauseTransition(WINDOW_CLOSE_DELAY);
        closeDelay.setOnFinished(_ -> stage.close());
        closeDelay.play();
    }
}
