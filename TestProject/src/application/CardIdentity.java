package application;
/*
* Class Name : CardIdentity
* Description : Establishes the allowed chars for value
*     and creates the enum Suit.
*/

public class CardIdentity 
{
   private char value;
   private Suit suit;
   
   public enum Suit 
   {
       CLUBS, DIAMONDS, HEARTS, SPADES
   }
   /*
    * Name :  CardIdentity()
    * Input : none
    * Output : none
    * Description : Constructor for CardIdentity. 
    */
   public CardIdentity() 
   {   
      value = 'A';
      suit = Suit.SPADES;
   }
   /*
    * Name :  set()
    * Input : char value, Suit suit
    * Output : boolean
    * Description : Sets the Card object's value and suit to
    *    passed parameters IF values are valid. If values are valid,
    *    returns true, if invalid, returns false.
    */
   public boolean set(char value, Suit suit) 
   {
      if(isValid(value, suit)) 
      {
         this.value = value;
         this.suit = suit;
         return true;
      }
      return false;   
   }
   /*
    * Name :  isValid()
    * Input : char value, Suit suit
    * Output : boolean
    * Description : checks to see if the value and suit passed through 
    *    through the parameter are valid values.
    */
   private static boolean isValid(char value, Suit suit) 
   {
      char [] validCardValueList = {'A','2','3','4','5','6',
            '7','8','9','T','J','Q','K', 'X'};
      for(int i = 0; i < validCardValueList.length; i++) 
      {
         int equal = Character.compare(value, validCardValueList[i]);
         if(equal == 0) 
         {
            return true;
         }
      }
      return false;
   }
   /*
    * Name :  getValue()
    * Input : none
    * Output : char
    * Description : returns the value of this.value. 
    */
   public char getValue() 
   {
      return this.value;
   }
   /*
    * Name :  getSuit()
    * Input : none
    * Output : Suit
    * Description : returns the value of this.suit. 
    */
   public Suit getSuit() 
   {
      return this.suit;
   }
}
