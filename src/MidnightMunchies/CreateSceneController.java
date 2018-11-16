package MidnightMunchies;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CreateSceneController {

  private int topIndex = 0, midIndex = 0, bottomIndex = 0;
  private boolean addCheese = true;

  // Arrays to hold url of each image
  private String[] topImgArray = {"BunTop.png", "Bread.png", "Poptart.png", "Pizza.png", "None.png"};
  private String[] midImgArray = {"Patty.png", "Hotdog.png", "Pizza.png", "Sponge.png", "None.png"};
  private String[] bottomImgArray = {"BunBottom.png", "Bread.png", "Poptart.png", "Pizza.png", "None.png"};
  // Arrays to hold description strings for each image
  private String[] topLabelDescription = {"Top Burger Bun w/ Sesame Seeds",
      "Non-Toasted Bread Slice",
      "Strawberry Filled Poptart", "Pepperoni Pizza Slice", "Bunless?"};
  private String[] midLabelDescription = {"Beef Patty (Probably)", "Good Ole Classic Frank",
      "Pepperoni Pizza Slice", "Like-New Dishwashing Sponge", "Gluten-Free"};
  private String[] bottomLabelDescription = {"Bottom Burger Bun", "Non-Moldy Bread Slice",
      "Blueberry Filled Poptart", "Pepperoni Pizza Slice", "The You'll Need a Fork"};

  // FXML control variables
  /*@FXML
  private Button mainMenuBtn, topPreviousBtn, topNextBtn, midPreviousBtn,
      midNextBtn, bottomPreviousBtn, bottomNextBtn, saveBtn, resetBtn;
  @FXML
  private CheckBox cheeseCheckBox;*/
  @FXML
  private ImageView bottomMenuImage, midMenuImage, topMenuImage,
      bottomPreviewImage, midPreviewImage, topPreviewImage, cheesePreviewImage;
  @FXML
  private Label topLabel, midLabel, bottomLabel;
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
  public void bottomNextBtnClicked(ActionEvent event) {
    // System.out.println("Next Bottom Option");
    bottomIndex = (++bottomIndex) % bottomImgArray.length;
    setBottomImages();
  }

  @FXML
  public void bottomPreviousBtnClicked(ActionEvent event) {
    // System.out.println("Previous Bottom Option");
    bottomIndex = (bottomIndex + bottomImgArray.length - 1) % bottomImgArray.length;
    setBottomImages();
  }

  @FXML
  public void midNextBtnClicked(ActionEvent event) {
    // System.out.println("Next Middle Option");
    midIndex = (++midIndex) % midImgArray.length;
    setMidImages();
  }

  @FXML
  public void midPreviousBtnClicked(ActionEvent event) {
    // System.out.println("Previous Middle Option");
    midIndex = (midIndex + midImgArray.length - 1) % midImgArray.length;
    setMidImages();
  }

  @FXML
  public void topNextBtnClicked(ActionEvent event) {
    // System.out.println("Next Top Option");
    topIndex = (++topIndex) % topImgArray.length;
    setTopImages();
  }

  @FXML
  public void topPreviousBtnClicked(ActionEvent event) {
    // System.out.println("Previous Top Option");
    topIndex = (topIndex + topImgArray.length - 1) % topImgArray.length;
    setTopImages();
  }

  // Method to toggle Cheese image
  @FXML
  public void cheeseCheckBoxClicked(ActionEvent event) {
    addCheese = !addCheese;
    // System.out.println(addCheese);
    cheesePreviewImage.setVisible(addCheese);
  }

  @FXML
  public void saveBtnClicked(ActionEvent event) {
    if (nameField.getText().equals("") || nameField.getText().isEmpty()) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Nameless");
      alert.setHeaderText("");
      alert.setContentText("You forgot to give your creation a name!");

      alert.showAndWait();
    }
  }

  // Method that resets all fields, labels, variables and images to default
  @FXML
  public void resetBtnClicked(ActionEvent event) {
    bottomIndex = 0;
    setBottomImages();
    midIndex = 0;
    setMidImages();
    topIndex = 0;
    setTopImages();
    nameField.setText("");
  }

  // Method used to switch scene to Main Menu scene
  @FXML
  public void mainMenuBtnClicked(ActionEvent event) throws IOException {
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



