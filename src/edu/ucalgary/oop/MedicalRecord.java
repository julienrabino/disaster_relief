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
    public MedicalRecord(String treatmentDetails, String dateOfTreatment){
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
        String REGEX = "\\d{4}-\\d{2}-\\d{2}";
        Pattern PATTERN = Pattern.compile(REGEX);
        Matcher match = PATTERN.matcher(date);
        boolean valid_date = match.find();
        String[] tokens = date.split("-");
        if(tokens[0].length() != 4){
            valid_date = false;
        }else if(tokens[1].length() != 2 || tokens[2].length() !=2){
            valid_date=false;
        }
        if (!valid_date){
            throw new IllegalArgumentException("Invalid format for date");
        }
        this.dateOfTreatment = date;
    }
}
