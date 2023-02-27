package Assignment_3;
/*
 * Class Name : DataMatrix
 * Description : This class implements BarcodeIO to implement necessary
 * methods to interact with BarcodeImage. Specific uses include converting
 * from image to text or from text to image.
 */
public class DataMatrix implements BarcodeIO 
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';  
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;   
   /*
    * Name : DataMatrix
    * Input : None
    * Output : None
    * Description : Default constructor
    */
   public DataMatrix() 
   {
      this.image = new BarcodeImage();
      this.text = "";
      this.actualWidth = 0;
      this.actualHeight = 0;
   }
   /*
    * Name : DataMatrix(BarcodeImage image)
    * Input : BarcodeImage
    * Output : None
    * Description : Second constructor
    */
   public DataMatrix(BarcodeImage image) 
   {
      scan(image);
      this.text = "";
   }
   /*
    * Name : DataMatrix(String texts)
    * Input : String
    * Output : None
    * Description : Third constructor
    */
   public DataMatrix(String text) 
   {
      this.image = new BarcodeImage();
      readText(text);
      this.actualWidth = 0;
      this.actualHeight = 0;
   }
   /*
    * Name : readText(String text) 
    * Input : String text, the string being converted into an image
    * Output : boolean, returns true if string is valid, else returns false
    * Description : if the string is not null and is within the MAX_WIDTH
    * (not including the borders), then it is valid
    */
   @Override
   public boolean readText(String text) 
   {
      if(text == null || text.length() > BarcodeImage.MAX_WIDTH - 2) 
      {
         return false;
      }
      this.text = text;
      return true;
   }
   /*
    * Name :  scan(BarcodeImage bc) 
    * Input : BarcodeImage, the image being converted to text
    * Output : boolean, returns true if image is valid, else returns false.
    * Description :  if the image is null and the clone is supported,
    * then it returns as true
    */
   @Override
   public boolean scan(BarcodeImage bc) 
   {
      if(bc == null)
       {
         return false;
       }
      try 
      {
         this.image = (BarcodeImage) bc.clone();   
         this.actualWidth = computeSignalWidth();
         this.actualHeight = computeSignalHeight();
      }
      catch(CloneNotSupportedException e)
      {
         
      }
      return true;  
   }
   /*
    * Name : getActualWidth() 
    * Input : none
    * Output : int actualWidth
    * Description : accessor which returns this.actualWidth
    */
   public int getActualWidth()
   {
      return this.actualWidth;
   }
   /*
    * Name :  getActualHeight()
    * Input : none
    * Output : int actualHeight
    * Description :  accessor which returns this.actualHeight
    */
    public int getActualHeight()
   {
      return this.actualHeight;
   }
    //width is calculated as the width of the bottom border (last row)
    // The first false pixel means that the end of the border 
    // has been reached
    /*
    * Name :  computeSignalWidth()
    * Input : none
    * Output : int width
    * Description : calculates the actual width of the image
    */
   private int computeSignalWidth()
   {
      int width = 0;
      for (int x = 0; x < BarcodeImage.MAX_WIDTH; x++) 
      {
         if (image.getPixel(BarcodeImage.MAX_HEIGHT - 1, x)) 
         {
            width++; 
         }
         else 
         {
            return width;
         }
         
      }
      return width;
   }
   //height is calculated as the height of the left border (col 0)
    // Since the image is at the bottom left, we start at the bottom of col 0 and go up
   // The first false pixel means that the top of the border 
    // has been reached
   /*
    * Name :  computeSignalHeight()
    * Input : none
    * Output : int height
    * Description : calculates the actual height of the image
    */
   private int computeSignalHeight()
   {
      int height = 0;
      for (int x = BarcodeImage.MAX_HEIGHT - 1; x >= 0; x--) 
      {
         if (image.getPixel(x, 0)) 
         {
            height++;
         }         
      }
      return height;
   }
   /*
    * Name : generateImageFromText() 
    * Input : none 
    * Output : boolean
    * Description : Converts text into image, returns false if
    * text length is bigger than MAX_WIDTH, else returns true 
    */
   @Override
   public boolean generateImageFromText() 
   {
      if (text.length() > BarcodeImage.MAX_WIDTH) 
      {
         return false;
      }
      else 
      {
         
         this.clearImage();
         //adding 2 for left and right border
         this.actualWidth = text.length() + 2;
         this.actualHeight = 0;
         //setting left border all true (binary 1111111)
         WriteCharToCol(0, 255);
         
         for (int i = 0; i < text.length(); i++) 
         {
                int hght = WriteCharToCol(i + 1, (int)text.charAt(i));
                if (hght > this.actualHeight)
                {
                   this.actualHeight = hght;
                }
            }
            
        }
      
        return true;
   }
   /*
    * Name : readCharFromCol(int col) 
    * Input : int col
    * Output : char code
    * Description :  takes column input and reads the binary
    * code in that column, converts to char, and returns the char
    */
   private char readCharFromCol(int col) 
   {
      String binaryString = "";
      int startIndex = BarcodeImage.MAX_HEIGHT - 2;
      int stopIndex = BarcodeImage.MAX_HEIGHT - this.actualHeight;
      for (int i = stopIndex + 1; i <= startIndex; i++) 
      {
         if (image.getPixel(i, col)) 
         {
            binaryString = binaryString + '1';
         } 
         else
         {
            binaryString = binaryString + '0';
         }
      }
      int code = Integer.parseInt(binaryString, 2);
      return (char) code;   
   }
   /*
    * Name :  WriteCharToCol(int col, int code) 
    * Input : int col, int code
    * Output : int colHeight
    * Description : return type different than suggested, returns
    * column height instead. Takes the code passed through parameters
    * and inserts it in binary in the column passed through parameters
    */
   private int WriteCharToCol(int col, int code) 
   {
      image.setPixel(BarcodeImage.MAX_HEIGHT - 1, col, true);
      String binaryString =  Integer.toBinaryString(code);
      if(binaryString.length() < 8) 
      {
         int loopCount = 8 - binaryString.length();
         for(int i = 0; i < loopCount; i++) 
         {
            binaryString = "0" + binaryString;
         }
      }
      int row = BarcodeImage.MAX_HEIGHT - 2;
      for (int i = binaryString.length() - 1; i >= 0; i--) 
      {
         char digit = binaryString.charAt(i);
         if (digit == '1')
         {
            image.setPixel(row, col, true);
          } 
         else 
         {
             image.setPixel(row, col, false);
          }
         row--;
      }
      int colHeight = binaryString.length() + 2;
      if (col % 2 == 0) 
      {
         image.setPixel(row, col, true);
      }
      return colHeight;
   }
   /*
    * Name :  translateImageToText() 
    * Input : none
    * Output : boolean
    * Description :  translates image to text form, converting 
    * binary to char and returns true
    */
   @Override
   public boolean translateImageToText() 
   {
      this.text = "";
      for (int i = 1; i < this.actualWidth - 1; i++) 
      {
         this.text = this.text + this.readCharFromCol(i);
      }
      return true;
   }
   /*
    * Name :  displayTextToConsole() 
    * Input : none
    * Output : void
    * Description : prints out this.text
    */
   @Override
   public void displayTextToConsole() 
   {
      System.out.println(this.text);
   }
   /*
    * Name :  displayImageToConsole() 
    * Input : none
    * Output : void
    * Description :  prints the image and the borders around it neatly
    * to the console 
    */
   @Override
   public void displayImageToConsole() 
   {
      for (int i = 0; i < this.actualWidth + 1; i++)
      {
         System.out.print("-");
      }
      System.out.println();
      int startRow = BarcodeImage.MAX_HEIGHT - this.actualHeight;
      for (int i = startRow; i < BarcodeImage.MAX_HEIGHT; i++ ) 
      {
         System.out.print("|");
         for (int j = 0; j < this.actualWidth - 1; j++)
         {
            if (this.image.getPixel(i, j)){
               System.out.print("*");
            }
            else
            {
               System.out.print(" ");
            }
         }
         System.out.println("|");   
      }   
   }
   /*
    * Name :  clearImage()
    * Input : none
    * Output : void
    * Description : resets the image by setting the entire
    * double array to be filled with "blanks" (false) 
    */
   private void clearImage()
   {
      for (int x = 0; x < BarcodeImage.MAX_HEIGHT - 1; x++) 
      {
         for (int y = 0; y < BarcodeImage.MAX_WIDTH - 1; y++) 
         {
            this.image.setPixel(x, y, false);
         }
      }
   }
}

