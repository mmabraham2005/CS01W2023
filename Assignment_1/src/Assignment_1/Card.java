package Assignment_1;

import Assignment_1.CardIdentity;

public class Card extends CardIdentity 
{
   
   private boolean cardError;
   
   public Card() 
   {
      super();
      this.cardError = false;
   }
   
   public Card(char value, Suit suit) 
   {
      super();
      this.set(value, suit);
      if (this.set(value, suit)) 
      {
         this.cardError = false;
      }
      else 
      {
         this.cardError = true;
      }
   }
   
   public boolean set(char value, Suit suit) 
   {
      this.cardError = !super.set(value, suit);
      return !this.cardError; 
   }
   
   public String toString() 
   {
      if(this.cardError) 
      {
         return "** illegal **";
      }
      else 
      {
         return this.getValue() + " of " + this.getSuit();
      }
   }

   public boolean getCardError() 
   {
      return this.cardError;
   }

   public boolean equals(Card card) 
   {
      boolean valueIsEqual = 
            Character.compare(this.getValue(), card.getValue()) == 0;
      boolean suitIsEqual = this.getSuit() == card.getSuit();
      boolean cardErrorEqual = this.getCardError() && card.getCardError();
      
      if(valueIsEqual && suitIsEqual && cardErrorEqual) 
      {
         return true;
      }
      return false;
   }
}