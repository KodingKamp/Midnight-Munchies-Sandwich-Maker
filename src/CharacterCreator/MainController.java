package CharacterCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {

  @FXML
  public ImageView headBox;
  @FXML
  private ImageView bodyBox;
  @FXML
  private ImageView legBox;
  @FXML
  public TextField nameVar;

  private int headCount = 0;
  private int bodyCount = 0;
  private int legCount = 0;
  private String[] head = {"h0.png", "h1.png", "h2.png"};
  private String[] body = {"b0.png", "b1.png", "b2.png"};
  private String[] leg = {"l0.png", "l1.png", "l2.png"};

  @FXML
  public void changeHeadNext() {
    // To wrap back to the beginning of the array, this algorithm uses modulus to keep the
    // variable with array bounds.
    System.out.println("Next Head");
    headCount = (++headCount) % head.length;
    headBox.setImage(new Image("/CharacterCreator/bodyParts/" + head[headCount]));
  }

  @FXML
  public void changeHeadBack() {
    // to wrap to the end of the array, this algorithm adds the length of the array then
    // subtracts one and then modulus the result so that the counter does not go into negative values
    System.out.println("Previous Head");
    headCount = (headCount + head.length - 1) % head.length;
    headBox.setImage(new Image("/CharacterCreator/bodyParts/" + head[headCount]));

  }

  @FXML
  public void changeTorsoNext() {

    System.out.println("Next Torso");
    bodyCount = (++bodyCount) % body.length;
    bodyBox.setImage(new Image("/CharacterCreator/bodyParts/" + body[bodyCount]));
  }

  @FXML
  public void changeTorsoBack() {

    System.out.println("Previous Torso");
    bodyCount = (bodyCount + body.length - 1) % body.length;
    bodyBox.setImage(new Image("/CharacterCreator/bodyParts/" + body[bodyCount]));
  }

  @FXML
  public void changeLegNext() {

    System.out.println("Next Legs");
    legCount = (++legCount) % leg.length;
    legBox.setImage(new Image("/CharacterCreator/bodyParts/" + leg[legCount]));
  }

  @FXML
  public void changeLegBack() {

    System.out.println("Previous Legs");
    legCount = (legCount + leg.length - 1) % leg.length;
    legBox.setImage(new Image("/CharacterCreator/bodyParts/" + leg[legCount]));
  }

  @FXML
  public void connect() {
    final String DB_URL = "jdbc:derby:lib\\books";
    final String QUERY = "SELECT authorID, firstName, lastName FROM authors";

    try (
        Connection connection = DriverManager.getConnection(
            DB_URL, "deitel", "deitel");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY)
    ) {
      resultSet.next();
      nameVar.setText(resultSet.getString(2));

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
