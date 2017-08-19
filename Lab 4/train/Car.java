/**
 * car models a car with seats available for booking.
 *
 * @author Lynn Marshall, SCE
 * @version 1.1 September 23, 2012
 * 
 * @author Narrthanan Seevananthan
 * @version 1.2 May 12,2016
 */
public class Car
{
    /** This car's identifier. */
    private int id;
   
    /**
     * true == this car is a business-class car,
     * false == this car is an economy-class car.
     */
    private boolean businessClass;
    
    /** The cost of a business class seat, in dollars. */
    public static final double BUSINESS_SEAT_COST = 125.0;
    
    /** The cost of an economy class seat, in dollars. */
    public static final double ECONOMY_SEAT_COST = 50.0;    
  
    /** The number of seats in a business class car. */
    public static final int BUSINESS_SEATS = 30;   
    
    /** The number of seats in an economy class car. */
    public static final int ECONOMY_SEATS = 40;   
   
    /** The list of this car's seats. */
    private Seat[] seats;
   
    /**
     * Constructs a new Car object with the specified id.
     * If parameter isBusinessClass is true, the car is a business-class
     * car. If parameter isBusinessClass is false, the car is an
     * economy-class car.
     * 
     * @param carID the ID number for the car.
     * @param isBuisinessClass The boolean variable containing true if the car is of buisiness class.
     */
    public Car(int carId, boolean isBusinessClass)
    {
        id=carId;
        businessClass= isBusinessClass;
        int TotalSeats;
        double Seatcost;
        if(businessClass)
        {
            TotalSeats = BUSINESS_SEATS;
            Seatcost = BUSINESS_SEAT_COST;
        }
        else
        {
            TotalSeats = ECONOMY_SEATS; 
            Seatcost = ECONOMY_SEAT_COST;
        }
        seats = new Seat[TotalSeats]; 
        
        for(int i=0;i<TotalSeats;i++)
                {
                seats[i] = new Seat(i+1,Seatcost);
                }
    }

    /**
     * Returns this car's list of seats. This method is intended for 
     * testing purposes only, and should not be called by other objects,
     * as it may be removed from the final version of this class.
     * 
     * @return The seats in this car, an array of Seat objects.
     */
    public Seat[] seats()
    {
        return seats;
    }
 
    /** 
     * Returns true if this is a business-class car,
     * false if this is an economy-class car.
     *  
     * @return businessClass if the car is a buisness car it returns true otherwise it returns false
     */
    public boolean isBusinessClass()
    {
            return businessClass;
    }
 
    /**
     * Returns the id of this car.
     * @return id the id number of the car
     */
    public int id()
    {
        return id;
    }
  
    /**
     * This method is called when the specified seat has been booked,
     * to print a ticket for that seat.
     * 
     * @param seatNo The integer identifier of the seat.
     */
    private void printTicket(int seatNo)
    {
        System.out.println("Car ID: " + id);
        System.out.println("Seat number: " + seatNo);
        System.out.println("Price: ");
        if (businessClass) {
            System.out.println(BUSINESS_SEAT_COST);
        } else {
            System.out.println(ECONOMY_SEAT_COST);
        }
    }   
 
    /**
     * Attempts to book a seat. If successful, this method prints a 
     * ticket and returns true.
     * If no seats are available, this method returns false.
     */
    public boolean bookNextSeat()
    {
        // After booking an available seat, print the ticket by calling
        // private method printTicket(); e.g.,
        // printTicket(seats[i].number());
        
        if(businessClass)
        {
        for(int i=0;i<BUSINESS_SEATS;i++)
                {
                if(!(seats[i].isBooked()))
                    {    
                    seats[i].book();
                    printTicket(i+1);
                    return true;
                    }
               }
            }
            else
            {
            for(int i=0;i<ECONOMY_SEATS;i++)
                {
                if(!(seats[i].isBooked()))
                    {    
                    seats[i].book();
                    printTicket(i+1);
                    return true;
                    }
               }
            }
            
        return false;
    }

    /** 
     * Cancels the booking for the specified seat, which must be between
     * 1 and the maximum number of seats in the car.
     * If the seat number is valid and if the seat has been reserved, this
     * method cancels the booking for that seat and returns true. 
     * If the seat number is not valid, this method returns false. 
     * If the seat number is valid, but the seat has not been reserved, 
     * this method returns false.
     */
    public boolean cancelSeat(int seatNo)
    {
        if(businessClass)
        {
            if(1<=seatNo && seatNo<=BUSINESS_SEATS)
            {
                    return seats[seatNo-1].cancelBooking();  
            }
        }
        else 
        {
            if(1<=seatNo && seatNo<=ECONOMY_SEATS)
            {
                    return seats[seatNo-1].cancelBooking();
            }
        }
        return false;
    }    
}
