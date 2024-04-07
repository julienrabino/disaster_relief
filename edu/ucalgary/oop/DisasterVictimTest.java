/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import java.util.Arrays;


public class DisasterVictimTest {
    private DisasterVictim victim;
    private HashSet<FamilyRelation> familyRelations;
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String validDate = "2024-01-15";
    private String invalidDate = "2024/12/20";
    private String expectedComments = "Needs medical attention and speaks 2 languages";
    private File file;


    /*
      setUp:
         - Setting up initial conditions for each test case.
         - Creating a test file for gender options if it doesn't exist and setting up gender options.
      */

    @Before
    public void setUp() {

        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);

        //setting up genderOptions
        file = new File("genderOptionsTest.txt");
        String genders = "boy\ngender queer\ngirl";
        FileWriter outputStream;

        try {
                outputStream = new FileWriter(file);
                outputStream.write(genders);
                outputStream.close();
        } catch (IOException e) {
                System.err.println("could not write to file: " + e.getMessage());
                System.exit(1);

            }
        DisasterVictim.readGenderOptions("genderOptionsTest.txt");

    }




    /*
         testConstructorWithValidEntryDate:
            - Ensures constructor creates an object with a valid entry date.
            - Expected Result: Constructor should create an object with the expected entry date.
         */
    @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }


    @Test public void testConstructorWithIDArgument(){

        DisasterVictim victim = new DisasterVictim(1, "Lola","2024-03-03");
        assertNotNull("Constructor should successfully create an instance with the given id", victim);
    }
    /*

     testConstructorWithInvalidEntryDateFormat:
        - Expected Result: IllegalArgumentException should be thrown due to the invalid date format.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024"; // Incorrect format according to your specifications
        new DisasterVictim("Freda", invalidEntryDate);
        // Expecting IllegalArgumentException due to invalid date format
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDate() {
        String invalidEntryDate = "18000-01-4";
        new DisasterVictim("Freda", invalidEntryDate);
        // Expecting IllegalArgumentException due to invalid date
    }
    /*
      testSetAndGetFirstName:
         - setFirstName() method updates the victim's firstName
         - Expected Result: The first name of the victim should be successfully updated. Checked by getFirstName.
      */
	@Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, victim.getFirstName());
    }
    /*
          testSetAndGetLastName:
             - setLastName() method updates the victim's lastName
             - Expected Result: The last name of the victim should be successfully updated. Checked by getLastName.
          */
    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update the victim's last name and getLastName should return the new last name", newLastName, victim.getLastName());
    }
    /*
         testGetComments:
            - getComments() method returns the victim's comments
            - Expected Result: The comments returned should match the expected comments.
         */
    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }


    /*
     testSetComments:
        - setComments() method updates the victim's comments
        - Expected Result: The comments should be successfully updated and returned by getComments().
     */
    @Test
    public void testSetComments() {
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments of the victim correctly", newComments, victim.getComments());
    }
    /*
          testGetAssignedSocialID:
             - getAssignedSocialID() method returns the assigned social ID for the victim
             - Expected Result: The assigned social ID should match the expected value.
          */
    @Test
    public void testGetAssignedSocialID() {
        // The next victim should have an ID one higher than the previous victim
        // Tests can be run in any order so two victims will be created
        DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId, actualVictim.getAssignedSocialID());
    }


    /*
    testGetEntryDate:
       - getEntryDate() method returns the entry date of the victim
       - Expected Result: The returned entry date should match the expected entry date.
    */
    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
    }




