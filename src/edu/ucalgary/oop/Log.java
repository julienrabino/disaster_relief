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
        Pattern datePattern = Pattern.compile("^\\d{4}[-]{1}\\d{2}[-]{1}\\d{2}");
        Matcher myMatcher = datePattern.matcher(date);
        if (!myMatcher.find()){
            throw new IllegalArgumentException("Invalid date");

        }
        this.logDate = date;
    }

    public String getLogDetails(){return this.logDetails;}
    public void setLogDetails(String details){this.logDetails = details;
    }

    public Location getLastKnownLocation(){return this.lastKnownLocation;}

    public void setLastKnownLocation(Location location){ this.lastKnownLocation = location;}

}
