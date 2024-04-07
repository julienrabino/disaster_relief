package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SiblingRelationTest {
    private DisasterVictim personOne = new DisasterVictim("John", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane", "2024-02-20");

    private SiblingRelation siblingRelation;


    @Before
    public void setup() {
        siblingRelation = new SiblingRelation(personOne, personTwo);}


    /*
   testObjectCreation:
      - Checks that a SiblingRelation object is created.
      - Expected Result: The object should not be null.
*/
    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a new SiblingRelation object",siblingRelation);
    }

    /*
        testGetRelationshipTo:
           - Checks that the getRelationshipTo method returns "sibling".
           - Expected Result: The method should return "sibling".
     */
    @Test
    public void testGetRelationshipTo(){
        assertEquals("The relationship between the disaster victims should be sibling",siblingRelation.getRelationshipTo(),"siblings");
    }

    /*
           testDuplicateRelation:
              - Expected Result: The duplicateRelation method should return a SiblingRelation that has the same DisasterVictims as the original.
        */
    @Test
    public void testDuplicateRelation(){
        SiblingRelation duplicateRelation = siblingRelation.duplicateRelation();

        assertEquals("duplicateRelation method should have returned a SiblingRelation that has the same person one as the original",siblingRelation.getPersonOne(), duplicateRelation.getPersonOne());
        assertEquals("duplicateRelation method should have returned a SiblingRelation that has the same person two as the original",siblingRelation.getPersonTwo(), duplicateRelation.getPersonTwo());

    }
    /*
       testSetAndGetPersonOne:
          - Checks that setPersonOne updates personOne.
          - Expected Result:  personOne of the SiblingRelation should be updated with the new value.
    */

    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        siblingRelation.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne of the relation", newPersonOne, siblingRelation.getPersonOne());
    }

    /*
       testSetAndGetPersonTwo:
          - Checks that setPersonTwo updates personTwo.
          - Expected Result:  personTwO of the SiblingRelation should be updated with the new value.
    */
    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        siblingRelation.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo of the relation", newPersonTwo, siblingRelation.getPersonTwo());
    }


}

