/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;


public class InquirerTest {
    

private String expectedFirstName = "Joseph";
private String expectedLastName = "Bouillon";
private String expectedPhoneNumber = "+1-123-456-7890";
private Inquirer inquirer ;

/*
testObjectCreation -> means testing Inquirer constructor: 
   - What we need: To verify that an "Inquirer" object is successfully created.
   - Actual result: The name "Joseph Bouillon", the services phone number "+1-123-456-7890", and the provided info is "looking for my family members".
   - Expected Result: The test checks that the Inquirer object is not null, confirming successful object creation.
*/
    @Before
    public void setup(){
        inquirer  = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber);
    }
    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should have made a new Inquirer object",inquirer);
    }

    @Test
    public void testConstructorWithIDArgument(){
        inquirer = new Inquirer(1, expectedFirstName,expectedFirstName,expectedPhoneNumber);

            assertNotNull("Constructor should have made a new Inquirer object", inquirer);
    }

    /*
       testSetAndGetFirstName:
          - Verifies that the setFirstName() and getFirstName() methods work correctly.
          - Expected Result: The inquirer's first name should be updated to "Caroline".
       */
    @Test
    public void testSetAndGetFirstName() {
        inquirer.setFirstName("Caroline");

        assertEquals("inquirer's firstName should be updated to Caroline after using setFirstName", "Caroline", inquirer.getFirstName());
    }

    /*
      testSetAndGetLastName:
         - Verifies that the setLastName() and getLastName() methods work correctly.
         - Expected Result: The inquirer's last name should be updated to "Joan".
      */
    @Test
    public void testSetAndGetLastName() {
        inquirer.setLastName("Joan");

        assertEquals("inquirer's lastName should be updated to Joan after using setLastName", "Joan", inquirer.getLastName());
    }
	
/*
testGetServicesPhoneNum**:
   - What we need: To confirm that "getServicesPhoneNum()" method correctly returns the services phone number.
   - Actual result: "+1-123-456-7890".
   - Expected result: "inquirer.getServicesPhoneNum()" should return "+1-123-456-7890".
*/
    @Test
    public void testGetServicesPhoneNum() {

        assertEquals("getServicesPhoneNum() should return the correct Services Number",expectedPhoneNumber, inquirer.getServicesPhone());
    }
	


}

