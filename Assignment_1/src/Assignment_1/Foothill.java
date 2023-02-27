package Assignment_1;

import Assignment_1.CardIdentity.Suit;
import java.util.Scanner;

public class Foothill 
{
   public static void main(String[] args) 
   {  
     //Phase 1- test of Class Deck
     //Declaring Deck object with 2 packs
      Deck deckObj1 = new Deck(2);
      
      //Dealing all cards
      for(int i = 0; i < (52 * 2); i++) 
      {
         System.out.print(deckObj1.dealCard() + " / ");
      }
      System.out.println();
      
      //Initializing Deck with 2 new packs
      deckObj1.initializePack(2);
      
      //Shuffling Deck
      deckObj1.shuffle();
      
      //Dealing all cards
      for(int i = 0; i < (52 * 2); i++) 
      {
         System.out.print(deckObj1.dealCard() + " / ");
      }
      System.out.println();
      
      //Declaring Deck object with 1 pack
      Deck deckObj2 = new Deck(1);
      
      //Dealing all cards
      for(int i = 0; i < 52; i++) 
      {
         System.out.print(deckObj2.dealCard() + " / ");
      }
      System.out.println();
      
      //Initializing Deck with 1 new pack
      deckObj2.initializePack(1);
      
      //Shuffling Deck
      deckObj2.shuffle();
      
      //Dealing all cards
      for(int i = 0; i < 52; i++) 
      {
         System.out.print(deckObj2.dealCard() + " / ");
      }
      System.out.println();
     
      //Phase 2- test of classes Deck and Hand
      //Gets user input number of Hands
      Scanner scanner = new Scanner(System.in); 
      System.out.println("How many hands? (1 - 10, please): ");
      int handNum = scanner.nextInt(); 
      
      //instantiating Deck object and Hand array
      Deck deckObj3 = new Deck(1);
      Hand [] totalHands = new Hand[handNum];
      
      for(int i = 0; i < handNum; i++) 
      {
         totalHands[i] = new Hand();
      }
      
      //Dealing Cards into Hands
      for(int j = 0; j < 52; j++) 
      {
         int testMod = j%handNum;
         totalHands[testMod].takeCard(deckObj3.dealCard());

      }
      System.out.println("Here are our hands, from unshuffled deck:");
      for(int i = 0; i < handNum; i++) 
      {
         System.out.println(totalHands[i]);
      }
      
      //Shuffled Deck
      //instantiating Deck object and Hand array
      deckObj3 = new Deck(1);
      totalHands = new Hand[handNum];
      deckObj3.shuffle();
      
      //Dealing Cards into Hands
      for(int i = 0; i < handNum; i++) 
      {
         totalHands[i] = new Hand();
      }
      
      for(int j = 0; j < 52; j++) 
      {
         int testMod = j%handNum;
         totalHands[testMod].takeCard(deckObj3.dealCard());

      }
      System.out.println("Here are our hands, from SHUFFLED deck:");
      for(int i = 0; i < handNum; i++) 
      {
         System.out.println(totalHands[i]);
      }
   }
}
