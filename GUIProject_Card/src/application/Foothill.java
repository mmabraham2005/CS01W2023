package application;
/*
* Class Name : Foothill
* Description : Contains required code to set pane, stage, and
*  call correct methods from KochSnowFlakePane
* */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Foothill extends Application 
{  
   /*
    * Name :  start
    * Input : Stage primaryStage
    * Output : void
    * Description : Creates all the panes, sets the Stage, and 
    *    loads all the correct methods from KochSnowFlakePane
    */
   public void start(Stage primaryStage) 
   {
      KochSnowFlakePane trianglePane = new KochSnowFlakePane(); 
      TextField tfOrder = new TextField(); 
      tfOrder.setOnAction(
            e -> trianglePane.setOrder(Integer.parseInt(tfOrder.getText())));
      tfOrder.setPrefColumnCount(4);
      tfOrder.setAlignment(Pos.BOTTOM_RIGHT);
      
      // Pane to hold label, text field, and a button    
      trianglePane.setMaxSize(350, 350);
      HBox hBox = new HBox(10);
      hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
      hBox.setAlignment(Pos.CENTER);
       
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(trianglePane);
      borderPane.setBottom(hBox);
          
      // Create a scene and place it in the stage
      Scene scene = new Scene(borderPane, 200, 210);
      primaryStage.setTitle("KochSnowFlake"); // Set the stage title
      primaryStage.setScene(scene); // Place the scene in the stage
      primaryStage.show(); // Display the stage
       
      trianglePane.widthProperty().addListener(ov -> trianglePane.paint());
      trianglePane.heightProperty().addListener(ov -> trianglePane.paint());
   }

/****** Embedded class ****/
/** Pane for displaying fractal */
/*
   * Class Name : KochSnowFlakePane
   * Description : Contains methods to set order, prep the pane 
   *  for the program, and run the recursion method to create the 
   *  Koch Snowflake
   * */
   static class KochSnowFlakePane extends Pane 
   {
      private int order;
    
      public KochSnowFlakePane() 
      {
         this.order = 0;
      }
      
      /*
       * Name :  setOrder
       * Input : int order
       * Output : boolean
       * Description : sets this.order to int passed through
       *    parameters as long as it is >=0. It then calls paint
       *    to reset the stage with the updated order number. 
       *    returns true if valid order. else does nothing and 
       *    returns false
       */
      public boolean setOrder(int order)
      {
         if(order >= 0) {
            this.order = order;
            paint();
            return true;
         }
         return false;
      }
      
      /*
       * Name :  paint
       * Input : none
       * Output : void
       * Description : resets the pane and redisplays the 
       *    Koch Snowflake according to the current order number  
       */
      protected void paint() 
      {
         Point2D p1 = new Point2D(getWidth() / 2, 10);
         Point2D p2 = new Point2D(10, getHeight() - 10);
         Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);
  
         this.getChildren().clear(); // Clear the pane before redisplay
  
         displayKochSnowFlake(order, p1, p2);
         displayKochSnowFlake(order, p2, p3);
         displayKochSnowFlake(order, p3, p1);
      }
      
      /*
       * Name :  displayKochSnowFlake
       * Input : int order, Point2D p1, Point2D p2
       * Output : void
       * Description : recursion method which repeats based 
       *    on order. Continues to add a new triangle on each line
       *    until repeated order amount of times and then displays it.
       */
      private void displayKochSnowFlake(int order, Point2D p1, Point2D p2) 
      {
         if(order == 0) 
         {
            Line line = new Line(p1.getX(), p1.getY(), p2.getX(),p2.getY());
            line.setStroke(Color.BLACK);
            line.setFill(Color.WHITE);
            this.getChildren().add(line);
         }
         else 
         {
            //Some of the math to get you down the right path
            double deltaX = p2.getX() - p1.getX();
            double deltaY = p2.getY() - p1.getY();

            Point2D x = new Point2D(p1.getX() 
                  + deltaX / 3, p1.getY() + deltaY / 3);
            Point2D y = new Point2D(p1.getX() + deltaX * 2 / 3,
                  p1.getY() + deltaY * 2 / 3);
            Point2D z = new Point2D(
                  (p1.getX() + p2.getX()) / 2 + 
                  Math.cos(Math.toRadians(30)) * (p1.getY() - p2.getY()) / 3,
                  (p1.getY() + p2.getY()) / 2 +
                  Math.cos(Math.toRadians(30)) * (p2.getX() - p1.getX()) / 3);

            // Recursively display snow flakes on lines      
            displayKochSnowFlake(order - 1, p1, x);
            displayKochSnowFlake(order - 1, x, z);
            displayKochSnowFlake(order - 1, z, y);
            displayKochSnowFlake(order - 1, y, p2);
         }    
      
      }  
      
      /*
       * Name :  public static void main 
       * Input : String[] args
       * Output : void
       * Description : the public static void main,
       *    just calls launch(args).
       */
      public static void main(String[] args) 
      {
         launch(args);
      }
   }
}