package Assignment_1;

public class CardIdentity 
{
   private char value;
   private Suit suit;
   
   public enum Suit 
   {
       CLUBS, DIAMONDS, HEARTS, SPADES
   }
   
   public CardIdentity() 
   {   
      value = 'A';
      suit = Suit.SPADES;
   }
   
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
   
   private static boolean isValid(char value, Suit suit) 
   {
      char [] validCardValueList = {'A','2','3','4','5','6',
            '7','8','9','T','J','Q','K'};
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

   public char getValue() 
   {
      return this.value;
   }
   
   public Suit getSuit() 
   {
      return this.suit;
   }
}
