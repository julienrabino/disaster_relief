package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class ParentChildRelationTest{
    private DisasterVictim personOne = new DisasterVictim("John", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane", "2024-02-20");

    private ParentChildRelation parentChildRelation = new ParentChildRelation(personOne, personTwo);

    /*
   testObjectCreation:
      - Checks that a ParentChildRelation object is created.
      - Expected Result: The object should not be null.
*/
    @Before
    public void setup() {
        parentChildRelation = new ParentChildRelation(personOne, personTwo);}


    @Test
    public void testObjectCreation() {
        assertNotNull("Constructor should create a new ParentChildRelation object",parentChildRelation);
    }

    /*
         testGetRelationshipTo:
            - Checks that the getRelationshipTo method returns "parent-child".
            - Expected Result: The method should return "parent-child".
      */
    @Test
    public void testGetRelationshipTo(){
        assertEquals("The relationship between the disaster victims should be parent-child",parentChildRelation.getRelationshipTo(),"parent-child");
    }


/*
    testDuplicateRelation:
            - Expected Result: The duplicateRelation method should return a ParentChildRelation that has the same DisasterVictims as the original.
            */
    @Test
    public void testDuplicateRelation(){
        ParentChildRelation duplicateRelation = parentChildRelation.duplicateRelation();

        assertEquals("duplicateRelation method should have returned a ParentChildRelation that has the same person one as the original",parentChildRelation.getPersonOne(), duplicateRelation.getPersonOne());
        assertEquals("duplicateRelation method should have returned a ParentChildRelation that has the same person two as the original",parentChildRelation.getPersonTwo(), duplicateRelation.getPersonTwo());
    }


        /*
       testSetAndGetPersonOne:
          - Checks that setPersonOne updates personOne.
          - Expected Result:  personOne of the ParentChildRelation should be updated with the new value.
    */

    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        parentChildRelation.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne of the relation", newPersonOne, parentChildRelation.getPersonOne());
    }

    /*
       testSetAndGetPersonTwo:
          - Checks that setPersonTwo updates personTwo.
          - Expected Result:  personTwO of the ParentChildRelation should be updated with the new value.
    */
    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        parentChildRelation.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo of the relation", newPersonTwo, parentChildRelation.getPersonTwo());
    }



}
