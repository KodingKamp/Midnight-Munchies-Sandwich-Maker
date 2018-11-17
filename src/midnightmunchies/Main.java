package midnightmunchies;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static Stage primaryStage;

  // This method overrides the start method in the Application class
  // and sets up the stage and first viewable scene of the program
  @Override
  public void start(Stage primaryStage) throws Exception {
    setPrimaryStage(primaryStage);
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    primaryStage.setTitle("Midnight Munchies: Sandwich Maker");
    primaryStage.setScene(new Scene(root, 1136, 639));
    primaryStage.show();
  }

  // This method assigns the primary stage passed to start to a variable in the Main class
  // for use by other classes to change scenes without creating new stages.
  private void setPrimaryStage(Stage primaryStage) {
    Main.primaryStage = primaryStage;
  }

  // This method is used to access the stage from outside of this class.
  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  // This is the main method of this class and launches the start method. When terminated, the
  // program disconnects from the database if it was still connected.
  public static void main(String[] args) {
    launch(args);
    DataBase.disconnect();
  }
}