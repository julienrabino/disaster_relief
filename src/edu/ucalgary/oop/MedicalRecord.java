package edu.ucalgary.oop;
import java.util.regex.*;

public class MedicalRecord {
    private Location Location;
    private String treatmentDetails;
    private String dateOfTreatment;

    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment){

        this.setLocation(location);
        this.setTreatmentDetails(treatmentDetails);
        this.setDateOfTreatment(dateOfTreatment);
    }

    public Location getLocation(){
        return this.Location;
    }

    public void setLocation(Location location){
        this.Location = location;
    }

    public String getTreatmentDetails(){
    
        return this.treatmentDetails;
    }

    public void setTreatmentDetails(String details) {
        this.treatmentDetails = details;
    }

    public String getDateOfTreatment(){
        return this.dateOfTreatment;
    }

    public void setDateOfTreatment(String date) throws IllegalArgumentException{
        Pattern datePattern = Pattern.compile("^\\d{4}[-]{1}\\d{2}[-]{1}\\d{2}");
        Matcher myMatcher = datePattern.matcher(date);
        if (!myMatcher.find()){
            throw new IllegalArgumentException("Invalid date");

        }
        this.dateOfTreatment = date;
    }
}
