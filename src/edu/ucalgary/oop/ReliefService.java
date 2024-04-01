package edu.ucalgary.oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class ReliefService {
  private Inquirer inquirer;

  private String infoProvided;
  private ArrayList <Log> logs;
  public ReliefService(Inquirer inquirer, String infoProvided){
    this.setInquirer(inquirer);
    this.setInfoProvided(infoProvided);
    this.logs = new ArrayList<Log>();
  }

  public void setInquirer(Inquirer inquirer){
    this.inquirer = inquirer;
  }

  public void setInfoProvided(String infoProvided){
    this.infoProvided = infoProvided;
  }
  public Inquirer getInquirer(){return this.inquirer;}

  public String getInfoProvided(){return this.infoProvided;}

  public ArrayList<Log> getLogs(){return this.logs;}

  public void addLog(Log log){
    this.logs.add(log);
  }
  public void removeLog(Log log){
    this.logs.remove(log);
  }

}

