/**
 * A Card is a playing card from an Anglo-American deck of cards.
 * 
 * @author Lynn Marshall
 * @version 1.1 October 11, 2012
 * 
 * @author Narrthanan Seevananthan
 * @version May 24, 2016
 */
public class Card
{
    /** The card's suit: "hearts", "diamonds", "clubs", "spades". */
    private String suit;
    
    /** 
     * The card's rank, between 1 and 13 (1 represents the ace, 11 represents
     * the jack, 12 represents the queen, and 13 represents the king.)
     */
    private int rank;

    /**
     * Constructs a new card with the specified suit and rank.
     * 
     * @param suit, the suit of the card to be created
     * @param rank, the rank of the card to be created
     */
    public Card(String suit, int rank)
    {
        this.rank = rank;
        this.suit = suit;
    }
    
    /**
     * Returns this card's suit.
     * 
     * @return suit the suit of the current card
     */
    public String suit()
    {
        return suit;
    }
    
    /**
     * Returns this card's rank.
     * 
     * @return rank, the rank of the current card
     */
    public int rank()
    {
        return rank;
    }
    
    /**
     * Determines if this card has the same rank as the specified card.
     * 
     * @param aCard, contains a card to compare rank with current card
     * @return true if both card ranks are the same, false otherwise
     */
    public boolean hasSameRank(Card aCard)
    {
        return (aCard.rank() == rank);
    }
    
    /**
     * Determines if this card is equal to the specified card.
     * 
     * @param aCard, contains a card to compare suits with current card
     * @return true if both card suits are the same, false otherwise
     */
    public boolean isEqualTo(Card aCard)
    {
       return (suit.equals(aCard.suit()));
    }
}
