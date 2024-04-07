package edu.ucalgary.oop;
import org.junit.Test;

import static org.junit.Assert.*;

public class DietaryRestrictionsTest {

    @Test
    public void testGetRestrictionName(){
        assertEquals("getRestrictionName should return the name of the dietary restriction",DietaryRestrictions.AVML.getRestrictionName(), "Asian vegetarian meal");
    }
}
