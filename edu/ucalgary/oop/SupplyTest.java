/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class SupplyTest {
    String expectedType = "Blanket";
	int expectedQuantity = 5;
    int invalidQuantity = -1;

    private Supply supply = new Supply(expectedType, expectedQuantity);




    /* testConstructorWithValidQuantity:
       - Verifies that a "Supply" object is created with a valid quantity.
       - Expected Result: The test checks that the Supply object is not null.
    */

    @Test
    public void testConstructorWithValidQuantity() {
        assertNotNull(supply);
    }


    /*
  testConstructorWithInvalidQuantity:
     - Verifies that an IllegalArgumentException is thrown when creating a Supply object with an invalid quantity (-1)
     - Expected Result: Expecting IllegalArgumentException
  */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidQuantity(){
        Supply invalidSupply = new Supply(expectedType, invalidQuantity);
        // Expecting IllegalArgumentException due to invalid quantity.
    }


    /*testGetType:
   - What we need: To ensure the "getType()" method returns the actual Supply type.
   - Actual result: "Blanket".
   - Expected result: "supply.getType()" should return "Blanket".
    */

    @Test
    public void testGetType() {
        assertEquals("getType should return the correct type", expectedType, supply.getType());
    }

    /*testSetType:
   - What we need: To ensure the "setType()" method correctly updates the Supply type.
   - Actual/current result: "Blanket".
   - Expected result: "Food".
    */
    @Test
    public void testSetType() {
        supply.setType("Food");
        assertEquals("setType should update the type", "Food", supply.getType());
    }



    /* testGetQuantity:
       - Ensures the "getQuantity()" method correctly returns the actual quantity of the Supply.
       - Expected result: 5.
    */
    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity should return the correct quantity", expectedQuantity, supply.getQuantity());
    }




    /* testSetQuantityWithValidQuantity:
           - Ensures the "setQuantity" method correctly updates the Supply quantity with a valid quantity.
           - Expected result: 20.
        */
    @Test
    public void testSetQuantityWithValidQuantity() {
        supply.setQuantity(20);
        assertEquals("setQuantity should update the quantity", 20, supply.getQuantity());
    }


    /* testSetQuantityWithInvalidQuantity:
       - Checks that an IllegalArgumentException is thrown when setting an invalid quantity (-1)
       - Expected Result: Expecting IllegalArgumentException
    */
    @Test (expected = IllegalArgumentException.class)
    public void testSetQuantityWithInvalidQuantity(){
        supply.setQuantity(invalidQuantity);
        // Expecting IllegalArgumentException due to invalid quantity.

    }
}
