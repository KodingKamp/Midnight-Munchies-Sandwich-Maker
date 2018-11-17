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

  //-------Variables-------
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

  //------- Methods -------

  // This method is called when the "New Creation" button is clicked.
  // The scene is changed to the createScene loaded over the same stage
  @FXML
  void createNewBtnClicked() throws IOException {
    // disconnects from the database in case it is connected at time of call
    DataBase.disconnect();
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("CreateScene.fxml"));
    stage.setScene(new Scene(root, 1136, 639));
    stage.show();
  }

  // This method toggles the visibility of the pane containing credits information and
  // hides the Load Menu if it is visible.
  @FXML
  void creditsBtnClicked() {
    creditsPane.setVisible(!creditsPane.isVisible());
    if (loadWindow.isVisible()) {
      loadWindow.setVisible(false);
    }
  }

  // This method is used as an alternate way to close the program and is called when
  // the user clicked the exit button.
  @FXML
  void exitBtnClicked() {
    DataBase.disconnect();
    Platform.exit();
    System.exit(0);
  }

  // This method toggles the visibility of the Load Menu and sets contained Image Views
  // to respective values stores in the dataBase. This also hides the Credits pane if visible.
  @FXML
  void loadCreationsBtnClicked() {
    // Hide Credits pane
    if (creditsPane.isVisible()) {
      creditsPane.setVisible(false);
    }
    // Connects to dataBase if first time clicking the Load Button during this run time or
    // first time clicking the button after the database is emptied and then inserted with records.
    if (!loadBtnClicked) {
      try {
        // Executes SQL statement to select all fields of the Sandwich table.
        resultSet = DataBase.executeQueryResultSet("SELECT * FROM SANDWICHES");
        // Stores all records' values in Sandwich table to an ArrayList
        while (resultSet.next()) {
          sandwiches.add(resultSet.getString(1));
          sandwiches.add(resultSet.getInt(2));
          sandwiches.add(resultSet.getInt(3));
          sandwiches.add(resultSet.getInt(4));
          sandwiches.add(resultSet.getBoolean(5));
        }
        // Calls Load Previous method to load latest addition to database if at least 1 record was
        // obtained from the database via previous instruction. Alerts user if database is empty.
        if (!sandwiches.isEmpty()) {
          loadPrevious();
        } else {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Empty Collection");
          alert.setHeaderText("No Saved Creations Found!");
          alert.setContentText("Your sandwich archive is empty. You'll be able to view "
              + "saved creations when you create a sandwich and save it.");
          alert.showAndWait();
          return;
        }
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      }
      // If successfully loaded and stored values from database, change loadBtnClicked to true
      // to skip connecting to database and show load window.
      loadBtnClicked = true;
      loadWindow.setVisible(true);
    } else {
      // Else statement runs after database has been connected to and values are stored into
      // sandwiches array list. Button will then function as a toggle for Load Menu pane.
      loadWindow.setVisible(!loadWindow.isVisible());
    }
  }

  // Using modulo to keep arrayListIndex within bounds of arrayList, first adding the
  // arrayList size to keep arrayListIndex non-negative then subtracting 5 before modulo.
  // This would allow the index to wrap to the back of the array if index was at 0.
  @FXML
  public void loadPrevious() {
    arrayListIndex = (arrayListIndex + sandwiches.size() - 5) % sandwiches.size();
    loadImages();
  }

  // Increments arrayListIndex by 5 and modulo with arrayList size to
  // keep index within bounds of arrayList and wraps index back to beginning.
  @FXML
  public void loadNext() {
    arrayListIndex = (arrayListIndex + 5) % sandwiches.size();
    loadImages();
  }

  // This method is called by other methods in this class to fill the contents of the
  // Load Menu pane with the values in sandwiches using the arrayListIndex variable.
  @FXML
  private void loadImages() {
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

  // This method is called when the Delete button is clicked in the Load Menu pane.
  @FXML
  public void deleteBtnClicked() {
    // Prompts the user that they are about to delete a record from the database.
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Delete Confirmation");
    alert.setHeaderText("Delete Current Sandwich Combination.");
    alert.setContentText("Are you sure?");
    alert.showAndWait();
    // If user selects "OK" process to run instructions to delete currently displayed content
    // from the SANDWICHES table.
    if (alert.getResult().getText().equals("OK")) {
      // Executes DELETE SQL statement to delete the record that matches the name in the textField.
      DataBase.executeQuery("DELETE FROM SANDWICHES WHERE NAME='"
          + sandwiches.get(arrayListIndex) + "'");
      System.out.println("Deleteing sandwich \"" + sandwiches.get(arrayListIndex) + "\".");
      // Remove the respective values from the sandwich ArrayList.
      for (int i = 0; i < 5; i++) {
        sandwiches.remove(arrayListIndex);
      }
      // If recently deleted record was the last but was not the only record DataBase,
      // wraps the index pointer variable to the start of the array and load its images.
      if (sandwiches.size() != 0) {
        arrayListIndex %= sandwiches.size();
        loadImages();
      } else {
        // else, the recently deleted record was the only record in the DataBase
        // sets array index pointer variable to 0
        arrayListIndex = 0;
        // sets loadBtnClicked to false to allow connection
        // to DB attempt when load button is clicked,
        loadBtnClicked = false;
        loadWindow.setVisible(false);
        // clears nameField
        loadName.setText("");
        // loads blank images into the image views of the load menu.
        Image tempBlankImage = new Image("/images/None.png");
        loadBottomImage.setImage(tempBlankImage);
        loadMidImage.setImage(tempBlankImage);
        loadTopImage.setImage(tempBlankImage);
        loadCheeseImage.setImage(tempBlankImage);
        // Prompts user that the database sandwiches table is empty
        alert = new Alert(AlertType.WARNING);
        alert.setTitle("Empty Archive");
        alert.setContentText("There are no more creations in your archive.");
        alert.showAndWait();
        System.out.println("Database Sandwiches Table Empty.");
      }
    }
  }

  // Method called when ENTER key is pressed while editing name Text Field to update name in DB.
  @FXML
  public void renameSandwich(KeyEvent keyEvent) {
    if (keyEvent.getCode().toString().equals("ENTER")) {
      // Checking to see if textField is empty or only contains whitespace and alerts user if true.
      if (loadName.getText().equals("") || loadName.getText().isEmpty()
          || loadName.getText().matches("\\s+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Nameless");
        alert.setHeaderText("Nameless Error");
        alert.setContentText("You forgot to give your creation a name!");
        alert.showAndWait();
        // Restores name TextField to previous name of that record.
        loadName.setText(sandwiches.get(arrayListIndex).toString());
      } else if (loadName.getText().contains("'")) {
        // Alerts user if an apostrophe (') exists in name field which causes errors when passed
        // to execute method because SQL uses '.
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Apostrophe");
        alert.setHeaderText("Apostrophe Error");
        alert.setContentText("Please refrain from using an apostrophe ( ' ) in the name.");
        alert.showAndWait();
      } else {
        // Executes statement to update Name field in DataBase.
        DataBase.executeQuery("UPDATE SANDWICHES SET NAME = '" + loadName.getText()
            + "' WHERE NAME = '" + sandwiches.get(arrayListIndex) + "'");
        sandwiches.set(arrayListIndex, loadName.getText());
      }
      System.out.println("Name Changed");
      // Alerts user that the name has been changed.
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Sandwich Rename");
      alert.setHeaderText("Sandwich Renamed!");
      alert.setContentText("Your sandwich has been successfully renamed!");
      alert.showAndWait();
    }
  }
}
