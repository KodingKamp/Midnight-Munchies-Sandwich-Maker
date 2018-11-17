package midnightmunchies;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

// Controller class for the Create Sandwich Scene
public class CreateSceneController {

  private int topIndex = 0;
  private int midIndex = 0;
  private int bottomIndex = 0;
  // Arrays to hold url of each image
  private String[] topImgArray = {"BunTop.png", "Bread.png", "Poptart.png", "Pizza.png",
      "None.png"};
  private String[] midImgArray = {"Patty.png", "Hotdog.png", "Pizza.png", "Sponge.png", "None.png"};
  private String[] bottomImgArray = {"BunBottom.png", "Bread.png", "Poptart.png", "Pizza.png",
      "None.png"};
  // Arrays to hold description strings for each image
  private String[] topLabelDescription = {"Top Burger Bun w/ Sesame Seeds",
      "Non-Toasted Bread Slice",
      "Strawberry Filled Poptart", "Pepperoni Pizza Slice", "Bunless?"};
  private String[] midLabelDescription = {"Beef Patty (Probably)", "Good Ole Classic Frank",
      "Pepperoni Pizza Slice", "Like-New Dishwashing Sponge", "Gluten-Free"};
  private String[] bottomLabelDescription = {"Bottom Burger Bun", "Non-Moldy Bread Slice",
      "Blueberry Filled Poptart", "Pepperoni Pizza Slice", "The You'll Need a Fork"};

  // FXML control variables
  @FXML
  private CheckBox cheeseCheckBox;
  @FXML
  private ImageView bottomMenuImage;
  @FXML
  private ImageView midMenuImage;
  @FXML
  private ImageView topMenuImage;
  @FXML
  private ImageView bottomPreviewImage;
  @FXML
  private ImageView midPreviewImage;
  @FXML
  private ImageView topPreviewImage;
  @FXML
  private ImageView cheesePreviewImage;
  @FXML
  private Label topLabel;
  @FXML
  private Label midLabel;
  @FXML
  private Label bottomLabel;
  @FXML
  private TextField nameField;

  // -------Menu Next and Previous Button Methods-------
  // The algorithm to move through the array uses modulo
  //  to stay within the bounds of the array while cycling
  //  to the first or last index without causing an
  //  OUT_OF_BOUNDS error.
  // Then sets the preview and menu images to the image
  //  respective to the index of the urlArrays
  @FXML
  public void bottomNextBtnClicked() {
    // System.out.println("Next Bottom Option");
    bottomIndex = (bottomIndex + 1) % bottomImgArray.length;
    setBottomImages();
  }

  @FXML
  public void bottomPreviousBtnClicked() {
    // System.out.println("Previous Bottom Option");
    bottomIndex = (bottomIndex + bottomImgArray.length - 1) % bottomImgArray.length;
    setBottomImages();
  }

  @FXML
  public void midNextBtnClicked() {
    // System.out.println("Next Middle Option");
    midIndex = (midIndex + 1) % midImgArray.length;
    setMidImages();
  }

  @FXML
  public void midPreviousBtnClicked() {
    // System.out.println("Previous Middle Option");
    midIndex = (midIndex + midImgArray.length - 1) % midImgArray.length;
    setMidImages();
  }

  @FXML
  public void topNextBtnClicked() {
    // System.out.println("Next Top Option");
    topIndex = (topIndex + 1) % topImgArray.length;
    setTopImages();
  }

  @FXML
  public void topPreviousBtnClicked() {
    // System.out.println("Previous Top Option");
    topIndex = (topIndex + topImgArray.length - 1) % topImgArray.length;
    setTopImages();
  }

  // Method to toggle Cheese image
  @FXML
  public void cheeseCheckBoxClicked() {
    cheesePreviewImage.setVisible(cheeseCheckBox.isSelected());
  }

  // Method to store currently displayed sandwich image combination and
  // name variable to the SANDWICHES database, alert user if name field is empty
  // or attempts to us an apostrophe.
  @FXML
  public void saveBtnClicked() {
    if (nameField.getText().equals("") || nameField.getText().isEmpty()
        || nameField.getText().matches("\\s+")) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Nameless");
      alert.setHeaderText("Nameless Error");
      alert.setContentText("You forgot to give your creation a name!");
      alert.showAndWait();
      // In case user tried to enter only spaces, clear the text field so string does not
      // contain whitespace before it.
      nameField.setText("");
    } else if (nameField.getText().contains("'")) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Apostrophe");
      alert.setHeaderText("Apostrophe Error");
      alert.setContentText("Please refrain from using an apostrophe ( ' ) in the name.");
      alert.showAndWait();
    } else {
      DataBase.executeQuery("INSERT INTO SANDWICHES (NAME, TOPINDEX, MIDINDEX, "
          + "BOTTOMINDEX, CHEESE) VALUES ('" + nameField.getText() + "', " + topIndex + ", "
          + midIndex + ", " + bottomIndex + ", " + cheeseCheckBox.isSelected() + ")");
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Sucessful Save");
      alert.setHeaderText("Sandwich Archived!");
      alert.setContentText("Your sandwich combination has been successfully saved to your virtual"
          + "storage.\n\nClick load in the Main Menu to view your saved creations!");
      alert.showAndWait();
    }
  }

  // Method that resets all fields, labels, variables and images to default
  @FXML
  public void resetBtnClicked() {
    bottomIndex = 0;
    setBottomImages();
    midIndex = 0;
    setMidImages();
    topIndex = 0;
    setTopImages();
    nameField.setText("");
  }

  // Method used to switch the scene to Main Menu scene
  @FXML
  public void mainMenuBtnClicked() throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    stage.setScene(new Scene(root, 1136, 639));
    stage.show();
  }

  // Method to set the Top Menu Image, Top Preview Image,
  //  // and the Top Menu Label to the corresponding index value
  @FXML
  private void setTopImages() {
    topPreviewImage.setImage(new Image("/images/"
        + topImgArray[topIndex]));
    topMenuImage.setImage(new Image("/images/"
        + topImgArray[topIndex]));
    topLabel.setText(topLabelDescription[topIndex]);
  }

  // Method to set the Middle Menu Image, Middle Preview Image,
  // and the Middle Menu Label to the corresponding index value
  @FXML
  private void setMidImages() {
    midPreviewImage.setImage(new Image("/images/"
        + midImgArray[midIndex]));
    midMenuImage.setImage(new Image("/images/"
        + midImgArray[midIndex]));
    midLabel.setText(midLabelDescription[midIndex]);
  }

  // Method to set the Bottom Menu Image, Bottom Preview Image,
  //  // and the Bottom Menu Label to the corresponding index value
  @FXML
  private void setBottomImages() {
    bottomPreviewImage.setImage(new Image("/images/"
        + bottomImgArray[bottomIndex]));
    bottomMenuImage.setImage(new Image("/images/"
        + bottomImgArray[bottomIndex]));
    bottomLabel.setText(bottomLabelDescription[bottomIndex]);
  }
}



