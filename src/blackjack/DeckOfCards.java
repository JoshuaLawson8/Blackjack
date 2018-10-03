package blackjack;

import java.util.ArrayList;

/*
 *  Joshua Lawson, Student and Pursuer of knowledge
 *  Code under no copyrights, free to use
 *  Credit is appreciated
 */

/**
 *
 * @author Josh
 */
public class DeckOfCards {
    //the deck
    public int number = 52;
    public int inverseNumber = 0;
    public Card[] Deck = new Card[number];
    public ArrayList<Card> DeltCards = new ArrayList<>();
    
    String[] ranks = {"Ace", "King", "Queen", "Jack", "Ten", "Nine", "Eight", "Seven", "Six", "Five", "Four", "Three", "Two"};
    String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
    int[] values = {11, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    
    public DeckOfCards()
    {
        Deck = makeDeck();
    }
    
    public Card[] makeDeck()
    {
        Card[] newDeck = new Card[number];
        for(int i = 0; i < number; i++)
            {   
		newDeck[i] = new Card(ranks[i % 13], suits[i % 4], values[i % 13]);  // praise sebek 
            }
        return newDeck;
    }
    
    /**
     * shuffles the deck
     */
    public void shuffle()
    {
        for(int i = 1; i<100; i++)
        {
            int x = (int)(Math.random()*number);
            int y = (int)(Math.random()*number);
            Swap(x,y);
        }
    }
    
    /**
     *
     * @param x is a random value from 0 to number
     * @param y is a random value from 0 to number
     */
    public void Swap(int x, int y)
    {
        Card z = Deck[x];
        Deck[x] = Deck[y];
        Deck[y] = z;
    }
    
    @Override
    public String toString()
    {
        String finale = "";
        for(int i = 0; i<Deck.length;i++)
        {
            finale+= Deck[i].getRank() +", "+Deck[i].getSuite()+", "+Deck[i].getValue()+"\n";
        }
        return finale;
    }
    
    public Card deal()
    {
        if(Deck.length == 0)
        {
            number = 52;
            inverseNumber = 0;
            Deck = makeDeck();
            
            shuffle();
            System.out.println("You reached the bottom of the deck. Shuffling...");
        }
        Card x = Deck[number-1];
        number--;
        inverseNumber++;
        Deck = changeDeck(number, Deck);
        DeltCards.add(x);
        return x;
    }
    public Card[] changeDeck(int number, Card[] Deck)
    {
        Card[] newDeck = new Card[number];
        for(int i = 0; i<number; i++)
        {
            newDeck[i] = Deck[i];
        }
        return newDeck;
    }
    
    
    public String printDelt()
    {
        String finale = "";
        for(int i = 0; i<DeltCards.size();i++)
        {
            finale+= DeltCards.get(i).getRank() +", "+DeltCards.get(i).getSuite()+", "+DeltCards.get(i).getValue()+"\n";
        }
        return finale;
    }
    
    public int numCards()
    {
        return number;
    }
}
