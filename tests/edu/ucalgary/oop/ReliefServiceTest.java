/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReliefServiceTest {
    private ReliefService reliefService;
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;
    private Log log;
    private String expectedInfoProvided = "Looking for family member";

    @Before
    public void setUp() {
        lastKnownLocation = new Location("ABC", "123 Avenue");
        missingPerson = new DisasterVictim("Monique", "2024-03-06");
        inquirer = new Inquirer("John", "Alex", "1234567890");
        reliefService = new ReliefService(inquirer, expectedInfoProvided);
        log = new Log("2024-03-06", "Looking for wife that was in the area of the disaster", missingPerson, lastKnownLocation);

    }

/*
   testObjectCreation:
      - Checks that a ReliefService object is created from setup().
      - Expected Result: The object should not be null.
*/

    @Test
    public void testObjectCreation() {
        assertNotNull("ReliefService object should not be null", reliefService);
    }

    /*
   testGetAndSetInquirer:
      - Verifies that the setInquirer() and getInquirer() methods work correctly.
      - Expected Result: The Inquirer should be updated to the new Inquirer object.
*/
    @Test
    public void testGetAndSetInquirer() {
        Inquirer newInquirer = new Inquirer("Ronn","Lumpia", "4034034033");
        reliefService.setInquirer(newInquirer);
        assertEquals("Inquirer should be updated to the newInquirer", newInquirer, reliefService.getInquirer());
    }


    /*
       testGetAndSetInfoProvided:
          - Verifies that the setInfoProvided() and getInfoProvided() methods work correctly.
          - Expected Result: The information provided should match the one set in setup.
    */
    @Test
    public void testGetAndSetInfoProvided() {
        reliefService.setInfoProvided("Looking for my wife");
        assertEquals("Info provided should match the one set in setup", "Looking for my wife", reliefService.getInfoProvided());
    }


/*
   testAddLog:
      - Verifies that addLog() adds a log to the ReliefService object's logs.
      - Expected Result: The logs of the ReliefService object should contain the added log.
*/

    @Test
    public void testAddLog() {
        ReliefService newReliefService = new ReliefService(inquirer, expectedInfoProvided);

        newReliefService.addLog(log);

        assertTrue("ArrayList<Log> of ReliefService object should contain the added log", newReliefService.getLogs().contains(log));


    }
    /*
       testRemoveLog:
          - Verifies that removeLog() correctly removes a log from the ReliefService object's logs.
          - Expected Result: The ArrayList<Log> of ReliefService object should not contain any logs after removal.
    */
    @Test
    public void testRemoveLog(){
        ReliefService newReliefService = new ReliefService(inquirer, expectedInfoProvided);

        newReliefService.addLog(log);
        newReliefService.removeLog(log);

        assertEquals("ArrayList<Log> of ReliefService object should not contain any logs after removal", newReliefService.getLogs().size(), 0);



    }
}
