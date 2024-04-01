package edu.ucalgary.oop;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DisasterVictimEntry {
    private Connection dbConnect;
    private ResultSet results;

    public DisasterVictimEntry() {
    }

    public void createConnection() {

        try {
            dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/reliefservice", "oop", "ucalgary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String selectDisasterVictims() {

        StringBuffer disasterVictims = new StringBuffer();

        try {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return disasterVictims.toString();

    }

    public String selectDisasterVictimsIDAndName() {
        StringBuffer disasterVictims = new StringBuffer();


        try {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return disasterVictims.toString();

    }

    public String searchDisasterVictimByID(int id) {
        StringBuffer disasterVictim = new StringBuffer();
        try {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disasterVictim.toString();
    }


    public String searchDisasterVictimByName(String name) {
        StringBuffer disasterVictim = new StringBuffer();
        try {
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
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disasterVictim.toString();

    }


    public void insertDisasterVictim(String firstName, String lastName, String age, String birthdate, String comments, String locationID, String gender, String entryDate) {

        int currentIDCount = 0;
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT MAX(id) FROM disastervictim");
            results.next();
            currentIDCount = results.getInt(1);
            currentIDCount++;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            String query = "INSERT INTO disastervictim (id, firstName, lastName, age, birthdate, comments, locationID, gender, entryDate) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1, currentIDCount);
            myStmt.setString(2, firstName);
            myStmt.setString(3, lastName);
            myStmt.setString(6, comments);
            myStmt.setString(8, gender);
            myStmt.setDate(9, java.sql.Date.valueOf(entryDate));
            if (locationID != null) {
                int victimLocation = Integer.parseInt(locationID);
                myStmt.setInt(7, victimLocation);
            } else {
                myStmt.setNull(7, java.sql.Types.INTEGER);
            }
            if (birthdate != null) {
                java.sql.Date birth = java.sql.Date.valueOf(birthdate);
                myStmt.setDate(5, birth);
            } else {
                myStmt.setDate(5, null);
            }
            if (age != null) {
                int victimAge = Integer.parseInt(age);
                myStmt.setInt(4, victimAge);
            } else {
                myStmt.setNull(4, java.sql.Types.INTEGER);
            }
            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDisasterVictim(int id) {
        try {
            String query = "DELETE FROM disastervictim WHERE id = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1, id);
            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addDisasterVictimMedicalRecord(int victimID, String details, String treatmentDate, int locationID) {
        try {
            String query = "INSERT INTO medicalrecord (victimID, details, treatmentDate, locationID) VALUES (?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1, victimID);
            myStmt.setString(2, details);
            myStmt.setDate(3, java.sql.Date.valueOf(treatmentDate));
            myStmt.setInt(4, locationID);
            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String searchMedicalRecordsByVictimID(int id) {
        StringBuffer medicalRecords = new StringBuffer();
        try {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return medicalRecords.toString();

    }

    public String selectMedicalRecords() {
        StringBuffer medicalRecords = new StringBuffer();
        try {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return medicalRecords.toString();
    }

    public String selectVictimDietaryRestrictions() {
        StringBuffer victimDietaryRestrictions = new StringBuffer();
        try {
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return victimDietaryRestrictions.toString();
    }


    public String selectDisasterVictimByDietaryRestriction(int id){
        StringBuffer disasterVictims = new StringBuffer();
        try {
            String query = "SELECT  disastervictim.id, disastervictim.firstName, disastervictim.lastName " +
            "FROM disastervictim, dietary_restrictions, victim_dietary_restrictions " +
            "WHERE disastervictim.id = victim_dietary_restrictions.victimid " +
            "AND victim_dietary_restrictions.restrictionid = dietary_restrictions.id "+
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disasterVictims.toString();

    }

    public void addDisasterVictimDietaryRestriction(int victimID, int restrictionID){
        try {
            String query = "INSERT INTO victim_dietary_restrictions (victimID, restrictionID) VALUES (?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1, victimID);
            myStmt.setInt(2, restrictionID);

            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {

        DisasterVictimEntry myJDBC = new DisasterVictimEntry();

        myJDBC.createConnection();
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
         */
    }
}
