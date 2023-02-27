package Assignment_3;
/*
* Interface Name : BarcodeIO
* Description : This interface is implemented in DataMatrix.
* The methods are used to convert text to image and image to
* text.
*/
public interface BarcodeIO {
   public boolean scan(BarcodeImage bc);
   public boolean readText(String text);
   public boolean generateImageFromText();
   public boolean translateImageToText();
   public void displayTextToConsole();
   public void displayImageToConsole();
}
