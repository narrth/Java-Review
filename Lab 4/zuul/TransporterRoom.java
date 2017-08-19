import java.util.*; 

/**
 * Returns a random room.
 * 
 * @author Narrthanan Seevananthan
 * @version June 4, 2016
 */

public class TransporterRoom extends Room
{
    public TransporterRoom (String description)
    {
    super(description);
    }

    /**
     * Returns a random room, independent of the direction parameter.
     *
     * @param direction Ignored.
     * @return A randomly selected room.
     */
    public Room getExit(String direction)
    {
        return findRandomRoom();
    }

    /**
     * Choose a random room.
     *
     * @return The room we end up in upon leaving this one.
     */
    private Room findRandomRoom()
    {
        Random ran = new Random(); 
        int i = ran.nextInt(6) + 1;
        return getRoom(i);
        
    }
}