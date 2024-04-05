package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;



import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class LogTest {


    private String validDate = "2024-02-10";
    private String invalidDate = "2024/02/10";

    private String logDetails = "Looking for my wife";
    private DisasterVictim missingPerson = new DisasterVictim("Monique", validDate);
    private Log logWithMissingPerson;
    private Log log;
    private Location lastKnownLocation = new Location("University of Calgary", "123 ABC Drive");

    @Before
    public void setUp(){
        logWithMissingPerson = new Log(validDate, logDetails, missingPerson, lastKnownLocation);
        log = new Log(validDate, logDetails);
    }
        /*
        testConstructorWithValidDate:
            - Checks that the constructor instantiates a Log object when a valid date is entered.
            - Expected Result: The Log object from setUp() should not be null.
            */
    @Test
    public void testConstructorWithValidDate(){

        assertNotNull("Log constructor should instantiate a new Log object", log);
        assertNotNull("Log constructor should instantiate a new Log object", logWithMissingPerson);

    }


    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithMissingPersonWithInvalidDate(){
        Log newLog = new Log(invalidDate, logDetails, missingPerson, lastKnownLocation);
        // Expecting IllegalArgumentException due to invalid date format.

    }
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithoutMissingPersonWithInvalidDateFormat(){
        Log newLog = new Log(invalidDate, logDetails);
        // Expecting IllegalArgumentException due to invalid date format.

    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithoutMissingPersonWithInvalidDate(){
        Log newLog = new Log("20200-11-22", logDetails);
        // Expecting IllegalArgumentException due to invalid date.

    }


     /*
    testSetDateWithInvalidDate:
        - Verifies that an IllegalArgumentException is thrown when trying to set a date with an invalid date format
        - Expected Result: IllegalArgumentException should be thrown.
    */

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateWithInvalidDateFormat() {
        log.setDate(invalidDate); // This should throw IllegalArgumentException due to invalid format
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateWithInvalidDate() {
        log.setDate("20000-11-11"); // This should throw IllegalArgumentException due to invalid format
    }

    @Test
    public void testSetDateWithValidDateFormat() {
        log.setDate("2024-02-11");

        assertEquals("date of log should be updated to the new date", "2024-02-11", log.getDate());
    }

    /*
        testSetAndGetMissingPerson:
            - Verifies that setMissingPerson and getMissingPerson methods work correctly.
            - Expected Result: The missing person should be updated to the new DisasterVictim object.
        */
    @Test
    public void testSetAndGetMissingPerson() {
        DisasterVictim newMissingPerson = new DisasterVictim("Yuni","2024-02-10");
        logWithMissingPerson.setMissingPerson(newMissingPerson);
        assertEquals("Missing person should be updated to the DisasterVictim object", newMissingPerson, logWithMissingPerson.getMissingPerson());
    }


   /*
    testSetAndGetLastKnownLocation:
        - Verifies that setLastKnownLocation and getLastKnownLocation methods work correctly.
        - Expected Result: The last known location should be updated to the new location.
    */
    @Test
    public void testSetAndGetLastKnownLocation() {
        Location newLocation = new Location("School", "XYZ Avenue");
        logWithMissingPerson.setLastKnownLocation(newLocation);
        assertEquals("Last known location should be updated to the new location", newLocation, logWithMissingPerson.getLastKnownLocation());
    }


    /*
   testSetAndGetLogDetails:
       - Verifies that setLogDetails and getLogDetails methods work correctly.
       - Expected Result: Log details should be updated with the new String.
   */
    @Test
    public void testSetAndGetLogDetails(){
        log.setLogDetails("Also looking for children");
        assertEquals("Log details should be updated with the new String", log.getLogDetails(),"Also looking for children");
    }


}
