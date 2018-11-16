package MidnightMunchies;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController {

  @FXML
  private Button createNewBtn;
  @FXML
  private Button loadCreationsBtn;
  @FXML
  private Button exitBtn;
  @FXML
  private Button creditsBtn;
  @FXML
  private GridPane creditsPane;

  @FXML
  void createNewBtnClicked(ActionEvent event) throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("CreateScene.fxml"));
    stage.setScene(new Scene(root, 1136, 639));
    stage.show();
  }
  @FXML
  void creditsBtnClicked(ActionEvent event) {
    boolean visibility = !creditsPane.isVisible();
    creditsPane.setVisible(visibility);

  }
  // This method is used as an alternate way to close the program.
  @FXML
  void exitBtnClicked() {
    Platform.exit();
    System.exit(0);
  }
  @FXML
  void loadCreationsBtnClicked(ActionEvent event) {
    connect();
  }
  @FXML
  public void connect() {
    final String DB_URL = "jdbc:derby:lib\\storage";
    final String QUERY = "SELECT * FROM SANDWICHES";

    try (
        Connection connection = DriverManager.getConnection(
            DB_URL, "deitel", "deitel");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY)
    ) {
      resultSet.next();
      //nameField.setText(resultSet.getString(2));

      { // code to display an alert box
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

}
