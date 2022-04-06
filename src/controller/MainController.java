package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.ServiceResult;
import service.AppService;
import service.MainService;
import util.Action;
import util.Colors;
import util.Constants;

public class MainController {

    // ------------------------------ declarations ------------------------------
    @FXML
    private Pane button;
    @FXML
    private Label buttonLabel, headerLabel;

    private AppService service = new MainService(Constants.PORT);


    // ------------------------------ event handlers ------------------------------

    public void onStartButtonPress () {

        System.out.println("Starting Client...");
        changeOnStartUI();

        final ServiceResult[] serviceResult = {null};
        Action.actOnce(() -> {
            serviceResult[0] = service.start();
        }, () -> {

            ServiceResult<String> result = serviceResult[0];
            if (result != null) {
                if (result.hasError()) {
                    setErrorOnHeader(result.getError());
                    buttonLabel.setText("Start");
                } else {
                    setSucceededStatusOnHeader(result.getResult());
                    buttonLabel.setText("Stop");
                }
                button.setDisable(false);
            }

        }).start();
        System.out.println("outer: " + serviceResult[0]);

    }

    public void onHoverStart () {
        button.setStyle("-fx-background-color: " + Colors.PRIMARY);
    }

    public void onHoverEnd () {
        button.setStyle("-fx-background-color: " + Colors.SECONDARY);
    }


    // ------------------------------ functions ------------------------------

    private void changeOnStartUI() {
        button.setDisable(true);
        buttonLabel.setText("Starting...");
        headerLabel.setText("Stabilising Connection...");
    }

    private void setSucceededStatusOnHeader(String text) {
        headerLabel.setTextFill(Color.web(Colors.SUCCESS));
        headerLabel.setText(text);
    }

    private void setErrorOnHeader(String text) {
        headerLabel.setTextFill(Color.web(Colors.ERROR));
        headerLabel.setText(text);
    }

}
