/**
 * A Heater models a simple space-heater. The operations provided by a Heater
 * object are:
 * 1. Increase and decrease the temperature setting by a set amount.
 * 2. Return the current temperature setting.
 * 3. Change the set amount by which the temperature is increased and lowered.
 * 
 * @author L.S. Marshall, SCE, Carleton University
 * (incomplete implementation for SYSC 2004 Lab 2)
 * @author Narrthanan Seevananthan
 * @version 1.03 April 5 2016
 */
public class Heater
{
    /** The temperature setting that the heater should maintain. */
    private int temperature;
    
    /** The temperature setting for a newly created heater. */
    private static final int INITIAL_TEMPERATURE = 15;
    
    /** 
     * The amount by which the temperature setting is raised/lowered when
     * warmer() and cooler() are invoked.
     */
     private int increment;
    
    /** 
     * The default amount by which the temperature setting is 
     * increased when warmer() is invoked and decreased when cooler()
     * is invoked.
     */
    private static final int DEFAULT_INCREMENT = 5;
    
   /**
   * min and max are fields that store the minimum and maximum temperature settings for the heater
   **/
   private int min;
   private int max;
   
    /**
     * Constructs a new Heater with an initial temperature setting of 15
     * degrees, and which increments and decrements the temperature
     * setting in increments of 5 degrees.
     * also sets the maximum and minimum temperature to 100 and 0 degrees respectively
     */
    public Heater()
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        max = 100;
        min = 0;
    }
 
    /**
     * Constructs a new Heater with an initial temperature setting of 15
     * degrees, and which increments and decrements the temperature
     * setting in increments of 5 degrees.
     * also sets the maximum and minimum temperature to user defined values.
     */   
    public Heater(int minTemp, int maxTemp)
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        min = minTemp;
        max = maxTemp;
    }

    /**
     * Returns the heater's current temperature setting.
     */    
    public int temperature()
    {
        return temperature;
    }
    
    /**
     * Increases the heater's temperature by the value stored in the field increment.
     */
    public void warmer()
    {
        if(this.increment>0 && ((max - temperature)>=increment))
        {
            temperature = temperature + increment;   
        }
    }

    /**
     * decreases the heater's temperature by the value stored in the field increment.
     */    
    public void cooler()
    { 
        if(this.increment>0 && ((this.temperature - this.min)>=this.increment))
        {
         temperature = temperature - increment;   
        }
    }
    
    
    /**
     * Write an appropriate comment here.
     */    
    public void setIncrement(int newIncrement)
    { 
        if (newIncrement>0)
        {
        increment = newIncrement;
    }
    }
}
