import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./view/main.fxml"));
        primaryStage.setTitle("Ping Client");
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(650);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
