package application;
/*
* Class Name : Card
* Description : Card instantiates and establishes 
*    the value and suit variables of the Card object.
*/

public class Card extends CardIdentity 
{
   
   private boolean cardError;
   /*
    * Name :  Card() 
    * Input : none
    * Output : none
    * Description : Constructor 1 for Card (No parameters) 
    */
   public Card() 
   {
      super();
      this.cardError = false;
   }
   /*
    * Name :  Card() 
    * Input : char value, Suit suit 
    * Output : none
    * Description : Constructor 2 for Card 
    *    (char value and Suit suit parameters) 
    */
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
   /*
    * Name :  set()
    * Input : char value, Suit suit
    * Output : boolean
    * Description : Sets the Card object's value and suit to
    *    passed parameters. If values are valid, returns
    *    true, if invalid, returns false.
    */
   public boolean set(char value, Suit suit) 
   {
      this.cardError = !super.set(value, suit);
      return !this.cardError; 
   }
   /*
    * Name : toString() 
    * Input : none
    * Output : String
    * Description : Returns Card value and suit in 
    *    the form of a String card title (K of HEARTS)
    */
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
   /*
    * Name :  getCardError()
    * Input : none
    * Output : boolean
    * Description : if an error exists in the Card object
    *    (invalid value or suit), it returns true. Else,
    *    returns false.
    */
   public boolean getCardError() 
   {
      return this.cardError;
   }
   /*
    * Name :  equals()
    * Input : Card card
    * Output : boolean
    * Description : compares Card object with Card passed
    *    through parameters. If equal, returns true. Else,
    *    return false. 
    */
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