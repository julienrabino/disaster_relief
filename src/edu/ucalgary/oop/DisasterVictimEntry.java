package edu.ucalgary.oop;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DisasterVictimEntry {
    private Connection dbConnect;
    private ResultSet results;
    public static int currentIDCount;
    public DisasterVictimEntry() {
    }

    public void createConnection() throws SQLException{
        dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/reliefservice", "oop", "ucalgary");

    }

    public void initializeCurrentIDCount() throws SQLException{
        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT MAX(id) FROM disastervictim");
        results.next();
        currentIDCount = results.getInt(1);
        myStmt.close();

    }

    public ArrayList<String> getLocationIDs()throws SQLException{
        ArrayList <String> locationIDs= new ArrayList<>();
        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT id FROM location");
        while (results.next()){
            locationIDs.add(results.getString("id"));
        }

        myStmt.close();
        return locationIDs;

    }

    public String getLocationNameFromID(int id) throws SQLException{
        StringBuffer name = new StringBuffer();
        String query = "SELECT name FROM location WHERE id = (?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, id);
        results = myStmt.executeQuery();

        while (results.next()){
            name.append(results.getString("name"));
        }

        return name.toString();
    }
    public String selectLocations() throws SQLException{
        StringBuffer locations = new StringBuffer();
        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT * FROM location");
        locations.append("Here are all the Locations:\n");
        locations.append("************\n");
        locations.append("id, name\n");
        locations.append("************\n");
        while (results.next()){
            locations.append(results.getString("id")+ ", " + results.getString("name")+ "\n");
        }
        myStmt.close();
        return locations.toString();
    }
    public String selectDisasterVictims() throws SQLException {

        StringBuffer disasterVictims = new StringBuffer();

        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT * FROM disastervictim");
        disasterVictims.append("Here are all the Disaster Victims:\n");
        disasterVictims.append("*************************************************************************************\n");
        disasterVictims.append("id, first name, last name, age, birthdate, comments, location id, gender, entry date \n");
        disasterVictims.append("*************************************************************************************\n");

        while (results.next()) {
            disasterVictims.append(results.getString("id") + ", " + results.getString("firstName") + ", " +
                    results.getString("lastName") + ", " + results.getString("age") + ", " + results.getString("birthdate") + ", " +
                    results.getString("comments") + ", " + results.getString("locationID") + ", " + results.getString("gender")
                    + ", " + results.getString("entryDate"));
            disasterVictims.append('\n');
        }

        myStmt.close();

        return disasterVictims.toString();
    }


    public String selectDisasterVictimsIDAndName() throws SQLException {
        StringBuffer disasterVictims = new StringBuffer();

        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT * FROM disastervictim");
        disasterVictims.append("Here are all the Disaster Victims:\n");
        disasterVictims.append("****************************\n");
        disasterVictims.append("id, first name, last name\n");
        disasterVictims.append("****************************\n");

        while (results.next()) {
            disasterVictims.append(results.getString("id") + ", " + results.getString("firstName") + ", " +
                    results.getString("lastName"));
            disasterVictims.append('\n');
        }
        myStmt.close();

        return disasterVictims.toString();
    }


    public String searchDisasterVictimByID(int id) throws SQLException {
        StringBuffer disasterVictim = new StringBuffer();

        String query = "SELECT * FROM disastervictim WHERE id = (?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, id);
        results = myStmt.executeQuery();
        disasterVictim.append("*************************************************************************************\n");
        disasterVictim.append("id, first name, last name, age, birthdate, comments, location id, gender, entry date \n");
        disasterVictim.append("*************************************************************************************\n");
        while (results.next()) {
            disasterVictim.append(results.getString("id") + ", " + results.getString("firstName") + ", " +
                    results.getString("lastName") + ", " + results.getString("age") + ", " + results.getString("birthdate") + ", " +
                    results.getString("comments") + ", " + results.getString("locationID") + ", " + results.getString("gender")
                    + ", " + results.getString("entryDate"));
            disasterVictim.append('\n');
            }
        myStmt.close();
        return disasterVictim.toString();
    }


    public String searchDisasterVictimByName(String name) throws SQLException {
        StringBuffer disasterVictim = new StringBuffer();
        String query = "SELECT * FROM disastervictim WHERE firstName ILIKE (?) or lastName ILIKE(?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setString(1, "%" + name + "%");
        myStmt.setString(2, "%" + name + "%");

        results = myStmt.executeQuery();
        disasterVictim.append("*************************************************************************************\n");
        disasterVictim.append("id, first name, last name, age, birthdate, comments, location id, gender, entry date \n");
        disasterVictim.append("*************************************************************************************\n");
        while (results.next()) {
            disasterVictim.append(results.getString("id") + ", " + results.getString("firstName") + ", " +
                    results.getString("lastName") + ", " + results.getString("age") + ", " + results.getString("birthdate") + ", " +
                    results.getString("comments") + ", " + results.getString("locationID") + ", " + results.getString("gender")
                    + ", " + results.getString("entryDate"));
            disasterVictim.append('\n');
        }
        return disasterVictim.toString();
    }


    public void insertDisasterVictim(int id,String firstName, String lastName, String age, String birthdate, String comments, String locationID,
                                     String gender, String entryDate) throws SQLException{

        String query = "INSERT INTO disastervictim (firstName, lastName, age, birthdate, comments, locationID, gender, entryDate, id) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement myInsertionStmt = dbConnect.prepareStatement(query);
        myInsertionStmt.setInt(9, id);
        myInsertionStmt.setString(1, firstName);
        myInsertionStmt.setString(2, lastName);
        myInsertionStmt.setString(5, comments);
        myInsertionStmt.setString(7, gender);
        myInsertionStmt.setDate(8, java.sql.Date.valueOf(entryDate));
        if (locationID != null) {
            int victimLocation = Integer.parseInt(locationID);
            myInsertionStmt.setInt(6, victimLocation);
        } else {
            myInsertionStmt.setNull(6, java.sql.Types.INTEGER);
        }
        if (birthdate != null) {
            java.sql.Date birth = java.sql.Date.valueOf(birthdate);
            myInsertionStmt.setDate(4, birth);
        } else {
            myInsertionStmt.setDate(4, null);
            }
        if (age != null) {
            int victimAge = Integer.parseInt(age);
            myInsertionStmt.setInt(3, victimAge);
        } else {
            myInsertionStmt.setNull(3, java.sql.Types.INTEGER);
            }

        int rowCount = myInsertionStmt.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        myInsertionStmt.close();


    }

    public void deleteDisasterVictim(int id) throws SQLException {

        String query = "DELETE FROM disastervictim WHERE id = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, id);
        int rowCount = myStmt.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        myStmt.close();

    }

    public void addDisasterVictimMedicalRecord(int victimID, String details, String treatmentDate, int locationID) throws SQLException{
        String query = "INSERT INTO medicalrecord (victimID, details, treatmentDate, locationID) VALUES (?,?,?,?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, victimID);
        myStmt.setString(2, details);
        myStmt.setDate(3, java.sql.Date.valueOf(treatmentDate));
        myStmt.setInt(4, locationID);
        int rowCount = myStmt.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        myStmt.close();

    }

    public String searchMedicalRecordsByVictimID(int id) throws SQLException {
        StringBuffer medicalRecords = new StringBuffer();
        String query = "SELECT * FROM medicalrecord WHERE victimID = (?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, id);
        results = myStmt.executeQuery();
        medicalRecords.append("*************************************************\n");
        medicalRecords.append("victimID, details, date of treatment, locationID\n");
        medicalRecords.append("*************************************************\n");
        while (results.next()) {
            medicalRecords.append(results.getString("victimID") + ", " + results.getString("details") + ", " +
                    results.getString("treatmentDate") + ", " + results.getString("locationID"));
            medicalRecords.append("\n");
        }
        myStmt.close();

        return medicalRecords.toString();

    }

    public String selectMedicalRecords() throws SQLException{
        StringBuffer medicalRecords = new StringBuffer();

        String query = "SELECT * FROM medicalrecord";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        results = myStmt.executeQuery();
        medicalRecords.append("*************************************************\n");
        medicalRecords.append("victimID, details, date of treatment, locationID\n");
        medicalRecords.append("*************************************************\n");
        while (results.next()) {
            medicalRecords.append(results.getString("victimID") + ", " + results.getString("details") + ", " +
                    results.getString("treatmentDate") + ", " + results.getString("locationID"));
            medicalRecords.append("\n");
        }
        myStmt.close();

        return medicalRecords.toString();
    }

    public String selectVictimDietaryRestrictions()  throws SQLException {
        StringBuffer victimDietaryRestrictions = new StringBuffer();

        victimDietaryRestrictions.append("**********************************************\n");
        victimDietaryRestrictions.append("victimID, firstName, lastName, restriction\n");
        victimDietaryRestrictions.append("**********************************************\n");

        String query = "SELECT DISTINCT disastervictim.id, disastervictim.firstName,disastervictim.lastName, dietary_restrictions.restriction_name " +
                    "FROM disastervictim, dietary_restrictions, victim_dietary_restrictions " +
                    "WHERE disastervictim.id = victim_dietary_restrictions.victimid " +
                    "AND victim_dietary_restrictions.restrictionid = dietary_restrictions.id;";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        results = myStmt.executeQuery();
        while (results.next()) {
            victimDietaryRestrictions.append(results.getString("id") + ", " + results.getString("firstName") + ", " +
                        results.getString("lastName") + ", " + results.getString("restriction_name"));
            victimDietaryRestrictions.append("\n");
        }
        myStmt.close();

       return victimDietaryRestrictions.toString();
    }


    public String selectDisasterVictimByDietaryRestriction(int id) throws SQLException{
        StringBuffer disasterVictims = new StringBuffer();

        String query = "SELECT  disastervictim.id, disastervictim.firstName, disastervictim.lastName " +
                "FROM disastervictim, dietary_restrictions, victim_dietary_restrictions " +
                "WHERE disastervictim.id = victim_dietary_restrictions.victimid " +
                "AND victim_dietary_restrictions.restrictionid = dietary_restrictions.id " +
                "AND dietary_restrictions.id = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, id);
        results = myStmt.executeQuery();
        disasterVictims.append("***************************************\n");
        disasterVictims.append("victimID, firstName, lastName\n");
        disasterVictims.append("***************************************\n");
        while (results.next()) {
            disasterVictims.append(results.getString("id") + ", " + results.getString("firstName") + ", " +
                    results.getString("lastName"));
            disasterVictims.append("\n");
        }
        myStmt.close();

        return disasterVictims.toString();

    }

    public void addDisasterVictimDietaryRestriction(int victimID, int restrictionID) throws SQLException {

        String query = "INSERT INTO victim_dietary_restrictions (victimID, restrictionID) VALUES (?,?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, victimID);
        myStmt.setInt(2, restrictionID);

        int rowCount = myStmt.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        myStmt.close();


    }

    public void addFamilyRelation(int firstPersonID, int secondPersonID, String relationship) throws SQLException {
        if (firstPersonID > secondPersonID) {
            int tempID = secondPersonID;
            secondPersonID = firstPersonID;
            firstPersonID = tempID;
        }

        String query = "INSERT INTO familyRelations (firstPerson, secondPerson, relationship) VALUES (?,?,?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, firstPersonID);
        myStmt.setInt(2, secondPersonID);
        myStmt.setString(3, relationship);

        int rowCount = myStmt.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        myStmt.close();


    }

    public String selectFamilyRelationsByID(int id) throws SQLException {
        StringBuffer familyRelation = new StringBuffer();

        String query = "SELECT * FROM familyrelations WHERE firstPerson = (?) or secondPerson = (?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setInt(1, id);
        myStmt.setInt(2, id);

        results = myStmt.executeQuery();
        familyRelation.append("**********************************************\n");
        familyRelation.append("victim id, victim id, relationship\n");
        familyRelation.append("**********************************************\n");
        while (results.next()) {
            familyRelation.append(results.getString("firstPerson") + ", " + results.getString("secondPerson") + ", " +
                    results.getString("relationship"));
            familyRelation.append("\n");
        }
        myStmt.close();


        return familyRelation.toString();
    }
    public static void main(String[] args) {

//        DisasterVictimEntry myJDBC = new DisasterVictimEntry();
//        try {
//            myJDBC.createConnection();
//        } catch(SQLException ex){
//
//        }
        /*
        String disasterVictims = myJDBC.selectDisasterVictimsIDAndName();
        System.out.println(disasterVictims);
        String Bob = myJDBC.searchDisasterVictimByID(1);
        System.out.println(Bob);


        String Demi = myJDBC.searchDisasterVictimByName("Demi");
        System.out.println(Demi);


        myJDBC.insertDisasterVictim("Ari",null,null,"2000-12-18",null, "1",null, "2024-02-29");

        myJDBC.deleteDisasterVictim(5);
        String disasterVictims = myJDBC.selectDisasterVictims();


        myJDBC.addDisasterVictimMedicalRecord(1, "Excessive coughing", "2024-03-01", 1);
        myJDBC.addDisasterVictimMedicalRecord(2, "Severely dehydrated", "2024-02-29", 1);


        String medicalrecords = myJDBC.selectMedicalRecords();
        System.out.println(medicalrecords);

        String dietaryRestrictions = myJDBC.selectVictimDietaryRestrictions();
        System.out.println(dietaryRestrictions);

        String disasterVictimsWithDietaryRestrictions = myJDBC.selectDisasterVictimByDietaryRestriction(3);
        System.out.println(disasterVictimsWithDietaryRestrictions);

        myJDBC.addDisasterVictimDietaryRestriction(4,2);
        String dietaryRestrictions = myJDBC.selectVictimDietaryRestrictions();
        System.out.println(dietaryRestrictions);


        String disasterVictimsWithDietaryRestrictions = myJDBC.selectDisasterVictimByDietaryRestriction(2);
        System.out.println(disasterVictimsWithDietaryRestrictions);

        myJDBC.addFamilyRelation(1,2,"siblings");
         */
       // System.out.println(myJDBC.selectFamilyRelationsByID(3));
    }
}
