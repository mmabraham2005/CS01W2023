/*
Name: Maya Abraham
Date: 2/1/23
This program can convert an image (a phrase represented in binary code)
to text, and convert text to an image
*/
package Assignment_3;
/*
 * Class Name : Foothill
 * Purpose- runs the "main" to execute the code.
 * 
 */
public class Foothill {

   public Foothill() {
      // TODO Auto-generated constructor stub
   }

   public static void main(String[] args)
      {
         String[] sImageIn =
         {
            "* * * * * * * * * * * * * * *",
            "*                           *",
            "**********  *** *** *******  ",
            "* ***************************",
            "**    * *   * *  *   * *     ",
            "* **     ** **          **  *",
            "****** ****  **   *  ** ***  ",
            "****  **     *   *   * **   *",
            "***  *  *   *** * * ******** ",
            "*****************************"
         };      
               
            
         
         String[] sImageIn_2 =
         {
               "* * * * * * * * * * * * * * *",
               "*                           *",
               "*** ** ******** ** ***** *** ",
               "*  **** ***************** ***",
               "* *  *    *      *  *  *  *  ",
               "*       ** **** *          **",
               "*    * ****  **    * * * *** ",
               "***    ***       * **    * **",
               "*** *   **  *   ** * **   *  ",
               "*****************************"
               
         };
        
         BarcodeImage bc = new BarcodeImage(sImageIn);
         DataMatrix dm = new DataMatrix(bc);
        
         // First secret message
         dm.translateImageToText();
         dm.displayTextToConsole();
         dm.displayImageToConsole();
         
         // second secret message
         bc = new BarcodeImage(sImageIn_2);
         dm.scan(bc);
         dm.translateImageToText();
         dm.displayTextToConsole();
         dm.displayImageToConsole();
         
         // create your own message
         dm.readText("What a great resume builder this is!");
         dm.generateImageFromText();
         dm.displayTextToConsole();
         dm.displayImageToConsole();
      }   
}
