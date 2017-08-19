import java.util.*;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kolling.
 * @version 2006.03.30
 *
 * @author (of AuctionSkeleton) Lynn Marshall
 * @version 2.0
 * 
 * @author Narrthanan Seevananthan
 * @version 2016.05.21 (renamed to Auction.java)
 * 
 */
public class Auction
{
    /** The list of Lots in this auction. */
    private ArrayList<Lot> lots;

    /** 
     * The number that will be given to the next lot entered
     * into this auction.  Every lot gets a new number, even if some lots have
     * been removed.  (For example, if lot number 3 has been deleted, we don't
     * reuse it.)
     */
    private int nextLotNumber;

    /**
     * Added new private boolean variable to tell whether the lot is open or closed
     */
    private boolean openClose;

    /**
     * Create a new auction.
     * set next lot number to 1 because there are currently no lots
     * also set the auction to open
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
        openClose = true;
    }
    
    /**
     * Add a second constructor here.  This constructor takes
     * an Auction as a parameter.  Provided the auction parameter
     * is closed, the constructor creates a new auction containing
     * the unsold lots of the closed auction.  If the auction parameter
     * is still open or null, this constructor behaves like the
     * default constructor.
     * (You need to write the entire method.)
     * 
     * getNoBids() returns a list with items that have no bids, in a closed
     * auction these items are also counted as not sold
     */
    public Auction(Auction auction)
    {
        if(!openClose)
        {
        lots = getNoBids();
        }
        else
        {
            lots = new ArrayList<Lot>();
        nextLotNumber = 1;
        openClose = true;
        }
    }
    

    /**
     * Enter a new lot into the auction.  Returns false if the
     * auction is not open or if the description is null.
     * 
     * changed method type from void to boolean
     * added if statements to check run conditions
     *
     * @param description: A description of the lot.
     * @return true/false: returns true if the new lot has been successfuly added
     * returns false if otherwise
     */
    public boolean enterLot(String description)
    {
        if(description==null || openClose==false)
        {
            return false;
        }
        else
        {
            lots.add(new Lot(nextLotNumber, description));
            nextLotNumber++;
            return true;
        }
    }

    /**
     * Show the full list of lots in this auction.
     *
     * Print a blank line first, to make our output look nicer. 
     * If there are no lots, print a message indicating this 
     * (You need to update the code given below.)
     */
    public void showLots()
    {
        boolean nolots = true;
        System.out.println();
        for(Lot lot : lots) {
            System.out.println(lot.toString());
            nolots = false;
        }
        if(nolots)
        {
        System.out.println("There are no lots.");
        }
    }
    
    /**
     * Bid for a lot.
     * Do not assume that the lots are stored in numerical order.
     * Prints a message indicating whether the bid is successful or not.
     *   
     * First print a blank line.  
     * Then print whether or not the bid is successful.
     * If the bid is successful, also print the
     * lot number, high bidder's name, and the bid value.
     * If the bid is not successful, also print the lot number 
     * and high bid (but not the high bidder's name).
     * 
     * Returns false if the auction is closed, the lot doesn't
     * exist, the bidder is null, or the bid was not positive
     * and true otherwise (even if the bid was not high enough).
     * (You need to update the return type, documentation, and code.)
     *
     * @param number The lot number being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public boolean bidFor(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
          if(openClose==false || bidder==null || selectedLot == null || (value<0))
          {
            return false;
            }
            else
            {
                System.out.println();   
            
                /**
                   * Following code is reused from oldAuction.java
                     */
                    Bid bid = new Bid(bidder, value);
                    boolean successful = selectedLot.bidFor(bid);
                    if(successful) 
                    {
                        System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
                                }
                    else {
                        // Report which bid is higher.
                        Bid highestBid = selectedLot.getHighestBid();
                        System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
                                }
                                return true;
            }
    }


    /**
     * Return the lot with the given number. 
     * Do not assume that the lots are stored in numerical order.   
     *   
     * Returns null if the lot does not exist.
     * (You need to update the code.)
     *
     * @param lotNumber The number of the lot to return.
     *
     * @return the Lot with the given number
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber))
        {
            for(Lot looplot : lots)
            {
                if(looplot.getNumber() == lotNumber)
                {
                return looplot;
                }
            }
        }
        return null;
        }
    
    /**
     * Closes the auction and prints information on the lots.
     * First print a blank line.  Then for each lot,
     * its number and description are printed.
     * If it did sell, the high bidder and bid value are also printed.  
     * If it didn't sell, that information is printed.
     *
     * Returns false if the auction is already closed, true otherwise.
     * (You need to update the return type, documentation, and code.)
     */
    public boolean close()
    {
        if(openClose)
        {
            openClose = false;
            System.out.println();
            for(Lot lot : lots)
            {
                Bid highestBid = lot.getHighestBid();
        
                System.out.println(lot.toString());
                if(highestBid!= null)
                {
                    System.out.println("Bidder: " + (highestBid.getBidder()).getName());
                }
            }
            return true;
        }
        return openClose;
    }
    
    /**
     * Returns an ArrayList containing all the items that have no bids so far.
     * (or have not sold if the auction has ended).
     * @return an ArrayList of the Lots which currently have no bids
     */
    public ArrayList<Lot> getNoBids()
    {
        ArrayList<Lot> noBids = new ArrayList<Lot>();
        int nextLotNumber2 = 1;
        
        for(Lot lot : lots)
            {
                Bid highestBid = lot.getHighestBid();
        
                if(highestBid== null)
                {
                    noBids.add(new Lot(nextLotNumber2, lot.getDescription()));
                    nextLotNumber2++;
                }
            }
        return noBids;
    }
    
    /**
     * Remove the lot with the given lot number, as long as the lot has
     * no bids, and the auction is open.  
     * You must use an Iterator object to search the list and,
     * if applicable, remove the item.
     * Do not assume that the lots are stored in numerical order.
     *
     * Returns true if successful, false otherwise (auction closed,
     * lot does not exist, or lot has a bid).
     * (You need to update the return type, documentation, and code.)
     *
     * @param number The number of the lot to be removed.
     */
    public boolean removeLot(int number)
    {
        Lot lot = getLot(number);
        Bid highestBid = lot.getHighestBid();
        
        if(lot!=null && highestBid==null && openClose==true)
        {
            for(Lot looplot : lots)
            {
                if(looplot.getNumber() == number)
                {
                return lots.remove(looplot);
                }
            }
            }
        return false;+
    }
          
}
