/**
 * The test class CarTest.
 *
 * @author  Lynn Marshall, SCE
 * @version 1.2 May 1st, 2015
 * 
 * @author Narrthanan Seevananthan
 * @version 31 May 2016
 */
public class CarTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class CarTest
     */
    public CarTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    
    /**
     * Check if buisness cars are created correctly
     */
    public void testCreateBusinessCar()
    {
        Car aCar = new Car(1385, true);
        Seat[] seats = aCar.seats();
        
        /* Verify that the car has the right number of seats. */
        assertEquals(Car.BUSINESS_SEATS, seats.length);
        
        /* Verify that each seat has the correct number and price. */
        for (int i = 0; i < seats.length; i++) {
            assertEquals(i+1, seats[i].number());
            assertEquals(Car.BUSINESS_SEAT_COST, seats[i].price());
        }
    }
    
    /**
     * Check if economy cars are created correctly
     */
    public void testCreateEconomyCar()
    {
        Car aCar = new Car(1400, false);
        Seat[] seats = aCar.seats();
        
        /* Verify that the car has the right number of seats. */        
        assertEquals(Car.ECONOMY_SEATS, seats.length);
        
        /* Verify that each seat has the correct number and price. */       
        for (int i = 0; i < seats.length; i++) {
            assertEquals(i+1, seats[i].number());
            assertEquals(Car.ECONOMY_SEAT_COST, seats[i].price());
        }
    }    
    
    /**
     * Check if car's ID are created correctly
     */
    public void testID()
    {
         Car aCar;
         aCar= new Car(1385, true);
         assertEquals(1385, aCar.id());
         aCar = new Car(1400, false);
         assertEquals(1400, aCar.id());
    }
    
    /**
     * Check if isBuisnessClass() works correctly
     */
    public void testIsBusinessClass()
    {
         Car aCar;
         aCar = new Car(1385, true);
         assertTrue(aCar.isBusinessClass());
         aCar = new Car(1400, false);
         assertFalse(aCar.isBusinessClass()); 
    }
    
    /**
     * Test for bookNextSeat() works correctly
     */
    public void testBookNextSeat()
    {
        Car aCar, bCar;
        aCar = new Car(1234, true);
        bCar = new Car(1235, false);
        
        Seat[] seats = aCar.seats();
        Seat[] seatsb = bCar.seats();
        
        /* Verify that no seats are booked. */
        for (int i = 0; i < seats.length; i++) {
            assertFalse(seats[i].isBooked());
        }
        
        for (int i = 0; i < seatsb.length; i++) {
            assertFalse(seatsb[i].isBooked());
        } 
        
        /* Verify that the seats are booked consecutively,
         * starting with Seat #1.
         */
        for (int i = 0; i < seats.length; i++) {
            seats = aCar.seats();
            assertFalse(seats[i].isBooked()); // not booked
            assertTrue(aCar.bookNextSeat()); // book it
            assertTrue(seats[i].isBooked()); // now booked
            if (i!=seats.length-1) {
                assertFalse(seats[i+1].isBooked()); // but next isn't
            }
        }
        
        for (int i = 0; i < seatsb.length; i++) {
            seatsb = bCar.seats();
            assertFalse(seatsb[i].isBooked()); // not booked
            assertTrue(bCar.bookNextSeat()); // book it
            assertTrue(seatsb[i].isBooked()); // now booked
            if (i!=seatsb.length-1) {
                assertFalse(seatsb[i+1].isBooked()); // but next isn't
            }
        }
        
        /* Try to book a seat now that all the seats have been booked. */
        assertFalse(aCar.bookNextSeat());
        assertFalse(bCar.bookNextSeat());
    }
    
    /**
     * Test for CancelSeat() works correctly
     */
    public void testCancelSeat()
    {
        Car aCar, bCar;
        aCar = new Car(1234, true);
        bCar = new Car(1235, false);
        
        Seat[] seats = aCar.seats();
        Seat[] seatsb = bCar.seats();
        
        /* Cancel seat 0. cancelSeat() should return false, as there
         * is no seat 0.
         */
        assertFalse(aCar.cancelSeat(0));
        assertFalse(bCar.cancelSeat(0));
        
        /* Try cancelling a seat whose number is one higher than 
         * the highest valid seat number (seats.length - 1). 
         * cancelSeat() should return false.
         */
        assertFalse(aCar.cancelSeat(seats.length));
        assertFalse(bCar.cancelSeat(seatsb.length));
        
        /*
         *Try cancelling seat number 50 in both cars even though this number doesn't exist 
         */
        assertFalse(aCar.cancelSeat(50));
        assertFalse(bCar.cancelSeat(50));
        
        /* Try cancelling all the seats in the car, even though 
         * they haven't been booked. cancelSeat() should 
         * return false.
         */
        for (int i = 0; i < seats.length; i++) {
            assertFalse(aCar.cancelSeat(i+1));
        }
        
        for (int i = 0; i < seatsb.length; i++) {
            assertFalse(bCar.cancelSeat(i+1));
        }
        
        /* Book all the seats */
        for (int i = 0; i < seats.length; i++) {
            aCar.bookNextSeat();
        }  
        
        for (int i = 0; i < seatsb.length; i++) {
            bCar.bookNextSeat();
        }  
        /* Try cancelling all the seats in the car.
         */
        for (int i = 0; i < seats.length; i++) {
            assertTrue(aCar.cancelSeat(i+1));
        } 
        
        for (int i = 0; i < seatsb.length; i++) {
            assertTrue(bCar.cancelSeat(i+1));
        } 
        /* In case seat numbers are off, try some more tests.
         */
        
        // book 2 seats
        assertTrue(aCar.bookNextSeat());
        assertTrue(aCar.bookNextSeat());
        // try to cancel the 3rd
        assertFalse(aCar.cancelSeat(3));
        // cancel the 1st and 2nd (were both booked)
        assertTrue(aCar.cancelSeat(1));
        assertTrue(aCar.cancelSeat(2));
        
        // book 2 seats
        assertTrue(bCar.bookNextSeat());
        assertTrue(bCar.bookNextSeat());
        // try to cancel the 3rd
        assertFalse(bCar.cancelSeat(3));
        // cancel the 1st and 2nd (were both booked)
        assertTrue(bCar.cancelSeat(1));
        assertTrue(bCar.cancelSeat(2));
       
    }      
}
