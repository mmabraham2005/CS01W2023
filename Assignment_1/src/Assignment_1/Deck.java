package Assignment_1;

import java.util.Random;

import Assignment_1.CardIdentity.Suit;

public class Deck 
{
   private final int MAX_PACKS = 6;
   private final int NUM_CARDS_PER_PACK = 52;
   private final int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;
   private static Card[] masterPack = null;
   private Card[] cards;
   private int topCard;
   private int numPacks;
   public Deck(int numPacks) 
   {
      allocateMasterPack();
      this.numPacks = numPacks;
      if(!initializePack(this.numPacks)) 
      {
         initializePack(1);
      }
   }
   public Deck() 
   {
      allocateMasterPack();
      this.numPacks = 1;
      initializePack(this.numPacks);
   }
   public boolean initializePack(int numPacks) 
   {
      if (numPacks > this.MAX_PACKS || numPacks < 1) 
      {
         return false;
      }
      
      this.cards = new Card[this.NUM_CARDS_PER_PACK * this.numPacks];
      int cardCount = 0;
      for(int i = 0; i < numPacks; i++) 
      {
         for(int j = 0; j < NUM_CARDS_PER_PACK; j++) 
         {
            Card masterCardCopyHold = masterPack[j];
            cards[cardCount] = masterCardCopyHold;
            cardCount++;
         }
      }   
      return true;
   }
    public void shuffle() 
   {
        Random randomGen = new Random();
        randomGen.nextInt();
        for (int i = 0; i < cards.length; i++) 
        {
            int newCardIndex = randomGen.nextInt(cards.length);
            Card temp = cards[i];
            cards[i] = cards[newCardIndex];
            cards[newCardIndex] = temp;
        }
   }
   public Card dealCard() 
   {
      int topCardIndex = topCard();
      Card newTopCard = new Card(this.cards[topCardIndex].getValue(),
            this.cards[topCardIndex].getSuit());
      this.cards[topCardIndex] = null;
      return newTopCard;
   }
   public int topCard() 
   {
      for(int i = this.cards.length-1; i >= 0; i--) 
      {
         if(cards[i] != null) 
         {   
            this.topCard = i;
            return this.topCard;
         }
      }
      this.topCard = 0;
      return this.topCard;
   }
   public Card inspectCard(int k) 
   {   
      try
      {
         Card returningCard = new Card(this.cards[k].getValue(), 
               this.cards[k].getSuit());
         return returningCard;
      }
      catch(IndexOutOfBoundsException e)
      {
         return new Card('M', Suit.SPADES);
      }
   }
   private static void allocateMasterPack() 
   {
      char [] validCardValueList = {'2','3','4','5','6','7',
               '8','9','T','J','Q','K','A'};
      Suit [] validCardSuitList = 
         {Suit.CLUBS, Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES};
      int cardCount = 0;
      if(masterPack == null) 
      {
         masterPack = new Card[52];
         for(int x = 0; x < 4; x++) 
         {
            for(int i = 0; i < 13; i++) 
            {   
               masterPack[cardCount] = new Card(validCardValueList[i],
                     validCardSuitList[x]);
               cardCount++;
            }
         }
      }
   }

}
