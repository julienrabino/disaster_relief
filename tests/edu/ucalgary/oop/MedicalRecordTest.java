/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class MedicalRecordTest {

    Location expectedLocation = new Location("ShelterA", "140 8 Ave NW ");
    private String expectedTreatmentDetails = "Broken arm treated";
    private String expectedDateOfTreatment = "2024-01-19";
    private String validDateOfTreatment = "2024-02-04";
    private String inValidDateOfTreatment = "2024/02/04";
    MedicalRecord medicalRecord = new MedicalRecord(expectedLocation, expectedTreatmentDetails, expectedDateOfTreatment);

    /*
       testConstructorWithValidDate:
          - Verifies that the constructor successfully creates a MedicalRecord object with a valid date.
          - Expected Result: The MedicalRecord object should not be null.
       */
    @Test
    public void testConstructorWithValidDate() {
        assertNotNull("constructor should have made a new MedicalRecord object",medicalRecord);
    }


    /*
    testConstructorWithInvalidDate:
       - Verifies that an IllegalArgumentException is thrown when creating a MedicalRecord object with an invalid date format.
       - Expected Result: Expecting IllegalArgumentException
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidDate(){
        MedicalRecord invalidMedicalRecord = new MedicalRecord(expectedLocation, expectedTreatmentDetails,inValidDateOfTreatment);
        // Expecting IllegalArgumentException due to invalid date format

    }

	
    @Test
    public void testGetLocation() {
    assertEquals("getLocation should return the correct Location", expectedLocation, medicalRecord.getLocation());
    }

 @Test
    public void testSetLocation() {
	Location newExpectedLocation = new Location("Shelter B", "150 8 Ave NW ");
	medicalRecord.setLocation(newExpectedLocation);
        assertEquals("setLocation should update the Location of the MedicalRecord", newExpectedLocation.getName(), medicalRecord.getLocation().getName());
    }

    @Test
    public void testGetTreatmentDetails() {
        assertEquals("getTreatmentDetails should return the correct treatment details", expectedTreatmentDetails, medicalRecord.getTreatmentDetails());
    }
@Test
    public void testSetTreatmentDetails() {
	String newExpectedTreatment = "No surgery required";
	medicalRecord.setTreatmentDetails(newExpectedTreatment);
    assertEquals("setTreatmentDetails should update the treatment details", newExpectedTreatment, medicalRecord.getTreatmentDetails());
    }


    @Test
    public void testGetDateOfTreatment() {
    assertEquals("getDateOfTreatment should return the correct date of treatment", expectedDateOfTreatment, medicalRecord.getDateOfTreatment());
    }
	
	@Test
    public void testSetDateOfTreatment() {
	String newExpectedDateOfTreatment = "2024-02-05";
	medicalRecord.setDateOfTreatment(newExpectedDateOfTreatment);
    assertEquals("setDateOfTreatment should update date of treatment", newExpectedDateOfTreatment, medicalRecord.getDateOfTreatment());
    }



	@Test
    public void testSetDateOfTreatmentWithValidFormat() {
        
        medicalRecord.setDateOfTreatment(validDateOfTreatment); // Should not throw an exception
    }

    /*
    testSetDateOfTreatmentWithInvalidFormat:
       - Verifies that an IllegalArgumentException is thrown when setting an invalid date format
       - Expected Result: Expecting IllegalArgumentException
    */
    @Test
    public void testSetDateOfTreatmentWithInvalidFormat() {
        boolean correctValue = false;
        String failureReason = "no exception was thrown";

        try {
           medicalRecord.setDateOfTreatment(inValidDateOfTreatment); // Should throw IllegalArgumentException
        }
        catch (IllegalArgumentException e) {
           correctValue = true;
        }
        catch (Exception e) {
           failureReason = "the wrong type of exception was thrown";
        }

        String message = "setDateOfTreatment() should throw an IllegalArgumentException with invalid date format '" + inValidDateOfTreatment + "' but " + failureReason + ".";
        assertTrue(message, correctValue);
    }


    /*
    testSetDateOfTreatmentWithNotADate:
       - Verifies that an IllegalArgumentException is thrown when setting a non-date input for the MedicalRecord.
       - Expected Result: Expecting IllegalArgumentException
    */
    @Test
    public void testSetDateOfTreatmentWithNotADate() {
        boolean correctValue = false;
        String failureReason = "no exception was thrown";

        try {
           medicalRecord.setDateOfTreatment(expectedTreatmentDetails); // Should throw IllegalArgumentException
        }
        catch (IllegalArgumentException e) {
           correctValue = true;
        }
        catch (Exception e) {
           failureReason = "the wrong type of exception was thrown";
        }

        String message = "setDateOfTreatment() should throw an IllegalArgumentException with invalid non-date input '" + inValidDateOfTreatment + "' but " + failureReason + ".";
        assertTrue(message, correctValue);
    }
}

