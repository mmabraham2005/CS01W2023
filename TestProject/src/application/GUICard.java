package application;
/*
* Class Name : GUICard
* Description : Contains and loads all possible card file names
*  into arrays. Also provides methods to convert the values from
*  int, char, or Suit form.
*/
import application.CardIdentity.Suit;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.control.Label;

public class GUICard extends Application {
   private static Image[][] imageCards = new Image[14][4];   // 14 = A thru K (+ joker)
   private static ImageView[][] imageCardViews = new ImageView[14][4];
   private static Image imageBack;
   private static ImageView imageCardBack;
   private static boolean imagesLoaded = false; 
   private static String cardlValsConvertAssist = "23456789TJQKAX";
   private static String suitValsConvertAssist  = "CDHS";
   private static Card.Suit suitConvertAssist[] =
   {  
      Card.Suit.CLUBS,
      Card.Suit.DIAMONDS,
      Card.Suit.HEARTS,
      Card.Suit.SPADES
   };
   /*
    * Name :  GUICard()
    * Input : none
    * Output : none
    * Description : Constructor for GUICard. 
    *    Loads the card images into private static arrays 
    */   
	public GUICard() {
	   loadCardImages();
	}
	//--------------------------
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}
	/*
    * Name :  loadCardImages()
    * Input : none
    * Output : void
    * Description :  Loads the card Images and ImageViews 
    *    into private static arrays  
    */
	
	static void loadCardImages() 
	{
		if(imagesLoaded) 
		{
			return;
		}
		String imageFileName;
	    int intSuit, intVal;
	    for (intSuit = 0; intSuit < 4; intSuit++) 
	    {
	       for (intVal = 0; intVal < 14; intVal++ )
	       {
	    	   imageFileName = "file:images/" + 
	    			   turnIntIntoCardValueChar(intVal) +
	    			   turnIntIntoCardSuitChar(intSuit) + ".gif";             
	       	 	imageCards[intVal][intSuit] = new Image(imageFileName);
	       	 	imageCardViews[intVal][intSuit] = new ImageView(imageCards[intVal][intSuit]);
	       }
	    }
	    imageFileName = "file:images/BK.gif";
	    imageBack = new Image(imageFileName);
	    imageCardBack = new ImageView(imageBack);
	    imagesLoaded = true;
	}
	/*
    * Name :  getImage()
    * Input : Card card
    * Output : Image
    * Description : Returns Image of Card passed through parameter 
    */
	static public Image getImage(Card card)
	{
	   loadCardImages();
	   return imageCards[valueAsInt(card)][suitAsInt(card)];
	}
	/*
    * Name :  getBackCardImage()
    * Input : none
    * Output : Image
    * Description : Returns Image of Back Card
    */
	static public Image getBackCardImage() 
	{
		loadCardImages();
		return imageBack;
	}
	/*
    * Name :  turnIntIntoCardValueChar()
    * Input : int k
    * Output : char
    * Description : converts int value passed through parameters
    *    into it's char value equivalent 
    */
	static char turnIntIntoCardValueChar(int k) 
	{
		return cardlValsConvertAssist.charAt(k);
	} 
	/*
    * Name :  turnIntIntoCardSuitChar()
    * Input : int k
    * Output : char
    * Description : converts int value passed through parameters
    *    into it's char suit equivalent 
    */
	static char turnIntIntoCardSuitChar(int k) 
	{
		return suitValsConvertAssist.charAt(k);
	}
	/*
    * Name :  turnIntIntoSuit()
    * Input : int k
    * Output : Card.Suit
    * Description : converts int value passed through parameters
    *    into it's Card.suit equivalent 
    */
	static Card.Suit turnIntIntoSuit(int k)
	{
		return suitConvertAssist[k];
	}
	/*
    * Name :  valueAsInt()
    * Input : Card card
    * Output : int
    * Description : converts value of Card passed through parameters
    *    into it's integer equivalent 
    */
	static int valueAsInt(Card card)  
	{
		char cardValue = card.getValue();
		for(int i = 0; i < 14; i++) 
		{
			char currentChar = turnIntIntoCardValueChar(i);
			if(Character.compare(cardValue, currentChar) == 0) 
			{
				return i;
			}
		}
		return -1;
	}
	/*
    * Name :  suitAsInt()
    * Input : Card card
    * Output : int
    * Description : converts suit of Card passed through parameters
    *    into it's integer equivalent 
    */
	static int suitAsInt(Card card) 
	{
		Suit cardSuit = card.getSuit();
		for(int i = 0; i < 4; i++) 
		{
			Suit currentCSuit = turnIntIntoSuit(i);
			if(cardSuit == currentCSuit) 
			{
				return i;
			}
		}
		return -1;
	}
	
}
