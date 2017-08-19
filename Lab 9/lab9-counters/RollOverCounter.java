
/**
 * Mimics a counter that rolls over to minimum at its max value. Inherits Counter.class
 * @author Narrthanan Seevananthan  
 * @version June 2, 2016
 * 
 */
public class RollOverCounter extends Counter
{

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public RollOverCounter()
    {
        super();
    }

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     */
    public RollOverCounter(int minCount, int maxCount)
    {
        super(minCount, maxCount);
    }

    /**
     * Increment this counter by 1. Uses methods from the super class Counter
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method rolls the counter over to its minimum value.
        if (isAtMaximum()) {
            reset();
        } else {
            incrementCount();
        }
    }
}
