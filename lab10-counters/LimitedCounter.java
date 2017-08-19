/**
 * Models a counter that counts up and down but stops when it count atempts 
 * to go past the min or max values
 * @author Narrthanan Seevananthan 
 * @version June 2, 2016
 */

public class LimitedCounter extends Counter
{
    
    /**
     * Constructs a new LimitedCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public LimitedCounter()
    {
        super();
    }

    /**
     * Constructs a new LimitedCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     */
    public LimitedCounter(int minCount, int maxCount)
    {
       super(minCount, maxCount);
    }

    /**
     * Increment this counter by 1.
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method doesn't change the state of the counter.
        if (!(isAtMaximum())) {
            super.countUp();
        }
    }
    /**
     * Decrement the counter by 1.
     */
    public void countDown()
    {
    if (!(isAtMinimum())) {
            super.decrementCount();
        }
    }
}