/*
      testAddFamilyConnection:
         - addFamilyConnection() adds a family connection between two victims.
         - Expected Result: Both victims should have the new family connection added to their list of family connections.
      */

    @Test
    public void testAddFamilyConnection() {
        DisasterVictim Jane = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim John = new DisasterVictim("John", "2024-01-22");

        SiblingRelation relationshipJaneJohn = new SiblingRelation(Jane, John);

        Jane.addFamilyConnection(relationshipJaneJohn);

        SiblingRelation relationshipJohnJane = new SiblingRelation(John, Jane);

        assertTrue("Jane's family connections should now include the newly added relationship",Jane.getFamilyConnections().contains(relationshipJaneJohn));
        assertEquals("John's family connections should also include the newly added relationship", John.getFamilyConnections().get(0).getPersonTwo(), Jane);
        assertEquals("Jane's relation with John should be the same as John's relation with Jane in John's familyConnections", John.getFamilyConnections().get(0).getRelationshipTo(),
                Jane.getFamilyConnections().get(0).getRelationshipTo());
    }



    /*
     testEnsureRelationshipWholeness:
        - ensureRelationshipWholeness() ensures wholeness of relationships between family members.
        - Expected Result: Family relationships should be whole
                        - if Jane is a sibling of John, and John is a child of Joy,
                          then Joy and Jane also have a ParenChildRelation and this
                          relationship should be present in both Jane and Joy
     */
    @Test
    public void testEnsureRelationshipWholeness(){
        DisasterVictim Jane = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim John = new DisasterVictim("John", "2024-01-22");
        DisasterVictim Joy = new DisasterVictim("Joy", "2024-01-23");

        SiblingRelation relationshipJaneJohn = new SiblingRelation(Jane, John);
        ParentChildRelation relationshipJohnJoy = new ParentChildRelation(John, Joy);

        // adds Jane+John relation to both DisasterVictims
        Jane.addFamilyConnection(relationshipJaneJohn);

        // adds John+Joy relation to both DisasterVictims
        John.addFamilyConnection(relationshipJohnJoy);

        // private method EnsureRelationshipWholeness used inside addFamilyConnection
        // should add a relationship between Jane and Joy


        assertEquals("Jane's family connections should contain a relation with Joy",Jane.getFamilyConnections().get(1).getPersonTwo(), Joy);
        assertEquals("Jane should have two family connections, ensuring completeness of relationships between Jane, John, and Joy", Jane.getFamilyConnections().size(),2);
        assertEquals("Joy should have two family connections, ensuring completeness of relationships between Jane, John, and Joy", Joy.getFamilyConnections().size(),2);
        assertEquals("Joy's family connections should contain a relation with Jane",Joy.getFamilyConnections().get(1).getPersonTwo(), Jane);
        assertEquals("Jane and Joy's relation should be 'parent-child' since Jane and John are siblings and John is Joy's child",
                Jane.getFamilyConnections().get(1).getRelationshipTo(), "parent-child");



    }

    /*
         testRemoveFamilyConnection:
            - removeFamilyConnection method removes a victim's family connection
            - Expected Result: Both victims should no longer have the removed family connection.
         */
    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");


        SiblingRelation relation1 = new SiblingRelation(victim1, victim2);
        victim1.addFamilyConnection(relation1);
        victim1.removeFamilyConnection(relation1);

        assertEquals("victim1's family connections should not contain any relations",victim1.getFamilyConnections().size(),0);
        assertEquals("victim2's family connections should not contain any relations",victim2.getFamilyConnections().size(), 0);
    }

     /*
      testSetFamilyConnections:
         - setFamilyConnections() method sets the family connections for a victim.
         - Expected Result: The victim's family connections should be updated to the HashSet.
      */

    @Test
    public void testSetFamilyConnections(){
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");


        SiblingRelation relation1 = new SiblingRelation(victim1, victim2);

        ArrayList <FamilyRelation> newFamilyRelations = new ArrayList<>();
        newFamilyRelations.add(relation1);

        victim1.setFamilyConnections(newFamilyRelations);

        assertEquals("victim1's family connections should contain the added Hashset that includes the relation with victim2",relation1, victim1.getFamilyConnections().get(0));
        assertEquals("victim2's family connections should include the reciprocal relation with victim1", victim2.getFamilyConnections().get(0).getPersonTwo(), victim1);
        assertEquals("victim2's relation with victim1 should be the same as victim1's relation with victim2", victim2.getFamilyConnections().get(0).getRelationshipTo(),
                victim1.getFamilyConnections().get(0).getRelationshipTo());



    }



    /*
        testAddPersonalBelongingFromLocationSupply:
            - Tests the addition of a personal belonging from a location supply to a victim.
            - Expected Result: Victim's personal belongings should include the newly added supply, and the location's supply quantity should be reduced.
    */
    @Test
    public void testAddPersonalBelongingFromLocationSupply() {
        Supply supplyToAdd = new Supply("Emergency Kit", 1);
        Supply supplyInLocation = new Supply("Emergency Kit",30);
        Location location = new Location("University of Calgary","College Street");


        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        location.addSupply(supplyInLocation);
        victim1.setLocation(location);

        victim1.addPersonalBelonging(supplyToAdd, false);
        victim1.addPersonalBelonging(supplyToAdd, false);




        int expectedRemainingQuantity = supplyInLocation.getQuantity() - 2*supplyToAdd.getQuantity();
        assertEquals("victim's personal belongings should include the newly added supply and that supply's type should be the same as the one added"
                ,victim1.getPersonalBelongings().get(0).getType(), "Emergency Kit");
        assertEquals("victim's personal belongings should include the newly added supply with correct quantity",victim1.getPersonalBelongings().get(0).getQuantity(),2);
        assertEquals("victim's location should have a reduced quantity of the supply that was allocated to the victim",location.getSupplies().get(0).getQuantity(), expectedRemainingQuantity);



    }

    /*
        testAddPersonalBelongingQuantity:
            - Tests the addition of the same personal belonging to a victim.
            - Expected Result: Victim's personal belongings should contain only one supply object with the increased quantity.
    */
    @Test
    public void testAddPersonalBelongingQuantity() {
        DisasterVictim victim = new DisasterVictim("Jane", "2024-01-20");
        Location location = new Location("University of Calgary","College Street");
        Supply supplyToAdd = new Supply("Emergency Kit", 1);

        // ensures that the victim's location has enough supplies for supply allocation
        location.addSupply(new Supply("Emergency Kit", 30));
        victim.setLocation(location);


        victim.addPersonalBelonging(supplyToAdd, false);
        victim.addPersonalBelonging(supplyToAdd, false);
        victim.addPersonalBelonging(supplyToAdd, false);
        assertEquals("There should still only be one supply type (Emergency Kit) in victim's personal belongings",victim.getPersonalBelongings().size(), 1);
        assertEquals("victim should have 3 emergency kits after correct incrementation/addition of Emergency Kits",victim.getPersonalBelongings().get(0).getQuantity(), 3);



    }

    /*
    testRemovePersonalBelonging:
        - Tests the removal of a personal belonging from a victim.
        - Expected Result: victim should have no personal belongings after removal.
*/

    @Test
    public void testRemovePersonalBelonging(){
        DisasterVictim victim = new DisasterVictim("Jane", "2024-01-20");
        Location location = new Location("University of Calgary","College Street");
        Supply supply = new Supply("Emergency Kit", 1);

        // ensures that the victim's location has enough supplies for supply allocation
        location.addSupply(new Supply("Emergency Kit", 30));
        victim.setLocation(location);

        victim.addPersonalBelonging(supply,false);
        victim.removePersonalBelonging(supply);

        assertEquals("victim should not have any personal belongings after removal of all of the victim's supplies",0, victim.getPersonalBelongings().size());

    }
    /*
        testRemovePersonalBelongingQuantity:
            - Tests the removal of quantity of a victim's personal belongings
            - Expected Result: victim's emergency kit quantity should be 2 after adding 3 and then removing 1.
    */
    @Test
    public void testRemovePersonalBelongingQuantity(){
        DisasterVictim victim = new DisasterVictim("Jane", "2024-01-20");
        Location location = new Location("University of Calgary","College Street");
        Supply supply = new Supply("Emergency Kit", 1);

        // ensures that the victim's location has enough supplies for supply allocation
        location.addSupply(new Supply("Emergency Kit", 30));
        victim.setLocation(location);

        victim.addPersonalBelonging(supply,false);
        victim.addPersonalBelonging(supply,false);
        victim.addPersonalBelonging(supply,false);
        victim.removePersonalBelonging(supply);

        assertEquals("victim's emergency kit quantity should be 2 after adding 3 kits and then removing 1", victim.getPersonalBelongings().get(0).getQuantity(),2);



    }
    /*
        testSetPersonalBelongings:
            - setPersonalBelongings should set the victim's personal belongings
            - Expected Result: Victim's personal belongings should be updated to the new list of supplies, and the location's supply quantity should reduce accordingly.
    */
    @Test
    public void testSetPersonalBelongings(){
        DisasterVictim victim = new DisasterVictim("Jane", "2024-01-20");
        Location location = new Location("University of Calgary","College Street");
        Supply supply1 = new Supply("Emergency Kit", 1);
        Supply supply2 = new Supply("Toothbrush", 1);
        ArrayList <Supply> supplies = new ArrayList<Supply>();
        supplies.add(supply1);
        supplies.add(supply2);

        // ensures that the victim's location has enough supplies for supply allocation
        location.addSupply(new Supply("Emergency Kit", 30));
        location.addSupply(new Supply("Toothbrush", 30));
        victim.setLocation(location);

        victim.setPersonalBelongings(supplies);

        assertTrue("victim's personal belongings should be updated to the new list of supplies",victim.getPersonalBelongings().containsAll(supplies));
        assertEquals("victim's personal belongings should contain exactly the same number of elements as the supplies ArrayList", supplies.size(), victim.getPersonalBelongings().size());

        assertEquals("victim's location should have a reduced quantity of the emergency kits after allocating supply to victim",29, location.getSupplies().get(0).getQuantity());
        assertEquals("victim's location should have a reduced quantity of the toothbrushes after allocating supply to victim",29, location.getSupplies().get(1).getQuantity());




    }






    /*
        testSetAndGetMedicalRecords:
            - Tests setting and getting medical records for a victim.
            - Expected Result: setMedicalRecords should correctly update medical records and victim's medical records should contain exactly the same elements as newRecords.
    */
    @Test
    public void testSetAndGetMedicalRecords() {
        Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");

        ArrayList<MedicalRecord> newRecords = new ArrayList<>();
        newRecords.add(testRecord);
        victim.setMedicalRecords(newRecords);
        ArrayList<MedicalRecord> actualRecords = victim.getMedicalRecords();

        assertTrue("setMedicalRecords should correctly update medical records of the victim", actualRecords.containsAll(newRecords));
        assertEquals("victim's medical records should contain exactly the same elements as newRecords ArrayList",actualRecords.size(), newRecords.size());
    }

    /*
        testAddMedicalRecord:
            - Tests the addition of a medical record to a victim.
            - Expected Result: Victim's medical records should include the newly added medical record.
    */
    @Test
    public void testAddMedicalRecord(){
        Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
        DisasterVictim Jane = new DisasterVictim("Jane", "2024-01-20");

        Jane.addMedicalRecord(testRecord);

        assertTrue("victim's medical records should include the newly added medical record",Jane.getMedicalRecords().contains(testRecord));
    }

    /*
        testSetAndGetLocation:
            - Tests setting and getting the location for a victim. Also tests if setLocation adds victim to the occupants list of the set location
            - Expected Result: setLocation should update victim's location to the set location and location's occupants include the victim if not already in the list
    */
    @Test
    public void testSetAndGetLocation(){
        Location location = new Location("University of Calgary","College Street");

        victim.setLocation(location);

        assertTrue("victim should have been added to location's occupant list",location.getOccupants().contains(victim));
        assertEquals("setLocation should update victim's location to the set location",location,victim.getLocation());
    }

    /*
        testSetDateOfBirthWithInvalidDateFormat:
            - Tests setting the date of birth with an invalid date.
            - Expected Result: Expecting IllegalArgumentException due to invalid date format.
    */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidDateFormat(){
        victim.setDateOfBirth(invalidDate);
         // Expecting IllegalArgumentException due to invalid date format

    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidDate(){
        victim.setDateOfBirth("20000-03-02");
        // Expecting IllegalArgumentException due to invalid date

    }
/*
    testSetDateOfBirthWithValidDate:
        - Tests setting the date of birth with a valid date.
        - Expected Result: Victim's date of birth should be updated to the new date.
*/

    @Test
    public void testSetDateOfBirthWithValidDate(){
        victim.setDateOfBirth(validDate);

        assertEquals("victim's date of birth should be updated to the new date", validDate, victim.getDateOfBirth());

    }

/*
    testSetAgeWithValidAge:
        - Tests setting the age with a valid age.
        - Expected Result: Victim's age should be updated to the new age.
*/

    @Test
    public void testSetAgeWithValidAge(){
        victim.setAge(12);

        assertEquals("victim's age should be updated to the new age",12, victim.getAge());
    }


/*
    testSetAgeWithInvalidAge:
        - Tests setting the age with an invalid age.
        - Expected Result: Expecting IllegalArgumentException due to invalid age.
*/

    @Test(expected = IllegalArgumentException.class)
    public void testSetAgeWithInvalidAge(){
        victim.setAge(-1);

        // Expecting IllegalArgumentException due to invalid age


    }

    /*
    testSetAgeOrBirthdate:
        - Tests that only the victim's age or birthdate is set, not both at the same time
        - Expected Result: Victim's age should be null when birthdate is set and victim's date of birth should be null when age is set.
*/

    @Test
    public void testSetAgeOrBirthdate(){
        victim.setDateOfBirth(validDate);
        assertEquals("victim's age should be -1 when birthdate is set",victim.getAge(),-1);

        victim.setAge(20);
        assertNull("victim's date of birth should be null when age is set", victim.getDateOfBirth());
    }


    /*
        testSetAndGetDietaryRestrictions:
            - Tests setting and getting dietary restrictions for a victim.
            - Expected Result: Victim's dietary restrictions should be updated to the new HashSet.
    */
    @Test
    public void testSetAndGetDietaryRestrictions(){
        HashSet <DietaryRestrictions> dietaryRestrictions = new HashSet<>();

        dietaryRestrictions.add(DietaryRestrictions.AVML);
        dietaryRestrictions.add(DietaryRestrictions.DBML);

        victim.setDietaryRestrictions(dietaryRestrictions);

        assertTrue("victim's dietary restrictions should be updated to the new HashSet of dietaryRestrictions",victim.getDietaryRestrictions().containsAll(dietaryRestrictions));

    }

/*
    testReadGenderOptions:
            - Tests reading gender options from a file.
        - Expected Result: readGendersOption method should read the file and set the gender options to the contents of the file.
*/


    @Test
    public void testReadGenderOptions() {
        HashSet<String> expectedGenders = new HashSet<>();
        expectedGenders.add("boy");
        expectedGenders.add("gender queer");
        expectedGenders.add("girl");


        assertEquals("readGendersOption method from setup should read the file and set the gender options attribute to the contents of the file",
                expectedGenders, DisasterVictim.genderOptions);

    }

    /*
          testSetAndGetGenderWithMisspelledGender:
             - setGender() updates the gender of the victim with a misspelled gender option
             - Expected Result: IllegalArgumentException should be thrown due to the misspelled gender.
          */
    @Test(expected = IllegalArgumentException.class)
    public void testSetAndGetGenderWithMisspelledGender(){
        String misspelledGender = "gir";
        victim.setGender(misspelledGender);
        // Expecting IllegalArgumentException due to misspelled gender that cannot be found in genderOptions HashSet



    }

  /*
      testSetAndGetGenderWithValidGenderOption:
         - setGender() updates the gender of the victim with a valid gender option
         - Expected Result: The gender should be updated and returned by getGender().
      */


    @Test
    public void testSetAndGetGenderWithValidGenderOption() {
        String newGender = "girl";
        victim.setGender(newGender);
        assertEquals("setGender should update the gender of the victim and getGender should return the new gender", newGender, victim.getGender().toLowerCase());
    }



    @After
    public void cleanup() {


        // Attempt to delete the file
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.err.println("Failed to delete the file.");
            }
        } else {
            System.err.println("File does not exist.");
        }
    }

}





