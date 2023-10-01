package application;
/*
* Class Name : Foothill
* Description : Main client, runs the "main" to execute the code.
*/
import application.CardIdentity.Suit;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.control.Label;
import java.lang.Math;
import javafx.scene.control.Button;

public class Foothill extends Application 
{
   final int NUM_CARDS_PER_HAND = 7;
   final int NUM_PLAYERS = 2;
   Image[] humanImages = new Image[NUM_CARDS_PER_HAND];
   ImageView[] humanViews = new ImageView[NUM_CARDS_PER_HAND];
   Image[] computerImages = new Image[NUM_CARDS_PER_HAND];
   ImageView[] computerViews = new ImageView[NUM_CARDS_PER_HAND];
   Image[] playedImages = new Image[NUM_CARDS_PER_HAND];
   ImageView[] playedViews = new ImageView[NUM_CARDS_PER_HAND];
   Label[] playLabelText = new Label[NUM_PLAYERS];
   Button[] humanCardButtons = new Button[NUM_CARDS_PER_HAND];
   
   public static void main(String[] args) 
   {
      launch(args);
   }
   /*
    * Name :  generateRandomCard()
    * Input : none
    * Output : Card
    * Description : Generates a Card with a randomized value and Suit
    */  
   static Card generateRandomCard() 
   {
      int randomValue = (int)(Math.random() * 14);
      int randomSuit = (int)(Math.random() * 4);
      char cardValue = GUICard.turnIntIntoCardValueChar(randomValue);
      Suit cardSuit = GUICard.turnIntIntoSuit(randomSuit);
      return new Card(cardValue, cardSuit);
   }
   /*
    * Name :  moveCard()
    * Input : Image cTemp, Image hTemp, Pane pPane
    * Output : void
    * Description : clears the pane passed through parameters and 
    *    places the images passed through parameters onto pane
    */  
   private void moveCard(Image cTemp, Image hTemp, Pane pPane) 
   {
      pPane.getChildren().clear();
      
      playedImages[0] = cTemp;
      playedImages[1] = hTemp;
      
      playedViews[0] = new ImageView(playedImages[0]);
      playedViews[1] = new ImageView(playedImages[1]);
      pPane.getChildren().addAll(playedViews[0], playedViews[1]);
      pPane.getChildren().addAll(playLabelText[0], playLabelText[1]);
   }
   /*
    * Name :  generateRandomImage()
    * Input : none
    * Output : String
    * Description : uses randomized variables to create a random file name
    *    for a card and returns the name as a string.
    */ 
   public String generateRandomImage() 
   {
      Card randomCard = generateRandomCard();
      int valueInt = GUICard.valueAsInt(randomCard);
      char valueChar = GUICard.turnIntIntoCardValueChar(valueInt);
      int suitInt = GUICard.suitAsInt(randomCard);
      char suitChar = GUICard.turnIntIntoCardSuitChar(suitInt);
      String imageFileName = "file:images/" + valueChar + suitChar +  ".gif";
      return imageFileName;
   }
   /*
    * Name :  start
    * Input : Stage primaryStage
    * Output : void
    * Description : Creates all the panes, sets the Stage, loads the
    *    Image and ImageView arrays for the computer, player, and played
    *    panes, and displays it all. 
    *    
    *    Assignment 5 additions- instead of displaying the human images,
    *        it displays buttons with the human cards on them. Also 
    *        sets the button to play the card if the button is clicked 
    */  
   public void start(Stage primaryStage)
   {
      // Create the scene and place it in the stage
      BorderPane pane = new BorderPane();
      Scene scene = new Scene(pane, 800, 600);
      primaryStage.setTitle("Card Table");
      primaryStage.setScene(scene);
      
      HBox computerPane = new HBox(15);
      computerPane.setPadding(new Insets(15, 100, 15, 100));
      computerPane.setAlignment(Pos.CENTER);
      computerPane.setStyle("-fx-border-color: blue");
      
      HBox humanPane = new HBox(15);
      humanPane.setPadding(new Insets(20, 50, 20, 50));
      humanPane.setAlignment(Pos.CENTER);
      humanPane.setStyle("-fx-border-color: blue");
      
      FlowPane playedPane = new FlowPane(150,15);
      playedPane.setPadding(new Insets(100, 200, 100, 200));
      playedPane.setAlignment(Pos.CENTER);
      playedPane.setStyle("-fx-border-color: blue");

      // CREATE IMAGES ----------------------------------------------------
      //code goes here ...
      for(int i = 0; i < NUM_CARDS_PER_HAND; i++) 
      {
         //Sets computer hand to all back card Images
         computerImages[i] = new Image("file:images/BK.gif");
         computerViews[i] = new ImageView(computerImages[i]);
         
         //Sets human hand to randomized card Images
         String imageFileName = generateRandomImage();
         humanImages[i] = new Image(imageFileName);
         humanViews[i] = new ImageView(humanImages[i]);
      
         //Sets played cards to randomized card Images
         imageFileName = generateRandomImage();
         playedImages[i] = new Image(imageFileName);
         playedViews[i] = new ImageView(playedImages[i]);         
         
         humanCardButtons[i] = new Button();
         humanCardButtons[i].setGraphic(humanViews[i]);
         
         final Image hTemp = humanImages[i];
         humanCardButtons[i].setOnAction(e -> {
            final Image cTemp = new Image(generateRandomImage());
            moveCard(cTemp, hTemp, playedPane);
            });
      }
      
      // ADD IMAGEVIEWS TO PANES -----------------------------------------
      //code goes here ...
      for(int i = 0; i < NUM_CARDS_PER_HAND; i++) 
      {
         computerPane.getChildren().add(computerViews[i]);
         humanPane.getChildren().add(humanCardButtons[i]);
      }
      
      // and two random cards in the play region (simulating a computer/hum ply)
      //code goes here ...
      playLabelText[0] = new Label("Computer");
      playLabelText[1] = new Label("You");
      playedPane.getChildren().addAll(playedViews[0], playedViews[1]);
      playedPane.getChildren().addAll(playLabelText[0], playLabelText[1]);

      pane.setTop(computerPane);
      pane.setCenter(playedPane);  
      pane.setBottom(humanPane); 

      // show everything to the user
      primaryStage.show();    
  }
}