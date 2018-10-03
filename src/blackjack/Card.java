package blackjack;

/*
 *  Joshua Lawson, Student and Pursuer of knowledge
 *  Code under no copyrights, free to use
 *  Credit is appreciated
 */

/**
 *
 * @author Josh
 */
public class Card implements Comparable<Card> {
    
    public String rank;
    public String suite;
    public int value;
    
    /**
     *
     * @param rank the rank is the 
     * @param suite one of the 4 suites possible for a card, diamond, spade, heart, club
     * @param value
     */
    public Card(String rank, String suite, int value)
    {
        this.rank = rank;
        this.suite = suite;
        this.value = value;
    }
    
    /**
     *
     * @return the rank(king, queen, etc.) of the card
     */
    public String getRank()
    {
        return this.rank;
    }
    
    /**
     *
     * @return the suite of the card
     */
    public String getSuite()
    {
        return this.suite;
    }
     /**
     *
     * @return the value of the card
     * I want to implement 1 as either 11 or 1
     * King, queen and jack are all 10
     */
    public int getValue()
    {
        return this.value;
    }
    
    @Override
    public int compareTo(Card other)
    {
        return Integer.compare(this.value, other.value);
    }
    
    @Override
    public String toString()
    {
        return getRank() + ", " + getSuite() + ", "+getValue();
    }
}
