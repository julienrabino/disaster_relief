package edu.ucalgary.oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log {
    private String logDate;
    private String logDetails;
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;

    public Log(String date, String details, DisasterVictim missingPerson, Location lastKnownLocation){
        this.setDate(date);
        this.logDetails = details;
        this.setMissingPerson(missingPerson);
        this.setLastKnownLocation(lastKnownLocation);
    }

    public Log(String date, String details){
        this.setDate(date);
        this.logDetails = details;
    }

    public DisasterVictim getMissingPerson(){ return this.missingPerson;}
    public void setMissingPerson(DisasterVictim missingPerson){
        this.missingPerson = missingPerson;
    }
    public String getDate(){ return this.logDate;}
    public void setDate(String date){
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

        this.logDate = date;

    }

    public String getLogDetails(){return this.logDetails;}
    public void setLogDetails(String details){this.logDetails = details;
    }

    public Location getLastKnownLocation(){return this.lastKnownLocation;}

    public void setLastKnownLocation(Location location){ this.lastKnownLocation = location;}

}
