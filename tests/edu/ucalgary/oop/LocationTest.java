/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class LocationTest {

    private Location location;
    private DisasterVictim victim;
    private Supply supply;

    @Before
    public void setUp() {
        // Initializing test objects before each test method
        location = new Location("Shelter A", "1234 Shelter Ave");
        victim = new DisasterVictim("John Doe", "2024-01-01");
        supply = new Supply("Water Bottle", 10);
    }

    // Helper method to check if a supply is in the list
    private boolean containsSupply(ArrayList<Supply> supplies, Supply supplyToCheck) {
        return supplies.contains(supplyToCheck);
    }

    /* testConstructor:
      - Checks that the constructor sets the name and address correctly.
      - Expected Result: The name should be "Shelter A" and the address should be "1234 Shelter Ave".
   */
    @Test
    public void testConstructor() {
        assertNotNull("Constructor should create a non-null Location object", location);
        assertEquals("Constructor should set the name correctly", "Shelter A", location.getName());
        assertEquals("Constructor should set the address correctly", "1234 Shelter Ave", location.getAddress());
    }

    /* testSetAndGetName:
         - Checks that the setName() and getName() methods update and return the name of the location.
         - Expected Result: The name should be updated to "Shelter B".
      */
    @Test
    public void testSetAndGetName() {
        String newName = "Shelter B";
        location.setName(newName);
        assertEquals("setName should update the name of the location", newName, location.getName());
    }


    /*testSetAndGetAddress:
          - Checks that the setAddress() and getAddress() methods update and return the address of the location.
          - Expected Result: The address should be changed to "4321 Shelter Blvd".
       */
    @Test
    public void testSetAndGetAddress() {
        String newAddress = "4321 Shelter Blvd";
        location.setAddress(newAddress);
        assertEquals("setAddress should update the address of the location", newAddress, location.getAddress());
    }
     /* testAddOccupant:
          - Verifies that addOccupant() adds a DisasterVictim to the occupants ArrayList and that the location
            of the added victims are set to the location
          - Expected Result: Occupants should contain the added victim.
       */
    @Test
    public void testAddOccupant() {
        location.setOccupants(new ArrayList<DisasterVictim>());

        location.addOccupant(victim,false);
        assertEquals("victim that was added should have their location set to the location they were added to",victim.getLocation(), location);
        assertTrue("addOccupant should add a disaster victim to the occupants list", location.getOccupants().contains(victim));
    }


    /* testRemoveOccupant:
      - Verifies that removeOccupant() correctly removes a disaster victim from the occupants list
      - Expected Result: The occupants list should not contain the removed victim and the victim's location is no longer the location
   */
    @Test
    public void testRemoveOccupant() {
        Location location = new Location ("ABC","123");
        victim.setLocation(location);
        location.addOccupant(victim, false); // Ensure the victim is added first
        location.removeOccupant(victim);
        assertTrue("removeOccupant should remove the disaster victim from the occupants list", location.getOccupants().contains(victim));
        assertEquals("removeOccupant should update the victim's location to null", victim.getLocation(), null);
    }



    /*
       testSetAndGetOccupants:
          - Verifies that setOccupants() updates the occupants.
          - Expected Result: The occupants list should be replaced with the new list.
       */
    @Test
    public void testSetAndGetOccupants() {
        ArrayList<DisasterVictim> newOccupants = new ArrayList<>();
        newOccupants.add(victim);
        location.setOccupants(newOccupants);
        assertTrue("setOccupants should replace the occupants list with the new list of occupants", location.getOccupants().containsAll(newOccupants));
        assertEquals("setOccupants should also change the newly added victim's location to the location", victim.getLocation(), location);
    }
    /*
       testAddSupply:
          - Verifies that addSupply() correctly adds a supply to the supplies list.
          - Expected Result: The supplies list should contain the added supply.
       */
    @Test
    public void testAddSupply() {
        Location location = new Location("123","ABC");
        location.addSupply(supply);
        assertEquals("addSupply should add a supply to the supplies list", location.getSupplies().getFirst().getType(),"Water Bottle");

    }
    /*
    testAddSupplyQuantity:
       - Verifies that the addSupply() method correctly increases the quantity of a supply if it already exists in the supplies list.
       - Expected Result: The quantity of the supply should be increased to 18.
    */

    @Test
    public void testAddSupplyQuantity(){
        ArrayList<Supply> supplies = new ArrayList<>();
        supplies.add(supply);
        location.setSupplies(supplies);
        Supply newSupply = new Supply("Water Bottle", 8);
        location.addSupply(newSupply);
        location.addSupply(newSupply);
        location.addSupply(newSupply);
        assertEquals("addSupply should increase the quantity of water bottle to 34", 34,
                location.getSupplies().getFirst().getQuantity());
    }


    /*
    testRemoveSupply:
       - Verifies that the removeSupply() method correctly removes a supply from the supplies list.
       - Expected Result: The supplies list should not contain the removed supply.
    */
    @Test
    public void testRemoveSupply() {
        location.setSupplies(new ArrayList<Supply>());
        location.addSupply(supply); // Ensure the supply is added first
        location.removeSupply(supply);
        assertFalse("removeSupply should remove the supply from the supplies list", containsSupply(location.getSupplies(), supply));


    }


    /*
 testRemoveSupplyQuantity:
    - Verifies that removeSupply() decreases the quantity of a supply.
    - Expected Result: The quantity of the supply should be decreased to 2.
 */
    @Test
    public void testRemoveSupplyQuantity() {
        ArrayList<Supply> supplies = new ArrayList<>();
        supply.setQuantity(10); // Ensure supply quantity is 10 initially
        supplies.add(supply);
        location.setSupplies(supplies);
        Supply supplyToRemove = new Supply("Water Bottle", 4);
        location.removeSupply(supplyToRemove);
        location.removeSupply(supplyToRemove);
        assertEquals("removeSupply should decrease the quantity of water bottle to 2 since 8 'Water Bottles' are removed", 2,
                location.getSupplies().getFirst().getQuantity());
    }


    /*
   testSetAndGetSupplies:
      - Verifies that setSupplies() correctly updates the supplies list with the new list and that getSupplies() returns this list
      - Expected Result: The supplies list should be replaced with the new list.
   */
    @Test
    public void testSetAndGetSupplies() {
        ArrayList<Supply> newSupplies = new ArrayList<>();
        newSupplies.add(supply);
        location.setSupplies(newSupplies);
        assertTrue("setSupplies should replace the supplies list with the new list", containsSupply(location.getSupplies(), supply));
    }

    /*
   testSupplyAllocationToVictim:
      - Verifies that allocateSupplyToVictim() method correctly allocates a supply to a victim.
      - Expected Result: The quantity of the allocated supply should be deducted from the location's supplies, and the supply should be added to the victim's personalBelongings.
   */
    @Test
    public void testSupplyAllocationToVictim(){
        victim.setPersonalBelongings(new ArrayList<Supply>());
        ArrayList<Supply> supplies = new ArrayList<>();
        Supply supplyToAllocate = new Supply("Water Bottle", 2);
        supply.setQuantity(10); // Ensure supply quantity is 10 initially
        supplies.add(supply);
        location.setSupplies(supplies);

        location.setOccupants(new ArrayList<DisasterVictim>());
        location.addOccupant(victim,false);

        location.allocateSupplyToVictim(victim, supplyToAllocate);
        assertEquals("allocateSupplyToVictim should remove 2 quantities from the supply", 8, location.getSupplies().getFirst().getQuantity());
        assertEquals("allocateSupplyToVictim should add supply to victim's personalBelongings", victim.getPersonalBelongings().getFirst().getType(),"Water Bottle");
        assertEquals("victim should have 2 water bottles in their personalBelongings", victim.getPersonalBelongings().getFirst().getQuantity(),2);
    }
}


