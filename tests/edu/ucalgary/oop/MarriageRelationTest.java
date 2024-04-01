package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarriageRelationTest {
    private DisasterVictim personOne = new DisasterVictim("John", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane", "2024-02-20");

    private MarriageRelation marriageRelation = new MarriageRelation(personOne, personTwo);


    @Before
    public void setup() {
        marriageRelation = new MarriageRelation(personOne, personTwo);}


    /*
       testObjectCreation:
          - Checks that a MarriageRelation object is created.
          - Expected Result: The object should not be null.
    */
    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a new MarriageRelation object", marriageRelation);
    }


    /*
       testGetRelationshipTo:
          - Checks that the getRelationshipTo method returns "married".
          - Expected Result: The method should return "married".
    */
    @Test
    public void testGetRelationshipTo(){
        assertEquals("The relationship between the disaster victims should be married",marriageRelation.getRelationshipTo(),"married");
    }

      /*
       testSetAndGetPersonOne:
          - Checks that setPersonOne updates personOne.
          - Expected Result:  personOne of the SiblingRelation should be updated with the new value.
    */

    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        marriageRelation.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne of the relation", newPersonOne, marriageRelation.getPersonOne());
    }

    /*
      testSetAndGetPersonTwo:
         - Checks that setPersonTwo updates personTwo.
         - Expected Result:  personTwO of the SiblingRelation should be updated with the new value.
   */
    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        marriageRelation.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo of the relation", newPersonTwo, marriageRelation.getPersonTwo());
    }

    /*
        testDuplicateRelation:
                - Expected Result: The duplicateRelation method should return a ParentChildRelation that has the same DisasterVictims as the original.
                */
    @Test
    public void testDuplicateRelation(){
        MarriageRelation duplicateRelation = marriageRelation.duplicateRelation();

        assertEquals("duplicateRelation method should have returned a MarriageRelation that has the same person one as the original",marriageRelation.getPersonOne(), duplicateRelation.getPersonOne());
        assertEquals("duplicateRelation method should have returned a MarriageRelation that has the same person two as the original",marriageRelation.getPersonTwo(), duplicateRelation.getPersonTwo());
    }


}
