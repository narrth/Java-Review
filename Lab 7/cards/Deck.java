import java.util.ArrayList;
import java.util.Random;

/**
 * Deck models a deck of 52 Anglo-American playing cards.
 * 
 * @author Lynn Marshall 
 * @version 1.1 October 11, 2012
 * 
 * @author Narrthanan Seevananthan  
 * @version May, 26, 2016
 *
 */
public class Deck
{
    /** 
     * The cards are stored in a linked-list implementation of the
     * List collection.
     */
    private ArrayList<Card> cards;
    
    /** Lowest ranking card (the ace). */
    private static final int ACE = 1;
    
    /** Highest ranking card (the king). */
    private static final int KING = 13;
    
    /** 
     * Total number cards in the deck (4 suits, each with 13 cards of 
     * different ranks).
     */ 
    private static final int TOTAL_CARDS = 52;
    
    /** 
     * Some constants that define the Strings for the various suits.
     */ 
    private static final String HEARTS = "hearts";
    private static final String DIAMONDS = "diamonds";
    private static final String CLUBS = "clubs";
    private static final String SPADES = "spades";

    /**
     * Constructs a new, unshuffled deck containing 52 playing cards.
     */
    public Deck()
    {
        cards = new ArrayList<Card>();
        buildSuit(HEARTS);
        buildSuit(DIAMONDS);
        buildSuit(CLUBS);
        buildSuit(SPADES);
    }
    
    /**
     * Creates the 13 cards for the specified suit, and adds them
     * to this deck.
     * 
     * @param suit, the given suit of the cards to be built
     */
    private void buildSuit(String suit)
    {
         for(int i = ACE; i<=KING;i++)
        {
        cards.add(new Card(suit,i));
        }
    }
 
    /**
     * Shuffles this deck of cards.
     */
    public void shuffle()
    {
        Random r = new Random();
        Card temp;
        int h,j;
       for(int i = 0; i<10000;i++) 
       {
           h = r.nextInt(52);
           j = r.nextInt(52);
       temp = cards.get(h);
       cards.set(h ,cards.set(j,temp));
       }
    }
 
    /**
     * Removes a card from this deck.
     * 
     * @return cards.remove(0) , returns the removed card
     */
    public Card dealCard()
    {
        return cards.remove(0);
    }
 
    /**
     * Determines if this deck is empty.
     * 
     * @return returns true if the deck is empty false if otherwise
     */
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }
  
    /**
     * Returns the number of cards that are currently in the deck.
     * 
     * @return cards.size(), returns the size of the arraylist cards, 
     * which is also the number of cards in the deck
     */
    public int size()
    {
        return cards.size();
    }
}
