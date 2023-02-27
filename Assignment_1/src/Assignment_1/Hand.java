package Assignment_1;

import Assignment_1.CardIdentity.Suit;

public class Hand 
{
   
   public int MAX_CARDS = 50;
   private Card[] myCards;
   private int numCards;
   
   public Hand() {
      this.myCards = new Card[MAX_CARDS];
      this.numCards = 0;
   }
   public void resetHand() 
   {
      this.myCards = new Card[MAX_CARDS];
      this.numCards = 0;
   }
   public boolean takeCard(Card card) 
   {
      boolean isHandFull = (this.numCards == this.MAX_CARDS);
      if(isHandFull) 
      {
         return false;
      }
      
      if(!card.getCardError()) 
      {
         this.myCards[this.numCards] = 
               new Card(card.getValue(), card.getSuit());
         this.numCards++;
      }

      return true;
   }
   public Card playCard()  
   {
      Card returningCard = this.myCards[this.numCards - 1];
      this.myCards[this.numCards - 1] = null;
      this.numCards--;
      return returningCard;
   }   
   public String toString() 
   {
      String printHand = "Hand = ( ";
      for(int i = 0; i < this.numCards; i++) 
      {
         printHand = printHand + this.myCards[i].toString();
         if(i != this.numCards - 1) 
         {
            printHand = printHand + ", ";
         }
      }
      printHand = printHand + " )";
      return printHand;
      
   }
   public int getNumCards() 
   {
      return this.numCards;
   }
   public Card inspectCard(int k) 
   {
      if(k >=0 && k <= this.numCards - 1) 
      {
         return this.myCards[k];
      }
      return new Card('M', Suit.SPADES);
   }
}
