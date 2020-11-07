package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Parilti");
        primaryStage.setScene(new Scene(root, 1080 , 720));
        primaryStage.setMaxHeight(720);
        primaryStage.setMaxWidth(1080);
        primaryStage.show();
        // Get the graphics context of the canvas


        // Draw a Text
    }


    public static void main(String[] args) {
        launch(args);
    }
}
