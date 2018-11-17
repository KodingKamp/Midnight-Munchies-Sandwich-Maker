package midnightmunchies;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController {

  private ArrayList sandwiches = new ArrayList();
  private int arrayListIndex = 0;
  private ResultSet resultSet;
  private String[] topImgArray = {"BunTop.png", "Bread.png", "Poptart.png", "Pizza.png",
      "None.png"};
  private String[] midImgArray = {"Patty.png", "Hotdog.png", "Pizza.png", "Sponge.png", "None.png"};
  private String[] bottomImgArray = {"BunBottom.png", "Bread.png", "Poptart.png", "Pizza.png",
      "None.png"};
  private boolean loadBtnClicked = false;

  @FXML
  private ScrollPane creditsPane;
  @FXML
  private GridPane loadWindow;
  @FXML
  private TextField loadName;
  @FXML
  private ImageView loadTopImage;
  @FXML
  private ImageView loadMidImage;
  @FXML
  private ImageView loadBottomImage;
  @FXML
  private ImageView loadCheeseImage;


  @FXML
  void createNewBtnClicked() throws IOException {
    DataBase.disconnect();
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("CreateScene.fxml"));
    stage.setScene(new Scene(root, 1136, 639));
    stage.show();
  }

  @FXML
  void creditsBtnClicked() {
    creditsPane.setVisible(!creditsPane.isVisible());
    if (loadWindow.isVisible()) {
      loadWindow.setVisible(false);
    }
  }

  // This method is used as an alternate way to close the program.
  @FXML
  void exitBtnClicked() {
    DataBase.disconnect();
    Platform.exit();
    System.exit(0);
  }

  @FXML
  void loadCreationsBtnClicked() {
    if (creditsPane.isVisible()) {
      creditsPane.setVisible(false);
    }
    if (!loadBtnClicked) {
      try {
        resultSet = DataBase.executeQueryResultSet("SELECT * FROM SANDWICHES");
        //resultSet = DataBase.getResultSet();
        while (resultSet.next()) {
          sandwiches.add(resultSet.getString(1));
          sandwiches.add(resultSet.getInt(2));
          sandwiches.add(resultSet.getInt(3));
          sandwiches.add(resultSet.getInt(4));
          sandwiches.add(resultSet.getBoolean(5));
        }
        loadPreviousBtnClicked();
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      }
      loadBtnClicked = !loadBtnClicked;
    }
    loadWindow.setVisible(!loadWindow.isVisible());
  }

  // Using modulo to keep arrayListIndex within bounds of arrayList, first adding the
  // arrayList size to keep arrayListIndex non-negative then subtracting 5 before modulo.
  // This would allow the index to wrap to the back of the array if index was at 0.
  @FXML
  public void loadPreviousBtnClicked() {
    arrayListIndex = (arrayListIndex + sandwiches.size() - 5) % sandwiches.size();
    loadImages();
  }

  // Increments arrayListIndex by 5 and modulo with arrayList size to
  // keep index within bounds of arrayList and wraps index back to beginning.
  @FXML
  public void loadNextBtnClicked() {
    arrayListIndex = (arrayListIndex + 5) % sandwiches.size();
    loadImages();
  }

  @FXML
  public void loadImages() {
    loadName.setText(sandwiches.get(arrayListIndex).toString());
    loadTopImage.setImage(new Image("/images/"
        + topImgArray[(int) sandwiches.get(arrayListIndex + 1)]));
    loadMidImage.setImage(new Image("/images/"
        + midImgArray[(int) sandwiches.get(arrayListIndex + 2)]));
    loadBottomImage.setImage(new Image("/images/"
        + bottomImgArray[(int) sandwiches.get(arrayListIndex + 3)]));
    if ((boolean) sandwiches.get(arrayListIndex + 4)) {
      loadCheeseImage.setImage(new Image("/images/Cheese.png"));
    } else {
      loadCheeseImage.setImage(new Image("/images/None.png"));
    }
  }

  @FXML
  public void deleteBtnClicked() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Delete Confirmation");
    alert.setHeaderText("Delete Current Sandwich Combination.");
    alert.setContentText("Are you sure?");
    alert.showAndWait();
    if (alert.getResult().getText().equals("OK")) {
      System.out.println("Delete");
      DataBase.executeQuery("DELETE FROM SANDWICHES WHERE NAME='"
          + sandwiches.get(arrayListIndex) + "'");
      for (int i = 0; i < 5; i++) {
        sandwiches.remove(arrayListIndex);
      }
      arrayListIndex %= sandwiches.size();
      loadImages();
    }
  }

  @FXML
  public void renameSandwich(KeyEvent keyEvent) {
    if (keyEvent.getCode().toString().equals("ENTER")) {
      if (loadName.getText().equals("") || loadName.getText().isEmpty()
          || loadName.getText().matches("\\s+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Nameless");
        alert.setHeaderText("Nameless Error");
        alert.setContentText("You forgot to give your creation a name!");
        alert.showAndWait();
        // In case user tried to enter only spaces, clear the text field so string does not
        // contain whitespace before it.
        loadName.setText(sandwiches.get(arrayListIndex).toString());
      } else if (loadName.getText().contains("'")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Apostrophe");
        alert.setHeaderText("Apostrophe Error");
        alert.setContentText("Please refrain from using an apostrophe ( ' ) in the name.");
        alert.showAndWait();
      } else {
        DataBase.executeQuery("UPDATE SANDWICHES SET NAME = '" + loadName.getText()
            + "' WHERE NAME = '" + sandwiches.get(arrayListIndex) + "'");
        sandwiches.set(arrayListIndex, loadName.getText());
      }
      System.out.println("Name Changed");
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Sandwich Rename");
      alert.setHeaderText("Sandwich Renamed!");
      alert.setContentText("Your sandwich has been successfully renamed!");
      alert.showAndWait();
    }
  }
}
