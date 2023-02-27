package Assignment_3;
/*
* Class Name : BarcodeImage
* Description : This class implements Cloneable to implement necessary
* methods to interact with DataMatrix. Specific uses include cloning
* an image object.
*/
public class BarcodeImage implements Cloneable {
   public static final int MAX_HEIGHT = 30; 
   public static final int MAX_WIDTH = 65;
   private boolean[][] imageData;
   /*
    * Name :  BarcodeImage() 
    * Input : none
    * Output : none
    * Description :  constructor for BarcodeImage
    */
   public BarcodeImage() 
   {
      this.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
      for(int x = 0; x < MAX_HEIGHT; x++) 
      {
         for(int j = 0; j < MAX_WIDTH; j++) 
         {
            this.imageData[x][j] = false;
         }
      }
   }
   /*
    * Name :  BarcodeImage(String[] strData)
    * Input : none
    * Output : none
    * Description :  second constructor for BarcodeImage
    */
   public BarcodeImage(String[] strData)
   {
      if(checkSize(strData))
       {
         this.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH]; 
          for(int x = 0; x < MAX_HEIGHT; x++) 
          {
             for(int j = 0; j < MAX_WIDTH; j++) 
             {
                this.imageData[x][j] = false;
             }
          }
          //Since it needs to be packed in the lower left corner of the
          //2D array, we start by skipping the difference in the array sizes 
          int difference = MAX_HEIGHT - strData.length;
          for (int x = 0; x < strData.length; x++)
          {
             for (int j = 0; j < strData[x].length(); j++) 
             {
                if (strData[x].charAt(j) == '*') 
                {
                   setPixel(x + difference, j, true);
                }
             }    
          }
       }
   }
   /*
    * Name : getPixel(int row, int col)
    * Input : int row, int col
    * Output : boolean
    * Description : returns true if valid pixel, 
    * else returns false
    */
   public boolean getPixel(int row, int col)
   {
      if (imageData != null && col < MAX_WIDTH && row < MAX_HEIGHT) 
       {
         return imageData[row][col];  
       }
       return false;
   }
   /*
    * Name : setPixel(int row, int col, boolean value)
    * Input : int row, int col, boolean value
    * Output : boolean
    * Description : returns true if valid pixel is chosen,
    * and sets pixel to value passed through parameters, 
    * else returns false
    */
   public boolean setPixel(int row, int col, boolean value)
   {
      if (imageData != null && col < MAX_WIDTH && row < MAX_HEIGHT)
       {
         imageData[row][col] = value;
           return true;
       }
       return false;
   }
   /*
    * Name : checkSize(String[] data)
    * Input : String [] data
    * Output : boolean
    * Description : returns true if size of data is valid,
    * else returns false
    */
   private boolean checkSize(String[] data)
   {
      if ((data != null) && (data.length < MAX_HEIGHT)) 
       {
         for(int i = 0; i < data.length; i++) 
          {
            if(data[i].length() > MAX_WIDTH) 
             {
               return false;
             }
          }
          return true;
       }
       return false;
   }
   /*
    * Name : clone()
    * Input : none
    * Output : object
    * Description : returns clone of image object
    */
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      if(this.imageData == null) 
      {
         System.out.println("Hello");
         throw new CloneNotSupportedException("imageData is null");
      }
      BarcodeImage copyImage = (BarcodeImage) super.clone();
      
      for(int x = 0; x < MAX_HEIGHT; x++) 
      {
         for(int j = 0; j < MAX_WIDTH; j++) 
         {
            copyImage.imageData[x][j] = imageData[x][j];
         }
      }
      return copyImage;
    }
}
