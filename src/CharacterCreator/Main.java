package CharacterCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {

  private static Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws Exception{
    setPrimaryStage(primaryStage);
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    primaryStage.setTitle("Character Creator");
    primaryStage.setScene(new Scene(root, 1080, 720));
    primaryStage.show();
  }

  private void setPrimaryStage(Stage primaryStage) {
    Main.primaryStage = primaryStage;
  }

  public static Stage getPrimaryStage(){
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
