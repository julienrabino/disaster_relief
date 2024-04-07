package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;

public class QueryLogger{
    private Connection dbConnect;
    private ResultSet results;
    public static int inquirerCurrentIDCount;
    public static int logIDCount;
    public void createConnection()  throws SQLException{

            dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");

    }
    public void initializeCurrentInquirerIDCount() throws SQLException {
        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT MAX(id) FROM inquirer");
        results.next();
        inquirerCurrentIDCount = results.getInt(1);
        myStmt.close();

    }
    public void initializeCurrentLogIDCount() throws SQLException {
        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT MAX(id) FROM inquiry_log");
        results.next();
        logIDCount = results.getInt(1);
        myStmt.close();

    }
    public ArrayList<String> getInquirerIDs() throws SQLException {
        ArrayList<String> inquirerIDs = new ArrayList<>();


            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT id FROM  inquirer");
            while (results.next()) {
                inquirerIDs.add(results.getString("id"));
            }
            myStmt.close();
            return inquirerIDs;
        }

    public String selectInquirers() throws SQLException {

        StringBuffer inquirers = new StringBuffer();


        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT * FROM inquirer");

        while (results.next()) {

            inquirers.append("id: " + results.getString("id") + "    phone number: " + results.getString("phoneNumber") + "    first name: " + results.getString("firstName") + "    last name: " +
                    results.getString("lastName"));
            inquirers.append('\n');
        }

        myStmt.close();


        return inquirers.toString();

    }

    public String selectInquiryLogs() throws SQLException {
        StringBuffer inquiryLogs = new StringBuffer();


        Statement myStmt = dbConnect.createStatement();
        results = myStmt.executeQuery("SELECT * FROM inquiry_log");

        while (results.next()) {

            inquiryLogs.append("id: " + results.getString("id") + "   inquirer id: " + results.getString("inquirer") + "     call date: " +
                    results.getString("callDate") + "    details: " + results.getString("details"));
            inquiryLogs.append('\n');
        }

        myStmt.close();


        return inquiryLogs.toString();


    }

    public void insertNewInquirer( Inquirer newInquirer)  throws  SQLException{
        String firstName = newInquirer.getFirstName();
        String lastName = newInquirer.getLastName();
        String phoneNumber = newInquirer.getServicesPhone();
        inquirerCurrentIDCount++;
        int id = inquirerCurrentIDCount;

            String query = "INSERT INTO inquirer (id, firstName, lastName, phoneNumber) VALUES (?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, id);
            myStmt.setString(2, firstName);
            myStmt.setString(3, lastName);
            myStmt.setString(4, phoneNumber);

            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            myStmt.close();

    }

    public void insertNewInquiryLog(int inquirerID, Log newLog) throws SQLException {
        String details = newLog.getLogDetails();
        String date = newLog.getDate();
        java.sql.Date SQLDate = java.sql.Date.valueOf(date);

        String query = "INSERT INTO inquiry_log (id, inquirer, callDate, details) VALUES (?,?,?,?)";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        logIDCount++;
        int id = logIDCount;
        myStmt.setInt(1, id);
        myStmt.setInt(2, inquirerID);

        myStmt.setDate(3, SQLDate);
        myStmt.setString(4, details);

        int rowCount = myStmt.executeUpdate();
        System.out.println("Rows affected: " + rowCount);

        myStmt.close();


    }

    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
