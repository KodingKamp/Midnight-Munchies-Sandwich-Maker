package MidnightMunchies;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws Exception{
    setPrimaryStage(primaryStage);
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    primaryStage.setTitle("Midnight Munchies: Sandwich Maker");
    primaryStage.setScene(new Scene(root, 1136, 639));
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
    System.out.println("Thank you for using Midnight Munchies: Sandwich Maker!");
  }
}