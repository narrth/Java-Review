import java.util.ArrayList;

/**
 * Hand models a hand of cards held by a player.
 * 
 * @author Lynn Marshall 
 * @version 1.1 October 11, 2012
 *
 * @author Narrthanan Seevananthan  
 * @verson May 26, 2016
 */
public class Hand
{
    /** 
     * The cards are stored in a linked-list implementation of the
     * List collection.
     */
    private ArrayList<Card> cards;

    /**
     * Constructs a new, empty hand.
     */
    public Hand()
    {
        cards = new ArrayList();
    }
    
    /**
     * Adds the specified card to this hand.
     * 
     * @param aCard, the card which is being added to the hand
     */
    public void addCard(Card aCard)
    {
        cards.add(aCard);
    }
     
   /**
     * Removes a card from this hand. Cards are removed in the order in
     * which they were added to the hand.
     * 
     * @return cards.remove(0), returns the card which was removed from the hand
     */
    public Card playCard()
    {
        return cards.remove(0);
    }

    /**
     * Returns the number of cards that are currently in this hand.
     * 
     * @return cards.size(), returns the size of the array list containing the cards in the hand
     */    
    public int size()
    {
        return cards.size();
    }

    /**
     * Determines if this hand is empty.
     * 
     * @return cards.isEmpty(), true if the array list containing the hand is empty, false otherwise
     */    
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }

    /**
     * Returns a String that lists the ranks (but not the suits) of all the 
     * cards in this hand, starting with the first card and finishing with
     * the last card. For example, if the first card is the two of hearts, 
     * the second card is the five of diamonds, and the last card is the
     * queen of clubs, the String returned by this method will be: "2 5 12".
     * 
     * @return S, a string containing a list of integers in string format
     */
    public String toString()
    {
        String S = "";
        for(int i = 0; i< cards.size();i++)
        {
        if(i!=0)
        {
        S = S  + " ";
        }
        S = S + (cards.get(i)).rank();
        }
        
        return S;
    }
}
