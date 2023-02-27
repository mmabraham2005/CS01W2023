package Practice_1;

public class Foothill
{
   public static void main (String[] args)
   {
      double rent, total, costThisYear;
      int year;
      final double INITIAL_RENT = 1700;
 
      rent = INITIAL_RENT;         // our initial rent
      total = 0;                   // keeps track of how much we paid "so far"

      // loop for five years
      for (year = 1; year <= 5; year++)
      {
         costThisYear = rent * 12;
         total += costThisYear;

         // now increase the rent in prep for the next year
         rent *= 1.05;
      }
      System.out.println("\nYou will have paid $" + total + " after five years\n");
   }
}