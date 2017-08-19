import java.util.ArrayList;

/**
 * The test class TrainTest.
 *
 * @author  Lynn Marshall
 * @version May 2015
 * 
 * @author Narrthanan Seevananthan
 * @version 31 May 2016
 */
public class TrainTest extends junit.framework.TestCase
{
    private Train aTrain;
    private Car car1, car2, car3, car4;
    /**
     * Default constructor for test class TrainTest
     */
    public TrainTest()
    {
        //create new train class
        aTrain = new Train();
        
        //create new car objects
        car1 = new Car(1250, true);
        car2 = new Car(1300, false);
        car3 = new Car(1740, false);
        car4 = new Car(1790, true);
        
        //add the carr objects to the train
        aTrain.addCar(car1);
        aTrain.addCar(car2);
        aTrain.addCar(car3);
        aTrain.addCar(car4);
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
     * Checks if new trains are truely empty
     * 
     */
    public void testCreateEmptyTrain()
    {
        Train emptyTrain = new Train();
        
        /* Verify that a new train has no cars. */
        assertEquals(0, emptyTrain.cars().size());
    }
    
    /**
     * Test to check if new cars are added correctly.
     * 
     */
    public void testAddCar()
    {
        ArrayList<Car> cars = aTrain.cars();
        assertEquals(4, cars.size());
        
        
        /* Verify that each car added to the train was placed at
         * the end of the list.
         */
        
        /* Important - assertSame() does not compare the Car objects 
         * referred to by car1 and get(0) to detemine if they are equal
         * (have the same state). It verifies that car1 an get(0) refer to
         * the same object; i.e., that the Car (reference) retrieved by get(0)
         * is the first first that was added to the ArrayList.
         */
        assertSame(car1, cars.get(0));
        assertSame(car2, cars.get(1));
        assertSame(car3, cars.get(2));    
        assertSame(car4, cars.get(3));      
    }
        
    /**
     * Test to check if issueTicket() truely books the next available ticket in the matching car class
     */
    public void testIssueTicket()
    {
        /* Book all the seats in the first business-class car. */
        for (int i = 0; i <(Car.BUSINESS_SEATS); i++) {
            assertTrue(aTrain.issueTicket(true));
        }
       
        ArrayList<Car> cars = aTrain.cars();
        /**
         * check if all the seats in the first car are booked
         * and all the seats in every other car is unbooked
         */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertFalse(cars.get(3).seats()[i].isBooked());
        }
        
        /* Book all the seats in the second business-class car. */
        for (int i = 0; i <(Car.BUSINESS_SEATS); i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Attempt to book one more business-class seat, even
         * though they are all booked.
         */
        assertFalse(aTrain.issueTicket(true)); 
        
        cars = aTrain.cars();
        
        /**
         * check if all the seats in the first and second car are booked
         * and all the seats in every other car is unbooked
         */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        cars = aTrain.cars();
        
        /**
         * check if all the seats in the first, second and fourth car are booked
         * and all the seats in every other car is unbooked
         */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }  
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        /**
         * check if all the seats in the first, second, third and fourth car are booked
         * and all the seats in every other car is unbooked
         */
        /* Book all the seats in the second economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* check that all seats are now booked */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(2).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        /* Try to book another business class seat (fails)*/
        assertFalse(aTrain.issueTicket(true));
        /* Try to book another economy class seat (fails)*/
        assertFalse(aTrain.issueTicket(false));
    }
    
    /**
     * Test if cance ticket truely works
     */
    public void testCancelTicket()
    {
        /* Book all the seats in the business-class car. */
        for (int i = 0; i <(1.5*Car.BUSINESS_SEATS); i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        ArrayList<Car> cars = aTrain.cars();
        
        // Cancel ticket in a seat that has been booked
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked());        
        
        /* Cancel ticket in a non-existent car. */
        assertFalse(aTrain.cancelTicket(1500, 7));
        
        /* Cancel ticket in a non-existent seat. */
        assertFalse(aTrain.cancelTicket(1300, 54));
        
        /* Cancel ticket for a seat that hasn't been booked. */
        assertFalse(aTrain.cancelTicket(1740, 21));
        assertFalse(cars.get(2).seats()[20].isBooked());        
        
        /* Cancel ticket for a seat that has been booked. */
        assertTrue(aTrain.cancelTicket(1790, 11));
        assertFalse(cars.get(3).seats()[10].isBooked());
        
        /* Cancel ticket for a seat that hasn't been booked. */
        assertFalse(aTrain.cancelTicket(1790, 21));
        assertFalse(cars.get(3).seats()[20].isBooked());
        
        /* Attempt to cancel the same ticket twice. */
        assertTrue(aTrain.cancelTicket(1250, 11));
        assertFalse(cars.get(0).seats()[10].isBooked());
        
        assertFalse(aTrain.cancelTicket(1250, 11));   
        assertFalse(cars.get(0).seats()[10].isBooked());         
    }
    
    /**
     * Test if check for isBooked() truely works
     */
    public void testBookCancelTicket()
    {
        //1250 first train buisness
        //1300 second train economy
        //1740 third train economy
        //1790 fourth train buisness
        
        /* Book all the seats in the first business-class car and half of the second one. */
        for (int i = 0; i <(1.5*Car.BUSINESS_SEATS); i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        
        
        // cancel any three seats in the first economy class car
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertTrue(aTrain.cancelTicket(1300, 5));
        assertTrue(aTrain.cancelTicket(1300, 6));
        
        ArrayList<Car> cars = aTrain.cars();
        
        //Check if seats were cancelled
        assertFalse(cars.get(1).seats()[3].isBooked());
        assertFalse(cars.get(1).seats()[4].isBooked());
        assertFalse(cars.get(1).seats()[5].isBooked());
        
        //book four tickets in economy class
        for (int i = 0; i <4; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        cars = aTrain.cars();
        
        //check if the seats were really booked
        assertTrue(cars.get(1).seats()[3].isBooked());
        assertTrue(cars.get(1).seats()[4].isBooked());
        assertTrue(cars.get(1).seats()[5].isBooked());
        assertTrue(cars.get(2).seats()[0].isBooked());
        
        //Cancel 3 tickets in buisness class (First Train(1) Fourth Train (2) )
        assertTrue(aTrain.cancelTicket(1250, 5));
        assertTrue(aTrain.cancelTicket(1790, 4));
        assertTrue(aTrain.cancelTicket(1790, 5));
        
        cars = aTrain.cars();
        
        //Check if seats were cancelled
        assertFalse(cars.get(0).seats()[4].isBooked());
        assertFalse(cars.get(3).seats()[3].isBooked());
        assertFalse(cars.get(3).seats()[4].isBooked());
        
        // Re-book the canceled tickets
        for (int i = 0; i <4; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        cars = aTrain.cars();
        
        //check if the tickets were really booked
        assertTrue(cars.get(0).seats()[4].isBooked());
        assertTrue(cars.get(3).seats()[3].isBooked());
        assertTrue(cars.get(3).seats()[4].isBooked());
        assertTrue(cars.get(3).seats()[15].isBooked());
    }
}
