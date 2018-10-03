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
public class Player {
    
    private ArrayList<Card> Hand = new ArrayList<>();
    private int money;
    private String name;
    private int deltCards;
    private int checkedAce = 0;
    
    /**
     * takes no inputs. A default constructor for the dealer that gives a variable hand, an empty ArrayList of Object Card, 
     * a variable name, a String named dealer, and an variable money, an integer with the max value for integers.
     */
    public Player()
    {
        name = "dealer";
        money = Integer.MAX_VALUE;
    }
    
    /**
     *
     * @param name The starting name of the player
     * The player starts off with a 100 dollar loan that is expected to be payed off
     */
    public Player(String name)
    {
        this.money = 100;
        this.name = name;
    }
    
    /**
     *
     * @return The amount of money of the player has
     * 
     */
    public int getMoney()
    {
        return this.money;
    }
    
    /**
     *
     * @param money sets money of player to parameter integer
     */
    public void setMoney(int money)
    {
        this.money = money;
    }
    
    /**
     *
     * @return The name of the player
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     *
     * @param name Sets the name of the player to parameter String
     * 
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     *
     * @param card an object defined by String Rank, String Suite, and integer value
     * Adds a Card to the ArrayList of Cards Hand of Player
     */
    public void addCard(Card card)
    {
        Hand.add(card);
        deltCards++;
    }
    /**
     *
     * @param index An integer: The index of the card you want to retrieve from the arrayList of cards the player has. (The hand)
     * @return the card at the index entered
     * Takes an index and provides the Card at the index in the ArrayList hand of the player
     */
    public Card getCard(int index)
    {
        return Hand.get(index);
    }

    /**
     *
     * @return A string containing all of the cards in the players Hand
     * Format for string is in a String, String, integer, newLine, String, String, integer, newLine, etc. format, ending in a newLine.
     */
    public String hand()
    {
        String Final = "";
        for(int loop = 0; loop<Hand.size();loop++)
        {
            Final+= Hand.get(loop).toString()+"\n";
        }
        return Final;
    }
    
    /**
     *
     * @return the card last delt to the player
     */
    public Card lastDelt()
    {
        //implement a system that catches if no cards have been delt
        /*if(deltCards == 0)
        {
        return
        }*/
        return Hand.get(deltCards-1);
    }
    public void clearHand()
    {
        Hand = new ArrayList<>();
        deltCards = 0;
    }
    
    public boolean checkAce()
    {
        boolean bool = false;
        
        for(int i = checkedAce; i<Hand.size();i++)
        {
            String x = getCard(i).getRank();
            if(x.equals("Ace"))
            {
                bool = true;
                checkedAce+=i;
            }
        }
        
        return bool;
    }
    
}
