package edu.ucalgary.oop;

public class Inquirer implements Person {
    private String FIRST_NAME;
    private String LAST_NAME;
    private String SERVICES_PHONE;
    public static int counter = QueryLogger.inquirerCurrentIDCount;
    private int id;
    
    public Inquirer(String FIRST_NAME, String LAST_NAME, String SERVICES_PHONE){
      this.FIRST_NAME = FIRST_NAME;
      this.LAST_NAME = LAST_NAME;
      this.SERVICES_PHONE = SERVICES_PHONE;
      counter++;
      this.id = counter;

    }
    public Inquirer(int id, String FIRST_NAME, String LAST_NAME, String SERVICES_PHONE){
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.SERVICES_PHONE = SERVICES_PHONE;
        this.id = id;

    }
    public int getID(){
        return this.id;
    }

    @Override
    public String getFirstName(){return this.FIRST_NAME;}
    @Override
    public String getLastName(){return this.LAST_NAME;}
    @Override
    public void setFirstName(String firstName){
        this.FIRST_NAME = firstName;
    }

    @Override
    public void setLastName(String lastName){
        this.LAST_NAME = lastName;
    }
    public String getServicesPhone(){return this.SERVICES_PHONE;}
}
