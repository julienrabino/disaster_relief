/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    private DisasterVictim personOne = new DisasterVictim("John", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane", "2024-02-20");

    private FamilyRelation testFamilyRelationObject;


    @Before
    public void setup() {
        testFamilyRelationObject = new FamilyRelation(personOne, personTwo);}

    @Test
    public void testObjectCreation(){
        assertNotNull("Constructor should create a new FamilyRelation object",testFamilyRelationObject);
    }
	
    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        testFamilyRelationObject.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne of the relation", newPersonOne, testFamilyRelationObject.getPersonOne());
    }

    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        testFamilyRelationObject.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo of the relation", newPersonTwo, testFamilyRelationObject.getPersonTwo());
    }

    @Test
    public void testGetRelationshipTo(){
        assertEquals("the relationship between the two victims should be 'family-relation'", testFamilyRelationObject.getRelationshipTo(), "family-relation");
    }

    /*
        testDuplicateRelation:
                  - Expected Result: The duplicateRelation method should return a FamilyRelation that has the same DisasterVictims as the original.
       */
    @Test
    public void testDuplicateRelation(){
        FamilyRelation duplicateRelation = testFamilyRelationObject.duplicateRelation();

        assertEquals("duplicateRelation method should have returned a FamilyRelation that has the same person one as the original",testFamilyRelationObject.getPersonOne(), duplicateRelation.getPersonOne());
        assertEquals("duplicateRelation method should have returned a FamilyRelation that has the same person two as the original",testFamilyRelationObject.getPersonTwo(), duplicateRelation.getPersonTwo());

    }


}
